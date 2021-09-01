package unsw.dungeon;

import java.util.ArrayList;
import java.util.Observer;

/**
 * Goal that contains subgoals that at least one must be completed
 * for the whole dungeon to be completed.
 *
 */
public class OrGoal implements Goal {
	
	private ArrayList<Goal> subgoals;
	
	/**
     * Create an OrGoal with an empty list of subgoals.
     */
	public OrGoal() {
		this.subgoals = new ArrayList<Goal>();
	}

	@Override
	public boolean isComplete() {
		for (int i = 0; i < subgoals.size(); i ++) {
			if (subgoals.get(i).isComplete()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String goalName() {
		String name = "";
		for (int i = 0; i < subgoals.size(); i ++) {
			if (name == "") {
				name += "(" + subgoals.get(i).goalName();
			} else {
				name += " OR " + subgoals.get(i).goalName(); 
			}
		}
		return name += ")";
	}

	@Override
	public void addGoal(Goal subgoal) {
		subgoals.add(subgoal);
	}
	
	@Override
	public void addEntity(Entity entity) {
	}

	@Override
	public void finishGoal(Entity entity) {
	}

	@Override
	public void attach(Observer observer) {
		for (int i = 0; i < subgoals.size(); i ++) {
			subgoals.get(i).attach(observer);
		}
	}

}
