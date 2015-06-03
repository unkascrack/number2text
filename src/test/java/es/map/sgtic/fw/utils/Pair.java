package es.map.sgtic.fw.utils;

class Pair {

    private Number number;
    private String text;

    public Pair(final Number number, final String text) {
        this.number = number;
        this.text = text;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(final Number number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

}
