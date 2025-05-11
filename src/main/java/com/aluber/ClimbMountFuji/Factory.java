package com.aluber.ClimbMountFuji;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;

public class Factory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();

        physics.setBodyType(BodyType.DYNAMIC);

        FixtureDef def = new FixtureDef();
        def.getFilter().categoryBits = 0x0004;
        def.getFilter().maskBits = 0x0002;

        physics.setFixtureDef(def);

        return FXGL.entityBuilder(data)
                .type(EntityTypes.PLAYER)
                .viewWithBBox("player.png")
                .with(physics)
                .with(new PlayerComponent())
                .build();
    }

    @Spawns("checkpoint")
    public Entity newCheckpoint(SpawnData data){


        return FXGL.entityBuilder(data).type(EntityTypes.CHECKPOINT)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .collidable()
                .build();
    }






    @Spawns("platform")
    public Entity newPlatform(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        FixtureDef def = new FixtureDef();
        def.getFilter().categoryBits = 0x0002;
        def.getFilter().maskBits = 0xFFFF;
        def.setFriction(1.0f);

        physics.setFixtureDef(def);

        return FXGL.entityBuilder(data)
                .type(EntityTypes.PLATFORM)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .collidable()
                .with(physics)
                .build();
    }

    @Spawns("arm")
    public Entity newArm(SpawnData data) {
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.DYNAMIC);

        FixtureDef def = new FixtureDef();
        def.getFilter().groupIndex = -1;
        //def.setDensity(50f);

        physicsComponent.setFixtureDef(def);

        return FXGL.entityBuilder(data)
                .type(EntityTypes.ARMS)
                .view("arm.png")
                .bbox(new HitBox(BoundingShape.box(38, 17)))
                .with(physicsComponent)
                .build();
    }

    @Spawns("hand")
    public Entity newHand(SpawnData data) {
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.DYNAMIC);

        FixtureDef def = new FixtureDef();
        def.getFilter().groupIndex = -1;
        //def.setDensity(50f);

        physicsComponent.setFixtureDef(def);

        return FXGL.entityBuilder(data)
                .type(EntityTypes.ARMS)
                .view("hand.png")
                .bbox(new HitBox(BoundingShape.box(38, 17)))
                .with(physicsComponent)
                .build();
    }

    @Spawns("hammer")
    public Entity newHammer(SpawnData data) {
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.DYNAMIC);

        FixtureDef def = new FixtureDef();
        def.getFilter().groupIndex = -1;
        def.setFriction(1.0f);

        physicsComponent.setFixtureDef(def);

        return FXGL.entityBuilder(data)
                .type(EntityTypes.HAMMER)
                .view("hammer.png")
                .bbox(new HitBox(BoundingShape.box(30, 120)))
                .with(physicsComponent)
                .build();
    }

    @Spawns("collectible")
    public Entity newCollectible(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        return FXGL.entityBuilder(data)
                .type(EntityTypes.COLLECTIBLE)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .collidable()
                .with(physics)
                .with(new CollectibleComponent())
                .build();
    }
}
