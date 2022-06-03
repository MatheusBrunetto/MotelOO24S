package com.utfpr.moteloo24s.controller;

import com.utfpr.moteloo24s.model.LocationItem;
import com.utfpr.moteloo24s.service.CrudService;
import com.utfpr.moteloo24s.service.LocationItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("locationitem")
public class LocationItemController extends CrudController<LocationItem, UUID> {

    private final LocationItemService locationItemService;

    @Override
    protected CrudService<LocationItem, UUID> getService() {
        return locationItemService;
    }
}
