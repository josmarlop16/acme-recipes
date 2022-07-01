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
		   
		<jstl:when test="${acme:anyOf(command,'show, update, delete' ) && published == false}">
			<acme:input-integer code="chef.quantity.form.label.quantity" path="quantity"/>
			<acme:input-textbox code="chef.item.form.label.name" path="item.name" readonly="true"/>			
			<acme:input-textbox code="chef.item.form.label.code" path="item.code" readonly="true"/>
			<acme:input-textbox code="chef.item.form.label.technology" path="item.technology" readonly="true"/>
			<acme:input-textarea code="chef.item.form.label.description" path="item.description" readonly="true"/>
			<acme:input-textbox code="chef.item.form.label.type" path="item.type" readonly="true"/>
			<acme:input-textbox code="chef.item.form.label.link" path="item.link" readonly="true"/>
			<acme:input-money code="chef.item.form.label.price" path="item.price" readonly="true"/>
			<acme:input-money code="chef.quantity.list.label.computedPrice" path="computedPrice" readonly="true"/>
			<acme:submit code="chef.item.form.button.update" action="/chef/quantity/update"/>
			<acme:submit code="chef.item.form.button.delete" action="/chef/quantity/delete"/>		
		</jstl:when>
		
		<jstl:when test="${acme:anyOf(command,'show, update, delete' ) && published == true}">
			<acme:input-integer code="chef.quantity.form.label.quantity" path="quantity" readonly="true"/>
			<acme:input-textbox code="chef.item.form.label.name" path="item.name" readonly="true"/>			
			<acme:input-textbox code="chef.item.form.label.code" path="item.code" readonly="true"/>
			<acme:input-textbox code="chef.item.form.label.technology" path="item.technology" readonly="true"/>
			<acme:input-textarea code="chef.item.form.label.description" path="item.description" readonly="true"/>
			<acme:input-textbox code="chef.item.form.label.type" path="item.type" readonly="true"/>
			<acme:input-textbox code="chef.item.form.label.link" path="item.link" readonly="true"/>
			<acme:input-money code="chef.item.form.label.price" path="item.price" readonly="true"/>
			<acme:input-money code="chef.quantity.list.label.computedPrice" path="computedPrice" readonly="true"/>
				
		</jstl:when>
		
		<jstl:when test="${command == 'create'}">
			<acme:input-integer code="chef.quantity.form.label.quantity" path="quantity"/>	
				
			<acme:input-select code="chef.quantity.form.label.select" path="itemId">
				<jstl:forEach items="${items}" var="item">
					<acme:input-option code="${item.getName()}" value="${item.getId()}" selected="${item.getId() == itemId}"/>
				</jstl:forEach>
			</acme:input-select>
			<acme:submit code="chef.quantity.form.button.create" action="/chef/quantity/create?toolkitId=${toolkitId}"/>			
		</jstl:when>	
	</jstl:choose>
	
</acme:form>
