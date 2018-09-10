package fr.lmsys.backend.event.tools;

import java.time.LocalDateTime;

import org.dozer.DozerConverter;

public class LocalDateTimeDozerConverter extends DozerConverter<LocalDateTime, LocalDateTime> {

    public LocalDateTimeDozerConverter() {
        super(LocalDateTime.class, LocalDateTime.class);
    }

    @Override
    public LocalDateTime convertTo(LocalDateTime source, LocalDateTime destination) {
        if (source == null) {
            return null;
        }
        return LocalDateTime.from(source);
    }

    @Override
    public LocalDateTime convertFrom(LocalDateTime source, LocalDateTime destination) {
        if (source == null) {
            return null;
        }
        return LocalDateTime.from(source);
    }
}
