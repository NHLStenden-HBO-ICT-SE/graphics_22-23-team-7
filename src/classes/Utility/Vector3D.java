package classes.Utility;

public class Vector3D {

   protected double x, y, z;

   /**
    * default values, java will already do this normally but just in case
    */
   public Vector3D()
   {
      this.x = 0.0;
      this.y = 0.0;
      this.z = 0.0;
   }

   public Vector3D(double x, double y, double z)
   {
        this.x = x;
        this.y = y;
        this.z = z;
   }

   public Vector3D(Vector3D vector)
   {
      this.x = vector.x;
      this.y = vector.y;
      this.z = vector.z;
   }


   /**
    * subtracts two 3D vectors
    */
   public Vector3D sub(Vector3D secondvector)
   {
      return new Vector3D((this.x - secondvector.x), (this.y - secondvector.y), (this.z - secondvector.z));
   }

   /**
    * calculates the sum of this vector and another 3D vector
    */
   public Vector3D add(Vector3D secondvector)
   {
      return new Vector3D((this.x + secondvector.x), (this.y + secondvector.y), (this.z + secondvector.z));
   }

   /**
    * multiplies all elements in the vector
    */
   public Vector3D multiply(double n)
   {
    return new Vector3D((x*n), (y*n), (z*n));
   }

   /**
    * divides all elements in a vector
    */
   public Vector3D divide(int n)
   {
      return new Vector3D((x/n), (y/n), (z/n));
   }

   /**
    * calculates inproduct from this and another 3D vector
    */
   public double inproduct(Vector3D secondvector)
   {
      return ((this.x * secondvector.x) + (this.y * secondvector.y) + (this.z * secondvector.z));
   }

   /**
    * calculates outproduct from this vector and another one
    */
   public Vector3D outproduct(Vector3D secondvector)
   {
      return new Vector3D(
       /*X*/  (this.y * secondvector.z) - (this.z * secondvector.y),
       /*Y*/  (this.z * secondvector.x) - (this.x * secondvector.z),
       /*Z*/  (this.x * secondvector.y) - (this.y * secondvector.x)
         );
   }

   /**
    * calculates length
    */
   public double length()
   {
      return Math.sqrt((this.x * this.x + this.y * this.y + this.z * this.z));
   }

   /**
    * returns corner in degrees
    */
   public double getCorner(Vector3D secondvector)
   {
     return Math.acos(this.inproduct(secondvector)/(this.length()*secondvector.length()));
   }

}
