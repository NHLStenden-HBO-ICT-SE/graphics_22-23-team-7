package classes.utilities;
//don't add public for package private

/**
 * Face class for the OBJReader
 */
class Face {
    int[] indices;
    int normal;

    /**
     * constructor for only indices
     * @param indices
     */
    Face(int[] indices) {
        this.indices = indices;
    }

    /**
     * constructor when normals are given
     * @param indices
     * @param normal
     */
    Face(int[] indices, int normal) {
        this(indices);
        this.normal = normal;
    }
}