package com.github.classyex.practice.refactoring.chapter1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        assertPlay(jsonNode.size(), jsonNode.get("as-like").get("name").asText(), jsonNode.get("as-like").get("type").asText());
    }

    private void assertPlay(int size, String name, String type) {
        assertThat(size, is(equalTo(3)));
        assertThat(name, is(equalTo("As You Like It")));
        assertThat(type, is(equalTo("comedy")));
    }

    @Test
    public void given_plays_string_when_format_bean_then_correct() throws JsonProcessingException {
        Map<String, Play> playMap = new ObjectMapper().readValue(PLAYS_STR, new TypeReference<Map<String, Play>>() {
        });
        assertPlay(playMap.size(), playMap.get("as-like").getName(), playMap.get("as-like").getType());
    }


}
