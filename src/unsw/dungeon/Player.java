package unsw.dungeon;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;



/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity{

    private Dungeon dungeon;
    private State state;
    private Key key;
    private Sword sword;
    private ArrayList<Bomb> bombs;
    private ArrayList<InvincibilityPotion> potions;
    
    /**
     * Create a player positioned in square (x,y) belonging to a dungeon
     * with no key, sword, bombs or potions.
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.state = new State();
        this.key = null;
        this.sword = null;
        this.bombs = new ArrayList<Bomb>();
        this.potions = new ArrayList<InvincibilityPotion>();
    }

    public Key getKey() {
		return key;
	}

	public Sword getSword() {
		return sword;
	}

	public ArrayList<Bomb> getBombs() {
		return bombs;
	}
	public ArrayList<InvincibilityPotion> getPotions() {
		return potions;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public void setSword(Sword sword) {
		this.sword = sword;
	}

	public void addBomb(Bomb bomb) {
		bombs.add(bomb);
	}

	public void removeBomb(Bomb bomb) {
		bombs.remove(bomb);
	}
	
	/**
     * Move the player up.
     */
    public void moveUp() {
	    if (state.live()) {
	    	Direction dr = Direction.up;

			if (ableToGo(dr)) {
				GridSquare up = this.dungeon.neighbour(getX(), getY(), dr);
	        	if (hasBoulder(dr)) { 												// there is a boulder in the way
	        		Boulder b = up.boulder();										// this is the boulder
	        		if(b.moveToDirection(dr)) {									// we can move the boulder
	        			dungeon.updateGrid(this, getX(), getY()-1);					// player moves up
	        			b.moveUp();													// boulder move up
	        			y().set(getY() - 1);
	        		}
	        	}else {
	        		dungeon.updateGrid(this, getX(), getY() - 1);
	        		y().set(getY() - 1);
	        	}
	        }
			pickUp(dungeon.usableItem(getX(), getY()));
	    }
    }

    /**
     * Move the player down.
     */
    public void moveDown() {
    	if (state.live()) {
    		Direction dr = Direction.down;
			if (ableToGo(dr)) {
				GridSquare d = this.dungeon.neighbour(getX(), getY(), dr);				// the down square
	        	if (hasBoulder(dr)) {												// there is a boulder in the way
	        		Boulder temp = d.boulder();										// this is the boulder
	        		if(temp.moveToDirection(dr)) {									// we can move up the boulder
	        			dungeon.updateGrid(this, getX(), getY()+1);					// player moves down
	        			temp.moveDown();
	        			y().set(getY() + 1);
	        			GridSquare up_up = this.dungeon.neighbour(getX(), getY(), dr);
	        			if(up_up.hasFloorSwitch() != null) up_up.hasFloorSwitch().trigger();
	        		}
	        	}else {
	        		dungeon.updateGrid(this, getX(), getY() + 1);
	        		y().set(getY() + 1);
	        	}
	        }else {
	        	
	        }
			pickUp(dungeon.usableItem(getX(), getY()));
    	}
    }

    /**
     * Move the player left.
     */
    public void moveLeft() {
    	if (state.live()) {
    		Direction dr = Direction.left;
	        if (ableToGo(dr)) {
		    	GridSquare l = this.dungeon.neighbour(getX(), getY(), dr);
	        	if (hasBoulder(dr)) { 												// there is a boulder in the way
	        		Boulder temp = l.boulder();
	        		if(temp.moveToDirection(dr)) {								// we can move up the boulder
	        			dungeon.updateGrid(this, getX()-1, getY());					// player moves left
	        			temp.moveLeft();
	        			x().set(getX() - 1);
	        		}
	        	}else {
	        		dungeon.updateGrid(this, getX() - 1, getY());
	        		x().set(getX() - 1);
	        	}

	        }
	        pickUp(dungeon.usableItem(getX(), getY()));
	    }
    }

    /**
     * Move the player right.
     */
    public void moveRight() {
    	if (state.live()) {
    		Direction dr = Direction.right;

	        if (ableToGo(dr)) {
	        	GridSquare r = this.dungeon.neighbour(getX(), getY(), dr);
	        	if (hasBoulder(dr)) { 														// there is a boulder in the way
	        		Boulder temp = r.boulder();
	        		if(temp.moveToDirection(dr)) {											// we can move up the boulder
	        			dungeon.updateGrid(this, getX()+1, getY());							// player moves left
	        			temp.moveRight();
	        			x().set(getX() + 1);
	        		}
	        	}
	        	else {
	        		dungeon.updateGrid(this, getX() + 1, getY());
	        		x().set(getX() + 1);
	        	}
	        }
	        pickUp(dungeon.usableItem(getX(), getY()));
    	}
    }
    
    public State state() {
    	return this.state;
    }

    /**
     * Is the player able to move in the direction.
     * @param direction
     * @return True if player can, false otherwise.
     */
    public boolean ableToGo(Direction e) {
    	if(e == Direction.right) {
    		if (getX() < dungeon.getWidth() - 1 && dungeon.canMove(this, getX() + 1, getY()))return true;
    	}else if (e == Direction.left) {
    		if (getX() > 0 && dungeon.canMove(this, getX() - 1, getY())) return true;
    	}else if (e == Direction.down) {
        	if (getY() < dungeon.getHeight() - 1 && dungeon.canMove(this, getX(), getY() + 1)) return true;
    	}else {
    		if (getY() > 0 && dungeon.canMove(this, getX(), getY() - 1)) return true;
    	}
    	return false;
    }

    /**
     * Is a there a boulder in the direction.
     * @param direction
     * @return True if there is a boulder, false otherwise.
     */
    public boolean hasBoulder(Direction e) {
    	int x = this.getX();
    	int y = this.getY();
    	GridSquare temp = neighbour(x,y,e);
    	if(temp.hasBoulder()){
    		return true;
        }
    	return false;
    }

    /**
     * Allows the player to pick up the item into their inventory.
     * @param item
     * @return True if player picked it up, false otherwise.
     */
	public boolean pickUp(Usable item) {
    	if (item != null && item.pickUpItem(this)) {
    		Entity entity = (Entity) item;
    		dungeon.updateGrid(entity, -1, -1);
    		entity.x().set(-1);
    		entity.y().set(-1);
    		String source = new File("resource/item.mp3").toURI().toString();
    		Media media = new Media(source);
    		MediaPlayer mediaPlayer = new MediaPlayer(media);

    	    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    	    mediaPlayer.setAutoPlay(true);
		    Timer t = new java.util.Timer();
			t.schedule(new StopMusic(mediaPlayer), 3000);	
    		return true;
    	} else {
    		return false;
    	}
    }

	/**
     * Uses the sword in the direction,
     * kills enemies in the adjacent square of that direction.
     * @param direction
     */
    public void useSword( Direction d) {
    	if (state.live() && sword != null) {
			sword.useItem();
			GridSquare trget = this.dungeon.neighbour(getX(), getY(), d);
			Enemy enemy = trget.hasEnemy();
			String source;
			Media media;
			Timer t = new java.util.Timer();
			MediaPlayer mediaPlayer;
			if (enemy != null) {
				enemy.killed();
				source= new File("resource/ooo.wav").toURI().toString();
				media = new Media(source);
				mediaPlayer = new MediaPlayer(media);
			    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			    mediaPlayer.setAutoPlay(true);
			    Timer n = new java.util.Timer();
				n.schedule(new StopMusic(mediaPlayer), 400);
			}else{
				source = new File("resource/swing.wav").toURI().toString();
				media = new Media(source);
				mediaPlayer = new MediaPlayer(media);
			    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			    mediaPlayer.setAutoPlay(true);
			    t.schedule(new StopMusic(mediaPlayer), 100);
			}

    	}
    }

    /**
     * Sets the bomb from inventory into the dungeon as lit.
     * Clears all surrounding gridsquares.
     */
    public void setBomb() {
    	if (state.live() && bombs.size() != 0) {
	    	Bomb b  = this.bombs.get(0);
	    	if (b != null) {
	    		GridSquare trget = this.dungeon.get_GridSquare(getX(), getY());
	    		if (!trget.hasUsable()) {
		    		b.useItem();
		    		this.bombs.remove(0);
			    	String source;
					Media media;
			    	source= new File("resource/time.mp3").toURI().toString();
					media = new Media(source);
					MediaPlayer mediaPlayer;
					mediaPlayer = new MediaPlayer(media);
				    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
				    mediaPlayer.setAutoPlay(true);
				    Timer n = new java.util.Timer();
					n.schedule(new StopMusic(mediaPlayer), 400);
	    		}
	    	}

    	}

    }

    /**
     * Uses the potion and makes them invincible
     * against enemies and bombs.
     */
    public void usePotion() {
    	if (state.live() && potions.size() != 0 && !this.state().invincible()) {
    		InvincibilityPotion p = this.potions.get(0);
    		if (p != null) {
	    		this.state().setInvincible();
	    		p.MyTimer();
	    		this.potions.remove(p);
	    		String source = new File("resource/inv.mp3").toURI().toString();
	    		Media media = new Media(source);
	    		MediaPlayer mediaPlayer = new MediaPlayer(media);
	    	    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	    	    mediaPlayer.setAutoPlay(true);
	    	    Timer t = new java.util.Timer();
	    		t.schedule(new StopMusic(mediaPlayer), 4000);
	    	}
    	}
    }

    /**
     * Removes an entity from the dungeon.
     * @param entity
     */
    public void removeEntity(Entity entity) {
    	dungeon.removeEntity(entity);
    }
    
    /**
     * Drops the key the player is holding onto the ground below.
     */
    public void dropKey() {
	    if (key != null) {
    		key.x().set(getX());
	    	key.y().set(getY());
	    	dungeon.addEntity(key);
	    	dungeon.updateGrid(key, getX(), getY());
	    	key = null;
	    }
    }

    /**
     * Kills the player
     */
    public void killed() {
    	this.state().setDead();
      	dungeon.removeEntity(this);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Dungeon Game");
		alert.setHeaderText(null);
		alert.setContentText("Game over!");
		alert.show();
		dungeon.fail();
    }

    /**
     * Kills the player by bomb
     */
    public void killedByBomb() {
    	this.state().setDead();
      	dungeon.removeEntity(this);
      	dungeon.fail();
    }

    public Dungeon dungeon() {
    	return this.dungeon;
    }

	@Override
	public String toString() {
		int t = 0;
		if (this.getSword() != null)  t = 1;
		String Sword = t + " Sword";
		if (this.getSword() != null) {
			Sword temp = this.getSword();
			Sword += " which can be used " + temp.use() + " more times\n";
		} else {
			Sword = t + " Swords\n";
		}
		if (this.getKey() != null) {
			Sword += "1 Key\n";
		} else {
			Sword += "0 Keys\n";
		}
		String Bomb = this.bombs.size() + " Bombs\n";
		if (this.bombs.size() == 1 ) {
			Bomb = this.bombs.size() + " Bomb\n";
		}
		String potion = this.potions.size() + " Potions\n";
		if (this.potions.size() == 1 ) {
			potion = this.potions.size() + " Potion\n";
		}
		return Sword + Bomb + potion;
	}


	/**
     * Returns the player at (x,y)'s neighbouring gridsquare
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
