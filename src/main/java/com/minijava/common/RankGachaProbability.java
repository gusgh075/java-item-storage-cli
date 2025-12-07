package com.minijava.common;

/**
 * 모든 등급의 합이 100이 되어야한다.
 */
public enum RankGachaProbability {
    NORMAL(80,"일반"),
    RARE(19,"희귀"),
    LEGENDARY(1,"레전더리");

    private final int probability;
    private final String rank;

    RankGachaProbability(int probability, String rank) {
        this.probability = probability;
        this.rank = rank;
    }

    public int getProbability() {
        return probability;
    }
    public String getRank(){
        return rank;
    }
}
