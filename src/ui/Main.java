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
		
		@SuppressWarnings("unused")
		
		int columns,rows,seed,numLinks;
		
		System.out.println("Enter the number of columns: ");
		columns = sc.nextInt();
		
		System.out.println("Enter the number of rows: ");
		rows = sc.nextInt();
		
		System.out.println("Enter the number of seeds: ");
		seed = sc.nextInt();
		
		System.out.println("Enter the number of game links: ");
		
		boolean stop = false;
		
		while(stop == false) {
			
			numLinks = sc.nextInt();
			
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
		
		@SuppressWarnings("unused")
		String rickPlayer,mortyPlayer;
		
		System.out.println("Enter the player name of who is goint to play as Rick: ");
		rickPlayer = sc.nextLine();
		
		System.out.println("Enter the player name of who is goint to play as Rick: ");
		mortyPlayer = sc.nextLine();
		
	}

}
