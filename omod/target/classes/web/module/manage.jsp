<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<p>Hello ${user.systemId}!</p>
<p>A new patient usage is saved to database now</p>
<p>${new_patient_info}</p>
<p>Display the patient usage according to the patientUsage_id</p>
<p>${one_patient_usage_from_database}</p>
<p>Patient usages between 2016-5-14 and 2016-5-15 are:</p>
<c:forEach items="${patient_usages}" var="patient_usage">
    <tr>
        <td><c:out value="${patient_usage.user_id}"/></td>
        <td><c:out value="${patient_usage.timestamp}"/></td>
    </tr>
</c:forEach>
<p>Patient usages between 2016-5-12 00:36 and 2016-5-15 14:29 are: </p>
<c:forEach items="${patient_usages_2}" var="patient_usage">
    <tr>
        <td><c:out value="${patient_usage.user_id}"/></td>
        <td><c:out value="${patient_usage.timestamp}"/></td>
    </tr>
</c:forEach>




<%@ include file="/WEB-INF/template/footer.jsp"%>