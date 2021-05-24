package kz.sports.decathlon.scoring.impl;

import kz.sports.decathlon.models.event.EventParams;

public class CentimeterFieldScorer extends FieldEventScorer {
    public CentimeterFieldScorer(EventParams eventParams) {
        super(eventParams);
    }

    @Override
    public String transformResult(String rawResult) {
        return String.valueOf(Double.parseDouble(rawResult) * 100);
    }
}
