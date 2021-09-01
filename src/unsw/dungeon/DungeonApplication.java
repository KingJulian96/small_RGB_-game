package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class DungeonApplication extends Application{
  @Override
   public void start(Stage primaryStage) throws IOException {
        StartScreen startScreen = new StartScreen(primaryStage);
        DungeonScreen dungeonScreen = new DungeonScreen(primaryStage);
        BouldersScreen bouldersScreen = new BouldersScreen(primaryStage);
        ExampleScreen exampleScreen = new ExampleScreen(primaryStage);
        
        startScreen.getController().setApp(this, primaryStage);

        startScreen.getController().setAdvencedScreen(dungeonScreen);
        startScreen.getController().setBoulderScreen(bouldersScreen);
        startScreen.getController().setExampleScreen(exampleScreen);
        
        dungeonScreen.getController().setStartScreen(startScreen);
        bouldersScreen.getController().setStartScreen(startScreen);
        exampleScreen.getController().setStartScreen(startScreen);
        
        startScreen.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

