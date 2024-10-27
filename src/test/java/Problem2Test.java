import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import p2.Problem2;

class Problem2Test {

    @Test
    void 정렬된_동전들일_경우() {
        // given
        int sum = 4;

        int[] coins = {1, 2, 3};

        // when
        int result = Problem2.solution(sum, coins);

        // then
        assertEquals(result, 4);
    }

    @Test
    void 정렬되지_않은_동전들일_경우() {
        // given
        int sum = 10;

        int[] coins = {2, 5, 3, 6};

        // when
        int result = Problem2.solution(sum, coins);

        // then
        assertEquals(result, 5);
    }

    @Test
    void sum보다_작은_동전일_경우() {
        // given
        int sum = 4;

        int[] coins = {3};

        // when
        int result = Problem2.solution(sum, coins);

        // then
        assertEquals(result, 0);
    }

    @Test
    void sum이랑_같은_동전일_경우() {
        // given
        int sum = 4;

        int[] coins = {4};

        // when
        int result = Problem2.solution(sum, coins);

        // then
        assertEquals(result, 1);
    }

    @Test
    void sum보다_큰_동전일_경우() {
        // given
        int sum = 4;

        int[] coins = {5};

        // when
        int result = Problem2.solution(sum, coins);

        // then
        assertEquals(result, 0);
    }
}