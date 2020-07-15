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
    private Map<String, Play> plays;

    public Performance() {
    }

    public Performance(final String playID, final Integer audience, final Map<String, Play> plays) {
        this.playID = playID;
        this.audience = audience;
        this.plays = plays;
    }

    public String getPlayID() {
        return playID;
    }

    public Integer getAudience() {
        return audience;
    }

    Play playFor() {
        return this.plays.get(getPlayID());
    }

}
