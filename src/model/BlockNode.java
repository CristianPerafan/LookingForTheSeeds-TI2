package model;

public class BlockNode {
	
	
	@SuppressWarnings("unused")
	private BlockNode next,previous;
	@SuppressWarnings("unused")
	private BlockInformation blockInfo;
	
	public BlockNode(int id) {
		blockInfo = new BlockInformation(id);
	}
	
	public boolean locateLinkId(int searchedId, String LinkId) {
		return false;
	}
	
	public boolean locateAPlayer(int searchedId,Player player) {
		
		if(blockInfo.getId() == searchedId) {
			blockInfo.setPlayer(player);
			return true;
		}
		else if(next == null){
			return false;
		}
		else {
			return next.locateAPlayer(searchedId,player); 
		}
		
	}
	
	//
	// === GETTERS Y SETTERS
	//
	
	public BlockNode getNext() {
		return next;
	}

	public void setNext(BlockNode next) {
		this.next = next;
	}

	public BlockNode getPrevious() {
		return previous;
	}

	public void setPrevious(BlockNode previous) {
		this.previous = previous;
	}
	

	
	public String toString(BlockNode first,int numColumns,int i, int counter) {
		
		String out  = "";
		
		//To calculate the number digits of id 
		int aux = blockInfo.getId();
		int n = 0;
		
		while(aux != 0) {
			aux = aux/10;
			n++;
		}
		
		
		if(blockInfo.getPlayer() != null) {
			out = blockInfo.getPlayer().toString();
		}
		else {
			out = blockInfo.getId()+"";
		}
		
		if(next != first) {
			if(i == numColumns) {
				out += "]";
				out += "\n";
				
				out += "["+next.toString(first,numColumns,1,counter+1);
				
				
			}
			else {
				
				//If the id has more of 1 digits
			
				if(n>1) {
					out += "]  "+"["+next.toString(first,numColumns,i+1,counter);
				}
				else {
					out += "]  "+" ["+next.toString(first,numColumns,i+1,counter);
				}
						
				
			}
			
		}
		
	
		
		return out;
	}


}
