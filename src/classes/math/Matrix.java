package classes.math;


public class Matrix <T extends Matrix>{

    private double[][] matrixContent;
  
      //initialize matrix with predefined nested array
      public Matrix(double[][] items){
          this.matrixContent = items;
      }

      //initialize matrix without values. needs an x length and an y length. note that 0 counts!
      public Matrix(int capacityRows, int capacityColumns){
          //set capacity for x
         matrixContent = new double[capacityRows][capacityColumns];
          for(int x = 0; x < capacityRows; x++){
              //set capacity for y
            for (int y = 0; x < capacityColumns; x++) {
              this.matrixContent[x][y] = 0.0;  
            }
          }
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
      private void subtract(double[][] second) 
      {
        int row = matrixContent.length;
        int column = matrixContent[0].length;
        double[][] sum = new double[row][column];
    
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                sum[r][c] = matrixContent[r][c] - second[r][c];
            }
        }
        this.matrixContent = sum;
       }

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

    public Vector3D multiply(Vector3D vector3d){
        if(this.getRowSize() > 3 | this.getColSize() > 3){
         System.err.println("vector projection with matrix failed.. matrix dimensions not 3x3");
        return vector3d;
        }
        else
        {

        
            int row = this.getRowSize();
            int column = this.getColSize();
            double[][] sum = new double[3][1];
            double[][] second = {{vector3d.x},{vector3d.y},{vector3d.z}};
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < column; c++) {
                    sum[r][0] += this.matrixContent[r][c] * second[c][0];
                }
            }
        
            return new Vector3D(sum[0][0],sum[1][0],sum[2][0]);
        }

        //has to be tested
        
        public T multiply(T input){
            if(this.getColSize()  != input.getRowSize()){
             System.err.println("vector projection with matrix failed.. dimensions dont match");
            return input;
            }
            else
            {
    
            
                int row = this.getRowSize();
                int column = input.getColSize();
                double[][] sum = new double[this.getRowSize()][input.getColSize()];
                for(int z = 0; z < input.getColSize(); z++){
                    for (int r = 0; r < row; r++) {
                        for (int c = 0; c < column; c++) {
                            sum[r][z] += this.matrixContent[r][c] * input.getMatrix()[c][z];
                        }
                    }
                }
                input.setMatrix(sum);
                return input;
            }
    }
  
  }