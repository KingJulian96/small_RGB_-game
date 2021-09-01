package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController{

    @FXML
    private GridPane squares;
    @FXML
    private Button back;
    
   
    @FXML
    private MenuItem list;
    
    @FXML
    private Button controls;
    
    @FXML
    private Label Goal;
    
    @FXML
    private Button Invt;
    
    @FXML
    private BorderPane background;
    
    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

	private StartScreen startScreen;
	

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
    	
        setGoal();
    }
    
    public void setStartScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
    }
    
    public StartScreen StartScreen() { 
        return this.startScreen;
    }

    @FXML
    public void handleBackButton(ActionEvent event) {
    	startScreen.getController().stopMusic();
        startScreen.start();
    }
    
    @FXML
    public void handleInvkButton(ActionEvent event) {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	alert.setHeaderText("Player's Inventory");
    	alert.getDialogPane().setContentText(this.dungeon.getPlayer().toString());
    	alert.showAndWait();
    }
    
    @FXML
    public void handleControlsButton(ActionEvent event) {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	alert.setHeaderText("Controls");
    	String controls = "Use sword in directions: (W) (A) (S) (D)\n";
    	controls += "Use invincibility potion: (Z)\n";
    	controls += "Set bomb: (X)\n";
    	controls += "Drop key: (C)\n";
    	alert.getDialogPane().setContentText(controls);
    	alert.showAndWait();
    }
    
    public void setGoal() {
    	Goal.setText(dungeon.goalString());
    }
    
    
    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        case W:
        	player.useSword(Direction.up);
        	break;
        case S:
        	player.useSword(Direction.down);
        	break;
        case A:
        	player.useSword(Direction.left);
        	break;
        case D:
        	player.useSword(Direction.right);
        	break;
        case X:
        	player.setBomb();    		
        	break;
        case C:
        	player.dropKey();
        	break;
        case Z:
        	player.usePotion();
        	break;
        default:
            break;
        }
        dungeon.moveEnemies();
    }

 

}

