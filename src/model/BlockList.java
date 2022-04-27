package model;

public class BlockList {
	
	//Attributes
	private BlockNode first,last;
	private int numSeeds;
	
	public BlockList() {
		numSeeds = 0;
	}

	public void add(int id) {
		if(first == null) {
			
			//To add the first element in the list
			first = new BlockNode(id);
			
			//To add the last element int the list
			last = first;
			
		}
		else {
			BlockNode temp = new BlockNode(id);
			temp.setPrevious(last);
			last.setNext(temp);
			last = temp;
			last.setNext(first);
			first.setPrevious(last);
		}
	}
	
	public void toLookForRickInTheBoard(int resultDice, int movement) {
		
		// movement == 0 --> Adelantar
		// movement == 1 --> Retroceder
		
		if(first == null) {
			return;
		}
		else {
			first.lookForRick(resultDice, movement);
		}
	}
	
	public void toLookForMortyInTheBoard(int resultDice,int movement)  {
		// movement == 0 --> Adelantar
		// movement == 1 --> Retroceder
				
		if(first == null) {
			return;
			}
			else {
				first.lookForMorty(resultDice, movement);
			}
	}
	
	public boolean toLookForABlockAndLocateSeed(int id, boolean seed) {
		if(first == null) {
			return false;
		}
		else {
			return first.locateSeed(id,seed);
		}
	}
	
	
	public boolean toLookForABlockAndLocatePlayer(int id, Player player) {
		
		if(first == null) {
			return false;
		}
		else {
			return first.locateAPlayer(id,player);
		}
	}
	
	public boolean toLookForAnBlockAndLocateLink(int id1,String linkId) {
		if(first == null) {
			return false;
		}
		else {
			return first.locateLinkId(id1,linkId);
		}
	}
	
	public void toUpdateNumSeeds() {
		if(first == null) {
			return;
		}
		else {
			//To get the new value of seeds
			int aux = first.upDateNumSeeds(first);
			setNumSeeds(aux); 
		}
	}
	
	
	public String toShowList(int numColumns) {
		if(first == null) {
			return "[]";
		}
		else {
			return "["+first.toString(first,numColumns,1)+"]";
		}
	}
	
	public String toShowListWithLinks(int numColumns) {
		if(first == null) {
			return "[]";
		}
		else {
			return "["+first.toStringLinks(first,numColumns,1)+"]";
		}
	}
	
	//
	// === GETTERS AND SETTERS ===
	// 
	
	public void setNumSeeds(int numSeeds) {
		this.numSeeds = numSeeds;
	}

	public int getNumSeeds() {
		return numSeeds;
	}
	
	
	
	
	
}
