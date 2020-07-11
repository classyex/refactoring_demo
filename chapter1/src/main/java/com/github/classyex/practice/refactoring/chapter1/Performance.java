package com.github.classyex.practice.refactoring.chapter1;

import lombok.Data;

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
    private Play play;

}
