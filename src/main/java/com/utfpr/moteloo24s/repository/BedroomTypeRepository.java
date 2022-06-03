package com.utfpr.moteloo24s.repository;

import com.utfpr.moteloo24s.model.BedroomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedroomTypeRepository extends JpaRepository<BedroomType, Long> {
}