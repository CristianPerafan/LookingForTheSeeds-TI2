package model;

import enums.CharacterType;

public class BlockNode {
	
	
	private BlockNode next,previous;
	private BlockInformation blockInfo;
	
	public BlockNode(int id) {
		blockInfo = new BlockInformation(id);
	}
	
	public void lookForRick(int resultDice) {
		
		if(blockInfo.getPlayer() != null) {
			if(blockInfo.getPlayer().getCharacter() == CharacterType.RICK) {
				Player temp = blockInfo.getPlayer();
				blockInfo.setPlayer(null);
				modifyPrevious(1,resultDice,temp);
				return;
			}
			
		}
		else if(blockInfo.getSecondPlayer() != null) {
			if(blockInfo.getSecondPlayer().getCharacter() == CharacterType.RICK) {
				Player temp = blockInfo.getSecondPlayer();
				blockInfo.setSecondPlayer(null);
				modifyPrevious(1,resultDice,temp);
				return;
			}
		}
		else {
			next.lookForRick(resultDice);
		}
	}
	
	public void modifyPrevious(int i,int resultDice, Player temp) {
		if(i == resultDice) {
			
			if(blockInfo.getPlayer() == null) {
				blockInfo.setPlayer(temp);
				
			}
			else {
				blockInfo.setSecondPlayer(temp);
			}
			
			return;
		}
		else {
			previous.modifyPrevious(i+1, resultDice,temp);
		}
		
	}
	
	
	
	public boolean locateSeed(int searchedId, boolean seed) {
		if(blockInfo.getId() == searchedId) {
			blockInfo.setSeed(seed);
			return true;
		}
		else if(next == null) {
			return false;
		}
		else {
			return next.locateSeed(searchedId,seed);
		}
	}
	
	public boolean locateLinkId(int searchedId,String linkId) {
		if(blockInfo.getId() == searchedId) {
			blockInfo.setLinkId(linkId);
			return true;
		}
		else if (next == null) {
			return false;
		}
		else {
			return next.locateLinkId(searchedId, linkId);
		}
	
		
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
	
	public String pruebaString(BlockNode first,int numColumns,int i,int counter) {
		String out = "";
		
		if(counter % 2 == 0) {
			
			if(next != first) {
				if(i == numColumns ) {				
					out += "["+next.pruebaString(first, numColumns, 1,counter+1)+blockInfo.getId();
					out += "]";
					out += "\n";
				}else {
					out += "] "+"["+next.pruebaString(first, numColumns, i+1, counter)+blockInfo.getId();
					
				}
			}
		}
		else {
			out += "Entra";
			out = ""+blockInfo.getId();
			if(next != first) {
				if(i == numColumns) {
					out += "]";
					out += "\n";
					
					out += "["+next.toString(first,numColumns,1,counter+1);
					
					
				}
				else {
					out += "]  "+"["+next.toString(first,numColumns,i+1,counter);
	
				}
			}
				
		}
		
		return out;
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
		else if(blockInfo.getSeed() == true) {
			out = "*";
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
