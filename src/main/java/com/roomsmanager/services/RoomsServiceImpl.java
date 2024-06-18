package com.roomsmanager.services;

import com.roomsmanager.models.RoomsRevenue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class RoomsServiceImpl implements RoomsService {

    // dummy hardcoded data
    public static final List<Double> defaultUserPayments = new ArrayList<>(List.of(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0));

    @Override
    public Optional<RoomsRevenue> calculateRoomsRevenue(int freePremiumRooms, int freeEconomyRooms, List<Double> payments) {
        if (freePremiumRooms < 0 || freeEconomyRooms < 0 || payments.isEmpty()) return Optional.empty();

        List<Double> sortedByPriority = payments.stream()
                .sorted(Comparator.reverseOrder())
                .toList();

        List<Double> premiumUsed = new ArrayList<>();
        List<Double> economUsed = new ArrayList<>();

        sortedByPriority.forEach((pay) -> {
            final var isPremium = pay >= 100;
            final var premiumAvailable = premiumUsed.size() < freePremiumRooms;
            final var economAvailable = economUsed.size() < freeEconomyRooms;

            if (isPremium && premiumAvailable) {
                premiumUsed.add(pay);
                return;
            }

            if (!isPremium && economAvailable) {
                economUsed.add(pay);
                return;
            }

            if (!isPremium && premiumAvailable) {
                premiumUsed.add(economUsed.getFirst());
                economUsed.removeFirst();
                economUsed.add(pay);
            }

        });

        return Optional.of(new RoomsRevenue(
                premiumUsed.size(), premiumUsed.stream().mapToDouble(Double::doubleValue).sum(),
                economUsed.size(), economUsed.stream().mapToDouble(Double::doubleValue).sum()
        ));


    }
}
