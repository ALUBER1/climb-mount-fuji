package com.aluber.ClimbMountFuji;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

public class CollectibleComponent extends Component {

    private boolean collected = false;

    public void collect(Entity player) {
        if (!collected) {
            collected = true;
            player.getComponent(PlayerComponent.class).addCollectibleCounter();

            entity.removeFromWorld();
        }
    }
}

