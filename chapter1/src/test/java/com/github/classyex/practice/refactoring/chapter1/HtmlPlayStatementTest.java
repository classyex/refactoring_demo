package com.github.classyex.practice.refactoring.chapter1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class HtmlPlayStatementTest {

    @Test
    public void given_input_when_html_statement_then_output_html() throws JsonProcessingException {
        String playsStr = "{\"hamlet\":{\"name\":\"Hamlet\",\"type\":\"tragedy\"},\"as-like\":{\"name\":\"As You Like It\",\"type\":\"comedy\"},\"othello\":{\"name\":\"Othello\",\"type\":\"tragedy\"}}";
        String invoicesStr = "[{\"customer\":\"BigCo\",\"performances\":[{\"playID\":\"hamlet\",\"audience\":55},{\"playID\":\"as-like\",\"audience\":35},{\"playID\":\"othello\",\"audience\":40}]}]";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Play> playMap = objectMapper.readValue(playsStr, new TypeReference<Map<String, Play>>() {
        });
        List<Invoice> invoicesList = objectMapper.readValue(invoicesStr, new TypeReference<List<Invoice>>() {
        });
        PlayStatement statement = new PlayStatement(playMap, invoicesList.get(0));
        String expectation = "<h1>Statement for BigCo<h1>\n" +
                "<table>\n" +
                "<tr><th>play</th><th>seats</th><th>cost</th></tr>\n" +
                "<tr><td>Hamlet</td><td>55</td><td>$650.00</td></tr>\n" +
                "<tr><td>As You Like It</td><td>35</td><td>$580.00</td></tr>\n" +
                "<tr><td>Othello</td><td>40</td><td>$500.00</td></tr>\n" +
                "</table>\n" +
                "<p>Amount owed is <em>$1,730.00</em></p>\n" +
                "<p>You earned <em>47</em> credits</p>\n";
        assertThat(statement.statement(), is(equalTo(expectation)));
    }


}
