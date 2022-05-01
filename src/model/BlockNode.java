package model;

import enums.CharacterType;

public class BlockNode {
	
	
	private BlockNode next,previous;
	private BlockInformation blockInfo;
	
	public BlockNode(int id) {
		blockInfo = new BlockInformation(id);
	}
	
	public Player lookForRickScore() {
		
		if(blockInfo.getPlayer() != null) {
			if(blockInfo.getPlayer().getCharacter() == CharacterType.RICK) {
				Player temp = blockInfo.getPlayer();	
				return temp;
			}
			else {
				//Acá se hace agregación de cambio
				if(blockInfo.getSecondPlayer() != null){
			        if(blockInfo.getSecondPlayer().getCharacter() == CharacterType.RICK){
			        	Player temp = blockInfo.getSecondPlayer();
			        	return temp;
			        }
			        else{
			             return next.lookForRickScore();
			       }
				}else {
					return next.lookForRickScore(); //Agrege esto acá porque el método manda error.
				}
				
			}
		}
		else if(blockInfo.getSecondPlayer() != null) {
			if(blockInfo.getSecondPlayer().getCharacter() == CharacterType.RICK) {
				Player temp = blockInfo.getSecondPlayer();
				return temp;
			}
			else {
				return next.lookForRickScore();
			}
		}
		else {
			
			return next.lookForRickScore();
		}
		
		
	}
	
	public Player lookForMortyScore() {
		
		if(blockInfo.getPlayer() != null) {
			if(blockInfo.getPlayer().getCharacter() == CharacterType.MORTY) {
				Player temp = blockInfo.getPlayer();	
				return temp;
			}
			else {
				//Acá se hace agregación de cambio
				if(blockInfo.getSecondPlayer() != null){
			        if(blockInfo.getSecondPlayer().getCharacter() == CharacterType.MORTY){
			        	Player temp = blockInfo.getSecondPlayer();
			        	return temp;
			        }
			        else{
			             return next.lookForMortyScore();
			       }
				}else {
					return next.lookForMortyScore(); //Agrege esto acá porque el método manda error.
				}
				
			}
		}
		else if(blockInfo.getSecondPlayer() != null) {
			if(blockInfo.getSecondPlayer().getCharacter() == CharacterType.MORTY) {
				Player temp = blockInfo.getSecondPlayer();
				return temp;
			}
			else {
				return next.lookForMortyScore();
			}
		}
		else {
			
			return next.lookForMortyScore();
		}
		
	}
	
	public void lookForRick(int resultDice, int movement) {
		// movement == 0 --> Adelantar
		// movement == 1 --> Retroceder
		
		if(blockInfo.getPlayer() != null) {
			if(blockInfo.getPlayer().getCharacter() == CharacterType.RICK) {
				Player temp = blockInfo.getPlayer();
				blockInfo.setPlayer(null);
				
				if(movement == 0) {
					//Adelantar Rick
					modifyNext(0,resultDice,temp);
				}
				else {
					//Retroceder Rick
					modifyPrev(0,resultDice,temp);
				}
				
				
				return;
			}
			else {
				next.lookForRick(resultDice,movement);
			}
			
		}
		else if(blockInfo.getSecondPlayer() != null) {
			
			if(blockInfo.getSecondPlayer().getCharacter() == CharacterType.RICK) {
				Player temp = blockInfo.getSecondPlayer();
				blockInfo.setSecondPlayer(null);
				modifyNext(1,resultDice,temp);
				return;
			}
			else {
				next.lookForRick(resultDice,movement);
			}
		}
		else {
			next.lookForRick(resultDice, movement);
		}
	}
	
	public void lookForMorty(int resultDice, int movement) {
		// movement == 0 --> Adelantar
		// movement == 1 --> Retroceder
				
				if(blockInfo.getPlayer() != null) {
					if(blockInfo.getPlayer().getCharacter() == CharacterType.MORTY) {
						Player temp = blockInfo.getPlayer();
						blockInfo.setPlayer(null);
						
						if(movement == 0) {
							//Adelantar Rick
							modifyNext(0,resultDice,temp);
						}
						else {
							//Retroceder Rick
							modifyPrev(0,resultDice,temp);
						}
						
						
						return;
					}
					else {
						next.lookForMorty(resultDice,movement);
					}
					
				}
				else if(blockInfo.getSecondPlayer() != null) {
					
					if(blockInfo.getSecondPlayer().getCharacter() == CharacterType.MORTY) {
						Player temp = blockInfo.getSecondPlayer();
						blockInfo.setSecondPlayer(null);
						modifyNext(1,resultDice,temp);
						return;
					}
					else {
						next.lookForMorty(resultDice,movement);
					}
				}
				else {
					next.lookForMorty(resultDice, movement);
				}
	}
	

	
	public void modifyNext(int i,int resultDice, Player temp) {
		if(i == resultDice) {
			
			if(blockInfo.getSeed() == true) {
				temp.increaseSeeds();
				blockInfo.setSeed(false);
			}
			
			if(blockInfo.getLinkId() != null) {
				System.out.println("Entra link next");
				System.out.println("IdLink "+ blockInfo.getLinkId());
				String idLink = blockInfo.getLinkId();
				next.modifyLink(idLink,temp);
			}
			else {
				
				if(blockInfo.getPlayer() == null) {
					
					blockInfo.setPlayer(temp);
				}
				else {
					blockInfo.setSecondPlayer(temp);
				}
				
			}
			
			
			
		}
		else {
			next.modifyNext(i = i+1,resultDice,temp);
		}
		
	}
	
	
	
	public void modifyPrev(int i, int resultDice,Player temp) {
		if(i == resultDice) {
			
			if(blockInfo.getSeed() == true) {
				temp.increaseSeeds();
				blockInfo.setSeed(false);
			}
			
			if(blockInfo.getLinkId() != null) {
				System.out.println("Entra link prev");
				System.out.println("IdLink "+ blockInfo.getLinkId());
				String idLink = blockInfo.getLinkId();
				next.modifyLink(idLink,temp);
			}
			else {
				if(blockInfo.getPlayer() == null) {
					blockInfo.setPlayer(temp);
				}
				else {
					blockInfo.setSecondPlayer(temp);
				}
			}
			
			
		}
		else {
			previous.modifyPrev(i = i+1, resultDice, temp);
		}
	}
	
	public void modifyLink(String idLink, Player temp) {
		
		if(blockInfo.getLinkId() != null) {
			if(blockInfo.getLinkId().equals(idLink)) {
				System.out.println("Enceuntra pareja");
				
				if(blockInfo.getSeed() == true) {
					temp.increaseSeeds();
					blockInfo.setSeed(false);
				}
				
				if(blockInfo.getPlayer() == null) {
					
					blockInfo.setPlayer(temp);
				}
				else {
					blockInfo.setSecondPlayer(temp);
				}
				
			}
			else {
				System.out.println("Busca siguiente");
				next.modifyLink(idLink, temp);
			}
		}
		else {
			System.out.println("Busca siguiente");
			next.modifyLink(idLink, temp);
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
	
	public int upDateNumSeeds(BlockNode first) {
		
		int out=0;
		
		if(blockInfo.getSeed() == true) {
			out++;
		}
		
		if(next != first) {
			out += next.upDateNumSeeds(first);
		}
		
		return out;
	}
	
	
	public String toStringLinks(BlockNode first,int numColumns,int i) {
		String out = "";
		
		if(blockInfo.getLinkId() != null) {
			out = blockInfo.getLinkId();
		}
		else {
			out = " ";
		}
		
		if(next != first) {
			if(i == numColumns) {
				out += "]";
				out += "\n";
				out += "["+next.toStringLinks(first,numColumns,1);
			}
			else {
				out += "]  "+" ["+next.toStringLinks(first,numColumns,i+1);
			}
		}
		
		return out;
	}
	
	public String toString(BlockNode first,int numColumns,int i) {
		
		String out  = "";
		
	
		if(blockInfo.getPlayer() != null) {
			if(blockInfo.getSecondPlayer() != null) {
				out = blockInfo.getPlayer().toString()+ blockInfo.getSecondPlayer().toString();
			}else {
				out = blockInfo.getPlayer().toString();
			}
			
		}
		else if(blockInfo.getSecondPlayer() != null) {
			out = blockInfo.getSecondPlayer().toString();
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
				out += "["+next.toString(first,numColumns,1);
			}
			else {
				out += "]  "+" ["+next.toString(first,numColumns,i+1);
			}
			
		}
		
		return out;
	}


}
