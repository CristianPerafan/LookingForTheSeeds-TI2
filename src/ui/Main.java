package ui;

import java.util.Scanner;

import model.GameController;

public class Main {
	
	//Attributes
	private static Scanner sc;
	private GameController gController;
	
	public Main() {
		sc = new Scanner(System.in);
		gController = new GameController();
	}

	public static void main(String[] args) {
		
		Main pc = new Main();
		
		int columns,rows,seeds;
		int numLinks = 0;
		
		System.out.println("**************************************************************");
		System.out.println("*                                                            *");
		System.out.println("*             Welcome to Looking for the seeds               *");
		System.out.println("*                                                            *");
		System.out.println("**************************************************************");
		

		
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
		
		//pc.toShowGameBoard();
		
		pc.toStartGame();
		//Acá deberian ir los métodos de menu y jugabilidad.
		
	}
	
	public void toCreateGameBoard(int columns, int rows, int seeds, int numLinks,
			String rickPlayer, String mortyPlayer) {
		
		gController.toSetUpGameBoard(columns,rows,seeds,numLinks,rickPlayer,mortyPlayer);
		
	}
	
	public void toShowGameBoard() {
		System.out.println(gController.toShowGameBoard());
	}

	public void toStartGame() {
		boolean stopGame = false;
	
	
		while(stopGame == false) {
			//Rick plays
			int optionR = menuGameOptions("Rick");
			executeGameOptions(optionR,0);
			
			if(gController.verifyNumSeeds()) {
				//MortyPlays
				int optionM = menuGameOptions("Morty");
				executeGameOptions(optionM,1);
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
			System.out.println("Hola 2");
			break;
		case 3:
			System.out.println("Hola 3");
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
		System.out.println("The result of rolling the die is "+resultDice);
	}
	
	public int generateRooolDiceResult() {
		return (int)((Math.random()*6)+1);
	}
	
	
	
	
}
