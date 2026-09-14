package com.example.composite;

public class PositiveHandler extends Handler {

    private static final int WIN_AMOUNT = 2;

    private final Player player;

    public PositiveHandler(Handler processor, Player player) {
        super(processor);
        this.player = player;
    }

    @Override
    public boolean process(Integer request) {
        if (request != ActionChain.SUCCESS)
            return super.process(request);
        player.addNumber(WIN_AMOUNT);
        return true;
    }
}
