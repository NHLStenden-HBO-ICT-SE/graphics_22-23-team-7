package classes.Utility;

public class Normal
{
    protected double x, y, z;

    /**
     * default values, java will already do this normally but just in case
     */
    public Normal()
    {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public Normal(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Normal(Normal normal)
    {
        this.x = normal.x;
        this.y = normal.y;
        this.z = normal.z;
    }

    /**
     * normalizes the normal
     */
    public void normalize()
    {
        double magnitude = Math.sqrt(x*x + y*y + z*z);

        x /= magnitude;
        y /= magnitude;
        z /= magnitude;
    }

}
