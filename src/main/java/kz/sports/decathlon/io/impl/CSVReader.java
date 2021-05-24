package kz.sports.decathlon.io.impl;

import kz.sports.decathlon.io.Reader;
import kz.sports.decathlon.models.tournament.RawTournamentResults;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader implements Reader {

    private static final String DELIMITER = ";";

    @Override
    public RawTournamentResults read(String path) throws IOException {
        RawTournamentResults records = new RawTournamentResults();
        readInto(path, records);
        return records;
    }

    private void readInto(String path, RawTournamentResults records) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(DELIMITER);
                records.add(values);
            }
        }
    }
}
