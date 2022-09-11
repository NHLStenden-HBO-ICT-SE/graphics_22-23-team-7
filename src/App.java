import classes.DrawingHelper;

public class App {
    public static void main(String[] args) throws Exception {
        DrawingHelper dh = new DrawingHelper(1280, 720);
        while (true) {
            if (dh.Draw()) {
                Thread.sleep(1500);
                dh.Blank();
            }
        }
    }
}

