package unsw.dungeon;

/**
 * The boulder entity.
 */

public class Boulder extends Entity {

	private Dungeon dungeon;

	/**
     * Create a boulder positioned in square (x,y) of a dungeon
     * @param dungeon
     * @param x
     * @param y
     */
	public Boulder(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		GridSquare d = dungeon.get_GridSquare(getX(), getY());
		if(d.hasFloorSwitch() != null) d.hasFloorSwitch().trigger();
	}

	/**
     * Move the boulder up.
     */
    public void moveUp() {
        if (getY() > 0) {
        	GridSquare d = dungeon.get_GridSquare(getX(), getY());
        	if(d.hasFloorSwitch() != null) d.hasFloorSwitch().untrigger();
        	dungeon.updateGrid(this, getX(), getY()-1);
            y().set(getY()- 1);

            d = dungeon.get_GridSquare(getX(), getY());
			if(d.hasFloorSwitch() != null) d.hasFloorSwitch().trigger();
		}
    }

    /**
     * Move the boulder down.
     */
    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) {
    		GridSquare d = dungeon.get_GridSquare(getX(), getY());
    		if(d.hasFloorSwitch() != null) d.hasFloorSwitch().untrigger();
    		dungeon.updateGrid(this, getX(), getY()+1);
            y().set(getY()+ 1);

            d = dungeon.get_GridSquare(getX(), getY());
			if(d.hasFloorSwitch() != null) d.hasFloorSwitch().trigger();
        }
    }

    /**
     * Move the boulder left.
     */
    public void moveLeft() {
        if (getX() > 0) {
    		GridSquare d = dungeon.get_GridSquare(getX(), getY());
    		if(d.hasFloorSwitch() != null) d.hasFloorSwitch().untrigger();
    		dungeon.updateGrid(this, getX()-1, getY());
            x().set(getX() - 1);

            d = dungeon.get_GridSquare(getX(), getY());
			if(d.hasFloorSwitch() != null) d.hasFloorSwitch().trigger();
        }
    }

    /**
     * Move the boulder right.
     */
    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
    		GridSquare d = dungeon.get_GridSquare(getX(), getY());
    		if(d.hasFloorSwitch() != null) d.hasFloorSwitch().untrigger();
    		dungeon.updateGrid(this, getX()+1, getY());
            x().set(getX() + 1);

            d = dungeon.get_GridSquare(getX(), getY());
			if(d.hasFloorSwitch() != null) d.hasFloorSwitch().trigger();
        }
    }

    @Override
    public boolean moveOn(Enemy enemy) {
        return false;
    }

    /**
     * Is the boulder able to move in the direction.
     * @param direction
     * @return True if boulder can, false otherwise.
     */
    public boolean moveToDirection(Direction d) {
    	int x = this.getX();
    	int y = this.getY();
    	GridSquare temp = neighbour(x,y,d);
    	return check(temp);
    }

    /**
     * Checks if the gridsquare contains items the boulder can't move on.
     * @param gridsquare
     * @return True if it doesn't, false otherwise.
     */
    public boolean check(GridSquare temp) {
    	if(temp.hasLockedDoor()) return false;
    	if(temp.isWall()) return false;
    	if(temp.hasBoulder()) return false;
    	if(temp.hasUsable()) return false;
    	if(temp.hasEnemy()!=null) return false;
    	return true;
    }

	public Dungeon dungeon() {
		return this.dungeon;
	}

	/**
     * Returns the boulder at (x,y)'s neighbouring gridsquare
     * in the specified direction.
     * @param x
     * @param y
     * @param direction
     * @return gridsquare adjacent in the direction.
     */
	public GridSquare neighbour(int x, int y,Direction e) {
		GridSquare temp = null;
		if(e == Direction.left) {
			temp = this.dungeon.get_GridSquare(x-1, y);
		}else if (e == Direction.right) {
			temp = this.dungeon.get_GridSquare(x+1, y);
		}else if (e == Direction.up) {
			temp = this.dungeon.get_GridSquare(x, y-1);
		}else if (e == Direction.down) {
			temp = this.dungeon.get_GridSquare(x, y+1);
		}
		return temp;
	}


}
