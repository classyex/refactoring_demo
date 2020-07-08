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
                    thisAmount = 40000;
                    if (perf.get("audience").intValue() > 30) {
                        thisAmount += 1000 * (perf.get("audience").intValue() - 30);
                    }
                    break;
                case "comedy":
                    thisAmount = 30000;
                    if (perf.get("audience").intValue() > 20) {
                        thisAmount += 10000 + 500 * (perf.get("audience").intValue() - 20);
                    }
                    thisAmount += 300 * perf.get("audience").intValue();
                    break;
                default:
                    throw new IllegalArgumentException(String.format("unknown type: %s", play.get("type").asText()));
            }

            // add volume credits
            volumeCredits += Math.max(perf.get("audience").intValue() - 30, 0);
            // add extra credit for every ten comedy attendees
            if ("comedy".equals(play.get("type").asText())) {
                volumeCredits += Math.floor(perf.get("audience").intValue() / 5);
            }

            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", play.get("name").asText(),
                    format.format(thisAmount / 100), perf.get("audience").intValue());
            totalAmount += thisAmount;
        }
        result += String.format("Amount owed is %s\n", format.format(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }
}
