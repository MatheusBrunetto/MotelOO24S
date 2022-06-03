package com.utfpr.moteloo24s.service.impl;

import com.utfpr.moteloo24s.model.Bedroom;
import com.utfpr.moteloo24s.model.BedroomType;
import com.utfpr.moteloo24s.model.Status;
import com.utfpr.moteloo24s.repository.BedroomRepository;
import com.utfpr.moteloo24s.service.BedroomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BedroomServiceImpl extends CrudServiceImpl<Bedroom, Long> implements BedroomService {
    private final BedroomRepository bedroomRepository;

    @Override
    protected JpaRepository<Bedroom, Long> getRepository() {
        return bedroomRepository;
    }

    @Override
    @Transactional
    public Bedroom changeBedroomStatus(Bedroom bedroom, Status status) {
        log.info("Change bedroom - {} to status - {}", bedroom.getBedroomNumber(), status.getDescription());
        bedroom.setStatus(status);
        bedroomRepository.save(bedroom);

        return bedroom;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bedroom> findAvaliableBedrooms() {
        return bedroomRepository.findAllByStatus(Status.DISPONIVEL);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bedroom> findAvaliabeBedrooms(BedroomType bedroomType) {
        return bedroomRepository.findAllByStatusAndBedroomType(Status.DISPONIVEL, bedroomType);
    }
}
