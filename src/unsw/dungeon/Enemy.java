package unsw.dungeon;

import java.util.Observable;
import java.util.Observer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The enemy entity
 *
 */
public class Enemy extends Entity implements Observer {

	private Dungeon dungeon;
	private Player player;
	private State state;
	private EnemyMovement behaviour;
	private SubGoal goal;
	private BooleanProperty dead;
	/**
	 * Create an enemy positioned in square (x,y) belonging to dungeon
	 * initialised as ready to attack with the enemy goal.
	 * @param dungeon
	 * @param x
	 * @param y
	 */
	public Enemy(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		this.player = dungeon.getPlayer();
		this.state = new State();
		this.behaviour = new OffenseMovement(dungeon, this, player);
		this.goal = null;
		this.dead = new SimpleBooleanProperty(false);
	}
	
	public State Enemy_state() {
		return this.state;
	}
	
	public BooleanProperty dead() {
		return this.dead;
	}
	
	public void setDead(Boolean b) {
		this.dead.set(b);;
	}

	/**
     * Move the enemy up.
     */
    public void moveUp() {
        if (getY() > 0) {
        	dungeon.updateGrid(this, getX(), getY() - 1);
        	y().set(getY() - 1);
        }
    }
    
    /**
     * Move the enemy down.
     */
    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) {
        	dungeon.updateGrid(this, getX(), getY() + 1);
        	y().set(getY() + 1);
        }
    }

    /**
     * Move the enemy left.
     */
    public void moveLeft() {
        if (getX() > 0) {
        	dungeon.updateGrid(this, getX() - 1, getY());
            x().set(getX() - 1);
        }
    }

    /**
     * Move the enemy right.
     */
    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
        	dungeon.updateGrid(this, getX() + 1, getY());
        	x().set(getX() + 1);
        }
    }
    
    /**
     * Move the enemy based on their behaviour
     * and kill player if on same square.
     */
    public void move() {
    	if (state.live()) {
		    behaviour.move();
	    	if (getX() - player.getX() == 0 && getY() - player.getY() == 0) {
	    		if (player.state().invincible()) {
	    			killed();
	    		} else {
	    			player.killed();
	    		}
	    	}
    	}
    }
    
    public void setGoal(SubGoal goal) {
		this.goal = goal;
	}
    
    /**
     * Finishes the enemy goal if the enemy is killed.
     */
    public void finishGoal() {
		goal.finishGoal(this);
	}
    
    /**
     * Kills the enemy.
     */
    public void killed() {
    	dungeon.removeEntity(this);
    	dungeon.removeEnemies(this);
    	this.state.setDead();
    	this.setDead(true);
    	finishGoal();
    }
    
    public State state() {
    	return this.state;
    }
    

    @Override
    public boolean moveOn(Enemy enemy) {
        return false;
    }

	@Override
	public void update(Observable o, Object arg) {
		boolean invincible = (boolean) arg;
		if (invincible) {
			behaviour = new ScaredMovement(dungeon, this, player);
		} else {
			behaviour = new OffenseMovement(dungeon, this, player);
		}
	}
}
