package com.github.classyex.practice.refactoring.chapter1;

import lombok.Data;

import java.util.Map;

/**
* Performance.java <br>
* @version 1.0 <br>
* @date 2020/7/9 16:53 <br>
* @author yex <br>
*/

@Data
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

    Play playFor() {
        return this.plays.get(getPlayID());
    }

    public void setPlays(final Map<String, Play> plays) {
        this.plays = plays;
    }

    int amountFor() {
        int result = 0;

        switch (playFor().getType()) {
            case "tragedy":
                final int tragedyBaseAmount = 40000;
                result = tragedyBaseAmount;
                final int tragedyAudienceBase = 30;
                if (getAudience() > tragedyAudienceBase) {
                    final int tragedyOverPerAmount = 1000;
                    result += tragedyOverPerAmount * (getAudience() - tragedyAudienceBase);
                }
                break;
            case "comedy":
                final int comedyBaseAmount = 30000;
                result = comedyBaseAmount;
                final int comedyAudienceBase = 20;
                if (getAudience() > comedyAudienceBase) {
                    final int comedyOverPerAmount = 500;
                    final int comedyOverBaseAmount = 10000;
                    result += comedyOverBaseAmount + comedyOverPerAmount * (
                            getAudience() - comedyAudienceBase);
                }
                final int comedyFactory = 300;
                result += comedyFactory * getAudience();
                break;
            default:
                throw new IllegalArgumentException(String.format("unknown type: %s", playFor().getType()));
        }
        return result;
    }
}
