public class Seat {

    private int rowNumber;
    private int columnNumber;
    private int price;
    private boolean isSold = false;

    public Seat(int rowNumber, int columnNumber, int price) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.price = price;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    @Override
    public String toString() {
        return isSold() ? "B" : "S";
    }
}
