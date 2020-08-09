import java.util.ArrayList;
import java.util.Arrays;

public class SingleNumberT136 {
    public int singleNumber(int[] nums) {
        int theone = 0;
        for (int num: nums
             ) {
            theone ^= num;
        }

        return theone;
    }
}
