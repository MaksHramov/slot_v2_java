package com.example.composite;

public abstract class Handler {

    private Handler processor;

    public Handler(Handler processor) {
        this.processor = processor;
    }

    public boolean process(Integer request) {
        if (processor != null)
            return processor.process(request);
        else
            return true;
    }
}
