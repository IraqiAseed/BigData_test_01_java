package model;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.io.Console;
import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class QuoteConsumerTest {

    @Test
    @SneakyThrows
    public void readQuoteAndCheckText() {

        File objFile = QuoteProducer.builder().text("READ QUOTE CHECK").build().createQuoteFile();
        QuoteConsumer qc = new QuoteConsumer();
        Quote q = qc.readQuote(objFile);

        Assert.assertEquals("READ QUOTE CHECK", q.getText());

    }

    @Test
    @SneakyThrows
    public void readQuoteAndCheckUniqueIdes() {

        File objFile1 = QuoteProducer.builder().text("READ QUOTE CHECK").build().createQuoteFile();
        File objFile2 = QuoteProducer.builder().text("READ QUOTE CHECK").build().createQuoteFile();
        QuoteConsumer qc1 = new QuoteConsumer();
        Quote q1 = qc1.readQuote(objFile1);
        QuoteConsumer qc2 = new QuoteConsumer();
        Quote q2 = qc2.readQuote(objFile2);
        Assert.assertTrue(q2.getId() != q1.getId());

    }

    @Test
    @SneakyThrows
    public void readQuoteAndCheckLengthQuote() {

        File objFile1 = QuoteProducer.builder().text("READ QUOTE CHECK").build().createQuoteFile();
        QuoteConsumer qc1 = new QuoteConsumer();
        Quote q1 = qc1.readQuote(objFile1);

        Assert.assertEquals(q1.getQuoteLength(), QuoteLength.handleLength("READ QUOTE CHECK".length()));

    }

}

