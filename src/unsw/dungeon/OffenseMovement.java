package unsw.dungeon;

/**
 * Enemy behaviour defined for an enemy on the offence
 * making them walk towards the player.
 *
 */

public class OffenseMovement implements EnemyMovement {
	
	private Dungeon dungeon;
	private Enemy enemy;
	private Player player;
	
	/**
     * Create an offensive behaviour for the enemy towards a player
     * in a dungeon
     * @param dungeon
     * @param enemy
     * @param player
     */
	public OffenseMovement(Dungeon dungeon, Enemy enemy, Player player) {
		this.dungeon = dungeon;
		this.enemy = enemy;
		this.player = player;
	}

	@Override
	public void move() {
		int closeX = enemy.getX() - player.getX();
    	int closeY = enemy.getY() - player.getY();
    	if (java.lang.Math.abs(closeX) <= java.lang.Math.abs(closeY) && closeX > 0 && dungeon.canMove(enemy, enemy.getX() - 1, enemy.getY())) {
    		enemy.moveLeft();
    	} else if (java.lang.Math.abs(closeX) <= java.lang.Math.abs(closeY) && closeX < 0 && dungeon.canMove(enemy, enemy.getX() + 1, enemy.getY())){
    		enemy.moveRight();
    	} else if (java.lang.Math.abs(closeY) <= java.lang.Math.abs(closeX) && closeY > 0 && dungeon.canMove(enemy, enemy.getX(), enemy.getY() - 1)) {
    		enemy.moveUp();
    	} else if (java.lang.Math.abs(closeY) <= java.lang.Math.abs(closeX) && closeY < 0 && dungeon.canMove(enemy, enemy.getX(), enemy.getY() + 1)) {
    		enemy.moveDown();
    	} else if (closeX > 0 && dungeon.canMove(enemy, enemy.getX() - 1, enemy.getY())) {
    		enemy.moveLeft();
    	} else if (closeX < 0 && dungeon.canMove(enemy, enemy.getX() + 1, enemy.getY())){
    		enemy.moveRight();
    	} else if (closeY > 0 && dungeon.canMove(enemy, enemy.getX(), enemy.getY() - 1)) {
    		enemy.moveUp();
    	} else if (closeY < 0 && dungeon.canMove(enemy, enemy.getX(), enemy.getY() + 1)) {
    		enemy.moveDown();
    	}
	}

}
