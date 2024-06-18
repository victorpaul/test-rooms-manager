package com.roomsmanager.services;

import com.roomsmanager.models.RoomsRevenue;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RoomsService {

    Optional<RoomsRevenue> calculateRoomsRevenue(int freePremiumRooms, int freeEconomyRooms, float[] payments);
}
