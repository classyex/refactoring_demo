package com.github.classyex.practice.refactoring.chapter1;

/**
* RichPerformance.java <br>
* @version 1.0 <br>
* @date 2020/7/15 14:08 <br>
* @author yex <br>
*/

public class RichPerformance extends Performance {

    private Play play;

    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }
}
