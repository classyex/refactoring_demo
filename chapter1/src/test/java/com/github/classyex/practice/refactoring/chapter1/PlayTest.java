package com.github.classyex.practice.refactoring.chapter1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PlayTest {

    public static final String PLAYS_STR = "{\"hamlet\":{\"name\":\"Hamlet\",\"type\":\"tragedy\"},\"as-like\":{\"name\":\"As You Like It\",\"type\":\"comedy\"},\"othello\":{\"name\":\"Othello\",\"type\":\"tragedy\"}}";

    @Test
    public void given_plays_string_when_json_format_then_get_value_correct() throws JsonProcessingException {
        JsonNode jsonNode = new ObjectMapper().readTree(PLAYS_STR);
        assertThat(jsonNode.size(), is(equalTo(3)));
        assertThat(jsonNode.get("as-like").get("name").asText(), is(equalTo("As You Like It")));
        assertThat(jsonNode.get("as-like").get("type").asText(), is(equalTo("comedy")));
    }

    @Test
    public void given_plays_string_when_format_bean_then_correct() throws JsonProcessingException {
        Map<String, Play> playMap = new ObjectMapper().readValue(PLAYS_STR, new TypeReference<Map<String, Play>>() {});
        assertThat(playMap.size(), is(equalTo(3)));
        assertThat(playMap.get("as-like").getName(), is(equalTo("As You Like It")));
        assertThat(playMap.get("as-like").getType(), is(equalTo("comedy")));
    }


}
