package com.github.classyex.practice.refactoring.chapter1;

import org.springframework.beans.BeanUtils;

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
    private Map<String, Play> plays;
    private List<RichPerformance> performances;
    private Integer totalAmount;
    private Integer totalVolumeCredits;

    public StatementData(final Invoice invoice, final Map<String, Play> plays) {
        this.customer = invoice.getCustomer();
        this.plays = plays;
        this.performances = invoice.getPerformances().stream()
                .map(this::enrichPerformance).collect(Collectors.toList());
        this.totalAmount = totalAmount();
        this.totalVolumeCredits = totalVolumeCredits();
    }

    private RichPerformance enrichPerformance(final Performance aPerformance) {
        RichPerformance result = new RichPerformance();
        BeanUtils.copyProperties(aPerformance, result);
        result.setPlay(playFor(aPerformance));
        result.setAmount(amountFor(aPerformance));
        result.setVolumeCredits(volumeCreditsFor(aPerformance));
        return result;
    }

    public String getCustomer() {
        return this.customer;
    }

    public List<RichPerformance> getPerformances() {
        return performances;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public Integer getTotalVolumeCredits() {
        return totalVolumeCredits;
    }

    Play playFor(final Performance aPerformance) {
        return plays.get(aPerformance.getPlayID());
    }

    int amountFor(final Performance aPerformance) {
        int result = 0;

        switch (playFor(aPerformance).getType()) {
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
                throw new IllegalArgumentException(String.format("unknown type: %s", playFor(aPerformance).getType()));
        }
        return result;
    }

    private int volumeCreditsFor(final Performance aPerformance) {
        final int creditsBase = 30;
        final int defaultCredits = 0;
        int result = Math.max(aPerformance.getAudience() - creditsBase, defaultCredits);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(playFor(aPerformance).getType())) {
            final float comedyExtraCreditPer = 5.0F;
            result += Math.floor(aPerformance.getAudience() / comedyExtraCreditPer);
        }
        return result;
    }

    int totalAmount() {
        return getPerformances().stream().map(this::amountFor).reduce(0, Integer::sum);
    }

    int totalVolumeCredits() {
        return getPerformances().stream().map(this::volumeCreditsFor).reduce(0, Integer::sum);
    }


}
