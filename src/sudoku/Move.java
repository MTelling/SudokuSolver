package sudoku;

/**
 * Created by Morten on 11/01/2017.
 */
public class Move {
        int x;
        int y;
        int newValue;
        Move(int x, int y, int newValue) {
            this.x = x;
            this.y = y;
            this.newValue = newValue;
        }

}
