<%-- 
    Document   : Order_Chart
    Created on : May 12, 2016, 9:09:56 AM
    Author     : aura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title>
            Order History Log Chart
        </title>
        <link rel="stylesheet" type="text/css" href="site.css">
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
 
 a{
     padding-left: 500px;
 }
        </style>
    </head>
    <body>
        <h1> <strong> Order History Log Chart </strong></h1>
        
       <canvas id="myCanvas" width="1000" height="600" >
        </canvas>
        
        <script>
   var canvas = document.getElementById("myCanvas");
        var ctx = canvas.getContext("2d");
        
        var scores = [100, 30, 50, 70];
        var width = 100;
        var currentX = 50;
        var currentY = 120;
        
        ctx.fillStyle = "black";
        
       
        for (i = 1; i <= 4; i++) {
            ctx.strokeStyle = "black";
            ctx.beginPath();
            ctx.moveTo(currentX, currentY);
            ctx.lineTo(currentX, currentY + 60);
            ctx.lineTo(currentX + 700, currentY + 60);
            ctx.lineTo(currentX + 700, currentY);
            ctx.lineTo(currentX, currentY);
            ctx.moveTo(currentX + 60, currentY);
            ctx.lineTo(currentX + 60,currentY + 60);
            ctx.moveTo(currentX + 240, currentY);
            ctx.lineTo(currentX + 240, currentY + 60);
            ctx.stroke();
            currentY += 60;
        }
        
        var b1X = 55;
        var b2X = 115;
        var b3X = 295;
        currentY = 165;
        ctx.font = "30px Arial";
        
        ctx.fillText("ID#", b1X, currentY, 50);
        ctx.fillText("Action", b2X, currentY, 170);
        ctx.fillText("Time", b3X, currentY, 250);
        
        currentY += 60;
        
        ctx.fillText("3", b1X, currentY, 50);
        ctx.fillText("Void", b2X, currentY, 170);
        ctx.fillText("May 11, 2016 8:00AM", b3X, currentY, 250);
        
        currentY += 60;
        
        ctx.fillText("3", b1X, currentY, 50);
        ctx.fillText("Add", b2X, currentY, 170);
        ctx.fillText("May 9, 2016 7:00PM", b3X, currentY, 250);
        
        currentY += 60;
        
        ctx.fillText("1", b1X, currentY, 50);
        ctx.fillText("Void", b2X, currentY, 170);
        ctx.fillText("May 5, 2016 2:00PM", b3X, currentY, 250);

    
  </script>
      
        
        <form action="index.jsp">
            <button class="btn" id="btn1" > <strong> Home </strong> </button>
        </form>
        
      

    </body>
</html>