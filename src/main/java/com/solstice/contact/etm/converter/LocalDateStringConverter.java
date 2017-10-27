package com.solstice.contact.etm.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.persistence.AttributeConverter;

public class LocalDateStringConverter implements AttributeConverter<LocalDate, String> {

  private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");

  @Override
  public String convertToDatabaseColumn(final LocalDate localDate) {

    try {
      return localDate.format(format);
    } catch (final NullPointerException e) {
      throw new IllegalArgumentException(
          String.format("Cannot convert '%s' to String", localDate), e);
    }
  }

  @Override
  public LocalDate convertToEntityAttribute(final String source) {
    try {
      return LocalDate.parse(source, format);
    } catch (final DateTimeParseException e) {
      throw new IllegalArgumentException(
          String.format("Cannot convert '%s' to LocalDate", source), e);
    }
  }
}
