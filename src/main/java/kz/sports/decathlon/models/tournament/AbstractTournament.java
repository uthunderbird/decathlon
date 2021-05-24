package kz.sports.decathlon.models.tournament;

import kz.sports.decathlon.models.event.Event;
import kz.sports.decathlon.models.tournament.participant.Participant;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class AbstractTournament implements Tournament {

    private ArrayList<Event> events;
    private ArrayList<Participant> participants;

    AbstractTournament() {
        setEvents(getEventsDefinition());
        setParticipants(new ArrayList<>());
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    private void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;
    }

    @Override
    public void addParticipant(String name, String[] rawResults) {
        participants.add(new Participant(this, name, rawResults));
    }

    @Override
    public ArrayList<Participant> getParticipantsOrderedByPlace() {

        ArrayList<Participant> orderedParticipants = new ArrayList<>();

        TreeMap<Integer, ArrayList<Participant>> orderedParticipantsMap = new TreeMap<>();

        for (Participant participant : participants) {
            Integer totalPoints = participant.getTotalPoints();
            if (!orderedParticipantsMap.containsKey(totalPoints)) {
                orderedParticipantsMap.put(totalPoints, new ArrayList<>());
            }
            orderedParticipantsMap.get(totalPoints).add(participant);
        }

        int place = 1;

        for (Integer key : orderedParticipantsMap.descendingKeySet()) {
            StringBuilder placeReprBuilder = new StringBuilder();

            ArrayList<Participant> placement = orderedParticipantsMap.get(key);

            placeReprBuilder.append(place);

            for (int i = 1; i < placement.size(); i++) {
                String s = String.valueOf(place + i);
                placeReprBuilder.append("-").append(s);
            }

            String placeRepr = placeReprBuilder.toString();

            for (Participant participant : placement)
                participant.setPlace(placeRepr);

            place += placement.size();

            orderedParticipants.addAll(placement);
        }

        return orderedParticipants;
    }

    @Override
    public ArrayList<Event> getEvents() {
        return events;
    }

    private void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    abstract ArrayList<Event> getEventsDefinition();

    // TODO make a child class that takes events definition from CSV file


}
