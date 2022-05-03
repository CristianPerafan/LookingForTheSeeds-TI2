package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GameController {
	//Attributes
	@SuppressWarnings("unused")
	private int numColumns,numRows,numBlocks,numLinks;
	private BlockList list;
	private ArrayList<Player> listOfPlayers;
	private File file; 
	
	
	public GameController() {
		file = new File(".\\files\\players.txt");
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
	

	
	public void toChooseWinner(int secondsR, int secondsM) {
	
		//To calculate rick score
		Player rick = list.toGetFinalStateRick();
		
		Player morty = list.toGetFinalStateMorty();
		
		if(rick.getNumSeeds()>morty.getNumSeeds()) {
			//Rick is the winner
			int scoreWinner = toCalculateScore(secondsR,rick.getNumSeeds());
			
			//To add winner (Rick-P1) in the list
			addWinPlayer(scoreWinner,rick.getPlayerName());
			
			
		} //In this case there is tie
		else if(rick.getNumSeeds()==morty.getNumSeeds()) {
			if(secondsR>secondsM) {
				//Rick is the winner
				int scoreWinner = toCalculateScore(secondsR,rick.getNumSeeds());
				
				//To add winner (Rick-P1) in the list for time
				addWinPlayer(scoreWinner,rick.getPlayerName());
			}
			else {
				//Morty is the winner
				int scoreWinner = toCalculateScore(secondsM,morty.getNumSeeds());
				
				//To add winner (Morty-P2) in the list
				addWinPlayer(scoreWinner,morty.getPlayerName());
			}
		}
		else {
			//Morty is the winner
			int scoreWinner = toCalculateScore(secondsM,morty.getNumSeeds());
			
			//To add winner (Morty-P2) in the list
			addWinPlayer(scoreWinner,morty.getPlayerName());
		}
		
		
	}
	
	public void addWinPlayer(int score, String name) {
		Player newPlayer = new Player (score, name);
		listOfPlayers.add(newPlayer);
		System.out.println("se agrega, lol");
		
	}
	
	public int toCalculateScore(int seconds, int seeds) {
		
		return (seeds*120)-seconds; 
	}
	
	public void probarList() {
		Player p1 = new Player(180,"Cristian");
		Player p2 = new Player(200,"Felipe");
		Player p3 = new Player(500,"Carlos");
		listOfPlayers.add(p1);
		listOfPlayers.add(p2);
		listOfPlayers.add(p3);
		sortByName();
		
		for(int i = 0;i<listOfPlayers.size();i++) {
			System.out.println("i: "+i);
			System.out.println(listOfPlayers.get(i).toShowTopFive());
		}
		
	}
	
	public void sortWinPlayers() {
		int n = listOfPlayers.size()-1;
		boolean flag = true; 
		for (int i = 0; i < n && flag; i++) {
			System.out.println("n:"+n);
			flag = false;
	    	for (int j = 1; j < n - i; j++) {
	    		if (listOfPlayers.get(j).getScore()>listOfPlayers.get(j-1).getScore()) {
	    			Player temp = listOfPlayers.get(j);
	    			listOfPlayers.set(j, listOfPlayers.get(j-1));
	    	        listOfPlayers.set(j-1, temp);
	    			flag = true;
	    		}
	    	}
	    }
	}
	
	public void sortByName() {
		int n = listOfPlayers.size();
		boolean flag = true;
		
		for(int i = 0;i<n && flag;i++) {
			flag = false;
			for(int j = 1;j<n-i;j++) {
				if(listOfPlayers.get(j).compareByName(listOfPlayers.get(j-1))<0) {
					Player temp = listOfPlayers.get(j);
					listOfPlayers.set(j,listOfPlayers.get(j-1));
					listOfPlayers.set(j-1, temp);
					flag = true;
				}
			}
		}
		
	}
	
	public ArrayList<Player> getPlayers(){
		return listOfPlayers; 
	}
	public void serializar() throws IOException {
		//File file = new File(".\\files\\players.txt");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(getPlayers());
		
		oos.close();
		fos.close();	
	}
	
	public void deserializar() throws IOException, ClassNotFoundException {
		//File file = new File(".\\files\\players.txt");
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		ArrayList<Player> obj = (ArrayList<Player>)ois.readObject();
		listOfPlayers.addAll(obj);
		ois.close();
		fis.close();
	}
	
	public boolean validatePlayersFile() {
		
		if(file.length()==0) {
			return false; 
		}else {
			return true; 
		}
	}
	
	public String listTopPlayers() {
		
		String out = ""; 
		int j = 0; 
		for(int i =listOfPlayers.size()-1; i>=0;i--) {
			if(j==5) {
				break; 
			}else {
				out+=listOfPlayers.get(i).dataPlayerSave()+"\n"; 
				j++;
			}
		}
		
		return out; 
	}
}
