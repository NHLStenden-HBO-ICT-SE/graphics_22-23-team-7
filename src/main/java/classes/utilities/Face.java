package classes.utilities;
//dont add public for package private
 class Face{
    int[] indices;
    int normal;
    Face(int[] indices){
        this.indices=indices;
    }
    Face(int[] indices, int normal){
        this(indices);
        this.normal = normal;
    }
}