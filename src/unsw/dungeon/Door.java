package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The door entity
 *
 */
public class Door extends Entity {
	
	private int id;
	private BooleanProperty locked;
	private BooleanProperty opened;

	/**
     * Create a door positioned in square (x,y) with an id
     * @param id
     * @param x
     * @param y
     */
	public Door(int id, int x, int y) {
		super(x, y);
		this.id = id;
		this.locked = new SimpleBooleanProperty(true);
		this.opened = new SimpleBooleanProperty(false);
	}
	
	public BooleanProperty locked() {
		return this.locked;
	}
	
	public BooleanProperty opened() {
		return this.opened;
	}
	
	public Boolean getLocked() {
		return locked.getValue();
	}

	/**
     * Checks if the key corresponds to the door.
     * @param key
     * @return True if the key id is equal to the door id, false otherwise.
     */
	public Boolean checkKey(Key key) {
		if (key.getID() == id) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
     * Unlocks the door using the key.
     * @param key
     * @return True if the key was able to unlock the door, false otherwise.
     */
	public boolean unlock(Key key) {
		if (key != null && checkKey(key)) {
			locked.set(false);
			opened.set(true);
			key.destroyItem();
			return true;
		} else {
			return false;
		}
	}
	
	@Override
    public boolean moveOn(Player player) {
		if (locked.getValue() == true) {
			return unlock(player.getKey());
		} else {
			return true;
		}
    }
	
	@Override
    public boolean moveOn(Enemy enemy) {
		if (locked.getValue() == true) {
			return false;
		} else {
			return true;
		}
    }
	
	@Override
    public boolean equals(Object obj) {
		
		if (obj == this) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (obj.getClass() == this.getClass()) {
			Door door = (Door) obj;
			
			if (door.getX() == this.getX() && door.getY() == this.getY()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}
}