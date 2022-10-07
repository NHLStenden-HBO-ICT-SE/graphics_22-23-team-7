package classes.math;


import interfaces.math.Operators;

public class Matrix <T extends Matrix> implements Operators<T> {

    private double[][] matrixContent;
  
      //initialize matrix with predefined nested array
      public Matrix(double[][] items){
          this.matrixContent = items;
      }
  

      public void setMatrix(double[][] items){
        this.matrixContent = items;
    }
    public double[][] getMatrix(){
        return this.matrixContent;
    }
  


      public void set(double value,int row, int col){
       this.matrixContent[row][col] = value;
      }
  
      //checks if matrix is square in size
      public boolean isSquare(){
          if(this.matrixContent.length == this.matrixContent[0].length){
              return true;
          } else {
              return false;
          }
      }
      //checks if matrix is a null matrix
      public boolean isNull(){
            for (double[] ds : matrixContent) {
                for (double d : ds) {
                if(d != 0){
                    return false;
                }
                }
            }
          return true;
      }
  
  
  
      //get collumns
      public int getColSize(){
          return this.matrixContent[0].length;
      }
      //get collumns
      public int getRowSize(){
          return this.matrixContent.length;
      }
  
      public void printmatrix(){
        for (double[] ds : matrixContent) {
            for (double d : ds) {
                System.out.print("val: " + d + " |");
            }
            System.out.println("");
        }
      }
  
      //substract input matrix from its own [needs testing]


       //multiply this matrix with the second [needs testing] returns result instead of setting
       private Matrix multiply(double[][] second) {
        int row = this.matrixContent.length;
        int column = this.matrixContent[0].length;
        double[][] sum = new double[row][column];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                sum[r][c] = this.matrixContent[r][c] * second[r][c];
            }
        }
        return new Matrix(sum);
    }
        //has to be tested
        
        public T multiply(T input){
            //criteria for multiplying a matrix
            if(this.getColSize()  != input.getRowSize()){
             System.err.println("vector projection with matrix failed.. dimensions dont match");
            return input;
            }
            else
            {
                //matrix calculation for sum
                int row = this.getRowSize();
                int column = input.getColSize();
                double[][] sum = new double[this.getRowSize()][input.getColSize()];
                for(int z = 0; z <  input.getColSize(); z++){
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
     * subtracts parameter matrix from matrix
     * @param second the second matrix
     * @return the summed matrix
     */
    @Override
    public T sub(T second) {
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

    @Override
    public void setSub(T second) {
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

    @Override
    public T add(T T) {
        return null;
    }

    @Override
    public void setAdd(T T) {

    }

    @Override
    public T multiply(double n) {
        return null;
    }

    @Override
    public void setMultiply(double n) {

    }

    @Override
    public T divide(double n) {
        return null;
    }

    @Override
    public void setDivide(double n) {

    }
}