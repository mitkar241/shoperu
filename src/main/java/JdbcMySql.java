package src.main.classes;

import java.sql.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class JdbcMySql{

  public static void main(String args[]){
    Connection conn=null;

    try {
      JdbcMySql mysqlConn = new JdbcMySql();
      conn = mysqlConn.getConn();
      
      mysqlConn.getCreds(mysqlConn, conn);
      
      // create a sql date object so we can use it in our INSERT statement
      Calendar calendar = Calendar.getInstance();
      java.sql.Date currDate = new java.sql.Date(calendar.getTime().getTime());
      Credential cred = null;

      Map<Integer,Credential> credMap=new HashMap<Integer, Credential>();
      //adding values to map
      cred = new Credential(1001, "ABCDEFGA", currDate);
      credMap.put(cred.getEmpId(), cred);
      cred = new Credential(1002, "ABCDEFGB", currDate);
      credMap.put(cred.getEmpId(), cred);
      cred = new Credential(1003, "ABCDEFGC", currDate);
      credMap.put(cred.getEmpId(), cred);

      //retrieving values from map
      Set<Integer> empIdSet= credMap.keySet();
      for(int i:empIdSet){
        mysqlConn.addCred(mysqlConn, conn, credMap.get(i));
      }

      mysqlConn.delCred(mysqlConn, conn, credMap.get(1002));
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

  public void addCred(JdbcMySql mysqlConn, Connection conn, Credential cred){
    Statement stmt = null;
    PreparedStatement preparedStmt = null;

    try {
      // the mysql insert statement
      String sql = "INSERT INTO credential VALUES (?, ?, ?)";

      // create the mysql insert preparedstatement
      preparedStmt = conn.prepareStatement(sql);
      preparedStmt.setInt(1, cred.getEmpId());
      preparedStmt.setString(2, cred.getPwdHash());
      preparedStmt.setDate(3, cred.getExpDate());

      // execute the preparedstatement
      preparedStmt.executeUpdate();
    } catch(Exception err){
      System.out.println(err);
    }
  }

  public void delCred(JdbcMySql mysqlConn, Connection conn, Credential cred){
    Statement stmt = null;
    PreparedStatement preparedStmt = null;

    try {
      // create the mysql delete statement.
      String sql = "DELETE FROM credential WHERE EmpId = ?";

      // create the mysql insert preparedstatement
      preparedStmt = conn.prepareStatement(sql);
      preparedStmt.setInt(1, cred.getEmpId());

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

class Credential {
    private int empId;
    private String pwdHash;
    private Date expDate;

    public Credential(int empId,String pwdHash, Date expDate){
      this.empId = empId;
      this.pwdHash = pwdHash;
      this.expDate = expDate;
    }
    public int getEmpId() {
      return empId;
    }
    public String getPwdHash() {
      return pwdHash;
    }
    public Date getExpDate() {
      return expDate;
    }
    @Override
    public String toString() {
      return "Credential [EmpId : " + empId + ", PwdHash  : " + pwdHash + ", ExpDate : " + expDate + "]";
    }

}
