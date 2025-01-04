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
//        List<Double> coins = Arrays.asList(1.00, 0.50, 2.50, 5.00);
//        List<Double> result1 = resource.calculateMinimumCoins(11.50, coins);
//        assertEquals(List.of(0.50, 1.00,5.00, 5.00), result1);

        // Example 2
        List<Double> coins2 = Arrays.asList(1.00, 0.50, 50.0, 1000.00);
        List<Double>result2 = resource.calculateMinimumCoins(12.60, coins2);
        assertEquals(0, result2.size());
    }
}