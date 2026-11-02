
package com.bit.backend.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class CommonCodeGenerator {

    private static final ZoneId ZONE_ID =
            ZoneId.of("Asia/Colombo");

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public String generateCode(
            String prefix,
            Integer id,
            String name,
            int columnLength) {

        if (prefix == null || prefix.isBlank()) {
            throw new IllegalArgumentException(
                    "Code prefix is required");
        }

        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Valid database ID is required");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Name is required for code generation");
        }

        // Generate current date
        String date = LocalDate.now(ZONE_ID)
                .format(DATE_FORMAT);

        // Generate six-digit minimum sequence
        String sequence = String.format(
                Locale.ROOT, "%06d", id);

        // Build code prefix
        String baseCode = prefix.trim().toUpperCase(Locale.ROOT)
                + "/" + date
                + "/" + sequence
                + "/";

        // Calculate available space for name
        int availableLength =
                columnLength - baseCode.length();

        if (availableLength < 1) {
            throw new IllegalArgumentException(
                    "Column length is insufficient");
        }

        // Clean name
        String identifier = name.trim()
                .toUpperCase(Locale.ROOT)
                .replaceAll("[^\\p{L}\\p{N}]+", "-")
                .replaceAll("^-+|-+$", "");

        if (identifier.isBlank()) {
            throw new IllegalArgumentException(
                    "Invalid name for code generation");
        }

        // Respect database column length
        if (identifier.length() > availableLength) {
            identifier = identifier.substring(
                    0, availableLength);
        }

        identifier = identifier.replaceAll("-+$", "");

        if (identifier.isBlank()) {
            throw new IllegalArgumentException(
                    "Unable to generate valid identifier");
        }

        return baseCode + identifier;
    }
}
