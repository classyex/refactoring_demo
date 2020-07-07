package com.github.classyex.practice.refactoring.chapter1;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

public class FirstTest {

    @Test
    public void test() {
        First first = new First("name");
        MatcherAssert.assertThat("name", Matchers.is(Matchers.equalTo(first.getName())));
    }


}
