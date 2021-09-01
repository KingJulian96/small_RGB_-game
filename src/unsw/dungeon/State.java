package unsw.dungeon;

import java.util.Observable;
import java.util.Observer;

/**
 * State of a player which handles behaviour of player
 * when player is alive, dead or invincible
 *
 */
public class State extends Observable {
	boolean Live;
	boolean dead;
	boolean invincible;
	
	/**
     * Set the state of the player as alive
     * and not invincible.
     */
	public State() {
		this.Live = true;
		this.invincible = false;
	}
	public boolean live() {
		return this.Live;
	}
	
	public boolean invincible() {
		return this.invincible;
	}
	
	/**
     * Set the state of the player as invincible.
     * @return True
     */
	public boolean setInvincible() {
		this.invincible = true;
		setChanged();
		notifyObservers(invincible);
		return true;
	}
	
	/**
     * Set the state of the player back to normal state.
     * @return True
     */
	public boolean backToNormal() {
		this.invincible = false;
		setChanged();
		notifyObservers(invincible);
		return true;
	}
	
	/**
     * Set the state of the player as dead.
     */
	public void setDead() {
		this.Live = false;
	}
	
	public void addObservers(Observer observer) {
		this.addObserver(observer);
	}
}
