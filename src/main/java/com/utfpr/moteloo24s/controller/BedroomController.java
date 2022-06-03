package com.utfpr.moteloo24s.controller;

import com.utfpr.moteloo24s.model.Bedroom;
import com.utfpr.moteloo24s.model.BedroomType;
import com.utfpr.moteloo24s.service.BedroomService;
import com.utfpr.moteloo24s.service.BedroomTypeService;
import com.utfpr.moteloo24s.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("bedroom")
public class BedroomController extends CrudController<Bedroom, Long> {
    private final BedroomService bedroomService;
    private final BedroomTypeService bedroomTypeService;

    @Override
    protected CrudService<Bedroom, Long> getService() {
        return bedroomService;
    }

    @GetMapping("avaliable")
    public List<Bedroom> findAvaliableBedrooms(@RequestParam(required = false) Long bedroomTypeId) {
        if (bedroomTypeId != null) {
            BedroomType bedroomType = bedroomTypeService.findOne(bedroomTypeId);
            return bedroomService.findAvaliabeBedrooms(bedroomType);
        } else {
            return bedroomService.findAvaliableBedrooms();
        }
    }
}
