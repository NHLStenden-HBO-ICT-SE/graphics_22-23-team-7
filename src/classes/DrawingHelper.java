package classes;

import classes.math.Ray;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class DrawingHelper {
    private final MainWindow window;

    //*****************************
    // Constructors
    //*****************************

    public DrawingHelper() {
        this(480, 480);
    }

    // Initialize DrawingHelper and window at a specified size
    public DrawingHelper(int width, int height) {
        this.window = new MainWindow(width, height);
    }

    public BufferedImage getImage() {
        return this.window.getImage();
    }

    public boolean drawScene(Scene scene) {
        // Get amount of threads available to the JVM
        int availableCores = Runtime.getRuntime().availableProcessors();
        // 10 is the maximum worker threads in the SwingWorker class
        if (availableCores > 10) {
            // Clamp at 10
            availableCores = 10;
        }
        // Starting point
        int start = 0;
        int maxBatchSize = this.window.getHeight() / availableCores;
        // List of batches
        List<RenderBatch> renderBlocks = new ArrayList();
        // List of background workers/threads
        List<SwingWorker<Void, Void>> workers = new ArrayList();
        int remainingLines = this.window.getHeight();
        while (remainingLines > 0) {
            // Check if the remaining lines are less than max batch size
            if (remainingLines / maxBatchSize < 1) {
                // Add a render job with the remainder of the pixels
                renderBlocks.add(new RenderBatch(start, remainingLines % maxBatchSize));
                remainingLines = 0;
            } else {
                // Add a render job with the maximum batch size and set the next start
                renderBlocks.add(new RenderBatch(start, maxBatchSize));
                remainingLines -= maxBatchSize;
                start += maxBatchSize;
            }
        }

        // Create render jobs based off batches
        for (RenderBatch batch : renderBlocks) {
            // Add render job to workers list
            workers.add(new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {

                    //horizontal pixels
                    for (int j = batch.getStart(); j < batch.getStart() + batch.getLength(); j++) {
                        //vertical pixels
                        for (int i = 0; i < window.getWidth(); i++) {

                            //create new ray current position on the screen, reason for division is normalization (between 0 and 1)
                            Ray ray = scene.getCamera().makeRay((double) i / window.getWidth(), (double) j / window.getHeight());

                            window.draw(i, j, scene.calculatePixel(ray));

                        }
                    }
                    return null;
                }
            });
        }

        // Execute all workers
        for (SwingWorker<Void, Void> worker : workers) {
            worker.execute();
        }
        // Wait for workers
        while (workers.stream().anyMatch(w -> !w.isDone())) {
        }
        return true;
    }

    public void update() {
        window.update();
    }

    // Blank the screen
    public void blank() {
        this.window.blank();
    }

    public int getHeight() {
        return this.window.getHeight();
    }

    public int getWidth() {
        return this.window.getWidth();
    }
}
