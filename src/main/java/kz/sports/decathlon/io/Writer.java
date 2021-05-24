package kz.sports.decathlon.io;

import kz.sports.decathlon.models.tournament.participant.Participant;

import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;

public interface Writer {
    void write(ArrayList<Participant> results) throws ParserConfigurationException;
}
