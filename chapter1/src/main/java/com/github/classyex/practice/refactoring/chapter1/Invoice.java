package com.github.classyex.practice.refactoring.chapter1;

import java.util.List;

/**
* Invoice.java <br>
* @version 1.0 <br>
* @date 2020/7/9 15:14 <br>
* @author yex <br>
*/

public class Invoice {

    private String customer;
    private List<Performance> performances;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(final String customer) {
        this.customer = customer;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(final List<Performance> performances) {
        this.performances = performances;
    }
}
