package com.minijava.common;

public enum RankGachaProbability {
    NORMAL(80),
    RARE(19),
    LEGENDARY(1);

    private final int probability;

    RankGachaProbability(int probability) {
        this.probability = probability;
    }

    public int getProbability() {
        return probability;
    }
}
