package com.github.classyex.practice.refactoring.chapter1;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class PerformanceTest {

    @Test
    public void given_plays_when_play_for_id_then_get_play() {
        Map<String, Play> plays = new HashMap<>();
        Play expectation = new Play();
        plays.put("Hamlet", expectation);
        Performance hamlet = new Performance("Hamlet", 22, plays);
        Play play = hamlet.playFor();
        MatcherAssert.assertThat(play, Matchers.is(Matchers.equalTo(expectation)));
    }


}
