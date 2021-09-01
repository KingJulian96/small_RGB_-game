package unsw.dungeon;

/**
 * Determines the movement strategy of the enemy
 *
 */
public interface EnemyMovement {
	/**
     * Moves the enemy towards or away from the player
     * based on the behaviour defined.
     */
	public void move();
}
