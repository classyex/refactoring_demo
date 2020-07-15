package com.github.classyex.practice.refactoring.chapter1;

/**
* TragedyPerformanceCalculator.java <br>
* @version 1.0 <br>
* @date 2020/7/15 15:36 <br>
* @author yex <br>
*/

public class TragedyPerformanceCalculator extends PerformanceCalculator {

    private RichPerformance performance;
    private Play play;

    public TragedyPerformanceCalculator(RichPerformance aPerformance, Play aPlay) {
        super(aPerformance, aPlay);
        performance = aPerformance;
        play = aPlay;
    }

    @Override
    int amountFor() {
        int result = 40000;
        final int tragedyAudienceBase = 30;
        if (performance.getAudience() > tragedyAudienceBase) {
            final int tragedyOverPerAmount = 1000;
            result += tragedyOverPerAmount * (performance.getAudience() - tragedyAudienceBase);
        }
        return result;
    }

}
