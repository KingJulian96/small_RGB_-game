package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity{

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x;
    private IntegerProperty y;
    private BooleanProperty visible;
    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.visible = new SimpleBooleanProperty(true);
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    

    public Boolean right_next (Entity t) {
    	if(t.getY() == this.getY() && (t.getX()-1) == this.getX()) return true;
    	return false;
    }
    
    public Boolean left_next (Entity t) {
    	if(t.getY() == this.getY() && (t.getX()+1) == this.getX()) return true;
    	return false;
    }
    
    public Boolean at_top (Entity t) {
    	if(t.getX() == this.getX() && (t.getY()-1) == this.getY()) return true;
    	return false;
    }
    
    public Boolean at_bottom (Entity t) {
    	if(t.getX() == this.getX() && (t.getY()+1) == this.getY()) return true;
    	return false;
    }

    /**
     * Is the player allowed to move on the same square as the item.
     * @param player
     * @return True if the player can, false otherwise.
     */
    public boolean moveOn(Player player) {
		return true;
	}
    
    /**
     * Is the enemy allowed to move on the same square as the item.
     * @param enemy
     * @return True if the enemy can, false otherwise.
     */
    public boolean moveOn(Enemy enmey) {
		return true;
	}
    
	public BooleanProperty getVisible() {
		return visible;
	}

	public void setVisible(boolean b) {
		this.visible.set(b);
	}
	
}
