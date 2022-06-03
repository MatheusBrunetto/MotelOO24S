package com.utfpr.moteloo24s.service.impl;

import com.utfpr.moteloo24s.model.LocationItem;
import com.utfpr.moteloo24s.repository.LocationItemRepository;
import com.utfpr.moteloo24s.service.LocationItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class LocationItemServiceImpl extends CrudServiceImpl<LocationItem, UUID> implements LocationItemService {
    private final LocationItemRepository locationItemRepository;

    @Override
    protected JpaRepository<LocationItem, UUID> getRepository() {
        return locationItemRepository;
    }
}
