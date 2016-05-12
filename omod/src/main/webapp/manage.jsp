<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<openmrs:htmlInclude file="/scripts/jquery/plotly-latest.min.js" />


<p>
    Here's a simple Plotly plot - 
    <a href="http://bit.ly/1Or9igj">plotly.js documentation</a>
</p>

<!-- Plots go in blank <div> elements. 
    You can size them in the plot layout,
    or give the div a size as shown here.
-->

<div id="myDiv" style="width: 480px; height: 800px;"><!-- Plotly chart will be drawn inside this DIV --></div>
<p><a href="a.jsp">HTML Images</a> is a link to a page on this website.</p>
<script>
var data = [{
  type: 'bar',
  x: [50,23,4,91,43,87,23,1,0,34,3,4,5,6,7,8,9,0,1,11],
  y: ['id 1','id 2','id 3','id 4','id 5','id 6','id 7','id 8','id 9','id 10','id 11','id 12','id 13','id 14','id 15','id 16','id 17','id 18','id 19','id 20'],
  orientation: 'h',

}];

Plotly.newPlot('myDiv', data);
	


</script>

<p>Hello ${user.systemId}!</p>
<%@ include file="/WEB-INF/template/footer.jsp"%>
