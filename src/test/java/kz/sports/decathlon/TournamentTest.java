package kz.sports.decathlon;

import kz.sports.decathlon.models.event.Event;
import kz.sports.decathlon.models.event.EventParams;
import kz.sports.decathlon.models.event.EventTypeEnum;
import kz.sports.decathlon.models.tournament.*;
import kz.sports.decathlon.models.tournament.participant.Participant;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class TournamentTest {

    @Test
    void testOrderedResults() {
        MaleTournament tournament = new MaleTournament();

        String[] ashtonEatonResults = {"10.23", "7.88", "14.52", "2.01", "45.00", "13.69", "43.34", "5.20", "63.63", "257.52"};
        String[] kevinMayerResults = {"10.55", "7.80", 	"16.00", "2.05", "48.42", "13.75", "50.54", "5.45", "71.90", "4.36.11"};

        tournament.addParticipant("Ashton Eaton", ashtonEatonResults);
        tournament.addParticipant("Kevin Mayer", kevinMayerResults);
        tournament.addParticipant("Ashton Eaton Clone", ashtonEatonResults);

        ArrayList<Participant> participants = tournament.getParticipantsOrderedByPlace();
        assertThat(participants).containsAll(tournament.getParticipants());

        assertThat(participants).startsWith(tournament.getParticipants().get(1));

        assertThat(participants.get(0).getPlace()).isEqualTo("1");
        assertThat(participants.get(0).getName()).isEqualTo("Kevin Mayer");
        assertThat(participants.get(1).getPlace()).isEqualTo("2-3");
        assertThat(participants.get(2).getPlace()).isEqualTo("2-3");


    }

    @Test
    void testRawTournamentResultPopulatesTournament() {
        RawTournamentResults results = new RawTournamentResults();
        String[] row1 = {"Irina Naumenko", "12.58", "5.98", "12.51", "1.77", "55.91", "14.42", "34.63", "3.30", "37.57", "4.59.03"};
        String[] row2 = {"Anna Snetkova", "12.66", "5.98", "13.48", "1.69", "58.88", "14.19", "36.9", "3.70", "37.50", "5.17.67"};
        String[] row3 = {"Julie Mezerette- Martin", "12.79", "5.67", "11.80", "1.68", "57.78", "14.92", "40.39", "2.80", "42.24", "5.00.62"};
        results.add(row3);
        results.add(row1);
        results.add(row2);
        FemaleTournament tournament = new FemaleTournament();

        results.populateTournament(tournament);

        assertThat(tournament.getParticipants()).hasSize(3);
        assertThat(tournament.getParticipantsOrderedByPlace().get(2).getName()).isEqualTo("Julie Mezerette- Martin");
    }

}
