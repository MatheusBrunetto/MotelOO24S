package com.utfpr.moteloo24s.service;

import com.utfpr.moteloo24s.model.Bedroom;
import com.utfpr.moteloo24s.model.BedroomType;
import com.utfpr.moteloo24s.model.Status;

import java.util.List;

public interface BedroomService extends CrudService<Bedroom, Long> {

    public abstract Bedroom changeBedroomStatus(Bedroom bedroom, Status status);

    public abstract List<Bedroom> findAvaliableBedrooms();

    public abstract List<Bedroom> findAvaliabeBedrooms(BedroomType bedroomType);
}
