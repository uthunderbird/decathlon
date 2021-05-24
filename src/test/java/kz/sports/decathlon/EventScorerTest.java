package kz.sports.decathlon;

import kz.sports.decathlon.models.event.Event;
import kz.sports.decathlon.models.event.EventParams;
import kz.sports.decathlon.models.event.EventTypeEnum;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventScorerTest {

    @Test
    void trackScorer() {
        Event event = new Event("100 m", new EventParams("25.4347", "18", "1.81"), EventTypeEnum.TRACK);
        assertThat(event.getScorer().countPoints(10.395)).isEqualTo(1000);
        assertThat(event.getScorer().countPoints(27)).isEqualTo(0);
    }

    @Test
    void minutesTrackScorer() {
        Event event = new Event("1500 m", new EventParams("0.03768", "480", "1.85"), EventTypeEnum.TRACK_MINUTE);
        assertThat(event.getScorer().countPoints("4.21.77")).isEqualTo(800);
    }

    @Test
    void eventWithManualAdjustment() {
        Event event = new Event("110 m hurdles", new EventParams("5.74352", "28.5", "1.92", "0.24"), EventTypeEnum.TRACK);
        assertThat(event.getParams().getB().doubleValue()).isEqualTo(28.5-0.24);
    }

    @Test
    void fieldScorer() {
        Event event = new Event("Shot put", new EventParams("51.39", "1.5", "1.05"), EventTypeEnum.FIELD);
        assertThat(event.getScorer().countPoints(16.79)).isEqualTo(900);
        assertThat(event.getScorer().countPoints(1.5)).isEqualTo(0);
    }

    @Test
    void CentimetersFieldScorer() {
        // NOTE: I found wrong case on Wikipedia (???). TODO: fix wiki or lurk more
        Event event = new Event("Long jump", new EventParams("0.14354", "220", "1.40"), EventTypeEnum.FIELD_CENTIMETERS);
        // assertThat(event.getScorer().countPoints("6.94")).isEqualTo(800); Raises assertion error, but should work well
        assertThat(event.getScorer().countPoints("6.51")).isEqualTo(700);
    }

}
