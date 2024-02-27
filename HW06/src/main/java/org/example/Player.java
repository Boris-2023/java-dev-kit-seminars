package org.example;

import lombok.Data;

import java.util.Random;

@Data
public class Player {
    private int firstChoice;
    private boolean isChangeChoice;

    Random rnd;


    public Player() {
        randomSeedDelay(3);
        rnd = new Random(System.currentTimeMillis());
        firstChoice = rnd.nextInt(0,3);
        isChangeChoice = rnd.nextBoolean();
    }

    private void randomSeedDelay(int delayMillis){
        try {
            Thread.sleep(delayMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
