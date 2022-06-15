package com.utfpr.moteloo24s.service.impl;

import com.utfpr.moteloo24s.model.BedroomType;
import com.utfpr.moteloo24s.model.Location;
import com.utfpr.moteloo24s.model.TotalLocation;
import com.utfpr.moteloo24s.model.TotalLocationType;
import com.utfpr.moteloo24s.service.BedroomTypeService;
import com.utfpr.moteloo24s.service.LocationService;
import com.utfpr.moteloo24s.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        log.info("Find data in repository and mount TotalLocation to add to list");
        List<Location> locationList = locationService.findAllByExitTimeGreaterThanEqualAndExitTimeLessThanEqual(inicialDate, finalDate);

        for (LocalDate dateFor = inicialDate.toLocalDate(); dateFor.isBefore(finalDate.toLocalDate()); dateFor.plusDays(1)) {
            locationList.stream().filter(l -> l.getEntryTime().toLocalDate() == dateFor)
                .map(location -> {
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

                    return null;
                }).collect(Collectors.toList());
        }

        return totalLocationList;
    }

    @Override
    public List<TotalLocationType> totalLocationType(Date inicialDate, Date finalDate, Long type) {
        log.info("Iniciate variable to return");
        List<TotalLocationType> totalLocationTypeList = new ArrayList<>();

        log.info("Find bedroomType by type_id" + type.toString());
        BedroomType bedroomType = bedroomTypeService.findOne(type);

        log.info("Find data in repository and mount TotalLocationType to add to list");
        List<Location> locationList = locationService.findAllByExitTimeGreaterThanEqualAndExitTimeLessThanEqualAndAndBedroom_BedroomType(inicialDate, finalDate, bedroomType);
        return totalLocationTypeList;
    }
}
