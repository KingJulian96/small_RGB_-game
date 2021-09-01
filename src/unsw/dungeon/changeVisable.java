package unsw.dungeon;

import java.util.TimerTask;

import javafx.beans.property.BooleanProperty;

public class changeVisable extends TimerTask{
	public BooleanProperty bp;
	public boolean t;

	public changeVisable(BooleanProperty bp, boolean t){
		this.bp = bp;
		this.t = t; 
	}
	@Override
	public void run() {
		bp.set(t);
	}

}
