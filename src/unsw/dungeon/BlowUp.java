package unsw.dungeon;

import java.util.TimerTask;

public class BlowUp extends TimerTask{
	GridSquare cr;
	Dungeon dungeon;
	public BlowUp(GridSquare cr, Dungeon dungeon){
		this.cr = cr;
		this.dungeon = dungeon;
	}
	@Override
	public void run() {
		int x = cr.x();
		int y = cr.y();
		if (cr.hasPlayer()!= null)cr.hasPlayer().killedByBomb();
		cr.clear(dungeon);
		if (x + 1 < dungeon.getWidth()) {
			GridSquare l = dungeon.get_GridSquare(x+1, y);
			l.clear(dungeon);
			if (l.hasPlayer()!= null)l.hasPlayer().killedByBomb();
		}
		if (x - 1 >= 0) {
			GridSquare r = dungeon.get_GridSquare(x-1, y);
			r.clear(dungeon);
			if (r.hasPlayer()!= null)r.hasPlayer().killedByBomb();
		}
		if (y + 1 < dungeon.getHeight()) {
			GridSquare u = dungeon.get_GridSquare(x, y+1);
			u.clear(dungeon);
			if (u.hasPlayer()!= null) u.hasPlayer().killedByBomb();
		}
		if (y - 1 >= 0) {
			GridSquare d = dungeon.get_GridSquare(x, y-1);
			d.clear(dungeon);
			if (d.hasPlayer()!= null)d.hasPlayer().killedByBomb();
		}
	}

}
