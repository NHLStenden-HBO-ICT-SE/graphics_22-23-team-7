package classes;

import java.lang.reflect.Constructor;

public class V3 {

   protected double x;
   protected double y;
   protected double z;
   

   public V3(double x, double y, Double z){
        this.x = x;
        this.y = y;
        this.z = z;
   }


   /*calculates the sum of this vector and another 3D vector*/
   public V3 add(V3 secondvector){
    return new V3((this.x + secondvector.x), (this.y + secondvector.y), (this.z + secondvector.z));
   }

   /* multiplies all elements in the vector */
   public V3 multiply(double n){
    return new V3((x*n), (y*n), (z*n));
   }

   /*divides all elements in a vector */
   public V3 divide(int n){
      return new V3((x/n), (y/n), (z/n));
   }

   /*calculates inproduct from this and another 3D vector */
   public double inproduct(V3 secondvector){
      return ((this.x * secondvector.x) + (this.y * secondvector.y) + (this.z * secondvector.z));
   }

   /*calculates outproduct from this vector and another one */
   public V3 outproduct(V3 secondvector){ 
      return new V3(
       /*X*/  (this.y * secondvector.z)-(this.z * secondvector.y),
       /*Y*/  (this.z * secondvector.x) -(this.x * secondvector.z),
       /*Z*/  (this.x * secondvector.y) - (this.y * secondvector.x)
         );
   }

   /*calculates length */
   public double length(){
      return Math.sqrt((this.x * this.x + this.y * this.y + this.z * this.z));
   }

   /*returns corner in degrees */
   public double getCorner(V3 secondvector){
     return Math.acos(this.inproduct(secondvector)/(this.length()*secondvector.length()));
   }

}
