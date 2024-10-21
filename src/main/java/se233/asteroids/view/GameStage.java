package se233.asteroids.view;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import se233.asteroids.Launcher;
import se233.asteroids.controller.GameStageController;

public class GameStage extends Pane {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private GameStageController controller;

    public GameStage() {
        setPrefSize(WIDTH, HEIGHT);

        Image backgroundImage = new Image(Launcher.class.getResourceAsStream("/se233/asteroids/assets/background/background.png"));

        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );
        setBackground(new Background(bgImage));

        controller = new GameStageController(this);
        getChildren().addAll(controller.getIdleSprite(), controller.getBoostSprite());

        setOnKeyPressed(event -> {
            controller.handleKeyPressed(event);
        });

        setOnKeyReleased(event -> {
            controller.handleKeyReleased(event);
        });

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                controller.update();
            }
        }.start();
    }

    public int getWidthValue() {
        return WIDTH;
    }

    public int getHeightValue() {
        return HEIGHT;
    }
}
