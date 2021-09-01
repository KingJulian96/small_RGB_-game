package unsw.dungeon;


/**
 * The exit entity
 *
 */
public class Exit extends Entity {
	
	private Dungeon dungeon;
	private SubGoal goal;
	
	/**
     * Create an exit positioned in square (x,y) belonging to a dungeon
     * with the exit goal.
     * @param dungeon
     * @param x
     * @param y
     */
	public Exit(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		this.goal = null;
	}
	
	/**
     * Allows player to only move on the exit if they have completed
     * any other and goals first.
     * @param player
     * @return True if they have completed, false otherwise.
     */
	@Override
    public boolean moveOn(Player player) {
		goal.checkGoal(this);
		if(dungeon.getGoal().isComplete()) {
			finishGoal();
			return true;
		} else {
			goal.addEntity(this);
			return false;
		}
    }
	
	public void setGoal(SubGoal goal) {
		this.goal = goal;
	}
    
    public void finishGoal() {
		goal.finishGoal(this);
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
			Exit exit = (Exit) obj;
			
			if (exit.getX() == this.getX() && exit.getY() == this.getY()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}

}
