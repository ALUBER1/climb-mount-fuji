package com.aluber.ClimbMountFuji;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class PlayerComponent extends Component {

    PhysicsComponent physics;

    private Entity player;

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

    @Override
    public void onUpdate(double tpf) {

        double ratio = 10;

        Point2D pos = FXGL.getInput().getMousePositionWorld();
        double dx = pos.getX() - leftArm.getX();
        double dy = pos.getY() - leftArm.getY();
        double dist = Math.sqrt(dx * dx + dy * dy);

        double phi = Math.atan2(-dy,dx);

        double preTheta = Math.max(-1, Math.min(1, (dist/2)/leftArm.getBoundingBoxComponent().getWidth()));

        double theta = Math.acos(preTheta);

        double alpha1 = phi + theta;
        double alpha2 = phi - theta;

        double dx2 = pos.getX() - rightArm.getX();
        double dy2 = pos.getY() - rightArm.getY();
        double dist2 = Math.sqrt(dx2 * dx2 + dy2 * dy2);

        double phi2 = Math.atan2(-dy2,dx2);

        double preTheta2 = Math.max(-1, Math.min(1, (dist2/2)/rightArm.getBoundingBoxComponent().getWidth()));

        double theta2 = Math.acos(preTheta2);

        double alpha1right = phi2 - theta2;
        double alpha2right = phi2 + theta2;

        double leftASpeed = shortestAngle((float) alpha2, leftArm.getComponent(PhysicsComponent.class).getBody().getAngle()) * ratio;
        double leftHSpeed = shortestAngle((float) alpha1, leftHand.getComponent(PhysicsComponent.class).getBody().getAngle()) * ratio;
        double rightASpeed = shortestAngle((float) alpha2right, rightArm.getComponent(PhysicsComponent.class).getBody().getAngle()) * ratio;
        double rightHSpeed = shortestAngle((float) alpha1right, rightHand.getComponent(PhysicsComponent.class).getBody().getAngle()) * ratio;
        double hammerSpeed = shortestAngle((float) (phi2 - (Math.PI/2)), hammer.getComponent(PhysicsComponent.class).getBody().getAngle())*ratio;

        leftArm.getComponent(PhysicsComponent.class).getBody().setAngularVelocity((float) leftASpeed);
        leftHand.getComponent(PhysicsComponent.class).getBody().setAngularVelocity((float) leftHSpeed);
        rightArm.getComponent(PhysicsComponent.class).getBody().setAngularVelocity((float) rightASpeed);
        rightHand.getComponent(PhysicsComponent.class).getBody().setAngularVelocity((float) rightHSpeed);

        hammer.getComponent(PhysicsComponent.class).getBody().setAngularVelocity((float)hammerSpeed);
    }

    private float shortestAngle(float target, float current) {
        float diff = target - current;
        while (diff > Math.PI) diff -= (float)(2 * Math.PI);
        while (diff < -Math.PI) diff += (float)(2 * Math.PI);
        return diff;
    }

    private void generateArmsAndHammer() {
        player = getGameWorld().getSingleton(EntityTypes.PLAYER);

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
                new Point2D(rightArm.getWidth(), rightArm.getHeight()/2),
                new Point2D(0, rightArm.getBoundingBoxComponent().getHeight()/2)
        );

        hammer = spawn("hammer", player.getX(), player.getY());
        FXGL.getPhysicsWorld().addRevoluteJoint(
                hammer,
                leftHand,
                new Point2D(hammer.getBoundingBoxComponent().getWidth()/2, hammer.getBoundingBoxComponent().getHeight()),
                new Point2D(leftHand.getBoundingBoxComponent().getWidth(), leftHand.getBoundingBoxComponent().getHeight()/2)
        );

        FXGL.getPhysicsWorld().addRevoluteJoint(
                hammer,
                rightHand,
                new Point2D(hammer.getBoundingBoxComponent().getWidth()/2, hammer.getBoundingBoxComponent().getHeight()),
                new Point2D(rightHand.getBoundingBoxComponent().getWidth(), rightHand.getBoundingBoxComponent().getHeight()/2)
        );

        physics.getBody().setFixedRotation(true);
    }

    public void addCollectibleCounter(){
        collectibleCounter++;
    }

    public int getCollectibleCounter(){
        return collectibleCounter;
    }

}
