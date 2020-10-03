<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="shopError.jsp"%>
<%@page import="java.sql.*"%>

<html lang="en">

<head>

  <meta charset="UTF-8">
  <meta name="description" content="E-mall WebApp">
  <meta name="author" content="raktimhalder241">
  <meta name="keywords" content="jsp, html, css, js">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title> Error!!! </title>
  <link rel="stylesheet" type="text/css" href="product.css">

  <script>
    function home()
    {
      window.location.href = "home.jsp";
    }

    function login()
    {
        var r = confirm("Are You Sure You Want to Log In?");
        if (r == true)
        {
            window.location.href = "loginForm.jsp";
        }
    }

    window.location.hash="logged-out";
    window.onhashchange=function()
    {
      window.location.hash="logged-out";alert("You have Successfully Logged Out. Session Expired. Cannot Go Back !!!");
    }

  </script>

</head>

<body>

  <%
    session.removeAttribute("personalize");
    session.invalidate();
  %>

  <table class="toolbar" style="height: 75%">
    <tr>

      <td style="width: 50%; padding-top: 10px;">
        <center>
        <form style="width: 40%; height: 60%; background-color: yellow; border:1px solid black;">
        <div style="line-height:50%;"><br></div>
        You Have Logged Out
        <div style="line-height:50%;"><br></div>
        Thanks for Shopping !!!
        <div style="line-height:50%;"><br></div>
        </form>
        <br>
        <button type="button" onclick="home()">Home Page</button>
        <button type="button" onclick="login()">Log In</button>
        </center>
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