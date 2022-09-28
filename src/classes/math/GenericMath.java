package classes.math;

public final class GenericMath {
    public static <T extends Comparable<T>> T clamp(T t, T min, T max) {
        if (t.compareTo(min) < 0) {
            return min;
        }
        else if (t.compareTo(max) > 0) {
            return max;
        }
        return t;
    }

}
