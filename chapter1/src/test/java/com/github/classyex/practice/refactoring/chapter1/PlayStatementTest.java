package com.github.classyex.practice.refactoring.chapter1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PlayStatementTest {

    @Test
    public void given_input_when_statement_then_output() throws JsonProcessingException {
        String playsStr = "{\"hamlet\":{\"name\":\"Hamlet\",\"type\":\"tragedy\"},\"as-like\":{\"name\":\"As You Like It\",\"type\":\"comedy\"},\"othello\":{\"name\":\"Othello\",\"type\":\"tragedy\"}}";
        String invoicesStr = "[{\"customer\":\"BigCo\",\"performances\":[{\"playID\":\"hamlet\",\"audience\":55},{\"playID\":\"as-like\",\"audience\":35},{\"playID\":\"othello\",\"audience\":40}]}]";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode plays = objectMapper.readTree(playsStr);
        JsonNode invoices = objectMapper.readTree(invoicesStr);
        PlayStatement statement = new PlayStatement();
        String expectation = "Statement for BigCo\n" +
                             "  Hamlet: $650.00 (55 seats)\n" +
                             "  As You Like It: $580.00 (35 seats)\n" +
                             "  Othello: $500.00 (40 seats)\n" +
                             "Amount owed is $1,730.00\n" +
                             "You earned 47 credits\n";
        assertThat(statement.statement(plays, invoices.get(0)), is(equalTo(expectation)));
    }


}
