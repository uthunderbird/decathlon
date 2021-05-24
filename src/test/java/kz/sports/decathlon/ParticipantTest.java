package kz.sports.decathlon;

import kz.sports.decathlon.models.tournament.MaleTournament;
import kz.sports.decathlon.models.tournament.Tournament;
import kz.sports.decathlon.models.tournament.participant.Participant;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {

    @Test
    void totalScore() {
        Tournament tournament = new MaleTournament();

        String[] ashtonEatonResults = {"10.23", "7.88", "14.52", "2.01", "45.00", "13.69", "43.34", "5.20", "63.63", "4.17.52"};

        // Ex-champion Ashton Eaton results
        Participant participant = new Participant(tournament, "Ashton Eaton", ashtonEatonResults);
        assertThat(participant.getTotalPoints()).isEqualTo(9045);


        // World champion Kevin Mayer results
        String[] kevinMayerResults = {"10.55", "7.80", 	"16.00", "2.05", "48.42", "13.75", "50.54", "5.45", "71.90", "4.36.11"};

        participant = new Participant(tournament, "Kevin Mayer", kevinMayerResults);
        assertThat(participant.getTotalPoints()).isEqualTo(9126);
    }
}
