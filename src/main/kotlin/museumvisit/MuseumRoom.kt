package museumvisit
import java.util.concurrent.locks.ReentrantLock

data class MuseumRoom(
    private val n: String,
    private val cap: Int,
) {
    private var _occupancy: Int = 0
    val occupancy: Int
        get() = _occupancy
    val lock = ReentrantLock()

    init {
        if (capacity < 0) throw IllegalArgumentException("Room capacity must be a positive integer.")
    }

    val name: String
        get() = n

    val capacity: Int
        get() = cap

    fun hasCapacity(): Boolean = _occupancy < capacity

    fun enter() {
        if (!hasCapacity()) {
            throw UnsupportedOperationException("Room $name is full.")
        }
        _occupancy++
    }

    fun exit() {
        if (_occupancy == 0) {
            throw UnsupportedOperationException("Room $name is empty.")
        }
        _occupancy--
    }
}
