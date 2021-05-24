package kz.sports.decathlon.models.tournament;

import java.util.ArrayList;
import java.util.Arrays;

public class RawTournamentResults extends ArrayList<String[]> {


    public void populateTournament(Tournament tournament) {
        for (String[] row: this) {
            String name = row[0];
            String[] results = Arrays.copyOfRange(row, 1, row.length);
            tournament.addParticipant(name, results);
        }
    }

}
