// 1 Generate a random 4-digit number.
//   The number must not contain repeating digits.
//   --------------------------------------------
// 2 Ask the user to guess the 4-digit number.
//   --------------------------------------------
// 3 The output should be in the format "n:m",
//   where "n" is the number of digits guessed correctly regardless of their position,
//   and "m" is the number of digits guessed correctly at their correct position.
//   --------------------------------------------
// 4 Once the user guesses the correct number, the game is over.
//   ---------------------------------------------


fun main() {
    startGame()
}

/**
 * Start "Number Guessing Game"
 */
fun startGame() {
    println("=== Guessing Game ===")
    println(" Rules ")
    println(
                "A number guessing game is a game\nin which a user should guess \n" +
                "a 4-digit number in endless attempts.\nThe number must not contain repeating digits.\n" +
                "The output shows the format n:m, where n is \nthe number of digits guessed " +
                "correctly \nregardless of their position,and m is \nthe number of digits guessed " +
                "correctly \nat their correct position.\nOnce the user guesses the correct number, the " +
                "game is over.\n \n"
    )


    val generatedNumber: Int = generatedNumber()
    println("Generated Number: ****")

    do {
        val userNumber: Int = getUserInput()
        val isCorrect: Boolean = checkIfAnswerIsCorrect(generatedNumber, userNumber)
    } while (!isCorrect)

    println("You won! Game is over.")
}

fun generatedNumber(): Int {
    val digits = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    //shuffle the array of numbers until zero is in the first place
    do {
        digits.shuffle()
    } while (digits[0] == 0)

    var string = ""
    //it will repeat 4 times with the index = 0, 1, 2, 3
    for (index in 0..3) {
        string = string + digits[index]
    }
    return string.toInt()
}

/**
 *  Ask the user to enter the 4-digit number.
 */
fun getUserInput(): Int {
    print("User input: ")
    val userInput = readln().toInt()
    return userInput
}

/**
 * Verify if generatedNumber and userNumber is the same.
 * Returns true if user guessed the number, otherwise returns false.
 */
fun checkIfAnswerIsCorrect(generatedNumber: Int, userNumber: Int): Boolean {
    val generatedList = convertNumberToDigitList(generatedNumber)
    val userList = convertNumberToDigitList(userNumber)

    var matchDigit = 0
    var matchPlace = 0

    for ((index, digit) in userList.withIndex()) {
        if (generatedList.contains(digit)) {
            matchDigit += 1
        }
        if (generatedList[index] == userList[index]) {
            matchPlace += 1
        }
    }
//                0  1  2  3
// userList      [3, 5, 8 ,6]
// generatedList [8, 5, 7, 6]
    println("User input: $userNumber, Output: $matchDigit:$matchPlace")

    return matchPlace == 4

}

/**
 * Convert number to list of digits
 * Input: 1234
 * Convert to "1234"
 * Split to "1", "2", "3", "4"
 * Convert to 1, 2, 3, 4
 */

fun convertNumberToDigitList(number: Number): List<Int> {
    val s = number.toString()
    val digit1 = s.substring(0,1).toInt()
    val digit2 = s.substring(1,2).toInt()
    val digit3 = s.substring(2,3).toInt()
    val digit4 = s.substring(3,4).toInt()

    return listOf(digit1,digit2,digit3,digit4)
}




