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

        Point2D pos = FXGL.getInput().getMousePositionWorld();
        double dx = pos.getX() - rightArm.getX();
        double dy = pos.getY() - rightArm.getY();
        double dist = Math.sqrt(dx * dx + dy * dy);

        double phi = Math.atan2(dy, dx);
        double cosTheta = (rightHand.getWidth() * rightHand.getWidth() + rightHand.getWidth() * rightHand.getWidth() - dist * dist)/(2*rightHand.getWidth()*rightHand.getWidth());
        cosTheta = Math.max(-1, Math.min(1, cosTheta));
        double theta = Math.acos(cosTheta);

        double alpha1 = phi + theta;
        double alpha2 = phi - theta;

        rightHand.getComponent(PhysicsComponent.class).getBody().setAngularVelocity((float) (alpha2 - rightHand.getComponent(PhysicsComponent.class).getBody().getAngle()));
        rightArm.getComponent(PhysicsComponent.class).getBody().setAngularVelocity((float) (alpha1 - rightArm.getComponent(PhysicsComponent.class).getBody().getAngle()));
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

        rightHand = spawn("hand", player.getX(), player.getY());
        FXGL.getPhysicsWorld().addRevoluteJoint(
                rightArm,
                rightHand,
                new Point2D(rightArm.getBoundingBoxComponent().getWidth(), rightArm.getBoundingBoxComponent().getHeight()/2),
                new Point2D(0, rightArm.getBoundingBoxComponent().getHeight()/2)
        );
    }

    public void addCollectibleCounter(){
        collectibleCounter++;
    }

    public int getCollectibleCounter(){
        return collectibleCounter;
    }

}
