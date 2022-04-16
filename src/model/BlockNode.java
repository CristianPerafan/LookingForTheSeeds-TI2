package model;

public class BlockNode {
	
	//Attributes 
	@SuppressWarnings("unused")
	private Player player;
	@SuppressWarnings("unused")
	private int id;
	@SuppressWarnings("unused")
	private boolean seed;
	@SuppressWarnings("unused")
	private BlockNode next,previous;
	
	public BlockNode(int id) {
		this.id = id;
	}
	
	//
	// == GETTERS AND SETTERS==
	//
	public void setNext(BlockNode next) {
		this.next = next;
	}
	public void setPrevious(BlockNode previous) {
		this.previous = previous;
	}
	
	public String toString(int numColumns,int i) {
		int aux = id;
		int n = 0;
		while(aux != 0) {
			aux = aux/10;
			n++;
		}
		
		String out = id+"";
		
		if(next != null) {
			if(i == numColumns) {
				out += "]";
				out += "\n";
				out += "["+next.toString(numColumns,1);
			}
			else {
				if(n>1) {
					out += "]  "+"["+next.toString(numColumns,i+1);
				}
				else {
					out += "]  "+" ["+next.toString(numColumns,i+1);
				}
				
			}
			
		}
		
		return out;
	}
}
