package org.ayoayo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ayoayo {
    private Player player1;
    private Player player2;
    private boolean gameEnded;
    private int currentPlayer; // 1 or 2

    public Ayoayo() {
        this.gameEnded = false;
        this.currentPlayer = 1; // Player 1 starts
    }

    public Player createPlayer(String name) {
        if (player1 == null) {
            player1 = new Player(name);
            return player1;
        } else if (player2 == null) {
            player2 = new Player(name);
            return player2;
        }
        return null; // Both players already created
    }

    public void printBoard() {
        System.out.println("player1:");
        System.out.println("store: " + player1.getStore());
        System.out.println(Arrays.toString(player1.getPits()));

        System.out.println("player2:");
        System.out.println("store: " + player2.getStore());
        System.out.println(Arrays.toString(player2.getPits()));
    }

    public String returnWinner() {
        if (!gameEnded) {
            return "Game has not ended";
        }

        int p1Score = player1.getStore();
        int p2Score = player2.getStore();

        if (p1Score > p2Score) {
            return "Winner is player 1: " + player1.getName();
        } else if (p2Score > p1Score) {
            return "Winner is player 2: " + player2.getName();
        } else {
            return "It's a tie";
        }
    }

    public List<Integer> playGame(int playerIndex, int pitIndex) {
        // Validate input
        if (gameEnded) {
            return getBoardState("Game is ended");
        }

        if (pitIndex <= 0 || pitIndex > 6) {
            return getBoardState("Invalid number for pit index");
        }

        Player current = (playerIndex == 1) ? player1 : player2;
        Player opponent = (playerIndex == 1) ? player2 : player1;

        // Adjust pitIndex to array index (0-5)
        int adjustedPitIndex = pitIndex - 1;
        int[] currentPits = current.getPits();

        // Check if pit is empty
        if (currentPits[adjustedPitIndex] == 0) {
            return getBoardState("Selected pit is empty");
        }

        // Collect seeds
        int seedsToSow = currentPits[adjustedPitIndex];
        currentPits[adjustedPitIndex] = 0;

        boolean extraTurn = false;
        boolean lastSeedInEmptyPit = false;
        int lastPitIndex = -1;
        Player lastPitOwner = null;

        // Sow seeds
        int currentPosition = adjustedPitIndex + 1; // Start sowing to the right
        while (seedsToSow > 0) {
            // Sow in current player's pits
            for (int i = currentPosition; i < 6 && seedsToSow > 0; i++) {
                currentPits[i]++;
                seedsToSow--;
                lastPitIndex = i;
                lastPitOwner = current;
                if (seedsToSow == 0) break;
            }

            // Sow in current player's store if any seeds left
            if (seedsToSow > 0) {
                current.addToStore(1);
                seedsToSow--;
                lastPitIndex = -1; // -1 indicates store
                lastPitOwner = current;
                if (seedsToSow == 0) {
                    extraTurn = true;
                    break;
                }
            }

            // Sow in opponent's pits if any seeds left
            int[] opponentPits = opponent.getPits();
            for (int i = 0; i < 6 && seedsToSow > 0; i++) {
                opponentPits[i]++;
                seedsToSow--;
                lastPitIndex = i;
                lastPitOwner = opponent;
                if (seedsToSow == 0) break;
            }

            currentPosition = 0; // Reset position for next full loop
        }

        // Check for capture rule
        if (lastPitIndex != -1 && lastPitOwner == current &&
                currentPits[lastPitIndex] == 1) { // Last seed landed in empty pit

            int oppositePitIndex = 5 - lastPitIndex;
            int[] opponentPits = opponent.getPits();
            int capturedSeeds = opponentPits[oppositePitIndex];

            if (capturedSeeds > 0) {
                opponentPits[oppositePitIndex] = 0;
                current.addToStore(capturedSeeds + 1); // Captured seeds + last seed
                currentPits[lastPitIndex] = 0;
            }
        }

        // Check for game end condition
        boolean p1Empty = true;
        for (int seeds : player1.getPits()) {
            if (seeds > 0) {
                p1Empty = false;
                break;
            }
        }

        boolean p2Empty = true;
        for (int seeds : player2.getPits()) {
            if (seeds > 0) {
                p2Empty = false;
                break;
            }
        }

        if (p1Empty || p2Empty) {
            gameEnded = true;
            // Collect remaining seeds
            Player remainingPlayer = p1Empty ? player2 : player1;
            int[] remainingPits = remainingPlayer.getPits();
            int remainingSeeds = 0;
            for (int i = 0; i < 6; i++) {
                remainingSeeds += remainingPits[i];
                remainingPits[i] = 0;
            }
            remainingPlayer.addToStore(remainingSeeds);
        }

        // Print extra turn message if applicable
        if (extraTurn) {
            System.out.println("player " + playerIndex + " take another turn");
        }

        return getBoardState();
    }

    private List<Integer> getBoardState(String message) {
        if (message != null) {
            System.out.println(message);
        }
        return getBoardState();
    }

    private List<Integer> getBoardState() {
        List<Integer> state = new ArrayList<>();

        // Player 1 pits
        for (int seeds : player1.getPits()) {
            state.add(seeds);
        }
        // Player 1 store
        state.add(player1.getStore());

        // Player 2 pits
        for (int seeds : player2.getPits()) {
            state.add(seeds);
        }
        // Player 2 store
        state.add(player2.getStore());

        return state;
    }
}