package classes.utility;

public class Color
{
    protected float r, g, b;

    public Color()
    {
        //this will give us black
        this.r = 0.0F;
        this.g = 0.0F;
        this.b = 0.0F;
    }

    public Color(float r, float g, float b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color(Color color)
    {
        this.r = color.r;
        this.g = color.g;
        this.b = color.b;
    }

    public void add(Color color)
    {
        this.r += color.r;
        this.g += color.g;
        this.b += color.b;
    }

    public void div(int scalar)
    {
        r /= scalar;
        g /= scalar;
        b /= scalar;
    }
    public int toInteger()
    {
        return (int) (r*255) <<16| (int) (g*255) <<8| (int) (b*255);
    }

    //TODO: add clamp method

}
