package p2;

import java.util.Arrays;

public class Problem2 {

    public static int solution(int sum, int[] coins) {
        Arrays.sort(coins);

        int[] countWaysToMakeTargetAmount = new int[sum + 1];

        countWaysToMakeTargetAmount[0] = 1;

        for (int coin : coins) {
            if(coin > sum) {
                break;
            }

            for (int currentTargetAmount = coin; currentTargetAmount <= sum; currentTargetAmount++) {
                countWaysToMakeTargetAmount[currentTargetAmount] += countWaysToMakeTargetAmount[currentTargetAmount - coin];
            }
        }

        return countWaysToMakeTargetAmount[sum];
    }

    public static void main(String[] args) {
        int sum = 10;
        int[] coins = {3, 5, 7};

        System.out.println("최종 경우의 수: " + solution(sum, coins));

        int sum2 = 4;
        int[] coins2 = {1, 2, 3};

        System.out.println("최종 경우의 수: " + solution(sum2, coins2));
    }
}