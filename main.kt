
enum class Colours{
    BLUE, GREEN, YELLOW, RED, PURPLE, ORANGE
}

//Ask the player to choose the colours wanted to test.
fun getColours(): MutableList<Colours>{
    val chosenColour = mutableListOf<Colours>()

    println("Colours to choose from: Blue, Green, Yellow, Red, Purple, Orange")
    while(chosenColour.size < 4){
        print("Please enter colour for #${chosenColour.size +1}: ")
        val choice = readln()
        if(Colours.values().map { it.name}.contains(choice.uppercase())) {
            when (choice) {
                "blue" -> chosenColour.add(Colours.BLUE)
                "green" -> chosenColour.add(Colours.GREEN)
                "yellow" -> chosenColour.add(Colours.YELLOW)
                "red" -> chosenColour.add(Colours.RED)
                "purple" -> chosenColour.add(Colours.PURPLE)
                "orange" -> chosenColour.add(Colours.ORANGE)
            }
        }
        else println("$choice is not an option. Please choose again.")
    }
    return chosenColour
}

//Make the code to guess.
fun codeMaker(): MutableList<Colours>{
    val code = mutableListOf<Colours>()
    for(colour in 1..4){
        code.add(Colours.values().random())
    }
    code.forEach(::println)
    return code
}

fun checkCode(code : MutableList<Colours>, guess : MutableList<Colours>){
    if(code == guess){
        println("Congratulations. You won!")
    }
    else{
        println("WRONG!!!!!!!!!!!!")
        guess.forEach {

        }
    }
}
fun main(){

    val code = codeMaker()
    val choseColour = getColours()
    checkCode(code, choseColour)





}