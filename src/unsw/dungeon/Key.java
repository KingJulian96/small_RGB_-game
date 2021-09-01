package unsw.dungeon;

/**
 * The key entity
 *
 */
public class Key extends Entity implements Usable {

	private Player player;
	private int id;
	
	/**
     * Create a key positioned in square (x,y) with an id
     * @param id
     * @param x
     * @param y
     */
	public Key(int id, int x, int y) {
		super(x, y);
		this.id = id;
		this.player = null;
	}
	
	public int getID() {
		return id;
	}

	@Override
	public void useItem() {
	}
	
	/**
     * Player can only hold one key.
     * Checks if the player can pick the key up.
     * @param player
     * @return True if the player can pick up the key, false otherwise.
     */
	@Override
	public boolean pickUpItem(Player player) {
		if(player.getKey() == null) {
			player.setKey(this);
			this.player = player;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void dropItem() {
		player.dropKey();
		destroyItem();
	}

	@Override
	public void destroyItem() {
		player.setKey(null);
		player = null;
	}
	
	@Override
    public boolean equals(Object obj) {
		
		if (obj == this) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (obj.getClass() == this.getClass()) {
			Key key = (Key) obj;
			
			if (key.getX() == this.getX() && key.getY() == this.getY()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}


}
