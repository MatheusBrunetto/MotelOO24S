package com.utfpr.moteloo24s.controller;

import com.utfpr.moteloo24s.model.Bedroom;
import com.utfpr.moteloo24s.model.Location;
import com.utfpr.moteloo24s.model.LocationItem;
import com.utfpr.moteloo24s.model.PeriodType;
import com.utfpr.moteloo24s.service.BedroomService;
import com.utfpr.moteloo24s.service.CrudService;
import com.utfpr.moteloo24s.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("location")
public class LocationController extends CrudController<Location, UUID> {

    private final LocationService locationService;

    private final BedroomService bedroomService;
    @Override
    protected CrudService<Location, UUID> getService() {
        return locationService;
    }

    @GetMapping("open")
    public Location findOpenLocationByBedroom(@RequestParam Long bedroomId) {
        return locationService.findOpenLocationByBedroom(bedroomService.findOne(bedroomId));
    }

    @PostMapping("start")
    public Location startLocation(
            @RequestParam Long bedroomId,
            @RequestParam(required = false) String periodTypeDescription) {
        Bedroom bedroom = bedroomService.findOne(bedroomId);
        if (!periodTypeDescription.isEmpty()) {
            return locationService.startLocation(bedroom, PeriodType.valueOf(periodTypeDescription));
        } else {
            return locationService.startLocation(bedroom);
        }
    }

    @PostMapping("finalize")
    public Location finalizeLocation(
            @RequestParam String locationId) {
        return locationService.finalizeLocation(UUID.fromString(locationId));
    }

}
