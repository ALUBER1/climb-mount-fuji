package com.aluber.ClimbMountFuji;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import java.io.*;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;

public class CheckpointHandler extends CollisionHandler {

    private BufferedWriter writer;
        private static String filename = "./../../../resources/Saves/Save.txt";

        public CheckpointHandler() {
            super(EntityTypes.PLAYER, EntityTypes.CHECKPOINT);
        }

        @Override
        protected void onCollisionBegin(Entity player, Entity checkpoint) {
            try {
                ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filename));
                stream.writeObject(player);
                stream.close();

            }catch (IOException e){

            }

        }

    protected static Entity onRestart(){
            Entity a=null;
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filename));
            a =(Entity) stream.readObject();
            stream.close();

        }catch (IOException | ClassNotFoundException e){

        }
            return a;
    }


        @Override
        protected void onCollisionEnd(Entity player, Entity btn) {
            //stampa checkpoint
        }
}
