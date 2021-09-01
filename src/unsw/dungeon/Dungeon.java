/**
 *
 */
package unsw.dungeon;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon implements Observer {

	private int width, height;
    private List<Entity> entities;
    private DungeonState state;
    private GridSquare[][] grid;
    private ArrayList<Enemy> enemies;
    private Goal goal;
    private Player player;
    private String goalString;

    /**
     * Create an active dungeon of specified width and height
     * with an empty list of entities and enemies
     * with no goal or player specified
     * on an empty grid of gridsquares
     * @param width
     * @param height
     */
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.state = new Active();
        this.grid = new GridSquare[width][height];
        for (int i = 0; i < width; i++) {
        	for (int j = 0; j < height; j ++) {
        		grid[i][j] = new GridSquare(i, j);
        	}
        }
        this.enemies = new ArrayList<Enemy>();
        this.goal = null;
        this.player = null;

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public GridSquare[][] getGrid() {
		return grid;
	}
    
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void addEnemies(Enemy enemy) {
		this.enemies.add(enemy);
		player.state().addObserver(enemy);
	}
	
	public void removeEnemies(Enemy enemy) {
		this.enemies.remove(enemy);
	}

    public List<Entity> entities(){
    	return this.entities;
    }

    /**
     * Updates the grid to change the entity position
     * to the new x and y.
     * @param x
     * @param y
     */
	public void updateGrid(Entity entity, int x, int y) {
    	grid[entity.getX()][entity.getY()].remove(entity);
    	if (x >= 0 && x < width && y >= 0 && y < height) {
    		grid[x][y].add(entity);
    	}
    }
    
	/**
     * Gets the grid square corresponding to square (x, y).
     * @param x
     * @param y
     * @return gridsquare at (x,y).
     */
    public GridSquare get_GridSquare(int x, int y) {
    	return this.grid[x][y];
    }
    
    /**
     * Checks if the player can move in the square (x, y)
     * depending on the entities on the same square
     * @param player
     * @param x
     * @param y
     * @return True if the player can move, false otherwise.
     */
    public Boolean canMove(Player player, int x, int y) {
	    if (state.endMode()) {
    		ArrayList<Entity> entities = grid[x][y].getEntities();
	    	for (int i = 0; i < entities.size(); i ++) {
	    		if (!entities.get(i).moveOn(player)) {
	    			return false;
	    		}
	    	}
	    	return true;
	    } else {
	    	return false;
	    }
    }
    
    /**
     * Checks if the enemy can move in the square (x, y)
     * depending on the entities on the same square
     * @param enemy
     * @param x
     * @param y
     * @return True if the enemy can move, false otherwise.
     */
    public Boolean canMove(Enemy enemy, int x, int y) {
	    if (state.endMode()) {
    		ArrayList<Entity> entities = grid[x][y].getEntities();
	    	for (int i = 0; i < entities.size(); i ++) {
	    		if (!entities.get(i).moveOn(enemy)) {
	    			return false;
	    		}
	    	}
	    	return true;
	    } else {
	    	return false;
	    }
    }
    
    /**
     * Moves all the enemies in the dungeon.
     */
    public void moveEnemies() {
    	for (int i = 0; i < enemies.size(); i ++) {
    		enemies.get(i).move();
    	}
    }
    
    /**
     * Checks if there is anything for a player to pick up
     * when moving to the square (x, y).
     * @param x
     * @param y
     * @return Usable item in the square (x, y) and null if there are none.
     */
    public Usable usableItem(int x, int y) {
    	ArrayList<Entity> entities = grid[x][y].getEntities();
		for (int i = 0; i < entities.size(); i ++) {
			if (entities.get(i) instanceof Usable) {
				return (Usable) entities.get(i);
			}
		}
		return null;
    }
    
    /**
     * Adds an entity to the dungeon list and places it on the grid.
     * @param entity
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
        if (entity != null) {
        	int x = entity.getX();
	        int y = entity.getY();
	        grid[x][y].add(entity);
        }
    }

	/**
     * Removes an entity from the dungeon list and takes it off the grid.
     * @param entity
     */
    public void removeEntity(Entity entity) {
        entities.remove(entity);
        if (entity != null) {
	        int x = entity.getX();
	        int y = entity.getY();
	        grid[x][y].remove(entity);
	        entity.x().set(-1);
	        entity.y().set(-1);
        }
    }
	
    /**
     * Returns the gridsquare (x,y)'s neighbouring gridsquare
     * in the specified direction.
     * @param x
     * @param y
     * @param direction
     * @return gridsquare adjacent in the direction.
     */
    public GridSquare neighbour(int x, int y,Direction e) {
    	GridSquare temp = null;
    	if(e == Direction.left) {
    		temp = this.get_GridSquare(x-1, y);
    	}else if (e == Direction.right) {
    		temp = this.get_GridSquare(x+1, y);
    	}else if (e == Direction.up) {
    		temp = this.get_GridSquare(x, y-1);
    	}else if (e == Direction.down) {
    		temp = this.get_GridSquare(x, y+1);
    	}
    	return temp;
    }
    
	public void setState(DungeonState state) {
		this.state = state;
	}
	
	public void setGoal(Goal goal) {
    	goal.attach(this);
		this.goal = goal;
    }
	
	public Goal getGoal() {
    	return goal;
    }
	
	public String goalString() {
		return this.goalString;
	}
	
	public void setGoalString(String s) {
		this.goalString = s;
	}
	
	/**
     * Sets the dungeon as failed.
     */
	public void fail() {
		String source = new File("resource/failed.mp3").toURI().toString();
		Media media = new Media(source);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
	    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	    mediaPlayer.setAutoPlay(true);
	    Timer t = new java.util.Timer();
		t.schedule(new StopMusic(mediaPlayer), 4000);
		state.fail(this);
	}
	
	/**
     * Sets the dungeon as complete.
     */
	public void complete() {
		state.complete(this);
		String source = new File("resource/jackpot.mp3").toURI().toString();
		Media media = new Media(source);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
	    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	    mediaPlayer.setAutoPlay(true);
	    Timer t = new java.util.Timer();
		t.schedule(new StopMusic(mediaPlayer), 4000);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Dungeon Game");
		alert.setHeaderText(null);
		alert.setContentText("You have finished the Dungeon!");
		alert.showAndWait();
	}

	@Override
	public void update(Observable observer, Object arg) {
		if (goal.isComplete()) {
			complete();
		}
	}

}