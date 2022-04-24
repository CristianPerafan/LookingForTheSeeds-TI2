package model;

import enums.CharacterType;

public class Player {
	
	//Attributes
	@SuppressWarnings("unused")
	private String playerName;
	@SuppressWarnings("unused")
	private int score;
	private CharacterType character;
	
	
	public Player(String playerName, int idCharacterType) {
		
		this.playerName = playerName;
		
		//To assign a character type enum
		if(idCharacterType == 0) {
			character = CharacterType.RICK;
		}
		else if(idCharacterType == 1) {
			character = CharacterType.MORTY;
		}
		
		score = 0;
	}
	
	//
	// === GETTERS AND SETTERS
	//
	
	public CharacterType getCharacter() {
		return character;
	}

	public void setCharacter(CharacterType character) {
		this.character = character;
	}
	
	
	public String toString() {
		String out = "";
		
		if(character == CharacterType.RICK) {
			out = "R";
		}
		else if(character == CharacterType.MORTY) {
			out = "M";
		}
		
		return out;
	}

	
	
	
}
