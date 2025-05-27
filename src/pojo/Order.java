package pojo;

import java.util.Date;
import java.util.List;

public class Order {
    private int oId;
    private int uId;
    private Date oTime;
    private String timeString;
    private double totalPrice;
    private List<Food> foodList;
    private String foodString;

    public Order() {}

    public Order(int oId, int uId, Date oTime, String foodString, double totalPrice) {
        this.setoId(oId);
        this.setuId(uId);
        this.setoTime(oTime);
        this.setFoodString(foodString);
        this.setTotalPrice(totalPrice);
    }

    public int getoId() {
        return oId;
    }
    public int getuId() {
        return uId;
    }
    public Date getoTime() {
        return oTime;
    }
    public String getTimeString() {
        return timeString;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public List<Food> getFoodList() {
        return foodList;
    }
    public String getFoodString() {
        return foodString;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }
    public void setuId(int uId) {
        this.uId = uId;
    }
    public void setoTime(Date oTime) {
        this.oTime = oTime;
    }
    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }
    public void setFoodString(String foodString) {
        this.foodString = foodString;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oId=" + oId +
                ", uId=" + uId +
                ", oTime=" + oTime +
                ", timeString='" + timeString + '\'' +
                ", totalPrice=" + totalPrice +
                ", foodList=" + foodList +
                ", foodString='" + foodString + '\'' +
                '}';
    }
}
