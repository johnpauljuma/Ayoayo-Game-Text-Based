package org.ayoayo;

import java.util.Arrays;

public class Player {
    private String name;
    private int store;
    private int[] pits;

    public Player(String name) {
        this.name = name;
        this.store = 0;
        this.pits = new int[6];
        Arrays.fill(this.pits, 4); // Initial 4 seeds in each pit
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getStore() {
        return store;
    }

    public int[] getPits() {
        return pits;
    }

    public void addToStore(int seeds) {
        store += seeds;
    }
}