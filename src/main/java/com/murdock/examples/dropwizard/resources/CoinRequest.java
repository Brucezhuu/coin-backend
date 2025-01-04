package com.murdock.examples.dropwizard.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CoinRequest {
    private double targetAmount;
    private List<Double> coinDenominations;

    @JsonProperty
    public double getTargetAmount() {
        return targetAmount;
    }

    @JsonProperty
    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    @JsonProperty
    public List<Double> getCoinDenominations() {
        return coinDenominations;
    }

    @JsonProperty
    public void setCoinDenominations(List<Double> coinDenominations) {
        this.coinDenominations = coinDenominations;
    }
}
