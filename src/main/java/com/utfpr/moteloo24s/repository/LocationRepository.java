package com.utfpr.moteloo24s.repository;

import com.utfpr.moteloo24s.model.Bedroom;
import com.utfpr.moteloo24s.model.BedroomType;
import com.utfpr.moteloo24s.model.Location;
import com.utfpr.moteloo24s.model.Situation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

    Location findByBedroomAndSituation(Bedroom bedroom, Situation situation);

    List<Location> findAllByExitTimeGreaterThanEqualAndExitTimeLessThanEqual(Date inicialDate, Date finalDate);

    List<Location> findAllByExitTimeGreaterThanEqualAndExitTimeLessThanEqualAndAndBedroom_BedroomType(Date incialDate, Date finalDate, BedroomType bedroomType);
}