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
        int result = 0;

        switch (play.getType()) {
            case "tragedy":
                final int tragedyBaseAmount = 40000;
                result = tragedyBaseAmount;
                final int tragedyAudienceBase = 30;
                if (performance.getAudience() > tragedyAudienceBase) {
                    final int tragedyOverPerAmount = 1000;
                    result += tragedyOverPerAmount * (performance.getAudience() - tragedyAudienceBase);
                }
                break;
            case "comedy":
                final int comedyBaseAmount = 30000;
                result = comedyBaseAmount;
                final int comedyAudienceBase = 20;
                if (performance.getAudience() > comedyAudienceBase) {
                    final int comedyOverPerAmount = 500;
                    final int comedyOverBaseAmount = 10000;
                    result += comedyOverBaseAmount + comedyOverPerAmount * (
                            performance.getAudience() - comedyAudienceBase);
                }
                final int comedyFactory = 300;
                result += comedyFactory * performance.getAudience();
                break;
            default:
                throw new IllegalArgumentException(String.format("unknown type: %s", play.getType()));
        }
        return result;
    }

}
