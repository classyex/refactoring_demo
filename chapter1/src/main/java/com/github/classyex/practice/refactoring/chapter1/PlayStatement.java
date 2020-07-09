package com.github.classyex.practice.refactoring.chapter1;

import com.fasterxml.jackson.databind.JsonNode;

import java.text.NumberFormat;
import java.util.Locale;

/**
* PlayStatement.java <br>
* @version 1.0 <br>
* @date 2020/7/8 9:17 <br>
* @author yex <br>
*/
public class PlayStatement {
    public String statement(final JsonNode plays, final JsonNode invoice) {
        int totalAmount = 0;
        int volumeCredits = 0;
        String result = String.format("Statement for %s\n", invoice.get("customer").asText());
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        format.setMinimumFractionDigits(2);

        for (JsonNode perf : invoice.get("performances")) {
            JsonNode play = plays.get(perf.get("playID").asText());
            int thisAmount = 0;

            switch (play.get("type").asText()) {
                case "tragedy":
                    final int tragedyBaseAmount = 40000;
                    thisAmount = tragedyBaseAmount;
                    final int tragedyAudienceBase = 30;
                    if (perf.get("audience").intValue() > tragedyAudienceBase) {
                        final int tragedyOverPerAmount = 1000;
                        thisAmount += tragedyOverPerAmount * (perf.get("audience").intValue() - tragedyAudienceBase);
                    }
                    break;
                case "comedy":
                    final int comedyBaseAmount = 30000;
                    thisAmount = comedyBaseAmount;
                    final int comedyAudienceBase = 20;
                    if (perf.get("audience").intValue() > comedyAudienceBase) {
                        final int comedyOverPerAmount = 500;
                        final int comedyOverBaseAmount = 10000;
                        thisAmount += comedyOverBaseAmount + comedyOverPerAmount * (
                                perf.get("audience").intValue() - comedyAudienceBase);
                    }
                    final int comedyFactory = 300;
                    thisAmount += comedyFactory * perf.get("audience").intValue();
                    break;
                default:
                    throw new IllegalArgumentException(String.format("unknown type: %s", play.get("type").asText()));
            }

            // add volume credits
            final int creditsBase = 30;
            final int defaultCredits = 0;
            volumeCredits += Math.max(perf.get("audience").intValue() - creditsBase, defaultCredits);
            // add extra credit for every ten comedy attendees
            if ("comedy".equals(play.get("type").asText())) {
                final float comedyExtraCreditPer = 5.0F;
                volumeCredits += Math.floor(perf.get("audience").intValue() / comedyExtraCreditPer);
            }

            // print line for this order
            final int formatNum = 100;
            result += String.format("  %s: %s (%s seats)\n", play.get("name").asText(),
                    format.format(thisAmount / formatNum), perf.get("audience").intValue());
            totalAmount += thisAmount;
        }
        final int formatNum = 100;
        result += String.format("Amount owed is %s\n", format.format(totalAmount / formatNum));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }
}
