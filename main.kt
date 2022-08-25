enum class Colours{
    BLUE, GREEN, YELLOW, RED, PURPLE, ORANGE, BLANK
}

//Ask the player to choose the colours wanted to test.
fun getColours(): MutableList<Colours>{
    val chosenColour = mutableListOf<Colours>()

    println("Colours to choose from: (B)lue, (G)reen, (Y)ellow, (R)ed, (P)urple, (O)range")
    while(chosenColour.size < 4){
        print("Please enter colour for #${chosenColour.size +1}: ")
        var choice = readln().uppercase()
        if(choice.length == 1){
            when(choice){
                "B" -> choice = "BLUE"
                "G" -> choice = "GREEN"
                "Y" -> choice = "YELLOW"
                "R" -> choice = "RED"
                "P" -> choice = "PURPLE"
                "O" -> choice = "ORANGE"
            }
        }
        if(Colours.values().map { it.name}.contains(choice)) {
            when (choice) {
                "BLUE"-> chosenColour.add(Colours.BLUE)
                "GREEN" -> chosenColour.add(Colours.GREEN)
                "YELLOW" -> chosenColour.add(Colours.YELLOW)
                "RED" -> chosenColour.add(Colours.RED)
                "PURPLE" -> chosenColour.add(Colours.PURPLE)
                "ORANGE" -> chosenColour.add(Colours.ORANGE)
            }
        }
        else println("$choice is not an option. Please choose again.")
    }
    return chosenColour
}

//Make the code to guess.
fun codeMaker(): MutableList<Colours>{
    val code = mutableListOf<Colours>()
    while(code.size < 4){
        val colour = Colours.values().random()
        if(colour != Colours.BLANK){
            code.add(colour)
        }
    }
    code.forEach { print("$it, ") }
    println()
    return code
}

fun checkCode(code : MutableList<Colours>, guess : MutableList<Colours>): MutableList<Char>{

    val peg = mutableListOf<Char>()

    if(code == guess){
        println("Congratulations. You won!")
    }
    else{
        println("Incorrect choices!")

        for(i in 0..3){
            if(guess[i] == code[i]){
                peg.add('b')
                code[i] = Colours.BLANK
            }
            else if(code.contains(guess[i])){
                peg.add('w')
                code[i] = Colours.BLANK
            }
        }
    }
//    peg.forEach{print("$it, ")}
    peg.shuffle()
    return peg
}

//fun pegs(peg : List<Char>){
//
//
//}
fun main(){
    val code = codeMaker()
    val choseColour = getColours()
    checkCode(code, choseColour)




}