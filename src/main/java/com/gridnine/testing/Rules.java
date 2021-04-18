package com.gridnine.testing;

import java.time.Duration;
import java.util.List;
import java.time.LocalDateTime;

public class Rules{
    static public boolean isBefore(Flight flight, LocalDateTime departure){
        List<Segment> segments = flight.getSegments();
        Segment first = segments.get(0);
        return departure.isBefore(first.getDepartureDate());
    }

    static public boolean isSegmentsTimeComply(Flight flight){
        List<Segment> segments = flight.getSegments();
        Segment current;
        Segment next;
        for (int i=0; i < segments.size()-1; ++i){
            current = segments.get(i);
            next = segments.get(++i);
            if ( current.getArrivalDate().isAfter(next.getDepartureDate()) ){
                return false;
            }
        }
        return true;
    }

    static public boolean isCommonEarthTimeNoLongerThan(Flight flight, Duration duration){
        List<Segment> segments = flight.getSegments();
        Segment current;
        Segment next;
        Duration common_time = Duration.ofSeconds(0);
        for (int i=0; i < segments.size()-1; ++i){
            current = segments.get(i);
            next = segments.get(++i);
            common_time = common_time.plus(Duration.between(current.getArrivalDate(), next.getDepartureDate()));
        }
        if (common_time.compareTo(duration) == 1){
            return false;
        }
        return true;
    }
}
