package com.utfpr.moteloo24s.repository;

import com.utfpr.moteloo24s.model.Bedroom;
import com.utfpr.moteloo24s.model.Location;
import com.utfpr.moteloo24s.model.Situation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

    Location findByBedroomAndSituation(Bedroom bedroom, Situation situation);
}