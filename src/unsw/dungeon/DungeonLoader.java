package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    private SubGoal exit;
    private SubGoal treasure;
    private SubGoal enemies;
    private SubGoal boulders;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
        exit = new SubGoal(new ArrayList<Entity>(), "Get to the exit");
        treasure = new SubGoal(new ArrayList<Entity>(), "Collect all treasure");
        enemies = new SubGoal(new ArrayList<Entity>(), "Kill all enemies");
        boulders = new SubGoal(new ArrayList<Entity>(), "Trigger all switches");
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }

        JSONObject goals = json.getJSONObject("goal-condition");
        Goal goal = setGoals(goals);
        dungeon.setGoal(goal);
        dungeon.setGoalString(goal.goalName());
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "exit":
        	Exit exit = new Exit(dungeon, x,y);
        	onLoad(exit);
        	entity = exit;
        	this.exit.addEntity(exit);
        	exit.setGoal(this.exit);
        	break;
        case "door":
        	int doorId = json.getInt("id");
        	Door door = new Door(doorId, x, y);
        	onLoad(door);
        	entity = door;
        	break;
        case "key":
        	int keyId = json.getInt("id");
        	Key key = new Key(keyId, x, y);
        	onLoad(key);
        	entity = key;
        	break;
        case "sword":
        	Sword sword = new Sword(x, y);
        	onLoad(sword);
        	entity = sword;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x, y);
        	onLoad(treasure);
        	entity = treasure;
        	this.treasure.addEntity(treasure);
        	treasure.setGoal(this.treasure);
        	break;
        case "enemy":
        	Enemy enemy = new Enemy(dungeon, x, y);
        	onLoad(enemy);
        	entity = enemy;
        	this.enemies.addEntity(enemy);
        	enemy.setGoal(enemies);
        	dungeon.addEnemies(enemy);
        	break;

        case "boulder":
        	Boulder boulder = new Boulder(dungeon, x, y);
            onLoad(boulder);
            entity = boulder;
            break;

        case "invincibility":
        	InvincibilityPotion potion = new InvincibilityPotion(x, y);
        	onLoad(potion);
        	entity = potion;
        	break;
        case "bomb":
        	Bomb bomb = new Bomb(x, y);
        	onLoad(bomb);
        	entity = bomb;
        	break;

        case "switch":
        	FloorSwitch floorSwitch = new FloorSwitch(x, y);
        	onLoad(floorSwitch);
        	entity = floorSwitch;
        	this.boulders.addEntity(floorSwitch);
        	floorSwitch.setGoal(boulders);
        	break;
        }
        dungeon.addEntity(entity);

    }

    public Goal setGoals(JSONObject goals) {

    	String goal = goals.getString("goal");
    	Goal all = null;
    	switch(goal) {
    	case "AND":
    		JSONArray subgoalsAnd = goals.getJSONArray("subgoals");
    		all = new AndGoal();
    		for (int i = 0; i < goals.length(); i++) {
                all.addGoal(setGoals(subgoalsAnd.getJSONObject(i)));
            }
    		break;
    	case "OR":
    		JSONArray subgoalsOr = goals.getJSONArray("subgoals");
    		all = new OrGoal();
    		for (int i = 0; i < goals.length(); i++) {
                all.addGoal(setGoals(subgoalsOr.getJSONObject(i)));
            }
    		break;

    	default:
    		switch(goal) {
    		case "exit":
    			all = exit;
    			break;
    		case "treasure":
    			all = treasure;
    			break;
    		case "enemies":
    			all = enemies;
    			break;
    		case "boulders":
    			all = boulders;
    			break;
    		}
    	}
    	return all;
    }


    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Exit exit);

    public abstract void onLoad(Door door);

    public abstract void onLoad(Key key);

    public abstract void onLoad(Sword sword);

    public abstract void onLoad(Treasure treasure);

    public abstract void onLoad(Enemy enemy);

    public abstract void onLoad(InvincibilityPotion potion);

    public abstract void onLoad(Bomb bomb);

    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(FloorSwitch floorSwitch);
}
