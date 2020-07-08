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

public class PlayTest {

    @Test
    public void given_plays_string_when_json_format_then_get_value_correct() throws JsonProcessingException {
        String playsStr = "{\"hamlet\":{\"name\":\"Hamlet\",\"type\":\"tragedy\"},\"as-like\":{\"name\":\"As You Like It\",\"type\":\"comedy\"},\"othello\":{\"name\":\"Othello\",\"type\":\"tragedy\"}}";
        JsonNode jsonNode = new ObjectMapper().readTree(playsStr);
        assertThat(jsonNode.size(), is(equalTo(3)));
        assertThat(jsonNode.get("as-like").get("name").asText(), is(equalTo("As You Like It")));
        assertThat(jsonNode.get("as-like").get("type").asText(), is(equalTo("comedy")));
    }


}
