package com.github.classyex.practice.refactoring.chapter1;

import java.util.Map;

/**
* Performance.java <br>
* @version 1.0 <br>
* @date 2020/7/9 16:53 <br>
* @author yex <br>
*/

public class Performance {

    private String playID;
    private Integer audience;

    public Performance() {
    }

    public Performance(final String playID, final Integer audience) {
        this.playID = playID;
        this.audience = audience;
    }

    public String getPlayID() {
        return playID;
    }

    public Integer getAudience() {
        return audience;
    }

}
