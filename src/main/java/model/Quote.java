package model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
//@Builder
public class Quote implements Serializable {
    private long id;
    private String text;
    private QuoteLength quoteLength;
    private  static final long serialVersionUID = 21L;

    public Quote() {
        id = System.nanoTime();
    }

    public String toString() {
        String var10000 = this.getText();
        return "Quote(text=" + var10000 + ", quoteLength=" + this.getQuoteLength() + ", id=" + this.id +")";
    }
}
