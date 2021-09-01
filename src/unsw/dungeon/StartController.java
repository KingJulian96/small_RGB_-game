package unsw.dungeon;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class StartController {
	@FXML
    private Button Advenced;
	@FXML
	private Button Boulders;
	@FXML
	private Button Example;
		
    private DungeonApplication app;
    private Stage primary;
	private DungeonScreen dungeonScreen;
    private BouldersScreen bouldersScreen;
    private ExampleScreen exampleScreen;
	private Media media;
	private MediaPlayer mediaPlayer;
		

    @FXML
    void handleAdvencedButton(ActionEvent event) {
		String source = new File("resource/cave.mp3").toURI().toString();
	    media = new Media(source);
	    mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.setAutoPlay(true);
        dungeonScreen.start();
    }

    @FXML
    void handleBouldersButton(ActionEvent event) {
		String source = new File("resource/cave.mp3").toURI().toString();
	    media = new Media(source);
	    mediaPlayer = new MediaPlayer(media);
	    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	    mediaPlayer.setAutoPlay(true);
    	bouldersScreen.start();
    }
    
    @FXML
    void handleExampleButton(ActionEvent event) {
		String source = new File("resource/cave.mp3").toURI().toString();
	    media = new Media(source);
	    mediaPlayer = new MediaPlayer(media);
	    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	    mediaPlayer.setAutoPlay(true);
    	exampleScreen.start();
    }
    
    @FXML
    void handleResetButton(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to reset all dungeons?", ButtonType.YES, ButtonType.NO);
    	alert.setHeaderText("Reset");
    	alert.showAndWait();

    	if (alert.getResult() == ButtonType.YES) {
    	    try {
				app.start(primary);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    public void setApp(DungeonApplication app, Stage stage) {
		this.app = app;
		this.primary = stage;
	}

	public void setAdvencedScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }
    
    public void setBoulderScreen(BouldersScreen bouldersScreen) {
        this.bouldersScreen = bouldersScreen;
    }
    
    public void setExampleScreen(ExampleScreen exampleScreen) {
        this.exampleScreen = exampleScreen;
    }
    
    public void stopMusic() {
    	mediaPlayer.stop();
    }

}
