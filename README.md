
# Hangman
This project is the backend for the Hangman game.  

## 1- Compile Instructions
If you don't have Maven installed in your machine see maven install instructions [here](https://maven.apache.org/install.html).

With Maven properly installed open your terminal and go to the base project folder, ones that have pom.xml file, and run:
`mvn package`
After command complete run:
`mvn install`

After that the project should be successfully compiled.

To run the project go inside target folder and run:

`java -jar hangman-0.0.1-SNAPSHOT.jar`

## 2- Game instructions

### 2.1- Important
This project contains only the backend api of the hangman game, without user interface, to see the frontend project visit [Hangman UI](https://github.com/dante-judson/hangman-ui) repository on Github.

### 2.2- How API works.
With the project running to start the game you will need to make a `GET` request for `http://localhost:8080/word/random` and will receive a response like:

    {
	    "id": 10,
	    "length": 10,
	    "wrongLetters": null,
	    "word": null
    }
Where the `id` is the identification of your word and `length` is the how many letters you word have.

To make an attempt, do  `POST` request for `http://localhost:8080/word/{id}/attempt` where {id} is the given id in the previous response and the body is a character array like: `["e","a"]` where each one is an attempt.

For example: If your word is DELL and you make a attempt your response will be:

	{
    "id": 10,
    "length": 4,
    "wrongLetters": ["A"],
    "word": [ null, "E", null, null ]
	}
This meaning that you missed the letter A, because is no A in DELL, and you hit letter E and it is the second letter of your word.

When you made many attempts and missed more than 5 letters you will receive a `BAD REQUEST` status and body:
	
	{
	    "message": "Game Over",
	    "type": "GameOverException"
	}
	
That meaning you lost the game.
If your word came completely, you won the game.