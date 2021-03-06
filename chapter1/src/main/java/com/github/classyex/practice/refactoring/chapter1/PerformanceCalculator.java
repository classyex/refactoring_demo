package com.github.classyex.practice.refactoring.chapter1;

/**
* PerformanceCalculator.java <br>
* @version 1.0 <br>
* @date 2020/7/15 14:44 <br>
* @author yex <br>
*/

public class PerformanceCalculator {

    private RichPerformance performance;
    private Play play;

    public PerformanceCalculator(final RichPerformance aPerformance, final Play aPlay) {
        this.performance = aPerformance;
        this.play = aPlay;
    }

    public Play getPlay() {
        return play;
    }


    int amountFor() {
        throw new IllegalArgumentException("subclass responsibility");
    }

    int volumeCreditsFor() {
        final int creditsBase = 30;
        final int defaultCredits = 0;
        return Math.max(performance.getAudience() - creditsBase, defaultCredits);
    }

}
