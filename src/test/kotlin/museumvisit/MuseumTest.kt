package museumvisit

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class MuseumTest {
    fun createArtGallery(): Museum {
        val entranceHall = MuseumRoom("Entrance hall", 10)
        val exhibitionRoom = MuseumRoom("Exhibition room", 5)

        val museum = Museum("Art Gallery", entranceHall)
        museum.addRoom(exhibitionRoom)
        museum.connectRoomTo(entranceHall, exhibitionRoom)
        museum.connectRoomToExit(exhibitionRoom)

        return museum
    }

    fun createAnimalSanctuary(): Museum {
        val entranceHall = MuseumRoom("Entrance hall", 20)
        val bats = MuseumRoom("Bats", 10)
        val snakes = MuseumRoom("Snakes", 10)
        val lizards = MuseumRoom("Lizards", 10)
        val insects = MuseumRoom("Insects", 10)
        val giftShop = MuseumRoom("Gift shop", 20)

        val museum = Museum("Animal sanctuary", entranceHall)

        museum.addRoom(bats)
        museum.addRoom(snakes)
        museum.addRoom(lizards)
        museum.addRoom(insects)
        museum.addRoom(giftShop)

        museum.connectRoomTo(entranceHall, bats)
        museum.connectRoomTo(bats, lizards)
        museum.connectRoomTo(lizards, insects)
        museum.connectRoomTo(insects, snakes)
        museum.connectRoomTo(snakes, entranceHall)

        museum.connectRoomTo(insects, giftShop)
        museum.connectRoomTo(lizards, giftShop)
        museum.connectRoomToExit(giftShop)

        return museum
    }

    fun createAnimalSanctuaryWithUnreachableRooms(): Museum {
        val entranceHall = MuseumRoom("Entrance hall", 20)
        val bats = MuseumRoom("Bats", 10)
        val snakes = MuseumRoom("Snakes", 10)
        val lizards = MuseumRoom("Lizards", 10)
        val insects = MuseumRoom("Insects", 10)
        val giftShop = MuseumRoom("Gift shop", 20)

        val museum = Museum("Animal sanctuary", entranceHall)

        museum.addRoom(bats)
        museum.addRoom(snakes)
        museum.addRoom(lizards)
        museum.addRoom(insects)
        museum.addRoom(giftShop)

        // museum.connectRoomTo(entranceHall, bats)
        museum.connectRoomTo(bats, lizards)
        museum.connectRoomTo(lizards, insects)
        museum.connectRoomTo(insects, snakes)
        museum.connectRoomTo(snakes, entranceHall)

        museum.connectRoomTo(insects, giftShop)
        museum.connectRoomTo(lizards, giftShop)
        museum.connectRoomToExit(giftShop)

        return museum
    }

    fun createAnimalSanctuaryWithRoomsThatDoNotLeadToExit(): Museum {
        val entranceHall = MuseumRoom("Entrance hall", 20)
        val bats = MuseumRoom("Bats", 10)
        val snakes = MuseumRoom("Snakes", 10)
        val lizards = MuseumRoom("Lizards", 10)
        val insects = MuseumRoom("Insects", 10)
        val giftShop = MuseumRoom("Gift shop", 20)

        val museum = Museum("Animal sanctuary", entranceHall)

        museum.addRoom(bats)
        museum.addRoom(snakes)
        museum.addRoom(lizards)
        museum.addRoom(insects)
        museum.addRoom(giftShop)

        museum.connectRoomTo(entranceHall, bats)
        museum.connectRoomTo(bats, lizards)
        museum.connectRoomTo(lizards, insects)
        museum.connectRoomTo(insects, snakes)
        // museum.connectRoomTo(snakes, entranceHall)

        // museum.connectRoomTo(insects, giftShop)
        museum.connectRoomTo(lizards, giftShop)
        museum.connectRoomToExit(giftShop)

        return museum
    }

    @Test
    fun `test toString animal sanctuary`() {
        assertEquals(
            """
            Animal sanctuary
            Entrance hall leads to: Bats
            Bats leads to: Lizards
            Lizards leads to: Insects, Gift shop
            Insects leads to: Snakes, Gift shop
            Gift shop leads to: Outside
            Snakes leads to: Entrance hall

            """.trimIndent(),
            createAnimalSanctuary().toString(),
        )
    }

    @Test
    fun `test well formed art gallery`() {
        createArtGallery().checkWellFormed()
    }

    @Test
    fun `test well formed animal santuary`() {
        createAnimalSanctuary().checkWellFormed()
    }

    @Test
    fun `test animal santuary with unreachable rooms`() {
        try {
            createAnimalSanctuaryWithUnreachableRooms().checkWellFormed()
            fail("An UnreachableRoomException should have been thrown")
        } catch (exception: UnreachableRoomsException) {
            assertEquals(
                """
                Unreachable rooms: Bats, Gift shop, Insects, Lizards, Snakes
                """.trimIndent(),
                exception.toString(),
            )
        }
    }

    @Test
    fun `test animal santuary with rooms that do not lead to exit`() {
        try {
            createAnimalSanctuaryWithRoomsThatDoNotLeadToExit().checkWellFormed()
            fail("An CannotExitMuseumException should have been thrown")
        } catch (exception: CannotExitMuseumException) {
            assertEquals(
                """
                Impossible to leave museum from: Insects, Snakes
                """.trimIndent(),
                exception.toString(),
            )
        }
    }

    @Test
    fun `cannot connect unknown room to exit`() {
        val museum = Museum("Some museum", MuseumRoom("Entrance", 5))
        try {
            museum.connectRoomToExit(MuseumRoom("Some room", 3))
            fail("Expected IllegalArgumentException to be thrown")
        } catch (exception: IllegalArgumentException) {
            // Good: exception expected
        }
    }

    @Test
    fun `cannot connect exit room to outside again`() {
        val museum = Museum("Some museum", MuseumRoom("Entrance", 5))
        try {
            museum.connectRoomToExit(MuseumRoom("Entrance", 5))
            museum.connectRoomToExit(MuseumRoom("Entrance", 5))
            fail("Expected IllegalArgumentException to be thrown")
        } catch (exception: IllegalArgumentException) {
            // Good: exception expected
        }
    }

    @Test
    fun `cannot have more people than capacity`() {
        val museum = Museum("Some museum", MuseumRoom("Entrance", 1))
        try {
            museum.enter()
            museum.enter()
            fail("Expected UnsupportedOperationException to be thrown")
        } catch (exception: UnsupportedOperationException) {
            // Good: exception expected
        }
    }

    @Test
    fun `admitted works properly`() {
        val museum = Museum("Some museum", MuseumRoom("Entrance", 5))
        museum.enter()
        museum.enter()
        assertEquals(2, museum.admitted)
    }

    @Test
    fun `cannot connect rooms if first is unknown`() {
        val entrance = MuseumRoom("Entrance", 5)
        val museum = Museum("Some museum", entrance)
        try {
            museum.connectRoomTo(MuseumRoom("Some room", 3), entrance)
            fail("Expected IllegalArgumentException to be thrown")
        } catch (exception: IllegalArgumentException) {
            // Good: exception expected
        }
    }

    @Test
    fun `cannot add room multiple times`() {
        val entrance = MuseumRoom("Entrance", 5)
        val museum = Museum("Some museum", entrance)
        try {
            museum.addRoom(entrance)
            fail("Expected IllegalArgumentException to be thrown")
        } catch (exception: IllegalArgumentException) {
            // Good: exception expected
        }
    }

    fun `cannot add room with same name`() {
        val entrance = MuseumRoom("Entrance", 5)
        val museum = Museum("Some museum", entrance)
        try {
            museum.addRoom(MuseumRoom("Entrance", 6))
            fail("Expected IllegalArgumentException to be thrown")
        } catch (exception: IllegalArgumentException) {
            // Good: exception expected
        }
    }

    @Test
    fun `cannot connect room to same room multiple times`() {
        val entrance = MuseumRoom("Entrance", 5)
        val exhibitionRoom = MuseumRoom("Exhibition room", 3)
        val museum = Museum("Some museum", entrance)
        museum.addRoom(exhibitionRoom)
        museum.connectRoomTo(entrance, exhibitionRoom)
        try {
            museum.connectRoomTo(entrance, exhibitionRoom)
            fail("Expected IllegalArgumentException to be thrown")
        } catch (exception: IllegalArgumentException) {
            // Good: exception expected
        }
    }

    @Test
    fun `cannot connect room to self`() {
        val entrance = MuseumRoom("Entrance", 5)
        val museum = Museum("Some museum", entrance)
        try {
            museum.connectRoomTo(entrance, entrance)
            fail("Expected IllegalArgumentException to be thrown")
        } catch (exception: IllegalArgumentException) {
            // Good: exception expected
        }
    }
}
