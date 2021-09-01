package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The invincibility potion entity.
 *
 */

public class InvincibilityPotion extends Entity implements Usable {

	private Player player;
	private static Timer timer = new Timer();
	private static int seconds = 0;
	
	/**
     * Create an invincibility potion positioned in square (x,y)
     * @param x
     * @param y
     */
	public InvincibilityPotion(int x, int y) {
		super(x, y);
		this.player = null;
	}

	/**
     * Sets the player as invincible.
     */
	@Override
	public void useItem() {
		//after 5 sec player back to normal
		this.player.state().backToNormal();
	}
	
	@Override
	public boolean pickUpItem(Player player) {
		player.getPotions().add(this);
		this.player = player;
		return true;
	}

	@Override
	public void dropItem() {
		this.player.removeEntity(this);
	}

	@Override
	public void destroyItem() {
		player = null;
		this.player.removeEntity(this);
	}

	/**
     * Times the bomb to be set for 5 seconds.
     */
    public void MyTimer() {
        TimerTask task;
        task = new TimerTask() {
            private final int MAX_SECONDS = 5;
            @Override
            public void run() { 
                if (seconds < MAX_SECONDS) {
                    seconds++;
                } else {
                	useItem();
                    cancel();
                }
            }
        };
        timer.schedule(task, 0, 1000);
    }

}
