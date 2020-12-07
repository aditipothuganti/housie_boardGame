# Welcome to the Housie Game wiki!
## Aim :
 `To build the Housie/Tambola Game, with any number of players, with each player having an individual ticket. 
## Features: 
* The numbers on the housie board can be specified by the user before starting the game.
* The ticket size ie, rows and columns on the ticket to can be varied based on the on users input. 

## Approach:
* The Project has been split into models and helper classes for the purpose of modularization. 
* The [Housie Board Class](https://github.com/aditipothuganti/housie_boardGame/blob/master/src/main/java/com/ss/boardgame/housie/model/HousieBoard.java) is initiated when the game begins, to generate the housie board with the range specified by the user.
* The [Ticket Number](https://github.com/aditipothuganti/housie_boardGame/blob/master/src/main/java/com/ss/boardgame/housie/model/TicketNumber.java) class is used to store the data of each number on the ticket with attributes named number and isCalled. 
* The [Ticket Class](https://github.com/aditipothuganti/housie_boardGame/blob/master/src/main/java/com/ss/boardgame/housie/model/Ticket.java) is used to build the entire ticket to which a player is assigned. The Ticket class has the number of rows, columns, player name, numbers marked as part of its attributes. All the Ticket Numbers in the ticket are unique and lie within the range specified by the user. 
* The[ Player Class](https://github.com/aditipothuganti/housie_boardGame/blob/master/src/main/java/com/ss/boardgame/housie/model/Player.java) is modeled to store the player name, the ticket associated the player.  
*  The caller/dealer class is synonymous to the controller of the application where it executes the appropriate methods on the model's data to the user prompts. The game generates values in a random and unique fashion from the numberRange specified by the user. The Housie Board consists of a list of lists of TicketNumbers which are marked as true when each number is generated.
* Each time a number is called all the tickets consisting of the number are marked at the appropriate cell index. 
* The game check for various [ winning combinations ](https://github.com/aditipothuganti/housie_boardGame/blob/master/src/main/java/com/ss/boardgame/housie/constants/WinningCombinations.java) like Early Five, Top Row, Full House to create a list of winners. 
* The Winning List consists of all the winners who have won a particular combination and are chosen based on the number called out. i.e.; If both Players 1 and Player 3 win Top Line Combination for a particular number then both as noted as winners for that combination. 
* Various criteria needs to be fulfilled for the combination of inputs to be considered correct. Validity of the input is checked and user is prompted to re-enter the values if they are found to be invalid.
* The Game is in Progress till the full house is done or until all the numbers are called.  
* The game can be exited by the user prompt Q to quit the game.
* We have also provided the functionality to view the tickets of players by entering the value P to display the status of the tickets once the game is in progress.

 
