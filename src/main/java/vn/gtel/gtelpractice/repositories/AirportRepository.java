package vn.gtel.gtelpractice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.gtel.gtelpractice.models.Airport;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String>, AirportRepositoryCustom {
    boolean existsByAirportCode(String airportCode);

    long count();



    //Native Query
    @Query(value = "select * from airport a where a.name like %:keyword% or a.airport_code like %:keyword% " +
            "or a.iata like %:keyword% or a.language like %:keyword% or a.priority like %:keyword%", nativeQuery = true)
    List<Airport> searchAirports(@Param("keyword") String keyword);
}
