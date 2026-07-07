package com.janish.tictactoe.view;

import com.janish.tictactoe.model.Difficulty;
import javafx.scene.control.ComboBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameView {

    private final BorderPane root;

    private final Label titleLabel;
    private final Label statusLabel;

    private final Label xScoreLabel;
    private final Label oScoreLabel;
    private final Label drawScoreLabel;
    private final RadioButton humanVsHumanButton;
    private final RadioButton humanVsComputerButton;
    private final ComboBox<Difficulty> difficultyComboBox;

    private final Button[][] boardButtons;

    private final Button playAgainButton;
    private final Button resetScoresButton;

    public GameView() {

        root = new BorderPane();

        titleLabel = new Label("Tic Tac Toe");
        statusLabel = new Label("Current Turn: X");

        xScoreLabel = new Label("Player X: 0");
        oScoreLabel = new Label("Player O: 0");
        drawScoreLabel = new Label("Draws: 0");
        humanVsHumanButton = new RadioButton("Human vs Human");
        humanVsComputerButton = new RadioButton("Human vs Computer");

        ToggleGroup gameModeGroup = new ToggleGroup();

        humanVsHumanButton.setToggleGroup(gameModeGroup);
        humanVsComputerButton.setToggleGroup(gameModeGroup);

        humanVsHumanButton.setSelected(true);
        difficultyComboBox = new ComboBox<>();
        difficultyComboBox.getItems().addAll(
            Difficulty.EASY,
            Difficulty.MEDIUM,
            Difficulty.HARD
        );
        difficultyComboBox.setValue(Difficulty.HARD);
        difficultyComboBox.setDisable(true);

        boardButtons = new Button[3][3];

        playAgainButton = new Button("Play Again");
        resetScoresButton = new Button("Reset Scores");

        createLayout();
    }

    private void createLayout() {

        titleLabel.getStyleClass().add("title-label");
        statusLabel.getStyleClass().add("status-label");
        playAgainButton.getStyleClass().add("reset-button");
        resetScoresButton.getStyleClass().add("reset-button");

        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setMargin(titleLabel, new Insets(20, 0, 20, 0));
        root.setTop(titleLabel);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(8);
        grid.setVgap(8);
        grid.setPadding(new Insets(15));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                Button button = new Button();

                button.setMinSize(90, 90);
                button.setPrefSize(100, 100);
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                button.getStyleClass().add("board-button");

                boardButtons[row][col] = button;

                grid.add(button, col, row);
            }
        }

        root.setCenter(grid);

        HBox modeBox = new HBox(25);
        modeBox.setAlignment(Pos.CENTER);
        
        modeBox.getChildren().addAll(
            humanVsHumanButton,
            humanVsComputerButton
        );

        HBox difficultyBox = new HBox(12);
        difficultyBox.setAlignment(Pos.CENTER);
        Label difficultyLabel = new Label("Difficulty:");
        difficultyBox.getChildren().addAll(
            difficultyLabel,
            difficultyComboBox
        );

        HBox scoreBox = new HBox(30);
        scoreBox.setAlignment(Pos.CENTER);
        scoreBox.getChildren().addAll(
            xScoreLabel,
            oScoreLabel,
            drawScoreLabel
        );

        VBox bottomBox = new VBox(18);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(20));

        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(
            playAgainButton,
            resetScoresButton
        );
        
        bottomBox.getChildren().addAll(
            statusLabel,
            modeBox,
            difficultyBox,
            scoreBox,
            buttonBox
        );

        root.setBottom(bottomBox);
    }

    public Parent getRoot() {
        return root;
    }

    public void setDifficultyEnabled(boolean enabled) {
        difficultyComboBox.setDisable(!enabled);
    }

    public Button[][] getBoardButtons() {
        return boardButtons;
    }

    public Label getStatusLabel() {
        return statusLabel;
    }

    public Button getPlayAgainButton() {
        return playAgainButton;
    }
    
    public Button getResetScoresButton() {
        return resetScoresButton;
    }

    public Label getXScoreLabel() {
        return xScoreLabel;
    }

    public Label getOScoreLabel() {
        return oScoreLabel;
    }

    public Label getDrawScoreLabel() {
        return drawScoreLabel;
    }
    public RadioButton getHumanVsHumanButton() {
        return humanVsHumanButton;
    }
    
    public RadioButton getHumanVsComputerButton() {
        return humanVsComputerButton;
    }

    public ComboBox<Difficulty> getDifficultyComboBox() {
        return difficultyComboBox;
    }
}