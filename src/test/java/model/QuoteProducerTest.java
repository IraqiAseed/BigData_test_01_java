package model;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.doAnswer;


public class QuoteProducerTest {


    @Test
    public void listOfQuotesInData() {
    List<String> list = QuoteProducer.produce();
    Assert.assertTrue(list.size() != 0);
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
            QuoteProducer.builder().setText(s).build().createQuoteFile();

            Thread.sleep(1000);
        }

        List<File> filesNew = Files.list(Paths.get("Quotes"))
                .map(Path::toFile)
                .collect(Collectors.toList());
        int newSize = filesNew.size();
        Thread.sleep(1000);
        Assert.assertEquals(oldSize+list.size(),newSize);
    }

    @Test
    public void getQuote() {
        Quote quoteFromProducer = QuoteProducer.builder().setText("Hello").setId(5).build().getQuote();
        Quote q = new Quote();
        q.setText("Hello");
        q.setId(5);

        Assert.assertEquals(q,quoteFromProducer);
    }


}