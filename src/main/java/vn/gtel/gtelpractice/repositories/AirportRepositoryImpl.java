package vn.gtel.gtelpractice.repositories;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import vn.gtel.gtelpractice.models.Airport;

import java.util.List;

@Repository
public class AirportRepositoryImpl implements AirportRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Airport> searchAirportsCustom(String keyword) {
        String sql = "SELECT * FROM airport WHERE name LIKE :keyword OR airport_code LIKE :keyword OR iata LIKE :keyword OR language LIKE :keyword OR priority LIKE :keyword";
        Query query = entityManager.createNativeQuery(sql, Airport.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();

    }
}
