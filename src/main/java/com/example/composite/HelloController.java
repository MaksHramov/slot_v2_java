package com.example.composite;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HelloController {

    private static final String[] SYMBOLS = {"7", "★", "●", "◆", "▼"};

    @FXML
    private Label balanceLabel;
    @FXML
    private Label reel1;
    @FXML
    private Label reel2;
    @FXML
    private Label reel3;
    @FXML
    private Button spinButton;

    private final Player player1 = new Player("Игрок", 10);
    private final Random random = new Random();
    private final ActionChain action = new ActionChain(player1);
    private boolean spinning;
    private int pendingType;

    @FXML
    public void initialize() {
        reel1.setText("?");
        reel2.setText("?");
        reel3.setText("?");
        updateBalance();
    }

    @FXML
    public void onPay(ActionEvent actionEvent) {
        player1.addNumber(1);
        updateBalance();
    }

    @FXML
    public void onSpin(ActionEvent actionEvent) {
        if (spinning) {
            return;
        }
        if (!player1.pay(1)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Средств на счете недостаточно, еще монетку плисс!");
            alert.show();
            return;
        }
        updateBalance();

        pendingType = action.drawType();
        spinning = true;
        spinButton.setDisable(true);

        Timeline timeline = new Timeline();
        for (int i = 0; i < 14; i++) {
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(70 * (i + 1)), e -> {
                reel1.setText(SYMBOLS[random.nextInt(SYMBOLS.length)]);
                reel2.setText(SYMBOLS[random.nextInt(SYMBOLS.length)]);
                reel3.setText(SYMBOLS[random.nextInt(SYMBOLS.length)]);
            }));
        }
        timeline.setOnFinished(e -> Platform.runLater(this::finishSpin));
        timeline.play();
    }

    private void finishSpin() {
        showResultOnReels(pendingType);
        action.process(pendingType);
        spinning = false;
        spinButton.setDisable(false);
        updateBalance();
    }

    private void showResultOnReels(int type) {
        if (type == ActionChain.SUCCESS) {
            String symbol = SYMBOLS[random.nextInt(SYMBOLS.length)];
            reel1.setText(symbol);
            reel2.setText(symbol);
            reel3.setText(symbol);
            return;
        }

        List<String> pool = new ArrayList<>(List.of(SYMBOLS));
        Collections.shuffle(pool, random);
        reel1.setText(pool.get(0));
        reel2.setText(pool.get(1));
        reel3.setText(pool.get(2));
    }

    private void updateBalance() {
        balanceLabel.setText("Монеты: " + player1.getNumber());
    }
}
