package com.utfpr.moteloo24s.repository;

import com.utfpr.moteloo24s.model.Bedroom;
import com.utfpr.moteloo24s.model.BedroomType;
import com.utfpr.moteloo24s.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BedroomRepository extends JpaRepository<Bedroom, Long> {

    List<Bedroom> findAllByStatus(Status status);

    List<Bedroom> findAllByStatusAndBedroomType(Status status, BedroomType bedroomType);
}