package museumvisit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class Museum(
    val name: String,
    val entrance: MuseumRoom,
) {
    private val rooms: MutableMap<String, MuseumRoom> = mutableMapOf()
    val connections: MutableMap<MuseumRoom, MutableList<MuseumRoom>> = mutableMapOf()
    private var _admitted: Int = 0
    val admitted: Int
        get() = _admitted
    private val out = MuseumRoom("Outside", Int.MAX_VALUE)
    val outside
        get() = out
    private val lock = ReentrantLock()

    init {
        rooms[entrance.name] = entrance
    }

    fun entranceHasCapacity(): Boolean = entrance.hasCapacity()

    fun enterIfPossible(): MuseumRoom? {
        entrance.lock.withLock {
            if (entranceHasCapacity()) {
                entrance.enter()
                lock.withLock { _admitted++ }
                return entrance
            } else {
                return null
            }
        }
    }

    fun passThroughTurnstile(
        roomA: MuseumRoom,
        roomB: MuseumRoom,
    ): MuseumRoom? {
        val (first, second) =
            if (
                roomA.name < roomB.name
            ) {
                Pair(roomA, roomB)
            } else {
                Pair(roomB, roomA)
            }

        first.lock.withLock {
            second.lock.withLock {
                if (!roomB.hasCapacity()) return null
                roomA.exit()
                roomB.enter()
                return roomB
            }
        }
    }

    fun enter() {
        lock.withLock {
            if (!entranceHasCapacity()) {
                throw UnsupportedOperationException("Museum entrance is full.")
            }
            entrance.enter()
            _admitted++
        }
    }

    fun addRoom(room: MuseumRoom) {
        lock.withLock {
            if (rooms.containsKey(room.name)) {
                throw IllegalArgumentException("Room ${room.name} already exists.")
            }
            rooms[room.name] = room
        }
    }

    fun connectRoomTo(
        roomA: MuseumRoom,
        roomB: MuseumRoom,
    ) {
        lock.withLock {
            if (roomA == roomB) {
                throw IllegalArgumentException("Cannot connect a room to itself.")
            }
            if (!rooms.containsKey(roomA.name) || !rooms.containsKey(roomB.name)) {
                throw IllegalArgumentException("Both rooms must belong to the museum.")
            }
            if (connections[roomA]?.contains(roomB) == true) {
                throw IllegalArgumentException("Room ${roomA.name} is already connected to ${roomB.name}.")
            }
            connections.computeIfAbsent(roomA) { mutableListOf() }.add(roomB)
        }
    }

    fun connectRoomToExit(room: MuseumRoom) {
        lock.withLock {
            if (!rooms.containsKey(room.name)) {
                throw IllegalArgumentException("Room ${room.name} must be in the museum.")
            }
            if (connections[room]?.contains(outside) == true) {
                throw IllegalArgumentException("Room ${room.name} is already an exit.")
            }
            connections.computeIfAbsent(room) { mutableListOf() }.add(outside)
        }
    }

    fun checkWellFormed() {
        val visited = mutableSetOf<MuseumRoom>()
        val queue = ArrayDeque<MuseumRoom>()
        queue.add(entrance)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (current !in visited) {
                visited.add(current)
                connections[current]?.let { queue.addAll(it) }
            }
        }
        val unVisited = rooms.values.filter { it !in visited }.toSet()
        if (unVisited.isNotEmpty()) {
            throw UnreachableRoomsException(unVisited)
        }

        visited.clear()

        queue.add(outside)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (current !in visited) {
                visited.add(current)
                connections.filterValues { it.contains(current) }.keys.let { queue.addAll(it) }
            }
        }

        val cantExit = rooms.values.filter { it !in visited }.toSet()
        if (cantExit.isNotEmpty()) {
            throw CannotExitMuseumException(cantExit)
        }
    }

    override fun toString(): String {
        var result = "$name\n"
        val visited = mutableSetOf<MuseumRoom>()
        val queue = ArrayDeque<MuseumRoom>()
        queue.add(entrance)

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (current !in visited && current.name != "Outside") {
                visited.add(current)
                val dest = connections[current]?.map { it.name }?.joinToString(", ") ?: ""
                result += "${current.name} leads to: $dest\n"
                queue.addAll(connections[current] ?: emptyList())
            }
        }
        return result
    }
}

class UnreachableRoomsException(
    private val rooms: Set<MuseumRoom>,
) : Exception() {
    override fun toString(): String = "Unreachable rooms: " + rooms.map { it.name }.sorted().joinToString(", ")
}

class CannotExitMuseumException(
    private val rooms: Set<MuseumRoom>,
) : Exception() {
    override fun toString(): String = "Impossible to leave museum from: " + rooms.map { it.name }.sorted().joinToString(", ")
}
