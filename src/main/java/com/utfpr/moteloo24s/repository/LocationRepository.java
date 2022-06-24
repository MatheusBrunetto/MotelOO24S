package com.utfpr.moteloo24s.repository;

import com.utfpr.moteloo24s.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

    Location findByBedroomAndSituation(Bedroom bedroom, Situation situation);

    List<Location> findAllByExitTimeGreaterThanEqualAndExitTimeLessThanEqual(LocalDateTime inicialDate, LocalDateTime finalDate);

    List<Location> findAllByExitTimeGreaterThanEqualAndExitTimeLessThanEqualAndPeriodType(LocalDateTime incialDate, LocalDateTime finalDate, PeriodType periodType);
}