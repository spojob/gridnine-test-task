package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void printFlights (List<Flight> list){
        for (Flight f :list){
            System.out.println(f);
        }
    }

    public static void main(String args[]){
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("Flights before filtration:");
        printFlights(flights);

        flights = flights.stream().filter(
                flight -> Rules.isBefore(flight, LocalDateTime.now())
        ).collect(Collectors.toList());
        System.out.println("Flights that are newer than now:");
        printFlights(flights);

        flights = flights.stream().filter(
                flight -> Rules.isSegmentsTimeComply(flight)
        ).collect(Collectors.toList());
        System.out.println("Flights that are time complined:");
        printFlights(flights);

        flights = flights.stream().filter(
                flight -> Rules.isCommonEarthTimeNoLongerThan(flight, Duration.ofHours(2))
        ).collect(Collectors.toList());
        System.out.println("Flights which time duration on earth is lower than 2 hours:");
        printFlights(flights);
    }
}