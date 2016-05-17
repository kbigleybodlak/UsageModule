<%-- 
    Document   : index
    Created on : May 11, 2016, 9:17:07 PM
    Author     : aura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OpenMRS Usage Data Home</title>
        
        <style>
            h1{
                font-family: fantasy;
                font-style: oblique;
                font-size: 50px;
                color: white;
                background-color: black;
                padding: 20px;
                text-align: center;
            }
            
            body{
                 background-color: activecaption;
            }
           

 .btn{
     width: 500px;
     height: 100px;
     background-color: cadetblue;
     font-size: 30px;
 }
 
 form {
     padding-left: 500px;
     padding-top: 30px;
 }
 
        </style>
        
    </head>
    <body>
        <h1> <strong> OpenMRS Home Page </strong></h1>
        
        <form action="Patient_Graph.jsp">
            <button class="btn" id="btn1" > <strong> Patient Usage History </strong> </button>
        </form>
        
        
        <form action="Order_Graph.jsp">
            <button class="btn" id="btn2" > <strong> Order Usage History </strong> </button>
        </form>
        
        <form action="Visit_Graph.jsp">
            <button class="btn" id="btn3" > <strong> Visit Usage History </strong> </button>
        </form>
        
        <form action="Suspicious_Activity.html">
            <button class="btn" id="btn4" > <strong> Suspicious Activity </strong> </button>
        </form>
    </body>
</html>
