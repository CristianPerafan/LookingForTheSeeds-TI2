package model;

public class GameController {
	//Attributes
	@SuppressWarnings("unused")
	private int numColumns,numRows,numBlocks,numSeeds,numLinks;
	@SuppressWarnings("unused")
	private String playerR1, playerM2;
	private BlockList list;
	
	
	public GameController() {
		list = new BlockList();
	}
	
	public void toSetUpGameBoard(int numColumns, int numRows,int numSeeds, int numLinks,
			String playerR1, String playerM2) {
		
		this.numColumns = numColumns;
		this.numRows = numRows;
		
		numBlocks = numColumns*numRows;
		
		this.numSeeds = numSeeds;
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
	
	public String toShowGameBoard() {
		String out = "";
		out = list.toShowList(numColumns);
		return out;
	}
}
