package com.utfpr.moteloo24s.controller;

import com.utfpr.moteloo24s.model.Item;
import com.utfpr.moteloo24s.service.CrudService;
import com.utfpr.moteloo24s.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("item")
public class ItemController extends CrudController<Item, Long> {

    private final ItemService itemService;

    @Override
    protected CrudService<Item, Long> getService() {
        return itemService;
    }
}
