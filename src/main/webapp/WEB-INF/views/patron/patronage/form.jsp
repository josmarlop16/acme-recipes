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
	<h1>PATRONAGE</h1>
	<acme:input-textbox code="patron.patronage.list.label.status" path="status"/>
	<acme:input-textbox code="patron.patronage.list.label.code" path="code"/>
	<acme:input-textarea code="patron.patronage.list.label.stuff" path="stuff"/>
	<acme:input-money code="patron.patronage.list.label.budget" path="budget"/>
	<acme:input-moment code="patron.patronage.list.label.periodOfTime" path="periodOfTime"/>
	<acme:input-url code="patron.patronage.list.label.optionalLink" path="optionalLink"/>
	<acme:input-select code="patron.patronage.list.label.published" path="published">
		<acme:input-option code="false" value="false" selected="${published == false }"/>
		<acme:input-option code="true" value="true" selected="${published == true}"/>
	</acme:input-select>	
	
	<acme:input-select code="patron.patronage.list.label.inventor" path="inventor">
	
		<jstl:forEach items="${inventors}" var="i">
			<acme:input-option code="1" value="${i}" selected="${inventor == i}"/>
			<acme:input-option code="${inventor.getUserAccount().getUsername()}" value="${inventor.getId()}" selected="${ inventor.getId() == inventId }"/>
		</jstl:forEach>
	
	</acme:input-select>
	
	<h1>INVENTOR</h1>
	<acme:input-textbox code="patron.patronage.inventor.list.label.company" path="inventor.company"/>
	<acme:input-textarea code="patron.patronage.inventor.list.label.statement" path="inventor.statement"/>
	<acme:input-url code="patron.patronage.inventor.list.label.optionalLink" path="inventor.optionalLink"/>
	 
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="patron.patronage.form.button.create" action = "/patron/patronage/create"/>
		</jstl:when>
	</jstl:choose>
</acme:form>

