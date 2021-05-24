package kz.sports.decathlon.models.tournament.participant;

public class Athlete {
    private String name;

    Athlete(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
