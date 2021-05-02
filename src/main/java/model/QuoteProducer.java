package model;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class QuoteProducer {

    private Quote quote;

    QuoteProducer(QuoteProducerBuilder quoteProducerBuilder)
    {
        quote = quoteProducerBuilder.quote;
    }

    public static QuoteProducerBuilder builder() {
        return new QuoteProducerBuilder();
    }

    public Quote getQuote()
    {
        return quote;
    }

    @SneakyThrows
    public void createQuoteFile(Quote quote)
    {
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy_HHmmss");
        String formatDateTime = localDateTimeNow.format(format);
        String fileName = formatDateTime + ".obj";

        File directory = new File("Quotes");
        directory.mkdir();
        File file = new File("Quotes", fileName);

        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(quote);

        fos.close();
        oos.close();

    }


    public static class QuoteProducerBuilder {
        private Quote quote;

        QuoteProducerBuilder() {
            this.quote = new Quote();
        }

        public QuoteProducerBuilder quote(Quote quote) {
            this.quote =quote;
            return this;
        }

        public QuoteProducerBuilder setText(String text) {
            this.quote.setText(text);
            return this;
        }
        public QuoteProducerBuilder setId(long id) {
            this.quote.setId(id);
            return this;
        }
        public QuoteProducerBuilder setLength() {
            quote.setQuoteLength(QuoteLength.handleLength(quote.getText().length()));
            return this;
        }

        public QuoteProducer build() {
            return new QuoteProducer(this);
        }

        public String toString() {
            return "QuoteProducer.QuoteProducerBuilder(quote=" + this.quote + ")";
        }


    }

    public static void main(String[] args) {
        QuoteProducer testMiddleName = QuoteProducer.builder().setText("Hello World!").setId(3).setLength().build();
        testMiddleName.createQuoteFile(testMiddleName.getQuote());

        QuoteProducer testShortName = QuoteProducer.builder().setText("Hell").setId(99).setLength().build();
        testShortName.createQuoteFile(testShortName.getQuote());

        QuoteProducer testLongName = QuoteProducer.builder().setText("" +
                "Hello World!Hello World World!Hello World!Hello World!Hello World!").setId(54).setLength().build();
        testLongName.createQuoteFile(testLongName.getQuote());
    }
}
