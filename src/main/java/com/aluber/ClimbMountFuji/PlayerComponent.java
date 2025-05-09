package com.aluber.ClimbMountFuji;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.joints.RevoluteJoint;
import com.almasb.fxgl.physics.box2d.dynamics.joints.RevoluteJointDef;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class PlayerComponent extends Component {

    PhysicsComponent physics;

    private double speedX = 0;
    private double speedY = 0;
    private int collectibleCounter = 0;
    private Entity hammer;

    private Entity leftArm;
    private Entity rightArm;

    private Entity leftHand;
    private Entity rightHand;

    @Override
    public void onAdded() {
        FXGL.runOnce(this::generateArmsAndHammer, Duration.millis(0.0));
    }

    public void moveRight() {
        physics.setVelocityX(200);
    }

    @Override
    public void onUpdate(double tpf) {

        PhysicsComponent physicsComponent = rightArm.getComponent(PhysicsComponent.class);

        Point2D pos = FXGL.getInput().getMousePositionWorld();



    }

    private void generateArmsAndHammer() {
        Entity player = getGameWorld().getSingleton(EntityType.PLAYER);

        leftArm = spawn("arm",player.getX(), player.getY());
        FXGL.getPhysicsWorld().addRevoluteJoint(
                player,
                leftArm,
                new Point2D(70, 56 + leftArm.getBoundingBoxComponent().getHeight()/2),
                new Point2D(0, leftArm.getBoundingBoxComponent().getHeight()/2)
        );

        rightArm = spawn("arm",player.getX(), player.getY());
        FXGL.getPhysicsWorld().addRevoluteJoint(
                player,
                rightArm,
                new Point2D(0, 56 + rightArm.getBoundingBoxComponent().getHeight()/2),
                new Point2D(0, rightArm.getBoundingBoxComponent().getHeight()/2)
        );

        leftHand = spawn("hand", player.getX(), player.getY());
        FXGL.getPhysicsWorld().addRevoluteJoint(
                leftArm,
                leftHand,
                new Point2D(leftArm.getBoundingBoxComponent().getWidth(), leftArm.getBoundingBoxComponent().getHeight()/2),
                new Point2D(0, leftArm.getBoundingBoxComponent().getHeight()/2)
        );

    }

    public void addCollectibleCounter(){
        collectibleCounter++;
    }

    public int getCollectibleCounter(){
        return collectibleCounter;
    }

}
