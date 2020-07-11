package com.github.classyex.practice.refactoring.chapter1;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
* PlayStatement.java <br>
* @version 1.0 <br>
* @date 2020/7/8 9:17 <br>
* @author yex <br>
*/
public class PlayStatement {

    private Map<String, Play> plays;

    public String statement(final Map<String, Play> plays, final Invoice invoice) {
        int totalAmount = 0;
        int volumeCredits = 0;
        this.plays = plays;
        String result = String.format("Statement for %s\n", invoice.getCustomer());
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        format.setMinimumFractionDigits(2);

        for (Performance perf : invoice.getPerformances()) {
            int thisAmount = amountFor(perf, playFor(perf));

            // add volume credits
            final int creditsBase = 30;
            final int defaultCredits = 0;
            volumeCredits += Math.max(perf.getAudience() - creditsBase, defaultCredits);
            // add extra credit for every ten comedy attendees
            if ("comedy".equals(playFor(perf).getType())) {
                final float comedyExtraCreditPer = 5.0F;
                volumeCredits += Math.floor(perf.getAudience() / comedyExtraCreditPer);
            }

            // print line for this order
            final int formatNum = 100;
            result += String.format("  %s: %s (%s seats)\n", playFor(perf).getName(),
                    format.format(thisAmount / formatNum), perf.getAudience());
            totalAmount += thisAmount;
        }
        final int formatNum = 100;
        result += String.format("Amount owed is %s\n", format.format(totalAmount / formatNum));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

    private Play playFor(Performance perf) {
        return this.plays.get(perf.getPlayID());
    }

    private int amountFor(final Performance aPerformance, final Play play) {
        int result = 0;

        switch (play.getType()) {
            case "tragedy":
                final int tragedyBaseAmount = 40000;
                result = tragedyBaseAmount;
                final int tragedyAudienceBase = 30;
                if (aPerformance.getAudience() > tragedyAudienceBase) {
                    final int tragedyOverPerAmount = 1000;
                    result += tragedyOverPerAmount * (aPerformance.getAudience() - tragedyAudienceBase);
                }
                break;
            case "comedy":
                final int comedyBaseAmount = 30000;
                result = comedyBaseAmount;
                final int comedyAudienceBase = 20;
                if (aPerformance.getAudience() > comedyAudienceBase) {
                    final int comedyOverPerAmount = 500;
                    final int comedyOverBaseAmount = 10000;
                    result += comedyOverBaseAmount + comedyOverPerAmount * (
                            aPerformance.getAudience() - comedyAudienceBase);
                }
                final int comedyFactory = 300;
                result += comedyFactory * aPerformance.getAudience();
                break;
            default:
                throw new IllegalArgumentException(String.format("unknown type: %s", play.getType()));
        }
        return result;
    }

}
