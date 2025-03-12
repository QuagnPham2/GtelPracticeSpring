package vn.gtel.gtelpractice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.gtel.gtelpractice.models.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {
    boolean existsByAirportCode(String airportCode);

    long count();
}
