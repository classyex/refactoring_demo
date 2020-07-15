package com.github.classyex.practice.refactoring.chapter1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PerformanceCalculatorTest {

    @Test
    public void given_without_subclass_when_amount_then_exception() {
        PerformanceCalculator calculator = new PerformanceCalculator(new RichPerformance(), new Play());
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculator.amountFor());
        assertThat(exception.getMessage(), is(equalTo("subclass responsibility")));
    }


}
