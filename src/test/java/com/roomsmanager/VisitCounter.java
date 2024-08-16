package com.roomsmanager;

import java.util.*;
import java.util.stream.Collectors;

class UserStats {
    private Optional<Long> visitCount;

    public UserStats(Optional<Long> visitCount) {
        this.visitCount = visitCount;
    }

    Optional<Long> getVisitCount() {
        return visitCount;
    }
}

public class VisitCounter {

    public Map<Long, Long> count(Map<String, UserStats>... visits) {

        if (visits == null) {
            return new HashMap<>();
        }

        return Arrays.stream(visits)
                .filter(Objects::nonNull)
                .flatMap(v -> v.entrySet().stream())
                .map(entry -> {
                    if (entry.getKey() != null && entry.getValue() != null && entry.getValue().getVisitCount().isPresent()) {
                        try {
                            return new AbstractMap.SimpleEntry<>(
                                    Long.parseLong(entry.getKey()),
                                    entry.getValue().getVisitCount().get()
                            );
                        } catch (NumberFormatException ignored) {
                        }
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Long::sum
                ));
    }
}