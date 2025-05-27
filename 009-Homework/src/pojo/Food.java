package pojo;

public class Food {
    private int fId;
    private String fName;
    private double fPrice;
    private int fType;

    public Food() {}

    public Food(int fId, String fName, double fPrice, int fType) {
        this.setfId(fId);
        this.setfName(fName);
        this.setfPrice(fPrice);
        this.setfType(fType);
    }

    public int getfId() {
        return fId;
    }

    public String getfName() {
        return fName;
    }

    public double getfPrice() {
        return fPrice;
    }

    public int getfType() {
        return fType;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setfPrice(double fPrice) {
        this.fPrice = fPrice;
    }

    public void setfType(int fType) {
        this.fType = fType;
    }
}
