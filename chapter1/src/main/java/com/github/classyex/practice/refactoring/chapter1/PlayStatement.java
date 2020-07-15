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

    private final Map<String, Play> plays;
    private final Invoice invoice;

    public PlayStatement(final Map<String, Play> plays, final Invoice invoice) {
        this.plays = plays;
        this.invoice = invoice;
    }


    public String statement() {
        return renderPlainText(new StatementData(invoice, plays));
    }

    private String renderPlainText(final StatementData statementData) {
        String result = String.format("Statement for %s\n", statementData.getCustomer());

        for (RichPerformance perf : statementData.getPerformances()) {
            result += String.format("  %s: %s (%s seats)\n", perf.getPlay().getName(),
                    usd(statementData.amountFor(perf)), perf.getAudience());
        }

        result += String.format("Amount owed is %s\n", usd(statementData.getTotalAmount()));
        result += String.format("You earned %s credits\n", statementData.getTotalVolumeCredits());
        return result;
    }

    public String htmlStatement() {
        return renderHtml(new StatementData(invoice, plays));
    }

    private String renderHtml(final StatementData statementData) {
        String result = String.format("<h1>Statement for %s</h1>\n", statementData.getCustomer());
        result += "<table>\n";
        result += "<tr><th>play</th><th>seats</th><th>cost</th></tr>\n";
        for (Performance perf : statementData.getPerformances()) {
            result += String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>\n",
                    statementData.playFor(perf).getName(), perf.getAudience(), usd(statementData.amountFor(perf)));
        }
        result += "</table>\n";
        result += String.format("<p>Amount owed is <em>%s</em></p>\n", usd(statementData.getTotalAmount()));
        result += String.format("<p>You earned <em>%s</em> credits</p>\n", statementData.getTotalVolumeCredits());
        return result;
    }

    private String usd(final int aNumber) {
        final int formatNum = 100;
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        format.setMinimumFractionDigits(2);
        return format.format(aNumber / formatNum);
    }


}
