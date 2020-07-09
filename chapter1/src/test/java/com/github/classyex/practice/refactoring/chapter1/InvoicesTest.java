package com.github.classyex.practice.refactoring.chapter1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class InvoicesTest {

    @Test
    public void given_invoices_string_when_format_then_get_value_correct() throws JsonProcessingException {
        String invoicesStr = "[{\"customer\":\"BigCo\",\"performances\":[{\"playID\":\"hamlet\",\"audience\":55},{\"playID\":\"as-like\",\"audience\":35},{\"playID\":\"othello\",\"audience\":40}]}]";
        JsonNode jsonNode = new ObjectMapper().readTree(invoicesStr);
        assertInvoice(jsonNode.size(), jsonNode.get(0).get("customer").asText(),
                jsonNode.get(0).get("performances").get(0).get("playID").asText(),
                jsonNode.get(0).get("performances").get(0).get("audience").asInt());
    }

    private void assertInvoice(int size, String customer, String playId, int audience) {
        assertThat(size, is(equalTo(1)));
        assertThat(customer, is(equalTo("BigCo")));
        assertThat(playId, is(equalTo("hamlet")));
        assertThat(audience, is(equalTo(55)));
    }

    @Test
    public void given_invoices_string_when_format_bean_then_correct() throws JsonProcessingException {
        String invoicesStr = "[{\"customer\":\"BigCo\",\"performances\":[{\"playID\":\"hamlet\",\"audience\":55},{\"playID\":\"as-like\",\"audience\":35},{\"playID\":\"othello\",\"audience\":40}]}]";
        List<Invoice> invoices = new ObjectMapper().readValue(invoicesStr, new TypeReference<List<Invoice>>() {
        });
        assertInvoice(invoices.size(), invoices.get(0).getCustomer(),
                invoices.get(0).getPerformances().get(0).getPlayID(),
                invoices.get(0).getPerformances().get(0).getAudience());
    }



}
