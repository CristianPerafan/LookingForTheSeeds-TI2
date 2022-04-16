package model;

public class GameController {
	//Attributes
	@SuppressWarnings("unused")
	private int numColumns,numRows,numBlocks,numSeeds,numLinks;
	@SuppressWarnings("unused")
	private String playerR1, playerM2;
	private BlockList list;
	
	
	public GameController() {
		list = new BlockList();
	}
	
	public void toSetUpGameBoard(int numColumns, int numRows,int numSeeds, int numLinks,
			String playerR1, String playerM2) {
		
		this.numColumns = numColumns;
		this.numRows = numRows;
		
		numBlocks = numColumns*numRows;
		
		this.numSeeds = numSeeds;
		this.numLinks = numLinks;
		
		for(int i = 1;i<=numBlocks;i++) {
			list.add(i);
		}
		
	}
	
	public String toShowGameBoard() {
		String out = "";
		out = list.toShowList(numColumns);
		return out;
	}
}
