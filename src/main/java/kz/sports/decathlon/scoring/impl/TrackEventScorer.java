package kz.sports.decathlon.scoring.impl;

import kz.sports.decathlon.models.event.EventParams;

import java.math.BigDecimal;

public class TrackEventScorer extends AbstractEventScorer {

    public TrackEventScorer(EventParams eventParams) {
        super(eventParams);
    }

    @Override
    public BigDecimal countAddition(BigDecimal result) {
        return params.getB().subtract(result);
    }
}
