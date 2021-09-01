package unsw.dungeon;

/**
 * The sword entity
 *
 */
public class Sword extends Entity implements Usable {

	private Player player;
	private int uses;
	
	/**
     * Create a sword positioned in square (x,y)
     * initialised with 5 uses
     * @param x
     * @param y
     */
	public Sword(int x, int y) {
		super(x, y);
		this.uses = 5;
		this.player = null;
	}
	
	/**
     * Deplenishes the use of the sword by 1
     * and destroys the sword when all 5 uses are up.
     */
	@Override
	public void useItem() {
		uses -= 1;
		if (uses == 0) {
			destroyItem();
		}
	}
	
	/**
     * Player can only hold one sword.
     * Checks if the player can pick the sword up.
     * @param player
     * @return True if the player can pick up the sword, false otherwise.
     */
	@Override
	public boolean pickUpItem(Player player) {
		if(player.getSword() == null) {
			player.setSword(this);
			this.player = player;
			return true;
		} else {
			return false;
		}
	}
	
	public int use() {
		return this.uses;
	}

	@Override
	public void dropItem() {
		destroyItem();
	}

	@Override
	public void destroyItem() {
		player.setSword(null);
		player = null;
	}

}
