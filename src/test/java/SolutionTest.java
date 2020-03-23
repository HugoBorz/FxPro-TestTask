import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;


class SolutionTest {

    @ParameterizedTest(name = "{index}. {2}")
    @MethodSource("provideValidLandscapes")
    void calculateWaterAmount_valid_test(int[] landscape, long expected, String testName) {
        Assertions.assertEquals(expected, Solution.calculateWaterAmount(landscape));
    }

    @ParameterizedTest(name = "{index}. Invalid landscape: {1}")
    @MethodSource("provideInvalidLandscapes")
    void calculateWaterAmount_invalid_test(int[] landscape, String testName) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Solution.calculateWaterAmount(landscape));
    }

    private static Stream<Arguments> provideValidLandscapes() {
        int[] deepestLandscape = new int[Solution.MAX_LANDSCAPE_SIZE];
        deepestLandscape[0] = Solution.MAX_LANDSCAPE_HEIGHT;
        deepestLandscape[Solution.MAX_LANDSCAPE_SIZE - 1] = Solution.MAX_LANDSCAPE_HEIGHT;
        long maxWaterAmount = (Solution.MAX_LANDSCAPE_SIZE - 2) * Solution.MAX_LANDSCAPE_HEIGHT;
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1}, 0, "Pyramid landscape (zero)"),
                Arguments.of(new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5}, 0, "Plain  (zero)"),
                Arguments.of(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 0, "Zero-level landscape (zero)"),
                Arguments.of(new int[]{5, 2, 3, 4, 5, 4, 0, 3, 1}, 9, "Test landscape (9)"),
                Arguments.of(new int[]{10, 0, 0, 0, 0, 0, 0, 0, 10}, 70, "Landscape (70)"),
                Arguments.of(new int[]{0, 0, 0, 0, 0, 10, 0, 0, 0}, 0, "Landscape (zero)"),
                Arguments.of(new int[]{100, 80, 60, 40, 0, 40, 60, 80, 100}, 340, "Landscape (340)"),
                Arguments.of(new int[]{100, 0, 0, 0, 0, 2, 0, 0, 0}, 8, "Landscape (8)"),
                Arguments.of(new int[]{1000, 0,100}, 100, "Landscape (100)"),
                Arguments.of(deepestLandscape, maxWaterAmount, "The deepest landscape ("+maxWaterAmount+")")
        );
    }

    private static Stream<Arguments> provideInvalidLandscapes() {
        final int size = 100;
        final int range = 100;

        int[] negativeHeightLandscape = generateIntArray(size, range);
        negativeHeightLandscape[size/2] = Solution.MIN_LANDSCAPE_HEIGHT - 100;

        int[] tooHighLandscape = generateIntArray(size, range);
        tooHighLandscape[size/2] = Solution.MAX_LANDSCAPE_HEIGHT + 100;

        int[] tooSmallLandscape = generateIntArray(Solution.MIN_LANDSCAPE_SIZE - 1, range);

        int[] tooLargeLandscape = generateIntArray(Solution.MAX_LANDSCAPE_SIZE + 1, range);

        return Stream.of(
                Arguments.of(null, "null"),
                Arguments.of(negativeHeightLandscape, "negative height"),
                Arguments.of(tooHighLandscape, "too high"),
                Arguments.of(tooSmallLandscape, "too small"),
                Arguments.of(tooLargeLandscape, "too large")
        );
    }

    private static int[] generateIntArray(int count, int range)
    {
        Random random = new Random();
        return IntStream.generate(() -> random.nextInt(range)).limit(count).toArray();
    }

}