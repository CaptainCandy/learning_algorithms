import java.util.Random;

public class ShuffleanArrayT384 {
    private int[] result;
    private int[] origin;

    private Random random = new Random();

    public ShuffleanArrayT384(int[] nums) {
        result = nums;
        origin = nums.clone();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        for (int i = 0; i < origin.length; i++) {
            result[i] = origin[i];
        }
        return result;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        for (int i = 0; i < result.length; i++) {
            int ran = random.nextInt(result.length - i);
            int t = result[i];
            result[i] = result[i + ran];
            result[i + ran] = t;
        }
        return result;
    }
}
