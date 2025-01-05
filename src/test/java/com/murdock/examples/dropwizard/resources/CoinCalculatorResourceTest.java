package com.murdock.examples.dropwizard.resources;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinCalculatorResourceTest {

    private final CoinCalculatorResource resource = new CoinCalculatorResource();

    @Test
    public void testCalculateMinimumCoins() {
        // Example 1
        List<Double> coins = Arrays.asList(1.00, 0.50, 2.50, 5.00);
        List<Double> result1 = resource.calculateMinimumCoins(11.50, coins);
        assertEquals(List.of(0.50, 1.00,5.00, 5.00), result1);

        // Example 2
        List<Double> coins2 = Arrays.asList(1.00, 0.50, 50.0, 1000.00);
        List<Double>result2 = resource.calculateMinimumCoins(12.60, coins2);
        assertEquals(0, result2.size());

        List<Double> coins3 = Arrays.asList(0.1, 2.00, 0.50, 5.0, 50.0, 1000.00);
        List<Double>result3 = resource.calculateMinimumCoins(12.60, coins3);
        assertEquals(List.of(0.10, 0.50, 2.00, 5.00,5.00), result3);

        List<Double> coins4 = Arrays.asList(0.01, 0.5, 1.0, 5.0, 10.0);
        List<Double>result4 = resource.calculateMinimumCoins(7.03, coins4);
        assertEquals(List.of(0.01, 0.01, 0.01, 1.00, 1.00, 5.00), result4);

        List<Double> coins5 = Arrays.asList(1.0, 2.0, 50.0);
        List<Double>result5 = resource.calculateMinimumCoins(103.0, coins5);
        assertEquals(List.of(1.0, 2.0, 50.0, 50.0), result5);
    }
}