package sudoku;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Created by Morten on 11/01/2017.
 */
public class NumberTile extends StackPane {



    public enum State {FINAL, TEMP, UNTOUCHED, DONE};
    private State state;
    private Label numberLabel;
    private Rectangle rectangle;

    public NumberTile(int value, State state) {
        numberLabel = new Label("" + value);
        numberLabel.setFont(new Font(24));

        rectangle = new Rectangle(0,0,43,43);
        rectangle.setFill(Color.WHITE);
        this.getChildren().add(rectangle);
        this.getChildren().add(numberLabel);

        this.state = state;
        this.update();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setText(String text) {
        numberLabel.setText(text);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(100), this);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(2);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();

        FillTransition fillTransition = new FillTransition(Duration.millis(2000), rectangle);
        fillTransition.setFromValue(Color.WHITE);
        fillTransition.setToValue(Color.RED);
        fillTransition.setCycleCount(2);
        fillTransition.setAutoReverse(true);
        fillTransition.play();

    }

    public void update() {
        if (this.state == State.FINAL) numberLabel.setTextFill(Color.BLACK);
        else if (this.state == State.TEMP) numberLabel.setTextFill(Color.RED);
        else if (this.state == State.UNTOUCHED) numberLabel.setTextFill(Color.BLUE);
        else if (this.state == State.DONE) numberLabel.setTextFill(Color.GREEN);
    }


}
