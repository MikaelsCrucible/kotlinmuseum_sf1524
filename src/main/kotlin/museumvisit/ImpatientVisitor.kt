package museumvisit

import java.io.PrintStream
import kotlin.random.Random

class ImpatientVisitor(
    private val name: String,
    private val output: PrintStream,
    private val museum: Museum,
) : Runnable {
    override fun run() {
        var current: MuseumRoom? = null

        while (current == null) {
            current = museum.enterIfPossible()
            if (current == null) {
                output.print("$name could not get into ${museum.name} but will try again soon.\n")
                Thread.sleep(10)
                output.print("$name is ready to try again.\n")
            }
        }

        output.print("$name has entered ${museum.name}.\n")

        output.print("$name has entered ${current.name}.\n")

        while (true) {
            val stayTime = Random.nextLong(1, 201)
            Thread.sleep(stayTime)
            output.print("$name wants to leave ${current!!.name}.\n")
            val exits = museum.connections[current]!!

            var next = exits.random()
            var nextRoom = museum.passThroughTurnstile(current, next)
//
            while (nextRoom == null) {
                output.print("$name failed to leave ${current.name} but will try again soon.\n")
                Thread.sleep(10)
                output.print("$name is ready to try leaving ${current.name} again.\n")
                next = exits.random()
                nextRoom = museum.passThroughTurnstile(current, next)
            }
            output.print("$name has left ${current.name}.\n")
            if (nextRoom.name == "Outside") {
                output.print("$name has left ${museum.name}.\n")
                break
            }
            output.print("$name has entered ${nextRoom.name}.\n")
            current = nextRoom

            //
        }
    }
}
