package vn.gtel.gtelpractice.repositories;

import org.springframework.stereotype.Repository;
import vn.gtel.gtelpractice.models.Airport;

import java.util.List;


@Repository
public interface AirportRepositoryCustom {
    List<Airport> searchAirportsCustom(String keyword);
}
