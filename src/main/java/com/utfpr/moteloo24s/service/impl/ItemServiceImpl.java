package com.utfpr.moteloo24s.service.impl;

import com.utfpr.moteloo24s.model.Item;
import com.utfpr.moteloo24s.repository.ItemRepository;
import com.utfpr.moteloo24s.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ItemServiceImpl extends CrudServiceImpl<Item, Long> implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    protected JpaRepository<Item, Long> getRepository() {
        return itemRepository;
    }
}
