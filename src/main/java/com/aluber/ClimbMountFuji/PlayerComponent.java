package com.aluber.ClimbMountFuji;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

public class PlayerComponent extends Component {

    PhysicsComponent physics;

    private double speedX = 0;
    private double speedY = 0;
    private int collectibleCounter = 0;
    private Entity hammer;
    private Entity leftArm;
    private Entity rightArm;

    @Override
    public void onAdded() {
        generateArmsAndHammer();
    }

    public void moveRight() {
        physics.setVelocityX(200);
    }

    @Override
    public void onUpdate(double tpf) {
        System.out.println("time: " + tpf + ", " + getEntity().getY());
    }

    private void generateArmsAndHammer() {
        //TODO
    }

    public void addCollectibleCounter(){
        collectibleCounter++;
    }

    public int getCollectibleCounter(){
        return collectibleCounter;
    }

}
