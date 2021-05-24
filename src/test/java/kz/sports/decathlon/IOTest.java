package kz.sports.decathlon;

import kz.sports.decathlon.io.impl.CSVReader;
import kz.sports.decathlon.io.impl.XMLWriter;
import kz.sports.decathlon.models.tournament.MaleTournamentManual;
import kz.sports.decathlon.models.tournament.RawTournamentResults;
import kz.sports.decathlon.models.tournament.Tournament;
import kz.sports.decathlon.models.tournament.participant.Participant;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class IOTest {

    @Test
    void CSVread() {
        CSVReader reader = new CSVReader();
        RawTournamentResults results = null;
        try {
            results = reader.read("./assets/sample_input3.csv");
            assertThat(results.size()).isEqualTo(28);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void XMLWrite() {

        CSVReader reader = new CSVReader();
        RawTournamentResults results;
        Tournament tournament = new MaleTournamentManual();
        try {
            results = reader.read("./assets/sample_input3.csv");
            results.populateTournament(tournament);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Participant> winners = tournament.getParticipantsOrderedByPlace();

        assertThat(winners.size()).isEqualTo(28);

        XMLWriter writer = new XMLWriter();
        Document doc = writer.prepareDocument(winners);
        assertThat(doc.getChildNodes().item(0).getChildNodes().getLength()).isEqualTo(28);
    }
}
