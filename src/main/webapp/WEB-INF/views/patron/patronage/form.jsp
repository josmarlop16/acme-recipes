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
	<h1>Patronage</h1>
	<acme:input-textbox code="patron.patronage.list.label.status" path="status" readonly = "true"/>
	<acme:input-textbox code="patron.patronage.list.label.code" path="code"/>
	<acme:input-textarea code="patron.patronage.list.label.stuff" path="stuff"/>
	<acme:input-money code="patron.patronage.list.label.budget" path="budget"/>
	<acme:input-moment code="patron.patronage.list.label.periodOfTime" path="periodOfTime"/>
	<acme:input-url code="patron.patronage.list.label.optionalLink" path="optionalLink"/>
	<acme:input-select code="patron.patronage.list.label.published" path="published">
		<acme:input-option code="false" value="false" selected="${published == false }"/>
		<acme:input-option code="true" value="true" selected="${published == true}"/>
	</acme:input-select>	
	

	<acme:input-select code="patron.patronage.list.label.inventor" path="inventorId">
		<jstl:forEach items="${inventors}" var="inventor">
			<acme:input-option code="${inventor.getUserAccount().getUsername()}" value="${inventor.getId()}" selected="${ inventor.getId() == inventId }"/>
		</jstl:forEach>
	</acme:input-select>

	<jstl:choose>
		<jstl:when test="${command == 'show'}">
			<h1>Inventor</h1>
			<acme:input-textbox code="patron.patronage.inventor.list.label.username" path="inventorUsername" readonly = "true"/>
			<acme:input-textbox code="patron.patronage.inventor.list.label.company" path="inventor.company" readonly = "true"/>
			<acme:input-textarea code="patron.patronage.inventor.list.label.statement" path="inventor.statement" readonly = "true"/>
			<acme:input-url code="patron.patronage.inventor.list.label.optionalLink" path="inventor.optionalLink" readonly = "true"/>
		</jstl:when>
	</jstl:choose>
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="patron.patronage.form.button.create" action = "/patron/patronage/create"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command,'show, update, delete, publish') && published == false}"> 
			<acme:submit code="patron.patronage.form.button.update" action="/patron/patronage/update"/>
			<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete"/>
			<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish"/>
		</jstl:when>
	</jstl:choose>
</acme:form>

