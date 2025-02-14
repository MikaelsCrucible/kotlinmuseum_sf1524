package museumvisit

import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class ImpatientVisitorsTest {
    private fun testMuseumVisit(
        museumUnderTest: MuseumUnderT,
        people: List<String>,
    ) {
        val byteOutputStreams =
            people.map {
                ByteArrayOutputStream()
            }
        val printStreams =
            byteOutputStreams.map {
                PrintStream(it)
            }
        assertEquals(0, museumUnderTest.museum.admitted)
        assertEquals(0, museumUnderTest.museum.outside.occupancy)
        for (room in museumUnderTest.rooms) {
            assertEquals(0, room.occupancy)
        }
        val visitors =
            people.mapIndexed { index, person ->
                Thread(ImpatientVisitor(person, printStreams[index], museumUnderTest.museum))
            }
        visitors.forEach { it.start() }
        visitors.forEach { it.join() }
        assertEquals(people.size, museumUnderTest.museum.admitted)
        assertEquals(people.size, museumUnderTest.museum.outside.occupancy)
        for (room in museumUnderTest.rooms) {
            assertEquals(0, room.occupancy)
        }
        byteOutputStreams.forEachIndexed { index, byteArrayOutputStream ->
            checkImpatientOutput(people[index], byteArrayOutputStream.toString(), museumUnderTest)
        }
    }

    @Test
    fun `two visitors to small museum`() {
        testMuseumVisit(createSmallMuseumUnderT(), listOf("Ally", "Chris"))
    }

    @Test
    fun `many visitors to small museum`() {
        testMuseumVisit(createSmallMuseumUnderT(), lotsOfPeople)
    }

    @Test
    fun `two visitors to aquarium`() {
        testMuseumVisit(createAquariumMuseumUnderT(), listOf("Ally", "Chris"))
    }

    @Test
    fun `many visitors to aquarium`() {
        testMuseumVisit(createAquariumMuseumUnderT(), lotsOfPeople)
    }
}
