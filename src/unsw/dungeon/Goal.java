package unsw.dungeon;

import java.util.Observer;

/**
 * Goal defined for a dungeon for player to complete.
 *
 */
public interface Goal {
	
	/**
     * Has the goal been completed.
     * @return True if the goal has been completed, false otherwise
     */
	public boolean isComplete();
	/**
     * Returns the statement for the goal.
     * @return String of the goal's name
     */
	public String goalName();
	/**
     * Adds a subgoal to the goal.
     * @param subgoal
     */
	public void addGoal(Goal subgoal);
	/**
     * Adds an entity to the subgoal
     * @param entity
     */
	public void addEntity(Entity entity);
	/**
     * Marks the entity part of the goal as completed
     * @param entity
     */
	public void finishGoal(Entity entity);
	public void attach(Observer observer);

}
