package com.aluber.ClimbMountFuji;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;

import static com.almasb.fxgl.dsl.FXGL.*;

public class MainApplication extends GameApplication {

    private Entity player;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle("CLIMB MOUNT FUJI");
        gameSettings.setDeveloperMenuEnabled(true);
        gameSettings.setApplicationMode(ApplicationMode.DEVELOPER);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initUI() {
        FXGL.getGameScene().setCursor(Cursor.DEFAULT);
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new Factory());
        FXGL.setLevelFromMap("level/Project.tmx");
        Viewport view = FXGL.getGameScene().getViewport();
        player = spawn("player", 1750,18800);
        view.bindToEntity(player, 275, 226);
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityTypes.PLAYER, EntityTypes.COLLECTIBLE) {
            @Override
            protected void onCollisionBegin(Entity player, Entity collectible) {
                collectible.getComponent(CollectibleComponent.class).collect(player);
            }
        });

    }
}
