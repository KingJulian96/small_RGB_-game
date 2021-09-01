package unsw.dungeon;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Subgoal that contains a list of either enemies, treasure, switches or exits
 * that must each be completed.
 *
 */
public class SubGoal extends Observable implements Goal {

	private ArrayList<Entity> entities;
	private String goalName;
	
	/**
     * Create a SubGoal with all the entities defining the goal
     * and those particular entities' type name.
     * @param entities
     * @param goalName
     * 
     */
	public SubGoal(ArrayList<Entity> entities, String goalName) {
		this.entities = entities;
		this.goalName = goalName;
	}

	@Override
	public boolean isComplete() {
		if (entities.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String goalName() {
		return goalName;
	}

	@Override
	public void addGoal(Goal subgoal) {
	}

	@Override
	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	@Override
	public void finishGoal(Entity entity) {
		entities.remove(entity);
		if (isComplete()) {
			setChanged();
			notifyObservers();
		}
	}

	@Override
	public void attach(Observer observer) {
		this.addObserver(observer);
	}
	
	/**
     * Temporarily checks if the exit is the last goal to complete.
     * @param exit
     */
	public void checkGoal(Exit exit) {
		entities.remove(exit);
	}
}
