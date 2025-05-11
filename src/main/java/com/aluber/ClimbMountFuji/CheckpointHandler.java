package com.aluber.ClimbMountFuji;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;

public class CheckpointHandler extends CollisionHandler {

    private BufferedWriter writer;
        private String filename = " testo";

        public CheckpointHandler() {
            super(EntityTypes.PLAYER, EntityTypes.CHECKPOINT);
        }

        @Override
        protected void onCollisionBegin(Entity player, Entity checkpoint) {
            try {
                writer = new BufferedWriter(new FileWriter(filename));
            }catch (IOException e){

            }
            //writer.write(checkpoint.getx);
            //writer.write(checkpoint.gety);

        }

        @Override
        protected void onCollisionEnd(Entity player, Entity btn) {
            //stampa checkpoint
        }
}
