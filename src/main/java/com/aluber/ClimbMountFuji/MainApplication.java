package com.aluber.ClimbMountFuji;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.Cursor;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class MainApplication extends GameApplication {

    private Entity player;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle("CLIMB MOUNT FUJI");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initUI() {
        getGameScene().setCursor(Cursor.DEFAULT);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new Factory());
        player = getGameWorld().spawn("player", getAppWidth(),getAppHeight());
        player.addComponent(new PlayerComponent());
    }
}
