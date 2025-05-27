package dao;

import pojo.Login;
import pojo.Order;
import utils.DBUtil;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderDAO {
	
    public List<Order> getOrders(Login loginForm) {
        List<Order> orders = new ArrayList<>();

        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("uId", loginForm.getuId());

        try {
            List<Map<String, Object>> data = DBUtil.query("orders", whereMap);
            for (Map<String, Object> stringObjectMap : data) {
                int oId = Integer.parseInt(String.valueOf(stringObjectMap.get("oId")));
                int uId = Integer.parseInt(String.valueOf(stringObjectMap.get("uId")));
                Date oTime = (Date) stringObjectMap.get("oTime");
                
                String oItems = (String) stringObjectMap.get("oItems");
                
                System.out.println("oItems: " + oItems);
                
                // Correctly convert BigDecimal to double
                BigDecimal totalPriceBD = (BigDecimal) stringObjectMap.get("totalPrice");
                double totalPrice = totalPriceBD.doubleValue();

                Order order = new Order(oId, uId, oTime, oItems, totalPrice);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
                String tmp = sdf.format(order.getoTime());
                order.setTimeString(tmp);
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<Order> checkOrders(List<Order> orders) {
        Map<String, Object> whereMap = new HashMap<>();

        for (Order order : orders) {
            try {
                whereMap.put("oId", order.getoId());
                List<Map<String, Object>> data = DBUtil.query("orders", whereMap);
                for (Map<String, Object> stringObjectMap : data) {
                    order.setuId(Integer.parseInt(String.valueOf(stringObjectMap.get("uId"))));
                    order.setoTime((Date) stringObjectMap.get("oTime"));
                    order.setFoodString((String) stringObjectMap.get("oItems"));

                    // Correctly convert BigDecimal to double
                    BigDecimal totalPriceBD = (BigDecimal) stringObjectMap.get("totalPrice");
                    double totalPrice = totalPriceBD.doubleValue();
                    order.setTotalPrice(totalPrice);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
                    String tmp = sdf.format(order.getoTime());
                    order.setTimeString(tmp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

    public int delCheck(List<Order> orders) {
        Map<String, Object> whereMap = new HashMap<>();
        Date toTime = new Date();
        long delTime = 24 * 60 * 60 * 1000L;

        for (Order order : orders) {
            whereMap.put("oId", order.getoId());
            try {
                List<Map<String, Object>> data = DBUtil.query("orders", whereMap);
                for (Map<String, Object> stringObjectMap : data) {
                    Date localDateTime = (Date) stringObjectMap.get("oTime");
                    long seconds = toTime.getTime() - localDateTime.getTime();
                    if (seconds < delTime) {
                        return 5;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int cancelCheck(List<Order> orders) {
        Map<String, Object> whereMap = new HashMap<>();
        Date toTime = new Date();
        long delTime = 10 * 60 * 1000L;

        for (Order order : orders) {
            whereMap.put("oId", order.getoId());
            try {
                List<Map<String, Object>> data = DBUtil.query("orders", whereMap);
                for (Map<String, Object> stringObjectMap : data) {
                    Date localDateTime = (Date) stringObjectMap.get("oTime");
                    long seconds = toTime.getTime() - localDateTime.getTime();
                    if (seconds > delTime) {
                        return 6;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public void delOrders(List<Order> orders) {
        Map<String, Object> whereMap = new HashMap<>();
        for (Order order : orders) {
            whereMap.put("oId", order.getoId());
            try {
                DBUtil.delete("orders", whereMap);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void cancelOrders(List<Order> orders) {
        Map<String, Object> whereMap = new HashMap<>();
        for (Order order : orders) {
            whereMap.put("oId", order.getoId());
            try {
                DBUtil.delete("orders", whereMap);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
