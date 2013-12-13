Scrabble Solver
===============

This was for a final project at Iowa State University (CPRE388)

Shown below are the requirements for the project:
-------------------------------------------------
Create a utility app that will help people cheat when playing Scrabble. 

Requirements: 

	-(30 points) A set of four activities: the scrabble board, a list of word plays, an anagram solver and a preferences screen. 
	-(30 points) The anagram solver activity allows the user to enter 7 letters (including blank tiles), and will find words that contain those letters from a dictionary. 
	-(30 points) The scrabble board activity allows the user to input their 7 letters, plus the contents of the board. 
	-(20 points) The board can be solved using a small dictionary in a reasonable amount of time, with the results listed on the 2nd activity. When selecting a result, the word is shown on the scrabble board. 
	-(40 points) The preferences activity will allow the user to choose from a few dictionaries (large and small) as well as switch between the original Scrabble board and the Words with Friends board. 

Optional Features: 

	-Use a DAWG (directed acyclic word graph) for storing the dictionaries. 
	-Solve a complex board using a 100,000+ dictionary (TWL) in less than 1 second. 
	
Instructions for using the app:
-------------------------------

	-Main Board Acitiviy - The actionbar in the main board activity has 4 different options
		-SOLVE: solves the anagram with its input being the tray box on the bottom of the screen.
		-SCORE: updates the total score of the board. This is displayed above the tray box.
		-CLEAR: resets the board and tray.
		-SETTINGS: allow the user to change board styles (words with friends or scrabble) and dictionary types (OPSD-scrabble or ENABLE-wordswithfriends)
	-Anagram Solver Activity
		-You can choose to sort words by word length or word score. The word score will change depending on the board layout (scrabble or wordswithfriends)

Information:
------------

	-Dictionaries are stored in DAWG format making them very fast and lightweight
	-The board is a scrollable layout with a editText table
	-To use a blank tile in the tray, use a '?'
	
DAWG solution derived from: http://pages.pathcom.com/~vadco/dawg.html
