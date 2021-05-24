package kz.sports.decathlon.models.tournament;

import kz.sports.decathlon.models.event.Event;
import kz.sports.decathlon.models.event.EventParams;
import kz.sports.decathlon.models.event.EventTypeEnum;

import java.util.ArrayList;

public class MaleTournament extends AbstractTournament {

    ArrayList<Event> getEventsDefinition() {
        // Male decathlon description

        ArrayList<Event> events = new ArrayList<>();
        events.add(new Event("100 m", new EventParams("25.4347", "18", "1.81"), EventTypeEnum.TRACK));
        events.add(new Event("Long jump", new EventParams("0.14354", "220", "1.4"), EventTypeEnum.FIELD_CENTIMETERS));
        events.add(new Event("Shot put", new EventParams("51.39", "1.5", "1.05"), EventTypeEnum.FIELD));
        events.add(new Event("High jump", new EventParams("0.8465", "75", "1.42"), EventTypeEnum.FIELD_CENTIMETERS));
        events.add(new Event("400 m", new EventParams("1.53775", "82", "1.81"), EventTypeEnum.TRACK));
        events.add(new Event("110 m hurdles", new EventParams("5.74352", "28.5", "1.92"), EventTypeEnum.TRACK));
        events.add(new Event("Discus throw", new EventParams("12.91", "4", "1.1"), EventTypeEnum.FIELD));
        events.add(new Event("Pole vault", new EventParams("0.2797", "100", "1.35"), EventTypeEnum.FIELD_CENTIMETERS));
        events.add(new Event("Javelin throw", new EventParams("10.14", "7", "1.08"), EventTypeEnum.FIELD));
        events.add(new Event("1500 m", new EventParams("0.03768", "480", "1.85"), EventTypeEnum.TRACK_MINUTE));

        return events;
    }
}
