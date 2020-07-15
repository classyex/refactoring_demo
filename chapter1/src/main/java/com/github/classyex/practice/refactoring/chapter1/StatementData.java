package com.github.classyex.practice.refactoring.chapter1;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* StateData.java <br>
* @version 1.0 <br>
* @date 2020/7/11 15:52 <br>
* @author yex <br>
*/

public class StatementData {

    private String customer;
    private List<Performance> performances;

    public StatementData(final Invoice invoice, final Map<String, Play> plays) {
        this.customer = invoice.getCustomer();
        this.performances = invoice.getPerformances().stream()
                .map(performance -> enrichPerformance(plays, performance))
                .collect(Collectors.toList());
    }

    private Performance enrichPerformance(final Map<String, Play> plays, final Performance aPerformance) {
        Performance result = new Performance();
        result.setAudience(aPerformance.getAudience());
        result.setPlayID(aPerformance.getPlayID());
        result.setPlays(plays);
        return result;
    }

    public String getCustomer() {
        return this.customer;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    int totalAmount() {
        return getPerformances().stream().map(Performance::amountFor).reduce(0, Integer::sum);
    }

    int totalVolumeCredits() {
        return getPerformances().stream().map(this::volumeCreditsFor).reduce(0, Integer::sum);
    }

    private int volumeCreditsFor(final Performance aPerformance) {
        final int creditsBase = 30;
        final int defaultCredits = 0;
        int result = Math.max(aPerformance.getAudience() - creditsBase, defaultCredits);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(aPerformance.playFor().getType())) {
            final float comedyExtraCreditPer = 5.0F;
            result += Math.floor(aPerformance.getAudience() / comedyExtraCreditPer);
        }
        return result;
    }
}
