package museumvisit

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.fail

class MuseumRoomTest {
    @Test
    fun `test name`() {
        assertEquals("Dali paintings", MuseumRoom("Dali paintings", 10).name)
    }

    @Test
    fun `exception on enter if room gets full`() {
        val room = MuseumRoom("Sharks", 3)
        assertTrue(room.hasCapacity())
        room.enter()
        assertTrue(room.hasCapacity())
        room.enter()
        assertTrue(room.hasCapacity())
        room.enter()
        assertFalse(room.hasCapacity())
        try {
            room.enter()
            fail("An UnsupportedOperationException should have been thrown.")
        } catch (exception: UnsupportedOperationException) {
            // Good: exception expected
        }
    }

    @Test
    fun `exception on exit if room is empty`() {
        val room = MuseumRoom("Sharks", 3)
        room.enter()
        room.exit()
        try {
            room.exit()
            fail("An UnsupportedOperationException should have been thrown.")
        } catch (exception: UnsupportedOperationException) {
            // Good: exception expected
        }
    }
}
