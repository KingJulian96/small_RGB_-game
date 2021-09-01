package unsw.dungeon;

/**
 * States an entity as usable by a player.
 *
 */
public interface Usable {

	/**
     * Uses the item
     */
	public void useItem();
	/**
     * Is the player allowed to pick the item up
     * @return True if the player is able to pick the item up, false otherwise.
     */
	public boolean pickUpItem(Player player);
	/**
     * Drops the item from the player's inventory.
     */
	public void dropItem();
	/**
     * Destroys the item from the dungeon.
     */
	public void destroyItem();

	
}
