<!-- Author: Jia Chen -->
<!-- Description: draw bar chart used data from database -->

<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<openmrs:htmlInclude file="/scripts/jquery/plotly-latest.min.js" />
<!-- The external javascript library is not in the right path. Copy it to openmrs-core/webapp/src/main/webapp/WEB-INF/view/scripts/jquery -->

<p>
Patient Usage Bar Chart
</p>

<!-- Plots go in blank <div> elements. 
    You can size them in the plot layout,
    or give the div a size as shown here.
-->

<div id="myDiv" style="width: 480px; height: 800px;"></div>
<!-- draw bar chart-->
<script>
<!--sample data-->
var data = [{
  type: 'bar',
  x: [50,23,4,91,43,87,23,1,0,34,3,4,5,6,7,8,9,0,1,11,32,55,6],
  y: ['id 1','id 2','id 3','id 4','id 5','id 6','id 7','id 8','id 9','id 10','id 11','id 12','id 13','id 14','id 15','id 16','id 17','id 18','id 19','id 20','id 21','id 22','id 23'],
  orientation: 'h',

}];

Plotly.newPlot('myDiv', data);
	
</script>

<!-- draw detail table -->
<table border="1">

<script language="javascript" type="text/javascript">
<!--
<!--sample data-->
var myArray    = ["1","2","3","4","5"];
var id = ["1","1","1","1","1"];
var action = ["add","edit","void","add","add"];
var date = ["2000/1/1 13:00",
		"2000/1/1 13:05",
		"2000/1/1 7:55",
		"2000/1/1 8:05",
		"2000/1/1 13:00"];
  document.write("<tr><td>id "+ "</td>");
  document.write("<td>action"+ "</td>");
  document.write("<td>date"+ "</td>");
  document.write("<td>special"+ "</td></tr>");
for (var i=0; i<5; i++) {
  document.write("<tr><td>" + id[i] + "</td>");
  document.write("<td>" + action[i] + "</td>");
  document.write("<td>" + date[i] + "</td>");
	if(date[i] == "2000/1/1 7:55"){
		document.write("<td>" + "*" + "</td></tr>");
	}else{
		document.write("<td>" + " " + "</td></tr>");
	}
}

//--> </script>

</table>
<%@ include file="/WEB-INF/template/footer.jsp"%>
