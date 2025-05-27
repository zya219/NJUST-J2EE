package pojo;

public class Product {
    private int pId;
    private String pName;
    private double pPrice;
    private int pCount;
    private int oCount;

    public Product(int pId, String pName, double pPrice, int pCount) {
        this.pId = pId;
        this.pName = pName;
        this.pPrice = pPrice;
        this.pCount = pCount;
    }
    public Product() {
        
    }
    public int getoCount() {
        return oCount;
    }

    public void setoCount(int oCount) {
        this.oCount = oCount;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public double getpPrice() {
        return pPrice;
    }

    public void setpPrice(double pPrice) {
        this.pPrice = pPrice;
    }

    public int getpCount() {
        return pCount;
    }

    public void setpCount(int pCount) {
        this.pCount = pCount;
    }
}
