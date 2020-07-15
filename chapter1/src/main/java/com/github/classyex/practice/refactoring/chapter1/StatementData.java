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
        PerformanceCalculator calculator = new PerformanceCalculator(result, playFor(result));
        result.setPlay(calculator.getPlay());
        result.setAmount(calculator.amountFor());
        result.setVolumeCredits(volumeCreditsFor(result));
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
        return getPerformances().stream().map(RichPerformance::getAmount).reduce(0, Integer::sum);
    }

    int totalVolumeCredits() {
        return getPerformances().stream().map(this::volumeCreditsFor).reduce(0, Integer::sum);
    }


}
