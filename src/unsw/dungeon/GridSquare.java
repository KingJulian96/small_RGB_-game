package unsw.dungeon;

import java.util.ArrayList;
/**
 * Single square of the grid pertaining to a dungeon.
 * 
 */
public class GridSquare {
	
	private int x;
	private int y;
	private ArrayList<Entity> entities;
	
	/**
     * Create a grid square corresponding to (x,y)
     * with an empty list of entities on it
     * @param x
     * @param y
     */
	public GridSquare(int x, int y) {
		this.x = x;
		this.y = y;
		this.entities = new ArrayList<Entity>();
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	public int x() {
		return this.x;
	}
	
	public int y() {
		return this.y;
	}
	
	/**
     * Checks if the grid square contains a usable item
     * @return True if it does contain, false otherwise.
     */
	public Boolean hasUsable() {
		for(int i = 0; i < this.entities.size(); i++) {
			Entity entity = this.entities.get(i);
			if (entity instanceof Usable){
				return true;
			}
		}
		return false;
	}
	
	/**
     * Checks if the grid square contains a boulder
     * @return True if it does contain, false otherwise.
     */
	public Boolean hasBoulder() {
		for(int i = 0; i < this.entities.size(); i++) {
			Entity entity = this.entities.get(i);
			if (entity instanceof Boulder){
				return true;
			}
		}
		return false; 
	}
	
	/**
     * Checks if the grid square contains a key
     * @return True if it does contain, false otherwise.
     */
	public Boolean hasKey() {
		for(int i = 0; i < this.entities.size(); i++) {
			Entity entity = this.entities.get(i);
			if (entity instanceof Key){
				return true;
			}
		}
		return false; 
	}
	
	/**
     * Checks if the grid square contains a sword
     * @return True if it does contain, false otherwise.
     */
	public Boolean hasSword() {
		for(int i = 0; i < this.entities.size(); i++) {
			Entity entity = this.entities.get(i);
			if (entity instanceof Sword){
				return true;
			}
		}
		return false; 
	}
	
	/**
     * Checks if the grid square contains a locked door
     * @return True if it does contain, false otherwise.
     */
	public Boolean hasLockedDoor() {
		for(int i = 0; i < this.entities.size(); i++) {
			Entity entity = this.entities.get(i);
			if (entity instanceof Door && ((Door) entity).getLocked()){
				return true;
			}
		}
		return false; 
	}
	
	/**
     * Checks if the grid square contains a bomb
     * @return True if it does contain, false otherwise.
     */
	public Boolean hasBomb() {
		for(int i = 0; i < this.entities.size(); i++) {
			Entity entity = this.entities.get(i);
			if (entity instanceof Bomb){
				return true;
			}
		}
		return false; 
	}
	
	/**
     * Checks if the grid square contains an exit
     * @return True if it does contain, false otherwise.
     */
	public Boolean hasExit() {
		for(int i = 0; i < this.entities.size(); i++) {
			Entity entity = this.entities.get(i);
			if (entity instanceof Exit){
				return true;
			}
		}
		return false; 
	}
	
	/**
     * Checks if the grid square contains a invincibility potion
     * @return True if it does contain, false otherwise.
     */
	public Boolean hasInvincibilityPotion() {
		for(int i = 0; i < this.entities.size(); i++) {
			Entity entity = this.entities.get(i);
			if (entity instanceof InvincibilityPotion){
				return true;
			}
		}
		return false; 
	}
	
	/**
     * Checks if the grid square contains a treasure.
     * @return True if it does contain, false otherwise.
     */
	public Boolean hasTreasure() {
		for(int i = 0; i < this.entities.size(); i++) {
			Entity entity = this.entities.get(i);
			if (entity instanceof Treasure){
				return true;
			}
		}
		return false; 
	}
	
	/**
     * Checks if the grid square contains a wall.
     * @return True if it does contain, false otherwise.
     */
	public Boolean isWall() {
		for(int i = 0; i < this.entities.size(); i++) {
			Entity entity = this.entities.get(i);
			if (entity instanceof Wall){
				return true;
			}
		}
		return false; 
	}
	
	
	public void add(Entity entity) {
		entities.add(entity);
	}
	
	public void remove(Entity entity) {
		entities.remove(entity);
	}
	
	
	/**
     * Returns the boulder that is on the gridsquare.
     * @return boulder on the gridsquare, or null if there are none.
     */
	public Boulder boulder() {
		for(int i = 0; i < this.entities.size(); i++) {
			Entity entity = this.entities.get(i);
			if (entity instanceof Boulder){
				return (Boulder) entity;
			}
		}
		return null;
	}
	
	/**
     * Returns the enemy that is on the gridsquare.
     * @return enemy on the gridsquare, or null if there are none.
     */
	public Enemy hasEnemy() {
		Enemy t = null;
		for(int i = 0; i < this.entities.size(); i++) {
			Entity entity = this.entities.get(i);
			if (entity instanceof Enemy){
				t = (Enemy) entity;
			}
		}
		return t;
	}
	
	/**
     * Returns the player that is on the gridsquare.
     * @return player on the gridsquare, or null if there are none.
     */
	public Player hasPlayer() {
		Player t = null;
		for(int i = 0; i < this.entities.size(); i++) {
			Entity entity = this.entities.get(i);
			if (entity instanceof Player){
				t = (Player) entity;
			}
		}
		return t;
	}
	
	/**
     * Returns the floor switch that is on the gridsquare.
     * @return floor switch on the gridsquare, or null if there are none.
     */
	public FloorSwitch hasFloorSwitch() {
		FloorSwitch t = null;
		for(int i = 0; i < this.entities.size(); i++) {
			Entity entity = this.entities.get(i);
			if (entity instanceof FloorSwitch){
				t = (FloorSwitch) entity;
			}
		}
		return t;
	}
	
	/**
     * Clears the dungeon of any usable item, boulder or enemy
     * in the square specified.
     */
	public void clear(Dungeon d) {
		for(int i = 0; i < this.entities.size(); i++) {
			Entity entity = this.entities.get(i);
			if ((entity instanceof Usable)){
				entity.y().set(- 1);
				entity.x().set(- 1);
			}else if ((entity instanceof Boulder)){
				Boulder b = (Boulder) entity;
				d.removeEntity(b);
				b.y().set(- 1);
	            b.x().set(- 1);
			}else if (entity instanceof Enemy) {
				Enemy e = (Enemy) entity;
				System.out.print("what the fuck?\n");
	        	
	            e.killed();
			}
		}
	}
	

}
