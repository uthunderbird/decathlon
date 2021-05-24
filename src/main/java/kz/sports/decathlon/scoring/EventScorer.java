package kz.sports.decathlon.scoring;

import java.math.BigDecimal;

public interface EventScorer {

    int countPoints(double result);

    int countPoints(String result);

    BigDecimal countAddition(BigDecimal result);
}
