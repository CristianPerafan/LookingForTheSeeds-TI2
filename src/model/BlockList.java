package model;

public class BlockList {
	
	//Attributes
	private BlockNode first,last;
	
	public BlockList() {
		
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
	
	public boolean toLookAnBlockAndLocatePlayer(int id, Player player) {
		
		if(first == null) {
			return false;
		}
		else {
			return first.locateAPlayer(id,player);
		}
	}
	
	public String toShowList(int numColumns) {
		if(first == null) {
			return "[]";
		}
		else {
			return "["+first.toString(first,numColumns,1,1)+"]";
		}
		
	}
	
	
}
