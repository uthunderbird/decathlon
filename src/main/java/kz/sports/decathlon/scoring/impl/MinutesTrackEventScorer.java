package kz.sports.decathlon.scoring.impl;

import kz.sports.decathlon.models.event.EventParams;

public class MinutesTrackEventScorer extends TrackEventScorer {

    public MinutesTrackEventScorer(EventParams eventParams) {
        super(eventParams);
    }

    @Override
    public String transformResult(String rawResult) {
        if (rawResult.chars().filter(ch -> ch == '.').count() == 2) {
            String[] splitted = rawResult.split("\\.");
            String minutesStr = splitted[0];
            String secondsStr = splitted[1];
            String millisStr = splitted[2];
            int totalSeconds = Integer.parseInt(minutesStr) * 60 + Integer.parseInt(secondsStr);
            rawResult = totalSeconds + "." + millisStr;
        }
        return rawResult;
    }
}
