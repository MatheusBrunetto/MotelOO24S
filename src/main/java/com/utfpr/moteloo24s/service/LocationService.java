package com.utfpr.moteloo24s.service;

import com.utfpr.moteloo24s.model.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface LocationService extends CrudService<Location, UUID> {

    public abstract Location findOpenLocationByBedroom(Bedroom bedroom);

    public abstract Location startLocation(Bedroom bedroom);

    public abstract Location startLocation(Bedroom bedroom, PeriodType periodType);

    public abstract Location finalizeLocation(UUID id);

    public abstract Location finalizeLocation(Location location);

    public abstract List<Location> findAllByExitTimeGreaterThanEqualAndExitTimeLessThanEqual(Date inicialDate, Date finalDate);

    public abstract List<Location> findAllByExitTimeGreaterThanEqualAndExitTimeLessThanEqualAndAndBedroom_BedroomType(Date inicialDate, Date finalDate, BedroomType bedroomType);

    public abstract double calculateTotalConsumption(Location location);

}
