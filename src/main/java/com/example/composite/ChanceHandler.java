package com.example.composite;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ChanceHandler extends Handler {

    private final Player player;

    public ChanceHandler(Handler processor, Player player) {
        super(processor);
        this.player = player;
    }

    @Override
    public boolean process(Integer request) {
        if (request != ActionChain.CHANCE)
            return super.process(request);

        player.addNumber(1);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Шанс!");
        alert.setHeaderText("Судьба возвращает вам 1 монету!");
        alert.getButtonTypes().setAll(new ButtonType("Спасибо"));
        alert.showAndWait();
        return true;
    }
}
