package dao;

import pojo.Food;
import pojo.Order;
import utils.DBUtil;
import java.math.BigDecimal;
import java.util.*;

public class FoodDAO {
    public List<Order> getFoodNames(List<Order> orders) {
        for(Order order:orders) {
            List<Food> foodList = new ArrayList<>();
            String foodString = order.getFoodString();
            int len = foodString.length();
            int tmp = 0;
            for(int i = 0; i < len; i++) {
                char c = foodString.charAt(i);
                if(c >= '0' && c <= '9') {
                    tmp = tmp * 10 + (c - '0');
                }
                if(c == ',' || c == ' ' || i == len - 1) {
                    Map<String, Object> whereMap = new HashMap<>();
                    if(tmp > 0) {
                        whereMap.put("fId", tmp);
                    }
                    try {
                        List<Map<String, Object>> data = DBUtil.query("food", whereMap);
                        for(Map<String, Object> stringObjectMap:data) {
                            int fId = Integer.parseInt(String.valueOf(stringObjectMap.get("fId")));
                            String fName = (String) stringObjectMap.get("fName");
                            BigDecimal fPriceBD = (BigDecimal) stringObjectMap.get("fPrice");
                            double fPrice = fPriceBD.doubleValue();

                            int fType = Integer.parseInt(String.valueOf(stringObjectMap.get("fType")));
                            Food food = new Food(fId, fName, fPrice, fType);
                            foodList.add(food);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    tmp = 0;
                }
            }
            order.setFoodList(foodList);
        }
        return orders;
    }
}
