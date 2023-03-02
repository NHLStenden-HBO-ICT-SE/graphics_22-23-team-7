package classes;

import classes.utilities.PathHandler;
import org.jcodec.api.awt.AWTSequenceEncoder;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Rational;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Copy instance thx to: https://stackoverflow.com/questions/3514158/how-do-you-clone-a-bufferedimage
other credits:
https://www.w3schools.com/java/java_files_delete.asp


 */
public class Recorder {

    private final int fps;
    private final int totalFrames;
    // sequence encoder and other local variables
    private AWTSequenceEncoder enc;
    private SeekableByteChannel out = null;
    private int currentFrame = 0;
    private final String recordingPath = PathHandler.getFile("recordings");

    private boolean isRecording = true;

    /**
     * @param fps
     * @param duration
     */
    public Recorder(int fps, int duration) {
        if (Files.notExists(Path.of(recordingPath))) {
            try {
                Files.createDirectories(Path.of(recordingPath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        int totalFrames = (fps * duration);
        this.fps = fps;
        this.totalFrames = totalFrames;

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
        //swingWorker for multithreading
        SwingWorker<Void, BufferedImage[]> swingWorker = new SwingWorker<>() {
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
        //actually execute the swingWorker
        swingWorker.execute();
    }

    /**
     * generate a recording with given values
     *
     * @param images   images to be processed
     * @param path     save to path
     * @param filename name of file
     */
    public void generateInSync(BufferedImage[] images, String path, String filename) throws FileNotFoundException {


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
        SwingWorker<Void, Void> swingWorker = new SwingWorker<>() {
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
        swingWorker.execute();
    }

    /**
     * For recording. stacks buffered images for later use and executes recording encoder when duration has expired.
     *
     * @param dh
     */
    public void generateImage(DrawingHelper dh) {
        if (currentFrame < totalFrames) {
            //because of high resolution. the heap size can be too small. I have to catch the error since I cant seem to get the available heap size |Runtime.getRuntime().freeMemory()| correctly before it happends
            try {
                snapShot(Recorder.deepCopyBufferedImage(dh.getWindow().getImage()), recordingPath, currentFrame);

                currentFrame++;
            } catch (OutOfMemoryError e) {
                //Todo: write error to screen
                System.out.println("ERROR: OUT OF HEAP SPACE, STOPPING RECORDING...");
                currentFrame = totalFrames + 1;
            }

        } else if (currentFrame == totalFrames) {
            //rendering into a mp4 file
            String[] strings = new String[totalFrames];
            for (int i = 0; i < totalFrames; i++) {
                strings[i] = "frame" + i + ".png";
            }
            generateFromMemory(recordingPath, strings);
            isRecording = false;
        }
    }

    public boolean isRecording() {
        return isRecording;
    }
}
