package model;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;



public class QuoteProducerTest {


    @Test
    public void testQuoteText() {
        Quote quoteFromProducer = QuoteProducer.builder().text("Hello").id(5).build().getQuote();
        Assert.assertEquals("Hello", quoteFromProducer.getText());
    }

    @Test
    public void testQuoteId() {
        Quote quoteFromProducer = QuoteProducer.builder().text("Hello").id(5).build().getQuote();
        Assert.assertEquals(5, quoteFromProducer.getId());
    }

    @Test
    public void testQuoteLength() {
        Quote quoteFromProducer = QuoteProducer.builder().text("Hello").id(5).build().getQuote();
        Assert.assertEquals(QuoteLength.SHORT, quoteFromProducer.getQuoteLength());
    }

    @Test
    public void listOfQuotesInData() {
        List<String> list = QuoteProducer.produce();
        Assert.assertTrue(list.size() != 0);
    }

    @Test
    public void getQuote() {
        Quote quoteFromProducer = QuoteProducer.builder().text("Hello").id(5).build().getQuote();
        Quote q = new Quote();
        q.setText("Hello");
        q.setId(5);

        Assert.assertEquals(q, quoteFromProducer);
    }

    @Test
    @SneakyThrows
    public void checkIfNewFilesAdded() {

        List<String> list = QuoteProducer.produce();
        List<File> filesOld = Files.list(Paths.get("Quotes"))
                .map(Path::toFile)
                .collect(Collectors.toList());
        int oldSize = filesOld.size();

        for (String s : list) {
            QuoteProducer.builder().text(s).build().createQuoteFile();


        }

        List<File> filesNew = Files.list(Paths.get("Quotes"))
                .map(Path::toFile)
                .collect(Collectors.toList());
        int newSize = filesNew.size();
        Thread.sleep(1000);
        Assert.assertEquals(oldSize + list.size(), newSize);
    }


}