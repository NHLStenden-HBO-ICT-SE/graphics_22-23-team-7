import java.io.Flushable;

public class App {
    public static void main(String[] args) throws Exception {
         // Image
    
         double image_width = 255;
         double image_height = 255;
    
        // Render
    
        System.out.print( "P3\r\n" + image_width + ' ' + image_height + "\r\n255\r\n" );
    
        for (int j = (int)(image_height - 1.0); j >= 0; --j) {
            System.err.print("in progress.." + j + " lines of "+ image_width +" pixels remaining\n");
            System.err.flush();
            for (int i = 0; i < image_width; ++i) {
                double r = i / (image_width-1);
                double g = j / (image_height-1);
                double b = 0.25;
    
                int ir = (int)(255.999 * r);
                int ig = (int)(255.999 * g);
                int ib = (int)(255.999 * b);
    
                System.out.println( ir + " " + ig + " " + ib );
            }
        }
    }
}
