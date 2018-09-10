package fr.lmsys.backend.event.tools;

import java.time.LocalDate;

import org.dozer.DozerConverter;

public class LocalDateDozerConverter extends DozerConverter<LocalDate, LocalDate> {

    public LocalDateDozerConverter() {
        super(LocalDate.class, LocalDate.class);
    }

    @Override
    public LocalDate convertTo(LocalDate source, LocalDate destination) {
        if (source == null) {
            return null;
        }
        return LocalDate.from(source);
    }

    @Override
    public LocalDate convertFrom(LocalDate source, LocalDate destination) {
        if (source == null) {
            return null;
        }
        return LocalDate.from(source);
    }
}
