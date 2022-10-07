package testing.object;

import classes.math.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * test class for testing matrix functionality
 */
public class MastrixTest {


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
    @Test
    public void subtractMatrixFromMatrix() {
        Matrix m5 = new Matrix(new double[][]{{1,2,3,4},{1,2,3,4},{1,2,3,4}, {1,2,3,4}});
        Matrix m6 = new Matrix(new double[][]{{1,2,3,4},{1,2,3,4},{1,2,3,4}, {1,2,3,4}});
        Matrix a = new Matrix(new double[][]{{0,0,0,0},{0,0,0,0},{0,0,0,0}, {0,0,0,0}});
        m5.setSub(m6);
        /**
         * checking content
         */
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(m5.getMatrix()[i][j],  a.getMatrix()[i][j]);
            }
        }
    }

    /**
     * tests issquare matrix function
     */
    @Test
    public void matrixIsSquare() {
        Matrix m3 = new Matrix(new double[][]{{1,2,3,4},{1,2,3,4},{1,2,3,4}, {1,2,3,4}});
        Matrix m4 = new Matrix(new double[][]{{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5}, {1,2,3,4,5}});
        assertEquals(m3.isSquare(),  true);
        assertEquals(m4.isSquare(), false);
    }

    /**
     * checks col and rowsize getters
     */
    @Test
    public void getColSizeAndGetRowSize() {
        Matrix m7 = new Matrix(new double[][]
                {{1,2,3,4,5},
                {1,2,3,4,5},
                {1,2,3,4,5},
                {1,2,3,4,5}});
        assertEquals(m7.getColSize(),  5);
        assertEquals(m7.getRowSize(), 4);
    }
    @Test
    public void multiplyMatrixWithMatrix() {
        Matrix m8 = new Matrix(new double[][]{
                {3,-1, 2},
                {4,-5, 0}});
        Matrix m9 = new Matrix(new double[][]{
                {-4,5},
                {1,7},
                {-2,6}});
        Matrix m10 = new Matrix(new double[][]{
                {-17,20},
                {-21,-15}});
        m8 = m8.multiply(m9);
        m8.printmatrix();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(m8.getMatrix()[i][j],  m10.getMatrix()[i][j]);
            }
        }
    }

    @Test
    public void devideMatrixWithScalar() {
        Matrix m11 = new Matrix(new double[][]{
                {4,8, 16,32},
                {4,8,16,32}});
        Matrix m12 = new Matrix(new double[][]{
                {2,4, 8,16},
                {2,4,8,16}});
        m11.setDivide(2);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(m11.getMatrix()[i][j],  m12.getMatrix()[i][j]);
            }
        }
    }
    @Test
    public void multiplyMatrixWithScalar() {
        Matrix m11 = new Matrix(new double[][]{
                {4,8, 16,32},
                {4,8,16,32}});
        Matrix m12 = new Matrix(new double[][]{
                {8,16, 32,64},
                {8,16,32,64}});
        m11.setMultiply(2);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(m11.getMatrix()[i][j],  m12.getMatrix()[i][j]);
            }
        }
    }

    @Test
    public void subMatrixFromMatrix() {
        Matrix m13 = new Matrix(new double[][]{
                {3,-1, 2},
                {4,-5, 0}});
        Matrix m14 = new Matrix(new double[][]{
                {3,-1, 2},
                {4,-5, 0}});
        Matrix m15 = new Matrix(new double[][]{
                {0,0,0},
                {0,0, 0}
        });
        m13.setSub(m14);
        m13.printmatrix();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(m13.getMatrix()[i][j],  m15.getMatrix()[i][j]);
            }
        }
    }
    @Test
    public void subMatrixFromMatrixFail() {
        Matrix m16 = new Matrix(new double[][]{
                {3,-1, 2},
                {4,-5, 0}});
        Matrix m17 = new Matrix(new double[][]{
                {-4,5},
                {1,7},
                {-2,6}});
        Matrix m18 = new Matrix(new double[][]{
                {3,-1, 2},
                {4,-5, 0}});
        m16.setSub(m17);
        m16.printmatrix();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(m16.getMatrix()[i][j],  m18.getMatrix()[i][j]);
            }
        }
    }

}
