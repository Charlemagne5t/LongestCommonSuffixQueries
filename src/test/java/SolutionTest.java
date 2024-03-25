import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void test1() {
        String[] wordsContainer = {"abcd", "bcd", "xbcd"};
        String[] wordsQuery = {"cd", "bcd", "xyz"};
        int[] expected = {1, 1, 1};
        int[] actual = new Solution().stringIndices(wordsContainer, wordsQuery);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void test2() {
        String[] wordsContainer = {"abcdefgh", "poiuygh", "ghghgh"};
        String[] wordsQuery = {"gh", "acbfgh", "acbfegh"};
        int[] expected = {2, 0, 2};
        int[] actual = new Solution().stringIndices(wordsContainer, wordsQuery);
        Assert.assertArrayEquals(expected, actual);
    }

}
