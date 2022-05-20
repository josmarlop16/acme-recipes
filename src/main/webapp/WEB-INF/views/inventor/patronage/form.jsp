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
	<acme:input-textbox code="inventor.patronage.list.label.status" path="status"/>
	<acme:input-textbox code="inventor.patronage.list.label.code" path="code"/>
	<acme:input-textarea code="inventor.patronage.list.label.stuff" path="stuff"/>
	<acme:input-money code="inventor.patronage.list.label.budget" path="budget"/>
	<acme:input-money code="inventor.patronage.list.label.computedPrice" path="computedPrice"/>
	<acme:input-moment code="inventor.patronage.list.label.periodOfTime" path="periodOfTime"/>
	<acme:input-url code="inventor.patronage.list.label.optionalLink" path="optionalLink"/>
	<acme:input-textbox code="inventor.patronage.list.label.published" path="published" />

    
	<jstl:choose>
		<jstl:when test="${status=='PROPOSED'}">
			<acme:submit code="inventor.patronage.form.button.accept" action="/inventor/patronage/accept"/>
			<acme:submit code="inventor.patronage.form.button.decline" action="/inventor/patronage/denied"/>
		</jstl:when>
	</jstl:choose>

	

	<h1>PATRON</h1>
	<acme:input-textbox code="inventor.patronage.patron.list.label.company" path="patron.company"/>
	<acme:input-textarea code="inventor.patronage.patron.list.label.statement" path="patron.statement"/>
	<acme:input-url code="inventor.patronage.patron.list.label.optionalLink" path="patron.optionalLink"/>
</acme:form>

