package classes;

public class RenderBatch {
    private final int start, length;
    public RenderBatch(int start, int length) {
        this.start = start;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public int getStart() {
        return start;
    }
}
