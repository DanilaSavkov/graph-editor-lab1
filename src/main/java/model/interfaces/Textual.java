package model.interfaces;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public interface Textual {
    double FONT_SIZE = 20;
    String FONT_FAMILY = "Arial";
    FontWeight FONT_WEIGHT = FontWeight.BOLD;
    FontPosture FONT_POSTURE = FontPosture.ITALIC;
    Color TEXT_FILL = Color.BLUE;
    Font FONT = Font.font(FONT_FAMILY, FONT_WEIGHT, FONT_POSTURE, FONT_SIZE);

    Text getText();
}