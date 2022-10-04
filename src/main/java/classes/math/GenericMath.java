package classes.math;

/**
 * a static class for generic methods
 */
public final class GenericMath {
    /**
     * clamps a value between min and max
     * @param t
     * @param min
     * @param max
     * @return clamped value
     * @param <T>
     */
    public static <T extends Comparable<T>> T clamp(T t, T min, T max) {
        //if number is smaller than min
        if (t.compareTo(min) < 0) return min;

        //if number is bigger than max
        else if (t.compareTo(max) > 0) return max;

        //otherwise return number
        return t;
    }

}