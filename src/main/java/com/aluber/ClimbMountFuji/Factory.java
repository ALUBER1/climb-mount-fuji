package com.aluber.ClimbMountFuji;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;

public class Factory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(EntityTypes.PLAYER)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .build();
    }
}
