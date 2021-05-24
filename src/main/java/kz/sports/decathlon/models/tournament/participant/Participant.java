package kz.sports.decathlon.models.tournament.participant;

import kz.sports.decathlon.models.event.Event;
import kz.sports.decathlon.models.tournament.Tournament;

import java.util.List;

public class Participant extends Athlete {
    private String[] rawResults;
    private Tournament tournament;

    private Integer totalPoints = null;
    private String place;

    public Participant(Tournament tournament, String name, String[] rawResults) {
        super(name);
        setRawResults(rawResults);
        this.tournament = tournament;
    }

    public String[] getRawResults() {
        return rawResults;
    }

    public List<Event> getEvents() {
        return tournament.getEvents();
    }

    private void setRawResults(String[] results) {
        this.rawResults = results;
    }

    private int countPoints() {
        int totalPoints = 0;
        for (int i = 0; i < rawResults.length; i++) {
            int points = tournament.getEvents().get(i).getScorer().countPoints(rawResults[i]);
            totalPoints += points;
        }
        return totalPoints;
    }

    public int getTotalPoints() {
        if (totalPoints == null)
            totalPoints = countPoints();

        return totalPoints;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
