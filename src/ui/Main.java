package ui;

import java.util.Scanner;

import model.GameController;

public class Main {
	
	//Attributes
	private static Scanner sc;
	private GameController gController;
	private boolean passTurn;
	
	public Main() {
		passTurn = false;
		sc = new Scanner(System.in);
		gController = new GameController();
	}

	public static void main(String[] args) {
		
		Main pc = new Main();
		
		int columns,rows,seeds;
		int numLinks = 0;
		

		System.out.println("  ___   _        _                        _ \r\n"
				+ " | _ \\ (_)  __  | |__    __ _   _ _    __| |\r\n"
				+ " |   / | | / _| | / /   / _` | | ' \\  / _` |\r\n"
				+ " |_|_\\ |_| \\__| |_\\_\\   \\__,_| |_||_| \\__,_|");
		System.out.println("  __  __               _          \r\n"
				+ " |  \\/  |  ___   _ _  | |_   _  _ \r\n"
				+ " | |\\/| | / _ \\ | '_| |  _| | || |\r\n"
				+ " |_|  |_| \\___/ |_|    \\__|  \\_, |\r\n"
				+ "                             |__/ ");
		
		System.out.println("Enter the number of columns: ");
		//columns = sc.nextInt();
		columns = 4;
		
		System.out.println("Enter the number of rows: ");
		//rows = sc.nextInt();
		rows = 4;
		
		System.out.println("Enter the number of seeds: ");
		//seeds = sc.nextInt();
		seeds = 6;
		
		System.out.println("Enter the number of game links: ");
		boolean stop = false;
		
		while(stop == false) {
			
			//numLinks = sc.nextInt();
			numLinks = 3;
			
			double aux = 0.5*(columns*rows);
			if(numLinks<aux) {
				stop = true;
			}
			else {
				System.out.println("You must enter a number less than "+aux);
			}
			
		}
		
		//
		sc.nextLine();
		//
		
		String rickPlayer,mortyPlayer;
		
		System.out.println("Enter the player name of who is goint to play as Rick: ");
		//rickPlayer = sc.nextLine();
		rickPlayer = "C";
		
		System.out.println("Enter the player name of who is goint to play as Rick: ");
		//mortyPlayer = sc.nextLine();
		mortyPlayer = "F";
		
		pc.toCreateGameBoard(columns, rows, seeds, numLinks, rickPlayer, mortyPlayer);
		
		pc.toShowGameBoard();
		
		pc.toStartGame();
		//Acá deberian ir los métodos de menu y jugabilidad.
		
	}
	
	public void toCreateGameBoard(int columns, int rows, int seeds, int numLinks,
			String rickPlayer, String mortyPlayer) {
		
		gController.toSetUpGameBoard(columns,rows,seeds,numLinks,rickPlayer,mortyPlayer);
		
	}
	

	public void toStartGame() {
		boolean stopGame = false;
	
	
		while(stopGame == false) {
			
			
			
			while(passTurn == false) {
				//Rick plays
				int optionR = menuGameOptions("Rick");
				executeGameOptions(optionR,0);
			}
			
			passTurn = false;
			
			
			if(gController.verifyNumSeeds()) {
				
				while(passTurn == false) {
					//MortyPlays
					int optionM = menuGameOptions("Morty");
					executeGameOptions(optionM,1);
				}
				passTurn = false;
				
				if(!gController.verifyNumSeeds()) {
					stopGame = true;
					
				}
			}
			else {
				stopGame = true;
			}
			
			
		}
		
		System.out.println();
	}


	public int menuGameOptions(String player) {
		int option=0;
		System.out.println("What do you want to do "+player+"?"+"\n"+
							"(1) Rool dice\n"+
							"(2) See board\n"+
							"(3) See links\n"+
							"(4) Score board\n");
		do {
			option = sc.nextInt(); 
		}while(option<=0 || option>4); 
		return option; 
	}
	
	public void executeGameOptions(int option, int numPlayer) {
		switch(option) {
		case 1: 
			roolDice(numPlayer);
			break;
		case 2:
			toShowGameBoard();
			break;
		case 3:
			toShowGameBoardWithLinks();
			break;
		case 4: 
			System.out.println("Hola 4");
			break; 
		default:
			System.out.println("No valid option!!!");
			break;
		}
	}
	
	public void roolDice(int numPlayer) {
		int resultDice = generateRooolDiceResult();
	
		System.out.println("");
		System.out.println("The result of rolling the die is: "+resultDice);
		System.out.println("");
		System.out.println("What do you want to do?\n"+
				"(1) Move back\n"+
				"(2) Move along");
		
		int answer = sc.nextInt();
		while(answer != 1 && answer != 2){
			System.out.println("No valid option!!!");
			answer = sc.nextInt();
		}
		sc.nextLine();
		
		movePlayer(numPlayer,answer,resultDice);
		passTurn = true;
		
		
	}
	
	public int generateRooolDiceResult() {
		return (int)((Math.random()*6)+1);
	}
	
	public void movePlayer(int numPlayer,int answer, int resultDice) {
		if(numPlayer == 0) {
			//To move Rick
			
			if(answer == 1) {
				 //When the player wants to move back.
				gController.movePlayerInTheBoard(numPlayer,1,resultDice);
			}
			else {
				//When the player wants to move along.
				gController.movePlayerInTheBoard(numPlayer,0,resultDice);
			}
			
		}else {
			//To move Morty
			if(answer == 1) {
				//When the player wants to move back.
				gController.movePlayerInTheBoard(numPlayer, 1, resultDice);
			}
			else {
				//When the player wants to move along.
				gController.movePlayerInTheBoard(numPlayer, 0, resultDice);
			}
		}
		
	}
	
	public void toShowGameBoard() {
		System.out.println(gController.toShowGameBoard());
	}
	
	public void toShowGameBoardWithLinks() {
		System.out.println(gController.toShowGameBoardWithLinks());
	}
	
	public void toShowScores() {
		System.out.println();
	}
	
	public void toSerialize() {
		
	}
	
	public void toDeserialize() {
		
	}

	
	
}
