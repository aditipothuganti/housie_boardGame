# Welcome to the Housie Game wiki!
## Aim :
 `To build the Indian Housie Game, with any number of players, with each player having an individual ticket. 
## Additional Features: 
* The numbers on the housie board can be specified by the user before starting the game.
* The ticket size ie, rows and columns on the ticket to can be varied based on the on users input. 

## Approach:
* The Project has been split into Models and helper classes for easy navigation. 
* The housie board is a singleton class which 
 is initialized at the beginning of the game. 
* The [Ticket Number](https://github.com/aditipothuganti/housie_boardGame/blob/master/src/main/java/com/ss/boardgame/housie/model/TicketNumber.java) class has to Modeled to store the data of each number on the ticket with attributes named number and isCalled. 
* The [Ticket Class](https://github.com/aditipothuganti/housie_boardGame/blob/master/src/main/java/com/ss/boardgame/housie/model/Ticket.java) is List<List<TicketNumber>> used to build the entire ticket which a player is assigned. The Ticket class has the number of rows, columns, player name, number marked, as part of its attributes. All the Ticket Numbers in the ticket are unique and lie within the range specified by the user. 
* The[ Player Class](https://github.com/aditipothuganti/housie_boardGame/blob/master/src/main/java/com/ss/boardgame/housie/model/Player.java) is modeled to store the player name, the ticket associated the player. 
* The [Housie Board Class](https://github.com/aditipothuganti/housie_boardGame/blob/master/src/main/java/com/ss/boardgame/housie/model/HousieBoard.java) is the singleton object initiated when the game begins, to generate the housie board with the range specified by the user. 
* The Housie Board is <List<List<TicketNumber>> which are marked as true when each number is generated. To make sure the game generates the values in a random and unique manner. 
* Each time a number is called all the tickets which have the number are marked off with the number. 
* The game check for various [ winning combinations ](https://github.com/aditipothuganti/housie_boardGame/blob/master/src/main/java/com/ss/boardgame/housie/constants/WinningCombinations.java) like Early Five, Top Row, Full House to create a list of winners. 
* The System makes sure to contain all the winners who have won a particular combination. i.e.; If both Players 1 and Player 3 win Top Line Combination for a particular number then both as noted as winners for that combination. 
* The Game is in Progress till the full house is done or until all the numbers are called.  

 
