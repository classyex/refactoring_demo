package com.github.classyex.practice.refactoring.chapter1;

import lombok.Data;

import java.util.List;

/**
* Invoice.java <br>
* @version 1.0 <br>
* @date 2020/7/9 15:14 <br>
* @author yex <br>
*/
@Data
public class Invoice {

    private String customer;
    private List<Performance> performances;

}
