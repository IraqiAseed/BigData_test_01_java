package model;

public enum QuoteLength {
    LONG(21,100),MIDDLE(11,20),SHORT(0,10);

    private int min;
    private int max;


    QuoteLength(int min,int max)
    {
        this.min = min;
        this.max = max;
    }

    public static QuoteLength handleLength(int length) throws IllegalStateException {
        QuoteLength[] statuses = values();
        for (QuoteLength status : statuses) {
            if (status.min <= length && status.max >= length ) {
                return status;
            }
        }
        throw new IllegalStateException(length + " length is not supported for now");
    }
}
