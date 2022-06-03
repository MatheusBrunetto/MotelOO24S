package com.utfpr.moteloo24s.service;

import com.utfpr.moteloo24s.model.Bedroom;
import com.utfpr.moteloo24s.model.Location;
import com.utfpr.moteloo24s.model.PeriodType;

import java.util.UUID;

public interface LocationService extends CrudService<Location, UUID> {

    public abstract Location findOpenLocationByBedroom(Bedroom bedroom);

    public abstract Location startLocation(Bedroom bedroom);

    public abstract Location startLocation(Bedroom bedroom, PeriodType periodType);

    public abstract Location finalizeLocation(UUID id);

    public abstract Location finalizeLocation(Location location);
}
