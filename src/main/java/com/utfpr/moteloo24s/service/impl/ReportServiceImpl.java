package com.utfpr.moteloo24s.service.impl;

import com.utfpr.moteloo24s.model.*;
import com.utfpr.moteloo24s.service.BedroomTypeService;
import com.utfpr.moteloo24s.service.LocationService;
import com.utfpr.moteloo24s.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final LocationService locationService;
    private final BedroomTypeService bedroomTypeService;
    @Override
    public List<TotalLocation> totalLocation(Date inicialDate, Date finalDate) {
        log.info("Iniciate variable to return");
        List<TotalLocation> totalLocationList = new ArrayList<>();

        log.info("Setting time in param dates");
        List<LocalDateTime> dates = convertDatesToLocalDateTime(inicialDate, finalDate);

        log.info("Find data in repository");
        List<Location> locationList = locationService.findAllByExitTimeGreaterThanEqualAndExitTimeLessThanEqual(dates.get(0), dates.get(1));

        log.info("Filter dates from locationList");
        filterDatesOnLocationList(locationList, totalLocationList);

        return totalLocationList;
    }

    private void filterDatesOnLocationList(List<Location> locationList, List<TotalLocation> totalLocationList) {
        locationList.stream().distinct().map(location -> { return location.getEntryTime().toLocalDate(); })
                .forEach(localDate -> {
                    locationList.stream().filter(l -> l.getEntryTime().toLocalDate() == localDate)
                            .map(location -> {
                                createOrIncrementTotalLocationList(totalLocationList, localDate, location);

                                return null;
                            });
                });
    }

    private void createOrIncrementTotalLocationList(List<TotalLocation> totalLocationList, LocalDate dateFor, Location location) {
        BigDecimal consumptionValue = BigDecimal.valueOf(locationService.calculateTotalConsumption(location));
        BigDecimal accomodationValue = BigDecimal.valueOf(location.getTotalValue()).subtract(consumptionValue);

        TotalLocation totalLocation = totalLocationList.stream()
                .filter(tl -> tl.getDate().toLocalDate().isEqual(dateFor))
                .findAny().orElse(null);

        if (totalLocation == null) {
            totalLocation = new TotalLocation(
                    Date.valueOf(dateFor),
                    Long.valueOf(1),
                    accomodationValue,
                    consumptionValue
            );
            totalLocationList.add(totalLocation);
        } else {
            totalLocation.getTotalAccommodation().add(accomodationValue);
            totalLocation.getTotalConsumption().add(consumptionValue);
            totalLocation.setQuantity(totalLocation.getQuantity() + 1);
        }
    }

    @Override
    public List<TotalLocationType> totalLocationType(Date inicialDate, Date finalDate, String periodType) {
        log.info("Iniciate variable to return");
        List<TotalLocationType> totalLocationTypeList = new ArrayList<>();

        log.info("Setting time in param dates");
        List<LocalDateTime> dates = convertDatesToLocalDateTime(inicialDate, finalDate);

        log.info("Find data in repository");
        List<Location> locationList = mountLocationListByPeriodType(periodType, dates);

        log.info("Create or increment TotalLocationTypeList");
        locationList.stream().forEach(location -> {
            createOrIncrementTotalLocationTypeList(totalLocationTypeList, location);
        });

        return totalLocationTypeList;
    }

    private void createOrIncrementTotalLocationTypeList(List<TotalLocationType> totalLocationTypeList, Location location) {
        BigDecimal locationTotalValue = BigDecimal.valueOf(location.getTotalValue());

        TotalLocationType totalLocationType = totalLocationTypeList.stream()
                .filter(tl -> tl.getPeriodType().equals(location.getPeriodType()))
                .findAny().orElse(null);

        if (totalLocationType == null) {
            totalLocationType = new TotalLocationType(
                    location.getPeriodType(),
                    Long.valueOf(1),
                    locationTotalValue
            );
            totalLocationTypeList.add(totalLocationType);
        } else {
            totalLocationType.getTotalValue().add(locationTotalValue);
            totalLocationType.setQuantity(totalLocationType.getQuantity() + 1);
        }
    }

    private List<Location> mountLocationListByPeriodType(String periodType, List<LocalDateTime> dates) {
        if (periodType == PeriodType.PERIODO.getDescription() || periodType == PeriodType.PERNOITE.getDescription()) {
            return locationService.findAllByExitTimeGreaterThanEqualAndExitTimeLessThanEqualAndPeriodType(
                    dates.get(0),
                    dates.get(1),
                    PeriodType.valueOf(periodType)
            );
        } else {
            return locationService.findAllByExitTimeGreaterThanEqualAndExitTimeLessThanEqual(
                    dates.get(0),
                    dates.get(1));
        }
    }

    private List<LocalDateTime> convertDatesToLocalDateTime(Date dateOne, Date dateTwo) {
        List<LocalDateTime> returnList = new ArrayList<>();

        returnList.add(LocalDateTime.of(dateOne.toLocalDate(), LocalTime.of(00,00,01)));
        returnList.add(LocalDateTime.of(dateTwo.toLocalDate(), LocalTime.of(23, 59, 59)));

        return returnList;
    }
}
