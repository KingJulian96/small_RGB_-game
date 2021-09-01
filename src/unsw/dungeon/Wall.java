package unsw.dungeon;

/**
 * The wall entity
 *
 */
public class Wall extends Entity {
	
	/**
     * Create a wall positioned in square (x,y)
     * @param x
     * @param y
     */
    public Wall(int x, int y) {
        super(x, y);
    }
    
    @Override
    public boolean moveOn(Player player) {
    	return false;
    }
    
    @Override
    public boolean moveOn(Enemy enemy) {
    	return false;
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
			Wall wall = (Wall) obj;
			
			if (wall.getX() == this.getX() && wall.getY() == this.getY()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}

}
