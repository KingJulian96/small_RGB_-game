package unsw.dungeon;

import java.util.TimerTask;

import javafx.scene.media.MediaPlayer;

public class StopMusic extends TimerTask{
	private MediaPlayer m;
	
	public StopMusic(MediaPlayer m) {
		this.m = m;
	}
	@Override
	public void run() {
		m.stop();
	}

}
