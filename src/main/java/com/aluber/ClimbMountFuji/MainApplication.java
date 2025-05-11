package com.aluber.ClimbMountFuji;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.Cursor;

import static com.almasb.fxgl.dsl.FXGL.*;

public class MainApplication extends GameApplication {

    private Entity player;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle("CLIMB MOUNT FUJI");
        gameSettings.setApplicationMode(ApplicationMode.DEBUG);
        gameSettings.setDeveloperMenuEnabled(true);
        gameSettings.setTicksPerSecond(60);
        gameSettings.setExperimentalTiledLargeMap(true);
        gameSettings.setIntroEnabled(false);
        gameSettings.setFullScreenAllowed(true);
        gameSettings.setFullScreenFromStart(true);

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
        view.bindToEntity(player, (getAppWidth()/2) - (player.getWidth()/2), (getAppHeight()/2) - (player.getHeight()/2));
        FXGL.getGameScene().getViewport().setLazy(true);

    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().setGravity(0,3000);
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityTypes.PLAYER, EntityTypes.COLLECTIBLE) {
            @Override
            protected void onCollisionBegin(Entity player, Entity collectible) {
                collectible.getComponent(CollectibleComponent.class).collect(player);
            }
        });

    }
}
