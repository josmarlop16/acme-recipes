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
	<acme:input-textbox code="inventor.item.list.label.name" path="name"/>
	<acme:input-textbox code="inventor.item.list.label.code" path="code"/>
	<acme:input-textbox code="inventor.item.list.label.technology" path="technology"/>
	<acme:input-textarea code="inventor.item.list.label.description" path="description"/>
	<acme:input-money code="inventor.item.list.label.retailPrice" path="retailPrice"/>

	<acme:input-money code="inventor.item.list.label.computedPrice" path="computedPrice"/>
	<acme:input-url code="inventor.item.list.label.link" path="link"/>	

	<acme:input-select code="inventor.item.list.label.type" path="type">
		<acme:input-option code="component" value="COMPONENT" selected="${type == 'COMPONENT'}"/>
		<acme:input-option code="tool" value="TOOL" selected="${type == 'TOOL' }"/>
	</acme:input-select>
	<acme:input-url code="inventor.item.list.label.link" path="link"/>
	<acme:input-select code="inventor.item.list.label.published" path="published">
		<acme:input-option code="true" value="true" selected="${published == true}"/>
		<acme:input-option code="false" value="false" selected="${published == false }"/>
	</acme:input-select>	
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command,'show, update, delete, publish' ) && published == false}">
			<acme:submit code="inventor.item.form.button.publish" action="/inventor/item/publish"/>
			<acme:submit code="inventor.item.form.button.update" action="/inventor/item/update"/>
			<acme:submit code="inventor.item.form.button.delete" action="/inventor/item/delete"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.item.form.button.create" action="/inventor/item/create"/>
		</jstl:when>
	</jstl:choose>

</acme:form>