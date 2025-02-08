package museumvisit

/*
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.fail
*/

class MuseumTest {

//    @Test
//    fun `test toString animal sanctuary`() {
//        assertEquals(
//            """
//            Animal sanctuary
//            Entrance hall leads to: Bats
//            Bats leads to: Lizards
//            Lizards leads to: Insects, Gift shop
//            Insects leads to: Snakes, Gift shop
//            Gift shop leads to: Outside
//            Snakes leads to: Entrance hall
//
//            """.trimIndent(),
//            createAnimalSanctuary().toString(),
//        )
//    }
//
//    @Test
//    fun `test well formed art gallery`() {
//        createArtGallery().checkWellFormed()
//    }
//
//    @Test
//    fun `test well formed animal santuary`() {
//        createAnimalSanctuary().checkWellFormed()
//    }
//
//    @Test
//    fun `test animal santuary with unreachable rooms`() {
//        try {
//            createAnimalSanctuaryWithUnreachableRooms().checkWellFormed()
//            fail("An UnreachableRoomException should have been thrown")
//        } catch (exception: UnreachableRoomsException) {
//            assertEquals(
//                """
//                Unreachable rooms: Bats, Gift shop, Insects, Lizards, Snakes
//                """.trimIndent(),
//                exception.toString(),
//            )
//        }
//    }
//
//    @Test
//    fun `test animal santuary with rooms that do not lead to exit`() {
//        try {
//            createAnimalSanctuaryWithRoomsThatDoNotLeadToExit().checkWellFormed()
//            fail("An CannotExitMuseumException should have been thrown")
//        } catch (exception: CannotExitMuseumException) {
//            assertEquals(
//                """
//                Impossible to leave museum from: Insects, Snakes
//                """.trimIndent(),
//                exception.toString(),
//            )
//        }
//    }
//
//    @Test
//    fun `cannot connect unknown room to exit`() {
//        val museum = Museum("Some museum", MuseumRoom("Entrance", 5))
//        try {
//            museum.connectRoomToExit(MuseumRoom("Some room", 3))
//            fail("Expected IllegalArgumentException to be thrown")
//        } catch (exception: IllegalArgumentException) {
//            // Good: exception expected
//        }
//    }
//
//    @Test
//    fun `cannot connect rooms if first is unknown`() {
//        val entrance = MuseumRoom("Entrance", 5)
//        val museum = Museum("Some museum", entrance)
//        try {
//            museum.connectRoomTo(MuseumRoom("Some room", 3), entrance)
//            fail("Expected IllegalArgumentException to be thrown")
//        } catch (exception: IllegalArgumentException) {
//            // Good: exception expected
//        }
//    }
//
//    @Test
//    fun `cannot add room multiple times`() {
//        val entrance = MuseumRoom("Entrance", 5)
//        val museum = Museum("Some museum", entrance)
//        try {
//            museum.addRoom(entrance)
//            fail("Expected IllegalArgumentException to be thrown")
//        } catch (exception: IllegalArgumentException) {
//            // Good: exception expected
//        }
//    }
//
//    fun `cannot add room with same name`() {
//        val entrance = MuseumRoom("Entrance", 5)
//        val museum = Museum("Some museum", entrance)
//        try {
//            museum.addRoom(MuseumRoom("Entrance", 6))
//            fail("Expected IllegalArgumentException to be thrown")
//        } catch (exception: IllegalArgumentException) {
//            // Good: exception expected
//        }
//    }
//
//    @Test
//    fun `cannot connect room to same room multiple times`() {
//        val entrance = MuseumRoom("Entrance", 5)
//        val exhibitionRoom = MuseumRoom("Exhibition room", 3)
//        val museum = Museum("Some museum", entrance)
//        museum.addRoom(exhibitionRoom)
//        museum.connectRoomTo(entrance, exhibitionRoom)
//        try {
//            museum.connectRoomTo(entrance, exhibitionRoom)
//            fail("Expected IllegalArgumentException to be thrown")
//        } catch (exception: IllegalArgumentException) {
//            // Good: exception expected
//        }
//    }
//
//    @Test
//    fun `cannot connect room to self`() {
//        val entrance = MuseumRoom("Entrance", 5)
//        val museum = Museum("Some museum", entrance)
//        try {
//            museum.connectRoomTo(entrance, entrance)
//            fail("Expected IllegalArgumentException to be thrown")
//        } catch (exception: IllegalArgumentException) {
//            // Good: exception expected
//        }
//    }
}
