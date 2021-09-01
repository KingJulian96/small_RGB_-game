package unsw.dungeon;


import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The bomb entity
 *
 */
public class Bomb extends Entity implements Usable {
	
	private Player player;

    private BooleanProperty stage1;
    private BooleanProperty stage2;
    private BooleanProperty stage3;
    private BooleanProperty stage4;
	/**
     * Create a bomb positioned in square (x,y)
     * Initialized with stages of being lit and not picked up by a player
     * @param x
     * @param y
     */
	public Bomb(int x, int y) {
		super(x, y);
		this.stage1 = new SimpleBooleanProperty(false);
		this.stage2 = new SimpleBooleanProperty(false);
		this.stage3 = new SimpleBooleanProperty(false);
		this.stage4 = new SimpleBooleanProperty(false);
		this.player = null;
	}

	/**
     * Light the bomb which blows up in 2 seconds
     * clearing all surrounding gridsquares.
     */
	@Override
	public void useItem(){
		this.x().set(player.getX());
		this.y().set(player.getY());
		GridSquare cr = this.player.dungeon().
				get_GridSquare(this.player.getX(), this.player.getY());
		Timer t = new java.util.Timer();
		t.schedule(new changeVisable(getStage1(), true), 0);		
		t.schedule(new changeVisable(getStage2(), true), 500);
		t.schedule(new changeVisable(getStage3(), true), 1000);
		t.schedule(new changeVisable(getStage4(), true), 1500);
		t.schedule(new BlowUp(cr,this.player.dungeon()), 1501);
		t.schedule(new changeVisable(getStage1(), false), 2000);
		t.schedule(new changeVisable(getStage2(), false), 2000);
		t.schedule(new changeVisable(getStage3(), false), 2000);
		t.schedule(new changeVisable(getStage4(), false), 2000);
		t.schedule(new Cancel(t,this), 2001);
	}
	
	public BooleanProperty getStage1() {
		return stage1;
	}
	public BooleanProperty getStage2() {
		return stage2;
	}
	public BooleanProperty getStage3() {
		return stage3;
	}
	public BooleanProperty getStage4() {
		return stage4;
	}

	public void setStage(BooleanProperty bp, Boolean b) {
		bp.set(b);
	}
	
	@Override
	public boolean pickUpItem(Player player) {
		player.addBomb(this);
		this.player = player;
		return true;
	}

	@Override
	public void dropItem() {
		player.removeBomb(this);
	}

	@Override
	public void destroyItem() {
		player.removeBomb(this);
		
	}
	
	public Player player() {
		return this.player;
	}
	

}
