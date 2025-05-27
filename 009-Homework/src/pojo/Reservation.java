package pojo;

import java.util.Date;
import java.util.List;

public class Reservation {
private int oId;
private int uId;
private Date oTime;
private double oTotal;
private int oStatus;
private String timeString;
private List<Product> productList;
private String productString;

public Reservation(int oId, int uId, Date oTime, String productString, double oTotal, int oStatus) {
    this.oId = oId;
    this.uId = uId;
    this.oTime = oTime;
    this.productString = productString;
    this.oTotal = oTotal;
    this.oStatus = oStatus;
}

public Reservation(int oId, int uId, Date oTime, double oTotal, int oStatus) {
    this.oId = oId;
    this.uId = uId;
    this.oTime = oTime;
    this.oTotal = oTotal;
    this.oStatus = oStatus;
}

public Reservation() {
}

public int getoId() {
    return oId;
}

public void setoId(int oId) {
    this.oId = oId;
}

public int getuId() {
    return uId;
}

public void setuId(int uId) {
    this.uId = uId;
}

public Date getoTime() {
    return oTime;
}

public void setoTime(Date oTime) {
    this.oTime = oTime;
}

public double getoTotal() {
    return oTotal;
}

public void setoTotal(double oTotal) {
    this.oTotal = oTotal;
}

public int getoStatus() {
    return oStatus;
}

public void setoStatus(int oStatus) {
    this.oStatus = oStatus;
}

public String getTimeString() {
    return timeString;
}

public void setTimeString(String timeString) {
    this.timeString = timeString;
}

public List<Product> getProductList() {
    return productList;
}

public void setProductList(List<Product> productList) {
    this.productList = productList;
}

public String getProductString() {
    return productString;
}

public void setProductString(String productString) {
    this.productString = productString;
}

@Override
public String toString() {
    return "Reservation{" +
            "oId=" + oId +
            ", uId=" + uId +
            ", oTime=" + oTime +
            ", timeString='" + timeString + '\'' +
            ", oTotal=" + oTotal +
            ", oStatus=" + oStatus +
            ", productList=" + productList +
            ", productString='" + productString + '\'' +
            '}';
}
}