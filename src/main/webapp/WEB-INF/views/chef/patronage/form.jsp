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
	<acme:input-textbox code="chef.patronage.list.label.status" path="status" readonly="true"/>
	<acme:input-textbox code="chef.patronage.list.label.code" path="code" readonly="true"/>
	<acme:input-textarea code="chef.patronage.list.label.stuff" path="stuff" readonly="true"/>
	<acme:input-money code="chef.patronage.list.label.budget" path="budget" readonly="true"/>
	<acme:input-money code="chef.patronage.list.label.computedPrice" path="computedPrice" readonly="true"/>
	<acme:input-moment readonly="true" code="patron.patronage.list.label.creation-moment" path="creationMoment"/>
	<acme:input-moment readonly="true" code="patron.patronage.list.label.startDate" path="startDate"/>
	<acme:input-moment readonly="true" code="patron.patronage.list.label.endDate" path="endDate"/>
	<acme:input-url code="chef.patronage.list.label.optionalLink" path="optionalLink" readonly="true"/>
	<acme:input-textbox code="chef.patronage.list.label.published" path="published" readonly="true"/>

    
	<jstl:choose>
		<jstl:when test="${status=='PROPOSED'}">
			<acme:submit code="chef.patronage.form.button.accept" action="/chef/patronage/accept"/>
			<acme:submit code="chef.patronage.form.button.decline" action="/chef/patronage/denied"/>
		</jstl:when>
	</jstl:choose>

	

	<h1>PATRON</h1>
	<acme:input-textbox code="chef.patronage.patron.list.label.username" path="patronUsername" readonly = "true"/>
	<acme:input-textbox code="chef.patronage.patron.list.label.company" path="patron.company" readonly="true"/>
	<acme:input-textarea code="chef.patronage.patron.list.label.statement" path="patron.statement" readonly="true"/>
	<acme:input-url code="chef.patronage.patron.list.label.optionalLink" path="patron.optionalLink" readonly="true"/>
</acme:form>

