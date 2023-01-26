package parking


val parkingCarSpace: MutableList<MutableList<String>> = mutableListOf()
var userInput: String = ""
var lookPositions = 0
var spot: Int = 0

fun main() {
    createManagerParkingLot()
}

fun createManagerParkingLot() {
    do {
        val createSpot = readln().split(" ")

        if ((createSpot[0] == "park"  && spot == 0)) {
            println("Sorry, a parking lot has not been created.")
            continue
        }
        if (createSpot[0] == "leave" && spot == 0) {
            println("Sorry, a parking lot has not been created.")
            continue
        }
        if (createSpot[0] == "status" && spot == 0) {
            println("Sorry, a parking lot has not been created.")
            continue
        }
        if (createSpot[0] == "reg_by_color" && spot == 0) {
            println("Sorry, a parking lot has not been created.")
            continue
        }
        if (createSpot[0] == "spot_by_reg" && spot == 0) {
            println("Sorry, a parking lot has not been created.")
            continue
        }
        if (createSpot[0] == "spot_by_color" && spot == 0) {
            println("Sorry, a parking lot has not been created.")
            continue
        }
        if (createSpot[0] == "park" && spot > 0) {
            addParkingCar(createSpot[1], createSpot[2], spot)
        }
        if (createSpot[0] == "leave" && spot > 0) {
            parkingLeaveCar(createSpot[0], createSpot[1])
        }
        if (createSpot[0] == "status" && spot > 0) {
            parkingStatus(createSpot[0])
        }
        if (createSpot[0] == "reg_by_color" && spot > 0) {
            findColorCar(createSpot[1])
            continue
        }
        if (createSpot[0] == "spot_by_reg" && spot > 0) {
            findParkingCarLicensePlate(createSpot[1])
            continue
        }
        if (createSpot[0] == "spot_by_color" && spot > 0) {
            findParkingCarColor(createSpot[1])
            continue
        }
        if (createSpot[0] == "create" && createSpot[1].toInt() > 0 ) {
            parkingCarSpace.removeAll(parkingCarSpace)
            parkingSpace(createSpot[1].toInt())
            spot = createSpot[1].toInt()
            continue
        }
    } while (createSpot[0] != "exit")
    if (userInput == "exit") {
    }
}

fun parkingSpace(spot: Int) {
    var i = 0
    repeat(spot) {
        parkingCarSpace.add(mutableListOf("${i + 1}", " ", " "))
        i++
    }
    println("Created a parking lot with $spot spots.")
}

fun addParkingCar(licensePlate: String, color: String, spot: Int) {

    lookPositions = 0
    for (findParking in parkingCarSpace.indices) {
        lookPositions++
        if (parkingCarSpace[spot -1][0].toInt() == lookPositions && parkingCarSpace[spot -1][1] != " ") { println("Sorry, the parking lot is full.")
            break
        }
        var carId = 0

        if (parkingCarSpace[findParking][0].toInt() == lookPositions && parkingCarSpace[findParking][1] == " ") {
            parkingCarSpace[findParking][1] = licensePlate
            parkingCarSpace[findParking][2] = color.toString()
            println("$color car parked in spot ${lookPositions}.")
            break
        }
    }
}

fun parkingLeaveCar(leavePark: String, parkingSpace: String) {
    if ((leavePark == "leave")) {

        parkingSpace
        lookPositions = 0

        for (leaveCar in parkingCarSpace.indices) {
            when {
                parkingCarSpace[lookPositions][0].toInt() == parkingSpace.toInt() -> {
                    parkingCarSpace[lookPositions][1] = " "
                    parkingCarSpace[lookPositions][2] = " "
                    println("Spot ${parkingCarSpace[lookPositions][0].toInt()} is free.")
                    break
                }
            }
            lookPositions++
        }
    }
}

fun parkingStatus(status: String) {
    var countParkCar = 0
    if (status == "status") {
        lookPositions = 0
        for (i in parkingCarSpace.indices) {
            lookPositions++
            when {
                parkingCarSpace[i][1] != " " -> {
                    countParkCar++
                    println("${parkingCarSpace[i][0]} ${parkingCarSpace[i][1]} ${parkingCarSpace[i][2]}")

                }
                countParkCar == 0 -> {
                    println("Parking lot is empty.")
                    break
                }

            }
        }
    }
}

fun findColorCar(color: String) {
    var findColorCar = ""
    lookPositions = 0
    for (i in parkingCarSpace.indices) {
        lookPositions++
        when {
            parkingCarSpace[i][0].toInt() == lookPositions && parkingCarSpace[i][2].lowercase() == color.lowercase() -> {
                findColorCar += "${parkingCarSpace[i][1]}, "
            }
        }
    }
    if (findColorCar == "") {
        println("No cars with color $color were found.")
    }
    else {
        println(findColorCar.dropLast(2))
    }

}

fun findParkingCarLicensePlate(licensePlate: String) {
    var findLicenseCar = ""
    lookPositions = 0
    for (i in parkingCarSpace.indices) {
        lookPositions++
        when {
            parkingCarSpace[i][0].toInt() == lookPositions && parkingCarSpace[i][1] == licensePlate -> {
                findLicenseCar += "${i +1}, "
            }
        }
    }
    if (findLicenseCar == "") {
        println("No cars with registration number $licensePlate were found.")
    }
    else {
        println(findLicenseCar.dropLast(2))
    }
}

fun findParkingCarColor(color: String) {
    var findColorCar = ""
    lookPositions = 0
    for (i in parkingCarSpace.indices) {
        lookPositions++
        when {
            parkingCarSpace[i][0].toInt() == lookPositions && parkingCarSpace[i][2].lowercase() == color.lowercase() -> {
                findColorCar += "${i + 1}, "
            }
        }
    }
    if (findColorCar == "") {
        println("No cars with color $color were found.")
    }
    else {
        println(findColorCar.dropLast(2))
    }
}