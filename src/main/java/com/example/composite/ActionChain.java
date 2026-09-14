package com.example.composite;

import java.util.Random;

public class ActionChain {

    public static final int SUCCESS = 1;
    public static final int CHANCE = 2;
    public static final int LOSS = 3;

    private final Handler chain;
    private final Player player;
    private final Random generate = new Random();

    public ActionChain(Player player) {
        this.player = player;
        this.chain = buildChain();
    }

    private Handler buildChain() {
        return new NegativeHandler(
                new ChanceHandler(
                        new PositiveHandler(null, player),
                        player));
    }

    public int drawType() {
        int games = player.getCount();
        int roll = generate.nextInt(100);
        if (games <= 3) {
            if (roll < 40) return CHANCE;
            if (roll < 70) return SUCCESS;
            return LOSS;
        }
        if (games <= 8) {
            if (roll < 20) return CHANCE;
            if (roll < 55) return SUCCESS;
            return LOSS;
        }
        if (roll < 10) return CHANCE;
        if (roll < 35) return SUCCESS;
        return LOSS;
    }

    public boolean process(Integer type) {
        return chain.process(type);
    }
}
