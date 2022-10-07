package classes.math;


import interfaces.math.Operators;

public class Matrix implements Operators<Matrix> {


    private double[][] matrixContent;

    /**
     * matrix init
     * @param items matrixcontent in double[][]
     */
    public Matrix(double[][] items) {
        this.matrixContent = items;
    }

    /**
     * sets matrix content
     * @param items list of double[][] to become content
     */
    public void setMatrix(double[][] items) {
        this.matrixContent = items;
    }

    /**
     * returns matrixcontent in double[][]
     * @return matrixcontent in double[][]
     */
    public double[][] getMatrix() {
        return this.matrixContent;
    }

    /**
     * set specific value in matrix content
     * @param value val
     * @param row row
     * @param col col
     */
    public void set(double value, int row, int col) {
        this.matrixContent[row][col] = value;
    }

    /**
     * checks if matrix is square
     * @return bool isSquare
     */
    public boolean isSquare() {
        if (this.matrixContent.length == this.matrixContent[0].length) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * checks if matrix is null
     * @return
     */
    public boolean isNull() {
        for (double[] ds : matrixContent) {
            for (double d : ds) {
                if (d != 0) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * gets col size
     * @return col size
     */
    public int getColSize() {
        return this.matrixContent[0].length;
    }

    /**
     * gets col size
     * @return col size
     */
    public int getRowSize() {
        return this.matrixContent.length;
    }

    /**
     * prints matrix content in terminal
     */
    public void printmatrix() {
        for (double[] ds : matrixContent) {
            for (double d : ds) {
                System.out.print("val: " + d + " |");
            }
            System.out.println("");
        }
    }

    //has to be tested


    /**
     * multiplies and returns value
     * @param input multiplier second place
     * @return the sum
     */
    public Matrix multiply(Matrix input) {
        //criteria for multiplying a matrix
        if (this.getColSize() != input.getRowSize()) {
            System.err.println("vector projection with matrix failed.. dimensions dont match");
            return input;
        } else {
            //matrix calculation for sum
            int row = this.getRowSize();
            int column = input.getColSize();
            double[][] sum = new double[this.getRowSize()][input.getColSize()];
            for (int z = 0; z < input.getColSize(); z++) {
                for (int r = 0; r < row; r++) {
                    for (int c = 0; c <= column; c++) {
                        sum[r][z] += (this.matrixContent[r][c]) * (input.getMatrix()[c][z]);
                    }
                }
            }
            input.setMatrix(sum);
            return input;
        }
    }

    /**
     * multiplies and sets value
     * @param input the multiplier second position
     */
    public void setmultiply(Matrix input) {
        //criteria for multiplying a matrix
        if (this.getColSize() != input.getRowSize()) {
            System.err.println("vector projection with matrix failed.. dimensions dont match");
        } else {
            //matrix calculation for sum
            int row = this.getRowSize();
            int column = input.getColSize();
            double[][] sum = new double[this.getRowSize()][input.getColSize()];
            for (int z = 0; z < input.getColSize(); z++) {
                for (int r = 0; r < row; r++) {
                    for (int c = 0; c <= column; c++) {
                        sum[r][z] += (this.matrixContent[r][c]) * (input.getMatrix()[c][z]);
                    }
                }
            }
            input.setMatrix(sum);
            this.matrixContent = sum;
        }
    }

    /**
     * subtracts parameter matrix from matrix
     *
     * @param second the second matrix
     * @return the summed matrix
     */
    @Override
    public Matrix sub(Matrix second) {
        int row = matrixContent.length;
        int column = matrixContent[0].length;
        double[][] sum = new double[row][column];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                sum[r][c] = matrixContent[r][c] - second.getMatrix()[r][c];
            }
        }
        second.setMatrix(sum);
        return second;
    }

    /**
     * subtracts parameter matrix from matrix and sets value of matrix
     * @param second the second matrix
     */
    @Override
    public void setSub(Matrix second) {
        int row = matrixContent.length;
        int column = matrixContent[0].length;
        double[][] sum = new double[row][column];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                sum[r][c] = matrixContent[r][c] - second.getMatrix()[r][c];
            }
        }
        this.matrixContent = sum;
    }

    /**
     * adds a matrix to another matrix
     * @param second the second matrox
     * @return the sum matrix
     */
    @Override
    public Matrix add(Matrix second) {
        int row = this.getRowSize();
        int column = this.getColSize();
        double[][] sum = new double[row][column];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                sum[r][c] = matrixContent[r][c] + second.getMatrix()[r][c];
            }
        }
        second.setMatrix(sum);
        return second;
    }

    /**
     * adds a matrix to another matrix
     * @param second the second matrix
     */
    @Override
    public void setAdd(Matrix second) {
        int row = this.getRowSize();
        int column = this.getColSize();
        double[][] sum = new double[row][column];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                sum[r][c] = matrixContent[r][c] + second.getMatrix()[r][c];
            }
        }
        this.setMatrix(sum);

    }

    /**
     * multiplies matrix by scalar
     * @param n the scalar
     * @return the scalar product as matrix
     */
    @Override
    public Matrix multiply(double n) {
        int row = this.getRowSize();
        int column = this.getColSize();
        double[][] sum = new double[row][column];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                sum[r][c] = matrixContent[r][c] * n;
            }
        }
        return new Matrix(sum);
    }

    /**
     * multiplies matrix with scalar and sets result
     * @param n the scalar
     */
    @Override
    public void setMultiply(double n) {

        int row = this.getRowSize();
        int column = this.getColSize();
        double[][] sum = new double[row][column];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                sum[r][c] = matrixContent[r][c] * n;
            }
        }
       this.matrixContent = sum;
    }

    /**
     * devide matrix by scalar
     * @param n scalar
     * @return the sum matrix
     */
    @Override
    public Matrix divide(double n) {
        int row = this.getRowSize();
        int column = this.getColSize();
        double[][] sum = new double[row][column];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                sum[r][c] = matrixContent[r][c] / n;
            }
        }
        return new Matrix(sum);
    }

    /**
     * devide matrix by scalar and sets sum as value
     * @param n the scalar
     */
    @Override
    public void setDivide(double n) {

        int row = this.getRowSize();
        int column = this.getColSize();
        double[][] sum = new double[row][column];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                sum[r][c] = matrixContent[r][c] / n;
            }
        }
        this.matrixContent = sum;
    }

}