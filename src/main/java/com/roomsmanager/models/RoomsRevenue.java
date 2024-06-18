package com.roomsmanager.models;

import lombok.Data;

@Data
public class RoomsRevenue {
    int premiumUsage = 0;
    float premiumRevenue = 0;
    int economyUsage = 0;
    float economyRevenue = 0;

    public RoomsRevenue(int premiumUsage, float premiumRevenue, int economyUsage, float economyRevenue) {
        this.premiumUsage = premiumUsage;
        this.premiumRevenue = premiumRevenue;
        this.economyUsage = economyUsage;
        this.economyRevenue = economyRevenue;
    }
}
