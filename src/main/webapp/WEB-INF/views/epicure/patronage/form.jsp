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
	<acme:input-textbox code="epicure.patronage.list.label.status" path="status" readonly = "true"/>
	<acme:input-textbox code="epicure.patronage.list.label.code" path="code"/>
	<acme:input-textarea code="epicure.patronage.list.label.stuff" path="stuff"/>
	<acme:input-money code="epicure.patronage.list.label.budget" path="budget"/>

	<acme:input-moment readonly="true" code="epicure.patronage.list.label.creation-moment" path="creationMoment"/>
	<acme:input-moment readonly="true" code="epicure.patronage.list.label.startDate" path="startDate"/>
	<acme:input-moment readonly="true" code="epicure.patronage.list.label.endDate" path="endDate"/>
	
	<acme:input-url code="epicure.patronage.list.label.optionalLink" path="optionalLink"/>
	<acme:input-textbox readonly="true" code="epicure.patronage.list.label.published" path="published" />

	

	<acme:input-select code="epicure.patronage.list.label.chef" path="chefId">
		<jstl:forEach items="${chefs}" var="chef">
			<acme:input-option code="${chef.getUserAccount().getUsername()}" value="${chef.getId()}" selected="${ chef.getId() == inventId }"/>
		</jstl:forEach>
	</acme:input-select>

	<jstl:choose>
		<jstl:when test="${command == 'show'}">
			<h1>Chef</h1>
			<acme:input-textbox code="epicure.patronage.chef.list.label.username" path="chefUsername" readonly = "true"/>
			<acme:input-textbox code="epicure.patronage.chef.list.label.company" path="chef.company" readonly = "true"/>
			<acme:input-textarea code="epicure.patronage.chef.list.label.statement" path="chef.statement" readonly = "true"/>
			<acme:input-url code="epicure.patronage.chef.list.label.optionalLink" path="chef.optionalLink" readonly = "true"/>
		</jstl:when>
	</jstl:choose>
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="epicure.patronage.form.button.create" action = "/epicure/patronage/create"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command,'show, update, delete, publish') && published == false}"> 
			<acme:submit code="epicure.patronage.form.button.update" action="/epicure/patronage/update"/>
			<acme:submit code="epicure.patronage.form.button.delete" action="/epicure/patronage/delete"/>
			<acme:submit code="epicure.patronage.form.button.publish" action="/epicure/patronage/publish"/>
		</jstl:when>
	</jstl:choose>
</acme:form>

