<%-- 
    Document   : Order_Graph
    Created on : May 12, 2016, 3:16:27 AM
    Author     : aura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            Order History Histogram
        </title>
  
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
        <h1> <strong> Order History </strong></h1>
        
         <h2> Month:  May     Year:  2016      </h2>
         
         <canvas id="myCanvas" width="1000" height="500" >
        </canvas>
        
        <script>
 
        var canvas = document.getElementById("myCanvas");
        var ctx = canvas.getContext("2d");
        
        var scores = [250, 100, 180, 70];
        var width = 100;
        var currentX = 500;
        var base = 100;
        
        
        
        ctx.fillStyle = "black";
        
        
        for (var i = 0; i <= 400; i += 50){
            ctx.font = "15px Arial";
            ctx.fillText(i, 445, canvas.height - 30 - i, 40);
        }
        
        for (var i = 0; i < scores.length; i++){
            var h = scores[i];
            ctx.fillRect(currentX, canvas.height - h - 30, width, h);
            ctx.font = "30px Arial";
            ctx.fillText("User " + (i+1), currentX, canvas.height, 100);

            currentX += width + 25;
        }
        
        ctx.fillText("Logins", 345, canvas.height - 230, 100);
        
        ctx.beginPath();
        ctx.moveTo(490, canvas.height - 430);
        ctx.lineTo(490, canvas.height - 30);
        ctx.lineTo(currentX, canvas.height - 30);
        ctx.strokeStyle = "black";
        ctx.stroke();

    
  </script>
        
        <form action="Order_Chart.jsp">
            <button class="btn" id="btn1" > <strong> Log Chart </strong> </button>
        </form>


    </body>
</html>