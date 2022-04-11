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
	<acme:input-textbox code="inventor.toolkit.list.label.title" path="title"/>
	<acme:input-textbox code="inventor.toolkit.list.label.code" path="code"/>
	<acme:input-textbox code="inventor.toolkit.list.label.assembly-notes" path="assemblyNotes"/>
	<acme:input-textarea code="inventor.toolkit.list.label.description" path="description"/>
	<acme:input-url code="inventor.toolkit.list.label.link" path="link"/>
	<jstl:forEach items="${items}" var="item">
		<jstl:out value="${item.name}"></jstl:out>
		<jstl:out value="${item.retailPrice.currency} ${item.retailPrice.amount}"></jstl:out>
		<jstl:out value="${item.type}"></jstl:out>
		<acme:button code="inventor.toolkit.form.button.item" action="/authenticated/item/show?id=${item.id}"/>	
	</jstl:forEach>	
	
</acme:form>