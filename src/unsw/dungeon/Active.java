package unsw.dungeon;

/**
 * State of the dungeon which handles behaviour of entities
 * when dungeon is still actively being played
 *
 */
public class Active implements DungeonState{

	@Override
	public boolean endMode() {
		return true;
	}
	
	@Override
	public void retry(Dungeon dungeon) {
	}

	@Override
	public void complete(Dungeon dungeon) {
		dungeon.setState(new Complete());
	}

	@Override
	public void fail(Dungeon dungeon) {
		dungeon.setState(new Failure());
	}

}
