package unsw.dungeon;

/**
 * State of which the dungeon is in.
 *
 */
public interface DungeonState {
	
	/**
     * Is the dungeon still being played or player has finished
     * whether completed or failed.
     * @return True if the dungeon has finished, false otherwise
     */
	public boolean endMode();
	/**
     * Changes the state of the dungeon to active.
     * @param dungeon
     */
	public void retry(Dungeon dungeon);
	/**
     * Changes the state of the dungeon to completed.
     * @param dungeon
     */
	public void complete(Dungeon dungeon);
	/**
     * Changes the state of the dungeon to failed.
     * @param dungeon
     */
	public void fail(Dungeon dungeon);
	
}
