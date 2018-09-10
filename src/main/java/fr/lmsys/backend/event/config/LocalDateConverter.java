package fr.lmsys.backend.event.config;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate dateToConvert) {
    	 return java.util.Date.from(dateToConvert.atStartOfDay()
    		      .atZone(ZoneId.systemDefault())
    		      .toInstant());
    }

    @Override
    public LocalDate convertToEntityAttribute(Date dateToConvert) {
    	return Instant.ofEpochMilli(dateToConvert.getTime())
    		      .atZone(ZoneId.systemDefault())
    		      .toLocalDate();
    }
    /**
     * LocalDateTime  localDateTime = LocalDateTime.from(new Date().toInstant());

to resolve the issue, please pass in zone -

LocalDateTime  localDateTime = LocalDateTime.from(new Date().toInstant().atZone(ZoneId.of("UTC")));
     */
}

