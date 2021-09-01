package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image exitImage;
    private Image doorImage;
    private Image keyImage;
    private Image swordImage;
    private Image treasureImage;
    private Image enemyImage;
    private Image potionImage;
    private Image bombImage;
    private Image boulderImage;
    private Image switchImage;

    private Image stage1;
	private Image stage2;
	private Image stage3;
	private Image stage4;
	
	private Image openDoorImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        exitImage = new Image("exit.png");
        switchImage = new Image("pressure_plate.png");
        boulderImage = new Image("boulder.png");
        keyImage = new Image("key.png");
        doorImage = new Image("closed_door.png");
        enemyImage = new Image("deep_elf_master_archer.png");
        swordImage = new Image("greatsword_1_new.png");
        potionImage = new Image("bubbly.png");
        treasureImage = new Image("gold_pile.png");
        bombImage = new Image("bomb_unlit.png");
        
        stage1 = new Image("bomb_lit_1.png");
    	stage2 = new Image("bomb_lit_2.png");
    	stage3 = new Image("bomb_lit_3.png");
    	stage4 = new Image("bomb_lit_4.png");
    	
    	openDoorImage = new Image("open_door.png");


    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    
    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }
    
    @Override
    public void onLoad(Door door) {
    	ImageView view = new ImageView(doorImage); 						// closed door
    	this.trackVisBP(door.locked(), view);
        addEntity(door, view);
        
        ImageView openDoorImage = new ImageView(this.openDoorImage); 	//opened the door
        this.trackVisBP(door.opened(), openDoorImage);
        changeVis(door, openDoorImage);
        
    }
    
    @Override
    public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        addEntity(key, view);
    }
    
	@Override
	public void onLoad(Sword sword) {
		ImageView view = new ImageView(swordImage);
		addEntity(sword, view);
	}
	
	@Override
	public void onLoad(Treasure treasure) {
		ImageView view = new ImageView(treasureImage);
		addEntity(treasure, view);
	}
	
	@Override
	public void onLoad(Enemy enemy) {
		ImageView view = new ImageView(enemyImage);
		addEntity(enemy, view);
	}
	
	@Override
	public void onLoad(InvincibilityPotion potion) {
		ImageView view = new ImageView(potionImage);
		addEntity(potion, view);
	}
	
	@Override
	public void onLoad(Bomb bomb) {
        ImageView view = new ImageView(bombImage);

        ImageView stage1 = new ImageView(this.stage1);
        this.trackVisBP(bomb.getStage1(), stage1);
        changeVis(bomb, stage1);

        ImageView stage2 = new ImageView(this.stage2);
        this.trackVisBP(bomb.getStage2(), stage2);
        changeVis(bomb, stage2);

        ImageView stage3 = new ImageView(this.stage3);
        this.trackVisBP(bomb.getStage3(), stage3);
        changeVis(bomb, stage3);

        ImageView stage4 = new ImageView(this.stage4);
        this.trackVisBP(bomb.getStage4(), stage4);
        changeVis(bomb, stage4);

        addEntity(bomb, view);
	}
	
    private void changeVis(Entity entity, ImageView view) {
        trackPosition(entity, view);
        view.setVisible(false);
        entities.add(view);
    }
	
	@Override
	public void onLoad(Boulder boulder) {
		ImageView view = new ImageView(boulderImage);
		addEntity(boulder, view);
	}

	@Override
	public void onLoad(FloorSwitch floorSwitch) {
		ImageView view = new ImageView(switchImage);
		addEntity(floorSwitch, view);
	}
    
    private void removeEntity(Entity entity, Node view) {
    	view.setVisible(false);
        entities.remove(view);
    }
    
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        trackVisible(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.intValue() >= 0) {
                	node.setVisible(true);
                	GridPane.setColumnIndex(node, newValue.intValue());
                } else {
                	removeEntity(entity, node);
                }
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	if (newValue.intValue() >= 0 && node != null) {
            		node.setVisible(true);
            		GridPane.setRowIndex(node, newValue.intValue());
            	} else {
            		removeEntity(entity, node);
            	}
            }
        });
    }
    
    private void trackVisible(Entity entity, final Node node) {
    	entity.getVisible().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				node.setVisible(newValue.booleanValue());
			}
    		
    	});
    }
    
    
    /**
     * Set a node in a GridPane 
     * @param BooleanProperty
     * @param node
     */
    private void trackVisBP(BooleanProperty b, final Node node){
    	//BooleanProperty b = (BooleanProperty)entity;
        b.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				node.setVisible(newValue.booleanValue());
			}
        	
        });
    }
    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }





}
