package com.murdock.examples.dropwizard.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class CoinCalculatorResource {

    private static final List<Double> VALID_DENOMINATIONS = List.of(
            0.01, 0.05, 0.1, 0.2, 0.5, 1.0, 2.0, 5.0, 10.0, 50.0, 100.0, 1000.0
    );
    @Path("/compute")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response calculateCoins(CoinRequest request) {
        // Validate inputs
        if (request.getTargetAmount() < 0 || request.getTargetAmount() > 10000) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Target amount must be between 0 and 10,000.")
                    .build();
        }
        if (!VALID_DENOMINATIONS.containsAll(request.getCoinDenominations())) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid coin denominations provided.")
                    .build();
        }

        // Calculate minimum coins
        List<Double> result = calculateMinimumCoins(request.getTargetAmount(), request.getCoinDenominations());
        return Response.ok(result).build();
    }

    public static BigInteger findMin(BigInteger a, BigInteger b) {
        return (a.compareTo(b) <= 0) ? a : b;
    }

    public List<Double> calculateMinimumCoins(double targetAmount, List<Double> denominations) {
        int n = denominations.size();
        int[] coins = new int[n];
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int j = BigDecimal.valueOf(denominations.get(i)).setScale(2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).intValue();
            coins[i] = j;
            System.out.println(coins[i]);
        }
        // 使用一个较大的常量表示无法到达
        int INF = 100000000;  // 相当于 1e8
        int amount = BigDecimal.valueOf(targetAmount).setScale(2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).intValue();
        // f[j] 表示凑成金额 j 所需的最少硬币数
        int[] f = new int[amount + 1];
        // path[j] 用于记录凑成金额 j 时，最后使用的硬币在 coins[] 中的索引
        int[] path = new int[amount + 1];

        // 初始化
        for (int j = 1; j <= amount; j++) {
            f[j] = INF;
            path[j] = -1;
        }
        f[0] = 0;

        // 动态规划
        for (int i = 0; i < n; i++) {
            int coinVal = coins[i];
            for (int j = coinVal; j <= amount; j++) {
                if (f[j - coinVal] + 1 < f[j]) {
                    f[j] = f[j - coinVal] + 1;
                    path[j] = i;
                }
            }
        }

        // 如果 f[amount] 仍然是 INF，说明无法凑成
        if (f[amount] == INF) {
            return result;
        }

        // 打印所用硬币（从后往前回溯）
        int m = amount;
        System.out.print("使用的硬币：");
        while (m > 0) {
            int idx = path[m];
            if (idx == -1) {
                break;
            }
            result.add(coins[idx] / 100.00);
            System.out.print(coins[idx]/100.00 + " ");
            m -= coins[idx];
        }

        Collections.sort(result);
        System.out.println();  // 换行
        // 返回最少硬币数
        return result;

    }
}
