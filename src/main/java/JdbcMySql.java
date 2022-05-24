package src.main.classes;

import java.sql.*;
import java.util.Calendar;

class JdbcMySql{

  public static void main(String args[]){
    Connection conn=null;

    try {
      JdbcMySql mysqlConn = new JdbcMySql();
      conn = mysqlConn.getConn();
      
      mysqlConn.getCreds(mysqlConn, conn);
      
      mysqlConn.addCred(mysqlConn, conn);
      mysqlConn.getCreds(mysqlConn, conn);

      mysqlConn.delCred(mysqlConn, conn);
      mysqlConn.getCreds(mysqlConn, conn);

      mysqlConn.emptyCred(mysqlConn, conn);
      mysqlConn.getCreds(mysqlConn, conn);

      mysqlConn.closeConn(conn);
    } catch(Exception err){
      System.out.println(err);
    }
  }

  public Connection getConn(){
    Connection conn=null;

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
    } catch(Exception err){
      System.out.println(err);
    }

    return conn;
  }

  public void closeConn(Connection conn){
    try {
      conn.close();
    } catch(Exception err){
      System.out.println(err);
    }
  }

  public Statement getStmt(Connection conn){
    Statement stmt = null;
    try {
      stmt = conn.createStatement();
    } catch(Exception err){
      System.out.println(err);
    }
    return stmt;
  }

  public void getCreds(JdbcMySql mysqlConn, Connection conn){
    Statement stmt = null;
    ResultSet rs=null;

    try {
      stmt = mysqlConn.getStmt(conn);
      rs=stmt.executeQuery("SELECT * FROM credential");
      while(rs.next()) {
        System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
      }
    } catch(Exception err){
      System.out.println(err);
    }
  }

  public void addCred(JdbcMySql mysqlConn, Connection conn){
    Statement stmt = null;
    PreparedStatement preparedStmt = null;

    try {
      // create a sql date object so we can use it in our INSERT statement
      Calendar calendar = Calendar.getInstance();
      java.sql.Date currDate = new java.sql.Date(calendar.getTime().getTime());

      // the mysql insert statement
      String sql = "INSERT INTO credential VALUES (?, ?, ?)";

      // create the mysql insert preparedstatement
      preparedStmt = conn.prepareStatement(sql);
      preparedStmt.setInt(1, 1003);
      preparedStmt.setString(2, "ABCDEFGH");
      preparedStmt.setDate(3, currDate);

      // execute the preparedstatement
      preparedStmt.executeUpdate();
    } catch(Exception err){
      System.out.println(err);
    }
  }

  public void delCred(JdbcMySql mysqlConn, Connection conn){
    Statement stmt = null;
    PreparedStatement preparedStmt = null;

    try {
      // create the mysql delete statement.
      String sql = "DELETE FROM credential WHERE EmpId = ?";

      // create the mysql insert preparedstatement
      preparedStmt = conn.prepareStatement(sql);
      preparedStmt.setInt(1, 1003);

      // execute the preparedstatement
      preparedStmt.executeUpdate();
    } catch(Exception err){
      System.out.println(err);
    }
  }

  public void emptyCred(JdbcMySql mysqlConn, Connection conn){
    Statement stmt = null;
    PreparedStatement preparedStmt = null;

    try {
      // create the mysql delete statement.
      String sql = "TRUNCATE credential";

      // create the mysql insert preparedstatement
      preparedStmt = conn.prepareStatement(sql);

      // execute the preparedstatement
      preparedStmt.executeUpdate();
    } catch(Exception err){
      System.out.println(err);
    }
  }

}
