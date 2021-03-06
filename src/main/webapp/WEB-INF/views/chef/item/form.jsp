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

	<jstl:choose>
	
		<jstl:when test="${published == false}">
			<acme:input-textbox code="chef.item.list.label.name" path="name"/>
			<acme:input-textbox code="chef.item.list.label.code" path="code"/>
			<acme:input-textbox code="chef.item.list.label.technology" path="technology"/>
			<acme:input-textarea code="chef.item.list.label.description" path="description"/>
			<acme:input-money code="chef.item.list.label.retailPrice" path="retailPrice"/>
			<acme:input-money code="chef.item.list.label.computedPrice" path="computedPrice" readonly="true"/>
			<acme:input-url code="chef.item.list.label.link" path="link"/>	
			<acme:input-select code="chef.item.list.label.type" path="type">	
				<acme:input-option code="component" value="COMPONENT" selected="${type == 'COMPONENT'}"/>
				<acme:input-option code="tool" value="TOOL" selected="${type == 'TOOL' }"/>
			</acme:input-select>
			<acme:input-textbox code="chef.item.list.label.published" path="published" readonly="true"/>	
		</jstl:when>
		
		<jstl:when test="${published == true}">
			<acme:input-textbox code="chef.item.list.label.name" path="name" readonly="true"/>
			<acme:input-textbox code="chef.item.list.label.code" path="code" readonly="true"/>
			<acme:input-textbox code="chef.item.list.label.technology" path="technology" readonly="true"/>
			<acme:input-textarea code="chef.item.list.label.description" path="description" readonly="true"/>
			<acme:input-money code="chef.item.list.label.retailPrice" path="retailPrice" readonly="true"/>
			<acme:input-money code="chef.item.list.label.computedPrice" path="computedPrice" readonly="true"/>
			<acme:input-url code="chef.item.list.label.link" path="link" readonly="true"/>	
			<acme:input-select code="chef.item.list.label.type" path="type" readonly="true">	
				<acme:input-option code="component" value="COMPONENT" selected="${type == 'COMPONENT'}"/>
				<acme:input-option code="tool" value="TOOL" selected="${type == 'TOOL' }"/>
			</acme:input-select>
			<acme:input-textbox code="chef.item.list.label.published" path="published" readonly="true"/>
		</jstl:when>
		
	</jstl:choose>
	
	<jstl:choose>
	
		<jstl:when test="${acme:anyOf(command,'show, update, delete, publish' ) && published == false}">
			<acme:submit code="chef.item.form.button.publish" action="/chef/item/publish"/>
			<acme:submit code="chef.item.form.button.update" action="/chef/item/update"/>
			<acme:submit code="chef.item.form.button.delete" action="/chef/item/delete"/>
		</jstl:when>
		
		<jstl:when test="${command == 'create'}">
			<acme:submit code="chef.item.form.button.create" action="/chef/item/create"/>
		</jstl:when>
		
	</jstl:choose>

</acme:form>