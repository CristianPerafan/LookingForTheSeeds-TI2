package model;

public class BlockInformation {
		//Attributes 
	    private int id;
		private Player player;
		@SuppressWarnings("unused")
		private String linkId;
	

		@SuppressWarnings("unused")
		private boolean seed;
		
		public BlockInformation(int id) {
			this.id = id;
		}
		
		//
		// === GETTERS AND SETTERS ===
		//
		
		public int getId() {
			return id;
		}
		
		public Player getPlayer() {
			return player;
		}
		
		public void setPlayer(Player player) {
			this.player = player;
		}
		
		public String getLinkId() {
			return linkId;
		}
		
		public void setLinkId(String linkId) {
			this.linkId = linkId;
		}
}
