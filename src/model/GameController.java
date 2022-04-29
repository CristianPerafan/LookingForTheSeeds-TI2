package model;

import java.util.ArrayList;

public class GameController {
	//Attributes
	@SuppressWarnings("unused")
	private int numColumns,numRows,numBlocks,numLinks;
	private BlockList list;
	@SuppressWarnings("unused")
	private ArrayList<Player> listOfPlayers;

	
	
	public GameController() {
		list = new BlockList();
		listOfPlayers = new ArrayList<Player>();
	}
	
	public void toSetUpGameBoard(int numColumns, int numRows,int numSeeds, int numLinks,
			String playerR1, String playerM2) {
		
		this.numColumns = numColumns;
		this.numRows = numRows;
		
		numBlocks = numColumns*numRows;
		
		list.setNumSeeds(numSeeds);
		
		this.numLinks = numLinks;
	
		
		for(int i = 1;i<=numBlocks;i++) {
			list.add(i);
		}
		
	
		
		locateSeedInTheBoard(numSeeds);
		//To locate the players in a random position
		locateThePlayersInTheBoard(playerR1,playerM2);
		
		locateTheLinksInTheBoard(numLinks);
	}
	
	public void locateSeedInTheBoard(int numSeeds) {
		
		int[] randomNumbers = new int[numSeeds];
		
		randomNumbers[0] = (int)((Math.random()*numBlocks)+1);
		
		for(int i = 1;i<randomNumbers.length;i++) {
			randomNumbers[i] = (int)((Math.random()*numBlocks)+1);
			
			for(int j = 0;j<i;j++) {
				if(randomNumbers[i] == randomNumbers[j]) {
					i--;
				}
			}
		}
		
		for (int i = 0; i < randomNumbers.length; i++) {
			list.toLookForABlockAndLocateSeed(randomNumbers[i], true);
		}
		
		
	}
	
	public void locateTheLinksInTheBoard(int numLinks) {
		String [] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O",
				"P","Q","R","S","T","U","V","W","X","Y","Z"};
		
		int [] randomNumbers = new int[numLinks*2];
		
		//To assign the random values to the array for the linked pairs
		
		randomNumbers[0] = (int)((Math.random()*numBlocks)+1);
		
		
		//To create n random numbers and save them in the array
		for(int i = 1;i<randomNumbers.length;i++) {
			randomNumbers[i] = (int)((Math.random()*numBlocks)+1);
			
			for(int j = 0;j<i;j++) {
				if(randomNumbers[i] == randomNumbers[j]) {
					i--;
				}
			}
		}
		
		int idABC = 0;
		for(int j = 0;j<randomNumbers.length;j = j+2) {
			String linkId = alphabet[idABC];
			int id1 = randomNumbers[j];
			list.toLookForAnBlockAndLocateLink(id1,linkId);
			int id2 = randomNumbers[j+1];
			list.toLookForAnBlockAndLocateLink(id2,linkId);
			
			idABC++;
			  
		}
	}
	
	public void movePlayerInTheBoard(int numPlayer,int movement, int resultDice) {
		
		// movement == 0 --> Adelantar
		// movement == 1 --> Retroceder
		if(numPlayer == 0 ) {
			//To move to Rick in the board game
			list.toLookForRickInTheBoard(resultDice,movement);
			list.toUpdateNumSeeds();
		}
		else {
			//To move to Morty in the board game
			list.toLookForMortyInTheBoard(resultDice,movement);
			list.toUpdateNumSeeds();
		}
	}
	
	
	public void locateThePlayersInTheBoard(String playerR1, String playerM2) {
		
		
		//To generate a random numbers to locate the players
		int posRick = (int)((Math.random()*numBlocks)+1);
		Player rick = new Player(playerR1,0);
		list.toLookForABlockAndLocatePlayer(posRick,rick);
		
		int posMorty = (int)((Math.random()*numBlocks)+1);
		Player morty = new Player(playerM2,1);
		
		//If the positions are equal then
		while(posMorty == posRick ) {
			posMorty = (int)((Math.random()*numBlocks)+1);
		}
		list.toLookForABlockAndLocatePlayer(posMorty,morty);
	}
	
	public void toCalculateScore(int seconds, int seeds) {
		
	}
	
	public String toShowGameBoard() {
		String out = "";
		out = list.toShowList(numColumns);
		return out;
	}
	
	public String toShowGameBoardWithLinks() {
		String out  = "";
		out = list.toShowListWithLinks(numColumns);
		return out;
	}
	
	public String toShowScores() {
		return list.toShowScores();
	}
	
	
	public boolean verifyNumSeeds() {
		int ns = list.getNumSeeds();
		
		return (ns>0)?true:false;
	}
	
	public void addWinPlayer(int score, String name) {
		Player newPlayer = new Player (score, name);
		
		listOfPlayers.add(newPlayer);
		
	}
	
	public void sortWinPlayers() {
		int n = listOfPlayers.size();
		boolean flag = true; 
		for (int i = 0; i < n && flag; i++) {
			flag = false;
	    	for (int j = 1; j < n - i; j++) {
	    		if (listOfPlayers.get(j).getScore()>listOfPlayers.get(j+1).getScore()) {
	    			Player temp = listOfPlayers.get(j);
	    			listOfPlayers.set(j, listOfPlayers.get(j-1));
	    	        listOfPlayers.set(j-1, temp);
	    			flag = true;
	    		}
	    	}
	    }
	}
}
