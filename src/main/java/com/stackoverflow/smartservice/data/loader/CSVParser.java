package com.stackoverflow.smartservice.data.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads and parses CSV files using only java.io.BufferedReader /
 * java.io.FileReader — no external library.
 *
 * Handles quoted fields (so a field can contain a comma or an embedded
 * double quote, escaped as "") and blank lines are skipped rather than
 * turned into a phantom record.
 */
public class CSVParser {

    /**
     * One raw row read from a CSV file, split into fields, together with
     * the 1-based physical line number it came from in the source file
     * (matches what a person would see opening the file in an editor).
     */
    public static class CSVRecord {
        private final String[] fields;
        private final int lineNumber;

        public CSVRecord(String[] fields, int lineNumber) {
            this.fields = fields;
            this.lineNumber = lineNumber;
        }

        public String[] getFields() { return fields; }
        public int getLineNumber() { return lineNumber; }
    }

    /**
     * Parses every row of the given CSV file, including the header row
     * (callers that expect a header should treat the first returned
     * record as such). Blank lines are skipped and do not consume a
     * record, but the line-number count still advances so reported line
     * numbers match the file on disk.
     */
    public List<CSVRecord> parseFile(String filePath) throws IOException {
        List<CSVRecord> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue;
                }
                records.add(new CSVRecord(parseLine(line), lineNumber));
            }
        }
        return records;
    }

    /**
     * Splits one CSV line into fields, honoring double-quoted fields
     * (which may contain commas) and the "" escape for a literal quote
     * inside a quoted field.
     */
    String[] parseLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (inQuotes) {
                if (c == '"') {
                    if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                        current.append('"');
                        i++; // skip the escaped quote's pair
                    } else {
                        inQuotes = false;
                    }
                } else {
                    current.append(c);
                }
            } else {
                if (c == '"') {
                    inQuotes = true;
                } else if (c == ',') {
                    fields.add(current.toString());
                    current.setLength(0);
                } else {
                    current.append(c);
                }
            }
        }
        fields.add(current.toString());

        return fields.toArray(new String[0]);
    }
}
