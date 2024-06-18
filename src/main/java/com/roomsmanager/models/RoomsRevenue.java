package com.roomsmanager.models;

import lombok.Data;

@Data
public class RoomsRevenue {
    int premiumUsage = 0;
    double premiumRevenue = 0;
    int economyUsage = 0;
    double economyRevenue = 0;

    public RoomsRevenue(int premiumUsage, double premiumRevenue, int economyUsage, double economyRevenue) {
        this.premiumUsage = premiumUsage;
        this.premiumRevenue = premiumRevenue;
        this.economyUsage = economyUsage;
        this.economyRevenue = economyRevenue;
    }
}
