package consumer;

import lombok.SneakyThrows;
import model.Quote;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class QuoteConsumer {

    public QuoteConsumer() {
    }


    @SneakyThrows
    public void handle() {
        List<File> files = Files.list(Paths.get("quotes"))
                .map(Path::toFile)
                .collect(Collectors.toList());

        for (File file : files) {
            Thread t = new Thread(() -> writeJsonFile(readQuote(file)));
            t.start();

        }
    }

    @SneakyThrows
    public Quote readQuote(File file) {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Quote q = (Quote) ois.readObject();

        fis.close();
        ois.close();
        return q;
    }

    @SneakyThrows
    public void writeJsonFile(Quote quote) {
        JSONObject obj = new JSONObject();
        Field[] fields = quote.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            obj.put(field.getName(), field.get(quote).toString());
        }

        File directory = new File("jsons");
        directory.mkdir();
        if(directory.exists())
        {
            String fileName = "jsons\\" + quote.getId() + ".json";
            FileWriter file = new FileWriter(fileName);
            file.write(obj.toJSONString());
            file.flush();
            file.close();

            System.out.println(obj.toJSONString());
        }


    }

    @SneakyThrows
    public static void main(String[] args) {
        QuoteConsumer qc = new QuoteConsumer();
        while (true) {
            qc.handle();
            Thread.sleep(10000);
        }


    }


}
