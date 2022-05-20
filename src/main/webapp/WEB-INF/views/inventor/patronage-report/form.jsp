<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.patronageReport.list.label.seqNumber" path="seqNumber"/>
	<acme:input-textbox code="inventor.patronageReport.list.label.creation" path="creation"/>
	<acme:input-textbox code="inventor.patronageReport.list.label.memorandum" path="memorandum"/>
	<acme:input-url code="inventor.patronageReport.list.label.optionalLink" path="optionalLink"/>
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:input-checkbox code="inventor.patronageReport.form.confirm" path="confirm"/>
			<acme:input-select code="inventor.patronageReport.list.label.patronage" path="patronageId">
			<jstl:forEach items="${myPatronages}" var="patronage">
				<acme:input-option code="${patronage.getCode()}" value="${patronage.getId()}" selected="${patronage.getId() == patronageId }"/>
			</jstl:forEach>
			</acme:input-select>
			<acme:submit code="inventor.patronageReport.form.button.create" action="/inventor/patronage-report/create"/>
		</jstl:when>
	</jstl:choose>
	
	
	<acme:input-textbox code="inventor.patronageReport.list.label.patronageCode" path="code" readonly="true"/>
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:input-checkbox code="inventor.patronageReport.form.confirm" path="confirm"/>
			<acme:submit code="inventor.patronageReport.form.button.create" action="/inventor/patronage-report/create"/>
		</jstl:when>
	</jstl:choose>
</acme:form>