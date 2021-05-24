package kz.sports.decathlon;

import kz.sports.decathlon.io.Reader;
import kz.sports.decathlon.io.Writer;
import kz.sports.decathlon.models.tournament.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class AppTest {

    @Test
    void args() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> App.main(new String[]{})).withMessageContaining("You should pass");

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> App.main(new String[]{"test", "test1"})).withMessageContaining("Too much arguments");
    }

    @Test
    void constructor() {
        App app = new App(TournamentTypeEnum.MALE, true);
        assertThat(app.getTournament().getClass()).isEqualTo(MaleTournamentManual.class);
        app = new App(TournamentTypeEnum.MALE, false);
        assertThat(app.getTournament().getClass()).isEqualTo(MaleTournament.class);
        app = new App(TournamentTypeEnum.FEMALE, false);
        assertThat(app.getTournament().getClass()).isEqualTo(FemaleTournament.class);
    }

    @Test
    void verifyCalls() {
        Reader reader = Mockito.mock(Reader.class);
        Writer writer = Mockito.mock(Writer.class);
        Tournament tournament = Mockito.mock(Tournament.class);

        App app = new App(TournamentTypeEnum.MALE, false);

        app.setReader(reader);
        app.setWriter(writer);
        app.setTournament(tournament);

        RawTournamentResults results = new RawTournamentResults();

        String[] row1 = {"Irina Naumenko", "12.58", "5.98", "12.51", "1.77", "55.91", "14.42", "34.63", "3.30", "37.57", "4.59.03"};
        String[] row2 = {"Anna Snetkova", "12.66", "5.98", "13.48", "1.69", "58.88", "14.19", "36.9", "3.70", "37.50", "5.17.67"};
        String[] row3 = {"Julie Mezerette- Martin", "12.79", "5.67", "11.80", "1.68", "57.78", "14.92", "40.39", "2.80", "42.24", "5.00.62"};
        results.add(row3);
        results.add(row1);
        results.add(row2);

        try {
            Mockito.when(reader.read("asdf")).thenReturn(results);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            app.run("asdf");
            Mockito.verify(reader, Mockito.times(1)).read("asdf");
            Mockito.verify(tournament, Mockito.times(3)).addParticipant(Mockito.any(), Mockito.any());
            Mockito.verify(tournament, Mockito.times(1)).getParticipantsOrderedByPlace();
            Mockito.verify(writer, Mockito.times(1)).write(Mockito.any());
        } catch (IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

}
