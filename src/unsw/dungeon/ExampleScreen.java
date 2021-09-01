package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExampleScreen {
	private Stage stage;
    private String title;
    private DungeonController controller;
    private Scene scene;

    public ExampleScreen(Stage stage) throws IOException{
    	this.stage = stage;
    	title = "Easy Screen";
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("example.json");

        controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("View/DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

    }

    public DungeonController getController() {
        return controller;
    }
}
