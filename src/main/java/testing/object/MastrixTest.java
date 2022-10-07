package testing.object;

import classes.math.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MastrixTest {
    /**
     * test class for testing matrix functionality
     */

    /**
     * for testing add
     */
    @Test
    public void addMatrixToMatrix() {
        Matrix m = new Matrix(new double[][]{{1,2,3,4},{1,2,3,4},{1,2,3,4}, {1,2,3,4}});
        Matrix m2 = new Matrix(new double[][]{{1,2,3,4},{1,2,3,4},{1,2,3,4}, {1,2,3,4}});
        Matrix a = new Matrix(new double[][]{{2,4,6,8},{2,4,6,8},{2,4,6,8}, {2,4,6,8}});
        m.setAdd(m2);
        /**
         * checking content
         */
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(m.getMatrix()[i][j],  a.getMatrix()[i][j]);
            }
        }

    }

    /**
     * for testing subtract
     */
    public void subtractMatrixFromMatrix() {
        Matrix m = new Matrix(new double[][]{{1,2,3,4},{1,2,3,4},{1,2,3,4}, {1,2,3,4}});
        Matrix m2 = new Matrix(new double[][]{{1,2,3,4},{1,2,3,4},{1,2,3,4}, {1,2,3,4}});
        Matrix a = new Matrix(new double[][]{{0,0,0,0},{0,0,0,0},{0,0,0,0}, {0,0,0,0}});
        m.setSub(m2);
        /**
         * checking content
         */
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(m.getMatrix()[i][j],  a.getMatrix()[i][j]);
            }
        }


    }
}
