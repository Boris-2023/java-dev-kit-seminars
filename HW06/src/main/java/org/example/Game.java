package org.example;

import lombok.Data;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.util.ArrayList;
import java.util.Arrays;

@Data
public class Game {
    ArrayList<Integer> gatesToOpen = new ArrayList<>(Arrays.asList(0, 1, 2));
    RandomDataGenerator rnd;
    Player player;
    private int prizeGate, emptyGateToOpen, playerFinalChoice;
    private boolean isPlayerWin = false;

    public Game() {
        rnd = new RandomDataGenerator();
        prizeGate = rnd.nextInt(0, 2);

        player = new Player();

        emptyGateToOpen = gateToOpen();
        openGate(emptyGateToOpen);
        playerFinalChoice = getPlayerFinalChoice();

        isPlayerWin = hasPlayerWon();
   }


    private int gateToOpen() {
        ArrayList<Integer> gates = new ArrayList<>(Arrays.asList(0, 1, 2));

        int playerChoice = player.getFirstChoice();
        gates.remove(playerChoice);

        if (playerChoice == prizeGate) {
            return gates.get(rnd.nextInt(0, 1));
        } else {
            if (gates.get(0) == prizeGate) return gates.get(1);
        }
        return gates.get(0);
    }

    private void openGate(int gateNumber) {
        gatesToOpen.remove(gateNumber);
    }

    private int getPlayerFinalChoice() {
        int playerFirstChoice = player.getFirstChoice();

        if (!player.isChangeChoice()) return playerFirstChoice;
        if (playerFirstChoice == gatesToOpen.get(0)) return gatesToOpen.get(1);
        return gatesToOpen.get(0);

    }

    private boolean hasPlayerWon() {
        return playerFinalChoice == prizeGate;
    }

    public boolean isPlayerChangedChoice() {
        return player.isChangeChoice();
    }
}
