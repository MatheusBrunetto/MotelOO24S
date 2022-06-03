package com.utfpr.moteloo24s.controller;

import com.utfpr.moteloo24s.model.BedroomType;
import com.utfpr.moteloo24s.service.BedroomTypeService;
import com.utfpr.moteloo24s.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("bedroomtype")
public class BedroomTypeController extends CrudController<BedroomType, Long> {
    private final BedroomTypeService bedroomTypeService;

    @Override
    protected CrudService<BedroomType, Long> getService() {
        return bedroomTypeService;
    }
}
