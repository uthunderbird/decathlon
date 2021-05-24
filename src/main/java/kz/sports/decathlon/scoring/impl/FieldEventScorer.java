package kz.sports.decathlon.scoring.impl;

import kz.sports.decathlon.models.event.EventParams;

import java.math.BigDecimal;

public class FieldEventScorer extends AbstractEventScorer {

    public FieldEventScorer(EventParams eventParams) {
        super(eventParams);
    }

    @Override
    public BigDecimal countAddition(BigDecimal result) {
        return result.subtract(params.getB());
    }
}
