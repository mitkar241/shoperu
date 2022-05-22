<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="shopError.jsp"%>
<%@page import="java.sql.*"%>

<html lang="en">

<head>

  <meta charset="UTF-8">
  <meta name="description" content="E-mall WebApp">
  <meta name="author" content="raktimhalder241">
  <meta name="keywords" content="jsp, html, css, js">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title> E-MALL </title>
  <link rel="stylesheet" type="text/css" href="product.css">

  <script>
      function login()
      {
              window.location.href = "loginForm.jsp";
      }
  </script>

</head>

<body>

  <table class="heading">
    <tr>
      <td>E-MALL
      </td>
    </tr>
  </table>

  <table class="toolbar">
    <tr>

      <td style="width: 50%;padding-top: 10px;">
        <center>
        <form  action="isValid.jsp" style="width: 60%; height: 60%; background-color: yellow; border:1px solid black;">
        <div style="line-height:50%;"><br></div>
        <input style="width:90%;" type="text" name="search">
        <input type="submit" value="search">
        <div style="line-height:50%;"><br></div>
        </form>
        </center>
      </td>

      <td style="width: 25%; ">
        <div style="background-color: yellow; width: 75%; padding: 10px; border: 1px solid black;text-align: center;">
          Hello <b>Guest</b>
        </div>
      </td>

      <td>
        <center>
        <button type="button" onclick="login()">Sign In</button>
        </center>
      </td>

    </tr>
  </table>

  <table class="advertisement">
    <tr>
      <td><img src="src/diwali.gif" style="width: 100%;height:200px;"></td>
      <td style="font-family: Georgia, serif; text-align: center; font-size: 50px;"> DIWALI OFFERS <br> LARGE DISCOUNTS !!! </td>
      <td><img src="src/diwali.gif" style="width: 100%;height:200px;"></td>
    </tr>
  </table>

  <table class="workspace">
    <tr>

      <td class="navigation">
        <br>
        <br>
        <center>
        <form class="customise" action="custHome.jsp">
          <br>
          <br>
          <div class="custoptions">
          <input type="radio" name="gender" value="male">MEN SECTION<br>
          <input type="radio" name="gender" value="female">LADIES SECTION<br>
          <input type="radio" name="gender" value="general">GENERAL SECTION<br>
          </div>
          <br>
          <br>
          <br>
          <div class="custoptions">
          <input type="radio" name="preference" value="discount">DISCOUNT<br>
          <input type="radio" name="preference" value="latest"> LATEST<br>
          </div>
          <br>
          <br>
          <center>
          <input type="submit" value="search">
          </center>
          <br>
          <br>
        </form>
        </center>
      </td>

      <td>
        <table class="products">
          <tr>


<%
String gender=request.getParameter("gender");
String preference=request.getParameter("preference");

String gen = " ";
String pref = " ";

if(gender.equals("male"))
 gen=" WHERE SEX = 'male'";
else if(gender.equals("female"))
 gen=" WHERE SEX = 'female'";
else if(gender.equals("general"))
 gen=" WHERE SEX = 'both'";

if(preference.equals("latest"))
 pref=" ORDER BY PRODDATE DESC";
else if(preference.equals("discount"))
 pref=" ORDER BY DISCOUNT DESC";

Connection conn=null;
Statement st=null;
ResultSet rs=null;
String image=null;

try
{
     String driver = "com.mysql.jdbc.Driver";
     String hostname = "localhost";
     String dbName = "EM";
     String url = "jdbc:mysql://" + hostname + ":3306/" + dbName;
     String uname = "Raktim";
     String password = "241";
     Class.forName(driver);

conn = DriverManager.getConnection(url, uname, password);
st=conn.createStatement();

String qry="SELECT * FROM EMPROD"+gen+pref;
rs=st.executeQuery(qry);
while(rs.next())
{
%>

            <td style="width: 50%; border-right: 1px solid black; border-bottom: 1px solid black;"><center>
              <img src="src/<%= rs.getString(1) %>" style="width: 50%;height:200px;">
            </center></td>
            <td style="padding-left: 5%; border-bottom: 1px solid black;">
              
              <b><div style="background-color: #000066; color: white; width: 85%; font-size: 30px; text-align: center;">
              <%= rs.getString(2) %></div></b><br>
              Arrival : <%= rs.getString(4) %>/10/2019<br>
              Price : Rs. <%= rs.getString(5) %>.00<br>
              Discount : <%= rs.getString(6) %> %<br>
            </td>
          </tr>
          <%
          }
          }catch(Exception e){System.out.println(e);}
          %>

        </table>
      </td>

    </tr>
  </table>

  <table class="footing">
    <tr class="footing header">
      <th width="25%">About</th>
      <th width="25%">Connect</th>
      <th width="25%">Business</th>
      <th width="25%">Help</th>
    </tr>
    <tr>
      <td>About Us</td>
      <td>Facebook</td>
      <td>Sell on E-mall</td>
      <td>Returns Centre</td>
    </tr>
    <tr>
      <td>Press Releases</td>
      <td>Twitter</td>
      <td>Become an Affiliate</td>
      <td>Help Desk</td>
    </tr>
    <tr>
      <td>Social Initiatives</td>
      <td>Instagram</td>
      <td>Advertise Your Products</td>
      <td>Download E-mall App</td>
    </tr>
  </table>

</body>

</html>