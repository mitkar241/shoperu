import java.sql.*;

class MysqlCon{

  public static void main(String args[]){
    Connection conn=null;
    Statement stmt=null;
    ResultSet rs=null;

    try {
      /*
      Loading class `com.mysql.jdbc.Driver'. This is deprecated.
      The new driver class is `com.mysql.cj.jdbc.Driver'.
      The driver is automatically registered via the SPI and
      manual loading of the driver class is generally unnecessary.
      */
      //String driver = "com.mysql.jdbc.Driver";
      String driver = "com.mysql.cj.jdbc.Driver";
      String hostname = "localhost";
      String dbName = "employee";
      String url = "jdbc:mysql://" + hostname + ":3306/" + dbName; // + "?useSSL=false";
      String uname = "manager";
      String pwd = "manager_password";
      Class.forName(driver);

      conn = DriverManager.getConnection(url, uname, pwd);
      stmt=conn.createStatement();
      rs=stmt.executeQuery("SELECT * FROM credential");
      while(rs.next()) {
        System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
      }
      conn.close();
    } catch(Exception err){
      System.out.println(err);
    }
  }

}
