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

<div id="myDiv" style="width: 480px; height: 400px;"><!-- Plotly chart will be drawn inside this DIV --></div>
<p><a href="a.jsp">HTML Images</a> is a link to a page on this website.</p>
<script>
var data = [{
  type: 'bar',
  x: [20, 14, 23,100,200,300,400,34,65,123,678,85],
  y: ['giraffes', 'orangutans', 'monkeys', 'fawf','fsad','rewd','bes','g4s','b64e','h5es','fse4','he64ss'],
  orientation: 'h'
}];

Plotly.newPlot('myDiv', data);
	


</script>

<p>Hello ${user.systemId}!</p>
<%@ include file="/WEB-INF/template/footer.jsp"%>
