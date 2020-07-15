package com.github.classyex.practice.refactoring.chapter1;

/**
* PerformanceCalculator.java <br>
* @version 1.0 <br>
* @date 2020/7/15 14:44 <br>
* @author yex <br>
*/

public class PerformanceCalculator {

    private Performance performance;
    private Play play;

    public PerformanceCalculator(final Performance aPerformance, final Play play) {
        this.performance = aPerformance;
        this.play = play;
    }

    public Play getPlay() {
        return play;
    }
}
