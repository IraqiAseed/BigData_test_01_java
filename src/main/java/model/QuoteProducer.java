package model;

import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class QuoteProducer {

    private Quote quote;

    QuoteProducer(QuoteProducerBuilder quoteProducerBuilder)
    {
        quote = quoteProducerBuilder.quote;
    }

    public QuoteProducer() { }

    public static QuoteProducerBuilder builder() {
        return new QuoteProducerBuilder();
    }

    @SneakyThrows
    public static List<String> produce() {
        BufferedReader reader = new BufferedReader(new FileReader("Data/quotes.txt"));

        List<String> list = reader.lines()
                .map(String::toUpperCase)
                .peek(System.out::println)
                .collect(Collectors.toList());

        System.out.println(list);

        return list;
    }
    public Quote getQuote()
    {
        return quote;
    }

    @SneakyThrows
    public void createQuoteFile()
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

        public QuoteProducerBuilder setText(@NonNull String text) {
            this.quote.setText(text);
            quote.setQuoteLength();
            return this;
        }
        public QuoteProducerBuilder setId(long id) {
            this.quote.setId(id);
            return this;
        }
        public QuoteProducerBuilder setLength() {
            quote.setQuoteLength();
            return this;
        }

        public QuoteProducer build() {
            return new QuoteProducer(this);
        }

        public String toString() {
            return "QuoteProducer.QuoteProducerBuilder(quote=" + this.quote + ")";
        }


    }

    @SneakyThrows
    public static void main(String[] args) {
        QuoteProducer qp = new QuoteProducer();
        qp.produce();
    }
}
