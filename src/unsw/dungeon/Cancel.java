package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

public class Cancel extends TimerTask {

	Timer timer;
	
	public Cancel(Timer t, Bomb b){
		this.timer = t;
		b.x().set(-1);
		b.y().set(-1);
	}
	
	@Override
	public void run() {
		timer.cancel();
	}
	
}
