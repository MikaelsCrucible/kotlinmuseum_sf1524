package museumvisit

/*
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class MuseumUnderT(val museum: Museum, val entrance: MuseumRoom, val rooms: List<MuseumRoom>)

fun createSmallMuseumUnderT(): MuseumUnderT {
    val entrance = MuseumRoom("Entrance", 1)
    val exhibition1 = MuseumRoom("Room 1", 1)
    val exhibition2 = MuseumRoom("Room 2", 1)

    val rooms = listOf(entrance, exhibition1, exhibition2)

    val museum = Museum("Small museum", entrance)
    museum.addRoom(exhibition1)
    museum.addRoom(exhibition2)
    museum.connectRoomTo(entrance, exhibition1)
    museum.connectRoomTo(exhibition1, entrance)
    museum.connectRoomTo(exhibition1, exhibition2)
    museum.connectRoomTo(exhibition2, exhibition1)
    museum.connectRoomToExit(exhibition2)
    museum.checkWellFormed()

    return MuseumUnderT(museum, entrance, rooms)
}

fun createAquariumMuseumUnderT(): MuseumUnderT {
    val entrance = MuseumRoom("Aquarium entrance", 20)
    val crustaceans = MuseumRoom("Crabs and lobsters", 4)
    val sharks = MuseumRoom("Sharks", 4)
    val rays = MuseumRoom("Rays", 6)
    val seahorses = MuseumRoom("Seahorses", 3)
    val smallFish = MuseumRoom("Small fish", 9)
    val bobbits = MuseumRoom("Bobbit worms", 1)

    val rooms = listOf(crustaceans, sharks, rays, seahorses, smallFish, bobbits, entrance)

    val museum = Museum("Ally's Grand Aquarium", entrance)
    museum.addRoom(crustaceans)
    museum.addRoom(sharks)
    museum.addRoom(rays)
    museum.addRoom(seahorses)
    museum.addRoom(smallFish)
    museum.addRoom(bobbits)
    museum.connectRoomTo(entrance, crustaceans)
    museum.connectRoomTo(crustaceans, sharks)
    museum.connectRoomTo(sharks, rays)
    museum.connectRoomTo(rays, seahorses)
    museum.connectRoomTo(seahorses, smallFish)
    museum.connectRoomTo(smallFish, bobbits)
    museum.connectRoomTo(bobbits, entrance)
    museum.connectRoomTo(sharks, smallFish)
    museum.connectRoomTo(smallFish, sharks)
    museum.connectRoomToExit(entrance)
    museum.connectRoomToExit(rays)
    museum.checkWellFormed()

    return MuseumUnderT(museum, entrance, rooms)
}

val lotsOfPeople = listOf(
    "Neha",
    "Alex",
    "Yi",
    "Jianyi",
    "Felix",
    "Oscar",
    "Amelia",
    "Noah",
    "Prakesh",
    "Satnam",
    "Susan",
    "Poppy",
    "Jaya",
    "Indy",
    "Lula",
    "Maximilian",
    "Minimilian",
    "Jacub",
    "Donald",
    "Liz",
    "Teresa",
    "Julia",
    "Parminda",
    "Xi",
)

fun checkImpatientOutput(person: String, output: String, museumUnderTest: MuseumUnderT) {
    val lines = output.split("\n")
    var index = 0
    while (lines[index] != "$person has entered ${museumUnderTest.museum.name}.") {
        assertEquals("$person could not get into ${museumUnderTest.museum.name} but will try again soon.", lines[index])
        index++
        assertEquals("$person is ready to try again.", lines[index])
        index++
    }
    index++
    while (index < lines.size) {
        val personEnteredRegex = """$person has entered ([a-zA-Z0-9 ]+)\.""".toRegex()
        val personEnteredRegexMatchResult = personEnteredRegex.find(lines[index])!!
        val (roomName) = personEnteredRegexMatchResult.destructured
        assertTrue(roomName in museumUnderTest.rooms.map { it.name }, "Unknown room name $roomName")
        assertTrue(index < lines.size - 1)
        index++
        assertEquals("$person wants to leave $roomName.", lines[index])
        assertTrue(index < lines.size - 1)
        index++
        while ("$person has left $roomName." != lines[index]) {
            assertEquals("$person failed to leave $roomName but will try again soon.", lines[index])
            assertTrue(index < lines.size - 1)
            index++
            assertEquals("$person is ready to try leaving $roomName again.", lines[index])
            assertTrue(index < lines.size - 1)
            index++
        }
        assertTrue(index < lines.size - 1)
        index++
        if (lines[index] == "$person has left ${museumUnderTest.museum.name}.") {
            assertEquals(lines.size - 2, index)
            assertEquals("", lines[lines.size - 1])
            return
        }
    }
    fail("Expected to see $person leaving the museum.")
}

fun checkPatientOutput(person: String, output: String, museumUnderTest: MuseumUnderT) {
    val lines = output.split("\n")
    var index = 0
    assertEquals("$person has entered ${museumUnderTest.museum.name}.", lines[index])
    assertTrue(index < lines.size - 1)
    index++
    while (index < lines.size) {
        val personEnteredRegex = """$person has entered ([a-zA-Z0-9 ]+)\.""".toRegex()
        val personEnteredRegexMatchResult = personEnteredRegex.find(lines[index])!!
        val (roomName) = personEnteredRegexMatchResult.destructured
        assertTrue(roomName in museumUnderTest.rooms.map { it.name }, "Unknown room name $roomName")
        assertTrue(index < lines.size - 1)
        index++
        assertEquals("$person wants to leave $roomName.", lines[index])
        assertTrue(index < lines.size - 1)
        index++
        assertEquals("$person has left $roomName.", lines[index])
        assertTrue(index < lines.size - 1)
        index++
        if (lines[index] == "$person has left ${museumUnderTest.museum.name}.") {
            assertEquals(lines.size - 2, index)
            assertEquals("", lines[lines.size - 1])
            return
        }
    }
    fail("Expected to see $person leaving the museum.")
}
*/
