package com.github.classyex.practice.refactoring.chapter1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class InvoicesTest {

    @Test
    public void given_invoices_string_when_format_then_get_value_correct() throws JsonProcessingException {
        String invoicesStr = "[{\"customer\":\"BigCo\",\"performances\":[{\"playID\":\"hamlet\",\"audience\":55},{\"playID\":\"as-like\",\"audience\":35},{\"playID\":\"othello\",\"audience\":40}]}]";
        JsonNode jsonNode = new ObjectMapper().readTree(invoicesStr);
        assertThat(jsonNode.size(), is(equalTo(1)));
        assertThat(jsonNode.get(0).get("customer").asText(), is(equalTo("BigCo")));
        assertThat(jsonNode.get(0).get("performances").get(0).get("playID").asText(), is(equalTo("hamlet")));
        assertThat(jsonNode.get(0).get("performances").get(0).get("audience").asInt(), is(equalTo(55)));
    }


}