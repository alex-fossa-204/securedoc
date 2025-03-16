package com.getarrayz.securedoc.enumeration.converter;

import com.getarrayz.securedoc.enumeration.Authority;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Authority, String> {

    @Override
    public String convertToDatabaseColumn(Authority attribute) {
        return Optional.ofNullable(attribute)
                .map(Authority::getValue)
                .orElse(null);
    }

    @Override
    public Authority convertToEntityAttribute(String dbData) {
        return Optional.ofNullable(dbData)
                .flatMap(dbDataChecked -> Stream.of(Authority.values())
                        .filter(authority -> authority.getValue().equals(dbDataChecked))
                        .findFirst())
                .orElseThrow(IllegalArgumentException::new);
    }

}
