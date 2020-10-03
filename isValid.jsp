<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<html lang="en">
<body>


<%
String search=request.getParameter("search");

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

String qry="SELECT * FROM EMPROD";
rs=st.executeQuery(qry);
while(rs.next())
{
	if((search.equals(rs.getString(2))))
	{
		session.setAttribute("display", search);
		response.sendRedirect("single.jsp");
	}
}
response.sendRedirect("all.jsp");
}catch(Exception e){System.out.println(e);}
%>

</body>
</html>
