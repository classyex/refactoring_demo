package com.github.classyex.practice.refactoring.chapter1;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
* PlayStatement.java <br>
* @version 1.0 <br>
* @date 2020/7/8 9:17 <br>
* @author yex <br>
*/
public class PlayStatement {

    private final Map<String, Play> plays;
    private final Invoice invoice;
    private StatementData statementData;

    public PlayStatement(final Map<String, Play> plays, final Invoice invoice) {
        this.plays = plays;
        this.invoice = invoice;
    }


    public String statement() {
        statementData = new StatementData();
        statementData.setCustomer(this.invoice.getCustomer());
        statementData.setPerformances(this.invoice.getPerformances().stream().map(performance -> {
            Performance perf = new Performance();
            perf.setAudience(performance.getAudience());
            perf.setPlayID(performance.getPlayID());
            perf.setPlays(plays);
            return perf;
        }).collect(Collectors.toList()));
        statementData.setTotalVolumeCredits(statementData.totalVolumeCredits());
        statementData.setTotalAmount(statementData.totalAmount());
        return renderPlainText(statementData);
    }

    private String renderPlainText(StatementData statementData) {
        String result = String.format("Statement for %s\n", statementData.getCustomer());

        for (Performance perf : statementData.getPerformances()) {
            result += String.format("  %s: %s (%s seats)\n", perf.playFor().getName(),
                    usd(perf.amountFor()), perf.getAudience());
        }

        result += String.format("Amount owed is %s\n", usd(statementData.getTotalAmount()));
        result += String.format("You earned %s credits\n", statementData.getTotalVolumeCredits());
        return result;
    }

    private String usd(int aNumber) {
        final int formatNum = 100;
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        format.setMinimumFractionDigits(2);
        return format.format(aNumber / formatNum);
    }

}
