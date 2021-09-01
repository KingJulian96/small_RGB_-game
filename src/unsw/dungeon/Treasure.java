package unsw.dungeon;

/**
 * The treasure entity
 *
 */
public class Treasure extends Entity {
	
	private SubGoal goal;

	/**
     * Create treasure positioned in square (x,y)
     * with the treasure goal.
     * @param x
     * @param y
     */
	public Treasure(int x, int y) {
		super(x, y);
		this.goal = null;
	}
	
	/**
     * A player picks up the treasure when the move on the square
     * and finsihes the entity as part of the goal.
     * @param player
     * @return True
     */
	@Override
    public boolean moveOn(Player player) {
		finishGoal();
		player.removeEntity(this);
		return true;
    }
	
	public void setGoal(SubGoal goal) {
		this.goal = goal;
	}
    
    public void finishGoal() {
		goal.finishGoal(this);
	}

}
