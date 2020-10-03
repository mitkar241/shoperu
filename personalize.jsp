<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<html lang="en">
<body>


<%
String username=request.getParameter("username");
String password=request.getParameter("password");

Connection conn=null;
Statement st=null;
ResultSet rs=null;


try
{
     String driver = "com.mysql.jdbc.Driver";
     String hostname = "localhost";
     String dbName = "EM";
     String url = "jdbc:mysql://" + hostname + ":3306/" + dbName;
     String uname = "Raktim";
     String pwd = "241";
     Class.forName(driver);

conn = DriverManager.getConnection(url, uname, pwd);
st=conn.createStatement();

String qry="SELECT * FROM EMCUST";
rs=st.executeQuery(qry);
while(rs.next())
{
	if((username.equals(rs.getString(1)) && password.equals(rs.getString(4))))
	{
		String personalize =rs.getString(1)+"#"+rs.getString(2)+"$"+rs.getString(3);
		session.setAttribute("personalize", personalize);
		response.sendRedirect("default.jsp");
	}
}
response.sendRedirect("loginRetry.jsp");
}catch(Exception e){System.out.println(e);}
%>

</body>
</html>
