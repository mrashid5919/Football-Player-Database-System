package controller;

import javafx.scene.control.Button;

public class FrequentMethods {
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: #6bd0ff;";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: #3fa0ef; -fx-text-fill: #ffffff;";
    public static void hover(Button b){
        b.setStyle(IDLE_BUTTON_STYLE);
        b.setOnMouseEntered(e -> b.setStyle(HOVERED_BUTTON_STYLE));
        b.setOnMouseExited(e -> b.setStyle(IDLE_BUTTON_STYLE));
    }
}
