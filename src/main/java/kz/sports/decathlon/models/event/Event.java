package kz.sports.decathlon.models.event;

import kz.sports.decathlon.scoring.impl.*;

public class Event {

    private String name;
    private EventParams params;
    private EventTypeEnum type;
    private AbstractEventScorer scorer = null;

    public Event(String name, EventParams params, EventTypeEnum type) {
        setName(name);
        setParams(params);
        setType(type);
    }

    public EventParams getParams() {
        return params;
    }

    private void setParams(EventParams params) {
        this.params = params;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public EventTypeEnum getType() {
        return type;
    }

    private void setType(EventTypeEnum type) {
        this.type = type;
    }

    public AbstractEventScorer getScorer() {
        if (scorer == null) {
            if (getType() == EventTypeEnum.FIELD) {
                scorer = new FieldEventScorer(getParams());
            } else if (getType() == EventTypeEnum.TRACK) {
                scorer = new TrackEventScorer(getParams());
            } else if (getType() == EventTypeEnum.TRACK_MINUTE){
                scorer = new MinutesTrackEventScorer(getParams());
            } else {
                scorer = new CentimeterFieldScorer(getParams());
            }
        }
        return scorer;
    }
}
