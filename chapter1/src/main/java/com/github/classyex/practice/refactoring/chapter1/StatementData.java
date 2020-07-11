package com.github.classyex.practice.refactoring.chapter1;

import java.util.List;

/**
* StateData.java <br>
* @version 1.0 <br>
* @date 2020/7/11 15:52 <br>
* @author yex <br>
*/

public class StatementData {

    private String customer;
    private List<Performance> performances;
    private int totalVolumeCredits;
    private int totalAmount;

    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setTotalVolumeCredits(int totalVolumeCredits) {
        this.totalVolumeCredits = totalVolumeCredits;
    }

    public int getTotalVolumeCredits() {
        return totalVolumeCredits;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    int totalAmount() {
        int result = 0;
        for (Performance perf : getPerformances()) {
            result += perf.amountFor();
        }
        return result;
    }
}
