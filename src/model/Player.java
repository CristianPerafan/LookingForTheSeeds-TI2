package model;

import java.io.Serializable;

import enums.CharacterType;

@SuppressWarnings("serial")
public class Player implements Serializable{
	
	//Attributes
	private String playerName;
	private int numSeeds;
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
		
		numSeeds = 0;
		score = 0;
	}
	
	public Player (int score, String playerName) {
		this.playerName = playerName;
		this.score = score; 
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
	
	public void increaseSeeds() {
		numSeeds++;
	}
	
	public String toShowScore() {
		String out = "";
		
		if(character == CharacterType.RICK) {
			out = "Rick: "+numSeeds + " seeds";
		}
		else if(character == CharacterType.MORTY) {
			out = "Morty: "+numSeeds + " seeds";
		}
		return out;
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
	
	
	public String toShowTopFive() {
		return playerName+" --- "+score;
	}

	public String dataPlayerSave() {
		return playerName + "     -     " + score; 
	}
	
	public int compareByScore(Player other) {
		int out = 0;
		
		if(this.getScore()>other.getScore()) {
			out = 1;
		}
		else if(this.getScore()<other.getScore()) {
			out = -1;
		}
		
		return out;
	}
	
	public int compareByName(Player other) {
		int out = 0;
		if(this.getPlayerName().compareTo(other.getPlayerName())>0) {
			out = 1;
		}
		else if(this.getPlayerName().compareTo(other.getPlayerName())<0) {
			out = -1;
		}
		
		return out;
	}
	//
	// === GETTERS AND SETTERS
	//
	
	

	public String getPlayerName() {
		return playerName;
	}

	public int getNumSeeds() {
		return numSeeds;
	}

	public void setNumSeeds(int numSeeds) {
		this.numSeeds = numSeeds;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	
	
	
}
