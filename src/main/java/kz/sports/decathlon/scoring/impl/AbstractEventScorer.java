package kz.sports.decathlon.scoring.impl;

import kz.sports.decathlon.models.event.EventParams;
import kz.sports.decathlon.scoring.EventScorer;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class AbstractEventScorer implements EventScorer {

    EventParams params;

    AbstractEventScorer(EventParams eventParams) {
        this.params = eventParams;
    }

    @Override
    public int countPoints(double rawResult) {
        return countPoints(BigDecimal.valueOf(rawResult));
    }

    @Override
    public int countPoints(String rawResult) {
        return countPoints(new BigDecimal(transformResult(rawResult)));
    }

    private int countPoints(BigDecimal result) {
        BigDecimal addition = countAddition(result);
        if (addition.doubleValue() <= 0)
            // If P is lower than B or if T is greater than B, we should score 0 points, not negative amount of points
            return 0;
        BigDecimal power = BigDecimal.valueOf(Math.pow(addition.doubleValue(), params.getC().doubleValue()));
        BigDecimal aMultiply = params.getA().multiply(power);
        return aMultiply.setScale(2, RoundingMode.HALF_UP).intValue();
    }

    public String transformResult(String rawResult) {
        // Just do nothing
        return rawResult;
    }
}
