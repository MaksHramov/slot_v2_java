package com.example.composite;

public class NegativeHandler extends Handler {

    public NegativeHandler(Handler processor) {
        super(processor);
    }

    @Override
    public boolean process(Integer request) {
        if (request != ActionChain.LOSS)
            return super.process(request);
        return true;
    }
}
