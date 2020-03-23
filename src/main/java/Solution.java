import java.util.Arrays;

public class Solution {

    // the task doesn't make sense if the array size is less than three
    static final int MIN_LANDSCAPE_SIZE = 3;
    static final int MAX_LANDSCAPE_SIZE = 32000;

    static final int MIN_LANDSCAPE_HEIGHT = 0;
    static final int MAX_LANDSCAPE_HEIGHT = 32000;

    static long calculateWaterAmount(int[] landscape)
    {
        validateLandscape(landscape);

        long result = 0;

        // init maximum height from right and left sides
        int rightMaxHeight = landscape[0];
        int leftMaxHeight = landscape[landscape.length - 1];

        // init left and right cursors
        int left = landscape.length - 2;
        int right = 1;

        while (right <= left)
        {
            // check which side maximum height is less
            // calculate water and move cursor from this side
            if (leftMaxHeight > rightMaxHeight) {
                rightMaxHeight = Math.max(landscape[right], rightMaxHeight);
                result += rightMaxHeight - landscape[right];
                right++;
            }
            else {
                leftMaxHeight = Math.max(landscape[left], leftMaxHeight);
                result += leftMaxHeight - landscape[left];
                left--;
            }
        }

        return result;
    }

    static void validateLandscape(int[] landscape)
    {
        if (landscape == null || landscape.length < MIN_LANDSCAPE_SIZE || landscape.length > MAX_LANDSCAPE_SIZE) {
            throw new IllegalArgumentException("Incorrect landscape size");
        }

        if (Arrays.stream(landscape)
                .parallel()
                .anyMatch(h -> (h < MIN_LANDSCAPE_HEIGHT  || h > MAX_LANDSCAPE_HEIGHT)))
        {
            throw new IllegalArgumentException("Incorrect landscape height");
        }
    }


}
