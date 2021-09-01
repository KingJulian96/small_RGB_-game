package unsw.dungeon;

/**
 * Enemy behaviour defined for a scared enemy
 * making them walk away from the player.
 *
 */

public class ScaredMovement implements EnemyMovement {

	private Dungeon dungeon;
	private Enemy enemy;
	private Player player;
	
	/**
     * Create a scared behaviour for the enemy from a player
     * in a dungeon
     * @param dungeon
     * @param enemy
     * @param player
     */
	public ScaredMovement(Dungeon dungeon, Enemy enemy, Player player) {
		this.dungeon = dungeon;
		this.enemy = enemy;
		this.player = player;
	}

	@Override
	public void move() {
		int closeX = enemy.getX() - player.getX();
    	int closeY = enemy.getY() - player.getY();
    	if (java.lang.Math.abs(closeX) <= java.lang.Math.abs(closeY) && closeX > 0 && dungeon.canMove(enemy, enemy.getX() + 1, enemy.getY())) {
    		enemy.moveRight();
    	} else if (java.lang.Math.abs(closeX) <= java.lang.Math.abs(closeY) && closeX < 0 && dungeon.canMove(enemy, enemy.getX() - 1, enemy.getY())){
    		enemy.moveLeft();
    	} else if (java.lang.Math.abs(closeY) <= java.lang.Math.abs(closeX) && closeY > 0 && dungeon.canMove(enemy, enemy.getX(), enemy.getY() + 1)) {
    		enemy.moveDown();
    	} else if (java.lang.Math.abs(closeY) <= java.lang.Math.abs(closeX) && closeY < 0 && dungeon.canMove(enemy, enemy.getX(), enemy.getY() - 1)) {
    		enemy.moveUp();
    	} else if (closeX > 0 && dungeon.canMove(enemy, enemy.getX() + 1, enemy.getY())) {
    		enemy.moveRight();
    	} else if (closeX < 0 && dungeon.canMove(enemy, enemy.getX() - 1, enemy.getY())){
    		enemy.moveLeft();
    	} else if (closeY > 0 && dungeon.canMove(enemy, enemy.getX(), enemy.getY() + 1)) {
    		enemy.moveDown();
    	} else if (closeY < 0 && dungeon.canMove(enemy, enemy.getX(), enemy.getY() - 1)) {
    		enemy.moveUp();
    	}
	}

}
