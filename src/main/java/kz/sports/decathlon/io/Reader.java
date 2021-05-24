package kz.sports.decathlon.io;

import kz.sports.decathlon.models.tournament.RawTournamentResults;

import java.io.IOException;

public interface Reader {
    RawTournamentResults read(String path) throws IOException;
}
