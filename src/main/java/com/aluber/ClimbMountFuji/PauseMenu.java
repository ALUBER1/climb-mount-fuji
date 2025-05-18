package com.aluber.ClimbMountFuji;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.control.Button;

public class PauseMenu extends FXGLMenu {

    public PauseMenu() {
        super(MenuType.GAME_MENU);

        Button resume = FXGL.getUIFactoryService().newButton("resume");
        resume.setOnAction(e -> fireResume());
        resume.setPrefSize(200, 100);
        resume.setStyle(
            "-fx-text-fill: black; " +
            "-fx-text-fill: black;" +
            "-fx-background-image: url('/assets/textures/arm.png');" +
            "-fx-background-repeat: no-repeat;" +
            "-fx-background-position: center;" +
            "-fx-background-size: cover;" +
            "-fx-background-color: transparent"
        );

        resume.setText("resume");

        Button quit = FXGL.getUIFactoryService().newButton("quit");
        quit.setOnAction(e -> fireExit());
        quit.setPrefSize(200, 100);
        quit.setStyle(
                "-fx-text-fill: black; " +
                        "-fx-text-fill: black;" +
                        "-fx-background-image: url('/assets/textures/arm.png');" +
                        "-fx-background-repeat: no-repeat;" +
                        "-fx-background-position: center;" +
                        "-fx-background-size: cover;" +
                        "-fx-background-color: transparent"
        );

        quit.setText("quit");
        quit.setTranslateY(100);

        getContentRoot().getChildren().add(resume);
        getContentRoot().getChildren().add(quit);
    }
}
