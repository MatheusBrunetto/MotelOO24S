package com.utfpr.moteloo24s.repository;

import com.utfpr.moteloo24s.model.Bedroom;
import com.utfpr.moteloo24s.model.BedroomType;
import com.utfpr.moteloo24s.model.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import repository.BedroomRepository;
import repository.BedroomTypeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BedroomRepositoryTest {

    @Autowired
    private BedroomRepository underTest;

    @Autowired
    private BedroomTypeRepository bedroomTypeRepository;

    @AfterEach
    void tearDown() {
        bedroomTypeRepository.deleteAll();
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfFindAllByStatus() {
        List<Bedroom> bedroomList = listOfBedroomForTest();

        List<Bedroom> bedroomListTest = underTest.findAllByStatus(Status.DISPONIVEL);

        assertThat(bedroomListTest.equals(bedroomList));
    }

    @Test
    void itShouldCheckIfDontFindAllByStatus() {
        List<Bedroom> bedroomList = listOfBedroomForTest();

        List<Bedroom> bedroomListTest = underTest.findAllByStatus(Status.OCUPADO);

        assertThat(!bedroomListTest.equals(bedroomList));
    }

    @Test
    void itShouldCheckIfFindAllByStatusAndBedroomType() {
        List<Bedroom> bedroomList = listOfBedroomForTest();

        BedroomType bedroomType = bedroomTypeRepository.findAll().get(0);

        List<Bedroom> bedroomListTest = underTest.findAllByStatusAndBedroomType(Status.DISPONIVEL, bedroomType);

        assertThat(bedroomListTest.equals(bedroomList));
    }

    @Test
    void itShouldCheckIfDontFindAllByStatusAndBedroomType() {
        List<Bedroom> bedroomList = listOfBedroomForTest();

        BedroomType bedroomType = bedroomTypeRepository.findAll().get(0);

        List<Bedroom> bedroomListTest = underTest.findAllByStatusAndBedroomType(Status.DISPONIVEL, bedroomType);

        assertThat(!bedroomListTest.equals(bedroomList));
    }

    private List<Bedroom> listOfBedroomForTest() {
        List<Bedroom> bedroomList = new ArrayList<>();

        BedroomType bedroomType = createBedroomType("Standard", 100.00, 50.00);

        Bedroom bedroom = createBedroom(10, Status.DISPONIVEL, bedroomType);
        bedroomList.add(bedroom);

        return bedroomList;
    }

    private BedroomType createBedroomType(String description, Double overnightValue, Double periodValue) {
        BedroomType bedroomType =
                BedroomType.builder()
                        .description(description)
                        .overnightValue(overnightValue)
                        .periodValue(periodValue)
                        .build();
        bedroomTypeRepository.save(bedroomType);

        return bedroomType;
    }

    private Bedroom createBedroom(int bedroomNumber, Status status, BedroomType bedroomType) {
        Bedroom bedroom =
                Bedroom.builder()
                        .bedroomNumber(10)
                        .status(Status.DISPONIVEL)
                        .bedroomType(bedroomType)
                        .build();
        underTest.save(bedroom);

        return bedroom;
    }
}
