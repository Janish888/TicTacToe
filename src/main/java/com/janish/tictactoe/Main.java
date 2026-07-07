package com.janish.tictactoe;

import com.janish.tictactoe.controller.GameController;
import com.janish.tictactoe.view.GameView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        GameView gameView = new GameView();

        new GameController(gameView);

        Scene scene = new Scene(gameView.getRoot());
        stage.setScene(scene);
        stage.sizeToScene();

        scene.getStylesheets().add(
                getClass().getResource("/css/style.css").toExternalForm());

        stage.setTitle("Tic Tac Toe");

        stage.setScene(scene);

        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}