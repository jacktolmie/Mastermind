enum class Colours{
    BLUE, GREEN, YELLOW, RED, PURPLE, ORANGE, EMPTY, CODEBLANK, GUESSBLANK
}

//Ask the player to choose the colours wanted to test.
fun getColours(): MutableList<Colours>{
    val chosenColour = mutableListOf<Colours>()

    println("Choose from: (B)lue, (G)reen, (Y)ellow, (R)ed, (P)urple, (O)range, (E)mpty")
    while(chosenColour.size < 4){
        print("Please enter colour for #${chosenColour.size +1}: ")
        var choice = readln().uppercase()

        /* If the choice is single char, change
        *  it to the full name of colour.
        */
        if(choice.length == 1){
            when(choice){
                "B" -> choice = "BLUE"
                "G" -> choice = "GREEN"
                "Y" -> choice = "YELLOW"
                "R" -> choice = "RED"
                "P" -> choice = "PURPLE"
                "O" -> choice = "ORANGE"
                "E" -> choice = "EMPTY"
            }
        }
        /* If the Colours enum contains the colour selected
        *  add it to the chosenColour list, otherwise
        *  print warning.
        */
        if(Colours.values().map { it.name}.contains(choice)) {
            when (choice) {
                "BLUE"-> chosenColour.add(Colours.BLUE)
                "GREEN" -> chosenColour.add(Colours.GREEN)
                "YELLOW" -> chosenColour.add(Colours.YELLOW)
                "RED" -> chosenColour.add(Colours.RED)
                "PURPLE" -> chosenColour.add(Colours.PURPLE)
                "ORANGE" -> chosenColour.add(Colours.ORANGE)
                "EMPTY" ->  chosenColour.add(Colours.EMPTY)
            }
        }
        else println("$choice is not an option. Please choose again.")
    }
    return chosenColour
}

//Make the code to guess.
fun codeMaker(): List<Colours>{
    val code = mutableListOf<Colours>()

    /* Choose four random colours from the
    *  enum class. If BLANK is selected do
    *  not add it to the list.
    */
    while(code.size < 4){
        val colour = Colours.values().random()
        if(colour != Colours.CODEBLANK){
            code.add(colour)
        }
    }
    return code.toList()
}

fun checkCode(code : List<Colours>, guess : MutableList<Colours>): MutableList<Char>{

    val peg = mutableListOf<Char>()
    val copyCode = code.toMutableList()
    val copyGuess = guess.toMutableList()

    /* If all the choices are right, and in the right order,
    *  fill list with 'b' and return.
    */
    if(copyCode == guess){
        (1..4).forEach{ _ ->
            peg.add('b')
        }
        return peg
    }
    else{
        for(i in 0..3) {

            /* Check if current guess matches the code. Add 'b' if it does and
            *  set the code element to BLANK.
            */
            if (guess[i] == copyCode[i]) {
                peg.add('b')
                copyCode[i] = Colours.CODEBLANK
                copyGuess[i] = Colours.GUESSBLANK
                continue
            }
        }


      for(i in 0..3) {

          // Count how many times current guess is in the list.
          val colourCount = copyCode.count {copyGuess[i].name == it.name}

          /* If guess matches colour but not peg location,
          *  add 'w' and set to BLANK.
          */
          if(colourCount  != 0){
              peg.add('w')
              //Removes the colour from the guesses and code copies.
              copyCode[copyCode.indexOf(copyGuess[i])] = Colours.CODEBLANK
              copyGuess[i] = Colours.GUESSBLANK
              continue
          }
      }
    }
    peg.shuffle()
    return peg
}

fun printRow(choices : MutableList<Map<MutableList<Colours>, MutableList<Char>>>){
    choices.forEachIndexed {i, choice ->
        choice.forEach { (t, u) ->
            print("${i + 1}\t")
            t.forEach { print("$it ") }
            print(" : ")
            u.forEach { print("$it ") }
            println()
        }
    }

}
fun main(){
    val code = codeMaker()
    val storeGuesses = mutableListOf<Map<MutableList<Colours>, MutableList<Char>>>()

    for(tries in 1..12){
        val chooseColour = getColours()
        val checkChoices = checkCode(code, chooseColour)
        storeGuesses.add(mapOf(chooseColour to checkChoices))
        printRow(storeGuesses)
        if((checkChoices.all { it == 'b'})  && checkChoices.size == 4){
            println("Congratulations. You won!")
            break
        }
    }

    print("The code was: ")
    code.forEach { print("$it ") }
    println()




}