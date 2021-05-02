package repo;

import lombok.NonNull;
import lombok.SneakyThrows;
import model.Quote;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class QuoteProducer {

    private Quote quote;

    QuoteProducer(QuoteProducerBuilder quoteProducerBuilder) {
        quote = quoteProducerBuilder.quote;
    }

    public QuoteProducer() {
    }

    public static QuoteProducerBuilder builder() {
        return new QuoteProducerBuilder();
    }

    @SneakyThrows
    public static List<String> produce() {
        BufferedReader reader = new BufferedReader(new FileReader("data/quotes.txt"));
        List<String> list = reader.lines()
                .map(String::toUpperCase)
                .peek(System.out::println)
                .collect(Collectors.toList());
        System.out.println(list);
        return list;
    }

    public Quote getQuote() {
        return quote;
    }

    @SneakyThrows
    public File createQuoteFile() {
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy_HHmmss");
        String formatDateTime = localDateTimeNow.format(format);
        String fileName = formatDateTime + ".obj";

        File directory = new File("quotes");
        directory.mkdir();

        if(directory.exists()) {
            File file = new File("quotes", fileName);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(quote);

            fos.close();
            oos.close();
            return file;
        }

        return null;
    }


    public static class QuoteProducerBuilder {
        private Quote quote;

        QuoteProducerBuilder() {
            this.quote = new Quote();
        }

        public QuoteProducerBuilder quote(Quote quote) {
            this.quote = quote;
            return this;
        }

        public QuoteProducerBuilder text(@NonNull String text) {
            this.quote.setText(text);
            quote.setQuoteLength();
            return this;
        }

        public QuoteProducerBuilder id(long id) {
            this.quote.setId(id);
            return this;
        }

        public QuoteProducerBuilder setLength() {
            if(quote.getText() != null)
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
