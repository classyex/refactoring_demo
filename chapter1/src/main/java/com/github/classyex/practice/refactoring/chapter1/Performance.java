package com.github.classyex.practice.refactoring.chapter1;

/**
* Performance.java <br>
* @version 1.0 <br>
* @date 2020/7/9 16:53 <br>
* @author yex <br>
*/

public class Performance {

    private String playID;
    private Integer audience;

    public String getPlayID() {
        return playID;
    }

    public void setPlayID(final String playID) {
        this.playID = playID;
    }

    public Integer getAudience() {
        return audience;
    }

    public void setAudience(final Integer audience) {
        this.audience = audience;
    }
}
