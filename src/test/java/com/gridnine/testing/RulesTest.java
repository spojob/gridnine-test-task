package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class RulesTest {

    @Test
    void isBefore() {
        List<Flight> flight = FlightBuilder.createFlights();
        List<Boolean> real = flight.stream()
                                   .map(f -> Rules.isBefore(f, LocalDateTime.now()))
                                   .collect(Collectors.toList());
        List<Boolean> reference = Arrays.asList( new Boolean[]{true, true, false, true, true, true});
        assertEquals(real, reference);
    }

    @Test
    void isSegmentsTimeComply() {
        List<Flight> flight = FlightBuilder.createFlights();
        List<Boolean> real = flight.stream()
                                   .map(f -> Rules.isSegmentsTimeComply(f))
                                   .collect(Collectors.toList());
        List<Boolean> reference = Arrays.asList( new Boolean[]{true, true, true, true, true, true});
        assertEquals(real, reference);
    }

    @Test
    void isCommonEarthTimeNoLongerThan() {
        List<Flight> flight = FlightBuilder.createFlights();
        List<Boolean> real = flight.stream()
                .map(f -> Rules.isCommonEarthTimeNoLongerThan(f, Duration.ofHours(2)))
                .collect(Collectors.toList());
        List<Boolean> reference = Arrays.asList( new Boolean[]{true, true, true, true, false, true});
        assertEquals(real, reference);
    }
}