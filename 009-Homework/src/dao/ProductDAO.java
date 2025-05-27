package dao;

import pojo.Product;
import utils.DBUtil;

import java.util.*;

public class ProductDAO {
	public Product findProduct(Product product){
		int pId=product.getpId();
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("pId", pId);
        try {
            List<Map<String, Object>> data = DBUtil.query("product", whereMap);
            for (Map<String, Object> row : data) {
                int pCount = (int) row.get("pCount");
                String pName = (String) row.get("pName");
                double pPrice = (double) row.get("pPrice");
                product.setpCount(pCount);
                product.setpName(pName);
                product.setpPrice(pPrice);
            }
        } catch (Exception e) {
                e.printStackTrace();
                return null;
        }  
        return product;
	}
}
