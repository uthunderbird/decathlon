package kz.sports.decathlon.models.tournament;

import kz.sports.decathlon.models.event.Event;
import kz.sports.decathlon.models.tournament.participant.Participant;

import java.util.ArrayList;
import java.util.List;

public interface Tournament {

    void addParticipant(String name, String[] rawResults);

    ArrayList<Participant> getParticipantsOrderedByPlace();

    List<Event> getEvents();
}
