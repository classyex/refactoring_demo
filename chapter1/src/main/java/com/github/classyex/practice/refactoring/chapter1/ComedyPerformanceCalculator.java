package com.github.classyex.practice.refactoring.chapter1;

/**
* ComedyPerformanceCalculator.java <br>
* @version 1.0 <br>
* @date 2020/7/15 15:37 <br>
* @author yex <br>
*/

public class ComedyPerformanceCalculator extends PerformanceCalculator {
    public static final int BASE_AMOUNT = 30000;
    private RichPerformance performance;
    private Play play;

    public ComedyPerformanceCalculator(final RichPerformance aPerformance, final Play aPlay) {
        super(aPerformance, aPlay);
        performance = aPerformance;
        play = aPlay;
    }

    @Override
    int amountFor() {
        int result = BASE_AMOUNT;
        final int comedyAudienceBase = 20;
        if (performance.getAudience() > comedyAudienceBase) {
            final int comedyOverPerAmount = 500;
            final int comedyOverBaseAmount = 10000;
            result += comedyOverBaseAmount + comedyOverPerAmount * (
                    performance.getAudience() - comedyAudienceBase);
        }
        final int comedyFactory = 300;
        result += comedyFactory * performance.getAudience();
        return result;
    }

    @Override
    int volumeCreditsFor() {
        final float comedyExtraCreditPer = 5.0F;
        return (int) (super.volumeCreditsFor() + Math.floor(performance.getAudience() / comedyExtraCreditPer));
    }
}
