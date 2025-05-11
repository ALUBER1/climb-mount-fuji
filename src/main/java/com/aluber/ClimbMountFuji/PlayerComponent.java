package com.aluber.ClimbMountFuji;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

public class PlayerComponent extends Component {

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

    @Override
    public void onUpdate(double tpf) {
        //TODO
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
