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
        PerformanceCalculator calculator = createPerformanceCalculator(result, playFor(result));
        result.setPlay(calculator.getPlay());
        result.setAmount(calculator.amountFor());
        result.setVolumeCredits(calculator.volumeCreditsFor());
        return result;
    }

    private PerformanceCalculator createPerformanceCalculator(final RichPerformance aPerformance, final Play aPlay) {
        switch (aPlay.getType()) {
            case "tragedy":
                return new TragedyPerformanceCalculator(aPerformance, aPlay);
            case "comedy":
                return new ComedyPerformanceCalculator(aPerformance, aPlay);
            default:
                throw new IllegalArgumentException(String.format("unknown type: %s", aPlay.getType()));
        }
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

    int totalAmount() {
        return getPerformances().stream().map(RichPerformance::getAmount).reduce(0, Integer::sum);
    }

    int totalVolumeCredits() {
        return getPerformances().stream().map(RichPerformance::getVolumeCredits).reduce(0, Integer::sum);
    }


}
