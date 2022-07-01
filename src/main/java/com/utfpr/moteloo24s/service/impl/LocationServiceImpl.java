package com.utfpr.moteloo24s.service.impl;

import com.utfpr.moteloo24s.model.*;
import repository.LocationRepository;
import com.utfpr.moteloo24s.service.BedroomService;
import com.utfpr.moteloo24s.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class LocationServiceImpl extends CrudServiceImpl<Location, UUID> implements LocationService {
    private final LocationRepository locationRepository;

    private final BedroomService bedroomService;

    @Override
    protected JpaRepository<Location, UUID> getRepository() {
        return locationRepository;
    }

    @Override
    @Transactional
    public Location findOpenLocationByBedroom(Bedroom bedroom) {
        return locationRepository.findByBedroomAndSituation(bedroom, Situation.ABERTA);
    }

    @Override
    @Transactional
    public Location startLocation(Bedroom bedroom) {
        return startLocation(bedroom, PeriodType.PERIODO);
    }

    @Override
    @Transactional
    public Location startLocation(Bedroom bedroom, PeriodType periodType) {
        log.info("Start location with periodType - {}", periodType.getDescription());
        Location location = new Location().builder()
                .bedroom(bedroom)
                .periodType(periodType)
                .situation(Situation.ABERTA)
                .entryTime(LocalDateTime.now())
                .totalValue(calculateLocationValue(bedroom, periodType))
                .build();
        locationRepository.save(location);

        bedroomService.changeBedroomStatus(bedroom, Status.OCUPADO);

        return location;
    }

    @Override
    @Transactional
    public Location finalizeLocation(UUID id) {
        return finalizeLocation(locationRepository.getById(id));
    }

    @Override
    @Transactional
    public Location finalizeLocation(Location location) {
        log.info("Finalize location - {}", location.getId());
        location.setExitTime(LocalDateTime.now());
        location.setSituation(Situation.FINALIZADA);
        locationRepository.save(location);

        bedroomService.changeBedroomStatus(location.getBedroom(), Status.DISPONIVEL);

        return location;
    }

    @Override
    public List<Location> findAllByExitTimeGreaterThanEqualAndExitTimeLessThanEqual(LocalDateTime inicialDate, LocalDateTime finalDate) {
        return locationRepository.findAllByExitTimeGreaterThanEqualAndExitTimeLessThanEqual(inicialDate, finalDate);
    }

    @Override
    public List<Location> findAllByExitTimeGreaterThanEqualAndExitTimeLessThanEqualAndPeriodType(LocalDateTime inicialDate, LocalDateTime finalDate, PeriodType periodType) {
        return locationRepository.findAllByExitTimeGreaterThanEqualAndExitTimeLessThanEqualAndPeriodType(inicialDate, finalDate, periodType);
    }

    private double calculateLocationValue(Bedroom bedroom, PeriodType periodType) {
        log.info("Get location value by period type {} for bedroom {}",
                periodType.getDescription(),
                bedroom.getBedroomNumber());
        if (periodType.equals(PeriodType.PERIODO)) {
            return bedroom.getBedroomType().getPeriodValue();
        } else if (periodType.equals(PeriodType.PERNOITE)) {
            return bedroom.getBedroomType().getOvernightValue();
        } else {
            return 0;
        }
    }

    @Override
    public double calculateTotalConsumption(Location location) {
        return location.getLocationItems().stream().collect(Collectors.summingDouble(LocationItem::getValue));
    }

}
