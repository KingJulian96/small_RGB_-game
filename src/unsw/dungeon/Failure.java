package unsw.dungeon;

/**
 * State of the dungeon which handles behaviour of entities
 * when dungeon has been failed (i.e player is killed)
 *
 */
public class Failure implements DungeonState{

	@Override
	public boolean endMode() {
		return false;
	}
	
	@Override
	public void retry(Dungeon dungeon) {
		dungeon.setState(new Active());
	}

	@Override
	public void complete(Dungeon dungeon) {
	}

	@Override
	public void fail(Dungeon dungeon) {
	}
	
}
