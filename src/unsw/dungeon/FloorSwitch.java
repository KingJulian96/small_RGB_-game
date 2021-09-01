package unsw.dungeon;


/**
 * The floor switch entity
 *
 */
public class FloorSwitch extends Entity {
	
	private Boolean triggered;
	private SubGoal goal;
	
	/**
     * Create a floor switch positioned in square (x,y)
     * @param x
     * @param y
     */
	public FloorSwitch(int x, int y) {
		super(x, y);
		this.triggered = false;
		this.goal = null;
	}
	
	/**
     * Sets the floor switch as triggered and finishes the goal.
     */
	public void trigger() {
		this.triggered = true;
		finishGoal();
	}
	
	/**
     * Untriggers the floor switch and resets the goal.
     */
	public void untrigger() {
		this.triggered = false;
		goal.addEntity(this);
	}
	
	public Boolean triggered() {
		return this.triggered;
	}
	
	public void setGoal(SubGoal goal) {
		this.goal = goal;
	}
    
    public void finishGoal() {
		goal.finishGoal(this);
	}
}
