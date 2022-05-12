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
	<acme:input-money code="inventor.item.list.label.retailPrice" path="EUR"/>
	<acme:input-money code="inventor.item.list.label.retailPrice" path="USD"/>
	<acme:input-money code="inventor.item.list.label.retailPrice" path="GBP"/>
	<acme:input-money code="inventor.toolkit.list.label.computedPrice" path="computedPrice"/>
	<acme:input-url code="inventor.toolkit.list.label.link" path="link"/>
	<acme:button code="inventor.toolkit.form.button.item" action="/inventor/item/list-by-toolkitId?toolkitId=${toolkitId}"/>

	
</acme:form>