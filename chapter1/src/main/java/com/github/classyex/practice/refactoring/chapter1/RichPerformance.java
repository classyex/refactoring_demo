package com.github.classyex.practice.refactoring.chapter1;

/**
* RichPerformance.java <br>
* @version 1.0 <br>
* @date 2020/7/15 14:08 <br>
* @author yex <br>
*/

public class RichPerformance extends Performance {

    private Play play;
    private Integer amount;
    private Integer volumeCredits;


    public Play getPlay() {
        return play;
    }

    public void setPlay(final Play play) {
        this.play = play;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(final Integer amount) {
        this.amount = amount;
    }

    public Integer getVolumeCredits() {
        return volumeCredits;
    }

    public void setVolumeCredits(Integer volumeCredits) {
        this.volumeCredits = volumeCredits;
    }
}
