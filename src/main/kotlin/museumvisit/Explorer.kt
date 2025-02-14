package museumvisit

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

fun choosemuseum(): Museum? {
    println("Welcome to the Museum Exploration Program!")
    println("Choose a museum: (1) Art Gallery, (2) Animal Sanctuary")
    println("Press Ctrl+Z to exit!")

    when (readlnOrNull()?.trim() ?: "") {
        "1" -> return createArtGallery()
        "2" -> return createAnimalSanctuary()
        "" -> return null
        else -> {
            println("Invalid choice. Try Again!")
            return choosemuseum()
        }
    }
}

fun main() {
    while (true) {
        val tmp = choosemuseum()
        val museum: Museum = tmp ?: return

        var current: MuseumRoom = museum.entrance
        println("You are now in the ${current.name}.")

        while (true) {
            print(":> ")
            val input = readlnOrNull()?.trim() ?: break
            val parts = input.split(" ", limit = 2)
            val command = parts[0]
            val argument = parts.getOrElse(1) { "" }

            when (command) {
                "move" -> {
                    val nextRoom = museum.connections[current]?.find { it.name == argument }
                    if (nextRoom != null) {
                        current = nextRoom
                        println("You moved to ${current.name}.")
                    } else {
                        println("Cannot move to $argument. No path exists.")
                    }
                }

                "where" -> {
                    val connectedRooms = museum.connections[current]?.joinToString(", ") { it.name } ?: "None"
                    println("You are in ${current.name}. Connected rooms: $connectedRooms")
                }

                "exit" -> {
                    if (museum.connections[current]?.contains(museum.outside) == true) {
                        println("You have successfully exited the museum!")
                        break
                    } else {
                        println("You cannot exit from here.")
                    }
                }

                "help" -> {
                    println("Available commands:")
                    println("  move ROOM_NAME - Move to another room if connected")
                    println("  where - Show current location and connected rooms")
                    println("  exit - Try to leave the museum")
                    println("  help - Show this help message")
                    println("  Ctrl+Z - Exit the program")
                }

                else -> println("Unknown command. Type 'help' for available commands.")
            }
        }
    }
}
