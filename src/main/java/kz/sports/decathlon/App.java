package kz.sports.decathlon;

import kz.sports.decathlon.io.Reader;
import kz.sports.decathlon.io.Writer;
import kz.sports.decathlon.io.impl.CSVReader;
import kz.sports.decathlon.io.impl.XMLWriter;
import kz.sports.decathlon.models.tournament.*;
import kz.sports.decathlon.models.tournament.participant.Participant;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class App {

    private Reader reader;
    private Writer writer;

    private Tournament tournament;

    App(TournamentTypeEnum tournamentType, boolean isManualTiming) {
        reader = new CSVReader();

        if (tournamentType.equals(TournamentTypeEnum.MALE))
            if (isManualTiming)
                tournament = new MaleTournamentManual();
            else
                tournament = new MaleTournament();
        else
            tournament = new FemaleTournament();

        writer = new XMLWriter();
    }

    void setReader(Reader reader) {
        this.reader = reader;
    }

    void setWriter(Writer writer) {
        this.writer = writer;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Tournament getTournament() {
        return tournament;
    }

    void run(String inputFilePath) throws IOException, ParserConfigurationException {
        RawTournamentResults rawResults = reader.read(inputFilePath);
        rawResults.populateTournament(tournament);
        ArrayList<Participant> winners = tournament.getParticipantsOrderedByPlace();
        writer.write(winners);
    }

    public static void main(String[] args) throws IllegalArgumentException, IOException, ParserConfigurationException {
        if (args.length == 0) {
            throw new IllegalArgumentException("You should pass path of a file containing raw results");
        }

        if (args.length > 1) {
            throw new IllegalArgumentException("Too much arguments passed");
        }

        // TODO make it possible to preconfigure app using args
        App app = new App(TournamentTypeEnum.MALE, false);
        app.run(args[0]);
    }
}
