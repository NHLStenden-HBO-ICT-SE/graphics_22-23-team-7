package classes;
import org.jcodec.api.SequenceEncoder;
import org.jcodec.api.awt.AWTSequenceEncoder;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.Rational;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
/*
Copy instance thx to: https://stackoverflow.com/questions/3514158/how-do-you-clone-a-bufferedimage



 */
public class Recorder {



    // sequence encoder and other local variables
    private AWTSequenceEncoder enc;
    private int fps;
    private SeekableByteChannel out = null;

    //constructor for setting fps
    public Recorder(int frames){
        fps = frames;
    }





    //copy function to copy instance of BufferedImage
    public static BufferedImage deepCopyBufferedImage(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }




    //generate a recording with given values
    public void generate(BufferedImage[] images, String path, String filename){
        //swingworker for multithreading
         SwingWorker<Void, BufferedImage[]> swingworker = new SwingWorker<Void, BufferedImage[]>() {
            @Override
            protected Void doInBackground() throws Exception {
                //try catch for possible errors
                try {
                    //setting out channel
                    out = NIOUtils.writableFileChannel(path + filename + ".mp4");
                    // initializing encoder
                    AWTSequenceEncoder encoder = new AWTSequenceEncoder(out, new Rational(fps, 1));
                    for (var image : images) {
                        // Encode the image
                        encoder.encodeImage(image);
                    }
                    // Finalize the encoding, i.e. clear the buffers, write the header, etc.
                    encoder.finish();
                    //all catches
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    NIOUtils.closeQuietly(out);
                }
                return null;
            }
        };
        //actually execute the swingworker
         swingworker.execute();
    }
}
