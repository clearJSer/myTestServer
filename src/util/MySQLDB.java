package util;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException; 
public class MySQLDB {  
//	public static final String url = "jdbc:mysql://127.0.0.1/mweb";  
//    public static final String name = "com.mysql.jdbc.Driver";  
//    public static final String user = "root";  
//    public static final String password = "root"; 
    
    //新浪云
    public static final String url = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_redlemon";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "22z05ymn4o";  
    public static final String password = "hx0z4wlkj5532w40x3310j4z2wihwy440mz1mi4y"; 
  
    public Connection conn = null;  
    public PreparedStatement pst = null;  
  
    public MySQLDB() {  
        try {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    public PreparedStatement init(String sql){
    	try {
			pst = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//准备执行语句  
    	return pst;
    }
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }
    public static void main(String[] args) {
    	MySQLDB a = new MySQLDB();
    	try {
			a.init("INSERT into user(username) VALUES ('ccc')").execute();
			System.out.println("success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
} 
