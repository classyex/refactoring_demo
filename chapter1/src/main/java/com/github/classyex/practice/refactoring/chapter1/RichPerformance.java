package com.github.classyex.practice.refactoring.chapter1;

/**
* RichPerformance.java <br>
* @version 1.0 <br>
* @date 2020/7/15 14:08 <br>
* @author yex <br>
*/

public class RichPerformance extends Performance {

    private Play play;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    private Integer amount;


    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }
}
