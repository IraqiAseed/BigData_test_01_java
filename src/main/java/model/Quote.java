package model;

import java.io.Serializable;

public class Quote implements Serializable {
    private long id;
    private String text;
    private QuoteLength quoteLength;
    private  static final long serialVersionUID = 21L;

    public Quote() {
        id = System.nanoTime();
    }

    public void setQuoteLength() {
        this.quoteLength = QuoteLength.handleLength(text.length());
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Quote;
    }

    public long getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public QuoteLength getQuoteLength() {
        return this.quoteLength;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
        setQuoteLength();
    }

    public void setQuoteLength(QuoteLength quoteLength) {
        this.quoteLength = quoteLength;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Quote)) return false;
        final Quote other = (Quote) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$text = this.getText();
        final Object other$text = other.getText();
        if (this$text == null ? other$text != null : !this$text.equals(other$text)) return false;
        final Object this$quoteLength = this.getQuoteLength();
        final Object other$quoteLength = other.getQuoteLength();
        if (this$quoteLength == null ? other$quoteLength != null : !this$quoteLength.equals(other$quoteLength))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * PRIME + (int) ($id >>> 32 ^ $id);
        final Object $text = this.getText();
        result = result * PRIME + ($text == null ? 43 : $text.hashCode());
        final Object $quoteLength = this.getQuoteLength();
        result = result * PRIME + ($quoteLength == null ? 43 : $quoteLength.hashCode());
        return result;
    }

    public String toString() {
        return "Quote(id=" + this.getId() + ", text=" + this.getText() + ", quoteLength=" + this.getQuoteLength() + ")";
    }
}
