package kz.sports.decathlon.models.tournament;

import kz.sports.decathlon.models.event.Event;
import kz.sports.decathlon.models.event.EventParams;
import kz.sports.decathlon.models.event.EventTypeEnum;

import java.util.ArrayList;

public class FemaleTournament extends AbstractTournament {

    ArrayList<Event> getEventsDefinition() {
        // Female decathlon description

        ArrayList<Event> events = new ArrayList<>();

        events.add(new Event("100 m", new EventParams("17.857", "21", "1.81"), EventTypeEnum.TRACK));
        events.add(new Event("Long jump", new EventParams("0.188807", "210", "1.41"), EventTypeEnum.FIELD_CENTIMETERS));
        events.add(new Event("Shot put", new EventParams("56.0211", "1.5", "1.05"), EventTypeEnum.FIELD));
        events.add(new Event("High jump", new EventParams("1.84523", "75", "1.348"), EventTypeEnum.FIELD_CENTIMETERS));
        events.add(new Event("400 m", new EventParams("1.34285", "91.7", "1.81"), EventTypeEnum.TRACK));

        events.add(new Event("100 m hurdles", new EventParams("9.23076", "26.7", "1.835"), EventTypeEnum.TRACK));
        events.add(new Event("Discus throw", new EventParams("12.3311", "3", "1.1"), EventTypeEnum.FIELD));
        events.add(new Event("Pole vault", new EventParams("0.44125", "100", "1.35"), EventTypeEnum.FIELD_CENTIMETERS));
        events.add(new Event("Javelin throw", new EventParams("15.9803", "3.8", "1.04"), EventTypeEnum.FIELD));
        events.add(new Event("1500 m", new EventParams("0.02883", "535", "1.88"), EventTypeEnum.TRACK_MINUTE));

        return events;
    }
}
