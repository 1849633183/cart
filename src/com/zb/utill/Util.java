package com.zb.utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import com.zb.pojo.OrderItem;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Util {
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/cart?useSSL=true&allowMultiQueries=true", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static UUID getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid;
	}

	public static void free(Connection con, Statement st, ResultSet re) {
		try {
			if (con != null)
				con.close();
			if (st != null)
				st.close();
			if (re != null)
				re.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//合并两个JSONArray
	public static JSONArray joinJSONArray(JSONArray mData, JSONArray array) {
        StringBuffer buffer = new StringBuffer();
        try {
            int len = mData.size()-1;
            for (int i = 0; i < len; i++) {
                JSONObject obj1 = (JSONObject) mData.get(i);
                if (i == len - 1)
                    buffer.append(obj1.toString());
                else
                    buffer.append(obj1.toString()).append(",");
            }
            len = array.size();
            if (len > 0)
                buffer.append(",");
            for (int i = 0; i < len; i++) {
                JSONObject obj1 = (JSONObject) array.get(i);
                if (i == len - 1)
                    buffer.append(obj1.toString());
                else
                    buffer.append(obj1.toString()).append(",");
            }
            buffer.insert(0, "[").append("]");
            return new JSONArray().fromObject(buffer.toString());
        } catch (Exception e) {
        }
        return null;
    }
	
	public static boolean oderitemnumberadd(JSONArray array,JSONObject jsonObject,OrderItem item) {
		// TODO Auto-generated method stub
		  for(int i=0;i<array.size();i++)
	        {
	        	if(((JSONObject)array.get(i)).getString("product").equals(jsonObject.getString("product")))
	        	{
	        		((JSONObject)array.get(i)).put("number", ((JSONObject)array.get(i)).getInt("number")+jsonObject.getInt("number"));
	        		item.setOiid(((JSONObject)array.get(i)).getString("oiid"));
	        		item.setNumber(((JSONObject)array.get(i)).getInt("number"));
	        	return true; 
	        	}
	        }
          return false;
	}
}
