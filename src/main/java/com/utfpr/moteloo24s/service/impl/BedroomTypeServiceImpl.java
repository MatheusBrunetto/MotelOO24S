package com.utfpr.moteloo24s.service.impl;

import com.utfpr.moteloo24s.model.BedroomType;
import repository.BedroomTypeRepository;
import com.utfpr.moteloo24s.service.BedroomTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BedroomTypeServiceImpl extends CrudServiceImpl<BedroomType, Long> implements BedroomTypeService {
    private final BedroomTypeRepository bedroomTypeRepository;

    @Override
    protected JpaRepository<BedroomType, Long> getRepository() {
        return bedroomTypeRepository;
    }
}
