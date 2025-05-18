package com.aluber.ClimbMountFuji;

import com.almasb.fxgl.app.scene.FXGLMenu;

public class SceneFactory extends com.almasb.fxgl.app.scene.SceneFactory {
    @Override
    public FXGLMenu newGameMenu() {
        return new PauseMenu();
    }
}
