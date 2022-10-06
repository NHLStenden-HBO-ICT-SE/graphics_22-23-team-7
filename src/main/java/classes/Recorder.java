package classes;

import org.jcodec.api.SequenceEncoder;
import org.jcodec.api.awt.AWTSequenceEncoder;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Rational;
import org.jcodec.scale.AWTUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

/*
Copy instance thx to: https://stackoverflow.com/questions/3514158/how-do-you-clone-a-bufferedimage
other credits:
https://www.w3schools.com/java/java_files_delete.asp


 */
public class Recorder {


    // sequence encoder and other local variables
    private AWTSequenceEncoder enc;
    private int fps;
    private SeekableByteChannel out = null;

    //constructor for setting fps
    public Recorder(int frames) {
        fps = frames;
    }


    /**
     * copy function to copy instance of BufferedImage
     *
     * @param bi the image to be copied
     * @return the copied image
     */
    public static BufferedImage deepCopyBufferedImage(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }


    /**
     * generate a recording with given values with swin gworker
     *
     * @param images   images to be processed
     * @param path     save to path
     * @param filename name of file
     */
    public void generate(BufferedImage[] images, String path, String filename) {
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

    /**
     * generate a recording with given values
     *
     * @param images   images to be processed
     * @param path     save to path
     * @param filename name of file
     */
    public void generatInSync(BufferedImage[] images, String path, String filename) {


        //try catch for possible errors
        try {
            //setting out channel
            out = NIOUtils.writableFileChannel(path + filename + ".mkv");
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

    }

    /**
     * takes snapshots for recording
     *
     * @param bufferedImage the frame to be saved
     * @param path          save to path
     * @param currentframe  the current frame of the simulation
     */
    public void snapShot(BufferedImage bufferedImage, String path, int currentframe) {
        snapShot(bufferedImage, path, "frame" + currentframe);
    }

    /**
     * takes snapshots for the user
     *
     * @param bufferedImage the frame to be saved
     * @param path          save to path
     * @param filename      namne of the file
     */
    public void snapShot(BufferedImage bufferedImage, String path, String filename) {
        try {
            ImageIO.write(bufferedImage, "png", new File(Paths.get(path, filename + ".png").toString()));
        } catch (IOException e) {
            System.out.println("IOERROR, snapshot not taken. reason: " + e.getMessage());
        }
    }

    /**
     * @param path  Path to files
     * @param files string[] of files like {"image0.png", "image1.png"}
     */
    public void generateFromMemory(String path, String[] files) {
        SwingWorker<Void, Void> swingworker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                out = NIOUtils.writableFileChannel(Paths.get(path, "test.mp4").toString());
                var encoder = new AWTSequenceEncoder(out, new Rational(fps, 1));
                for (String file : files) {
                    File fileActual = new File(Paths.get(path, file).toString());
                    encoder.encodeNativeFrame(AWTExtension.decodePNG(fileActual, ColorSpace.RGB));
                    fileActual.delete();
                }
                encoder.finish();
                return null;
            }
        };
        swingworker.execute();
    }
}
