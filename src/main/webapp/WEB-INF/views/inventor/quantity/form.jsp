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
			<acme:input-integer code="inventor.quantity.form.label.quantity" path="quantity"/>
			<acme:input-textbox code="inventor.item.form.label.name" path="item.name" readonly="true"/>			
			<acme:input-textbox code="inventor.item.form.label.code" path="item.code" readonly="true"/>
			<acme:input-textbox code="inventor.item.form.label.technology" path="item.technology" readonly="true"/>
			<acme:input-textarea code="inventor.item.form.label.description" path="item.description" readonly="true"/>
			<acme:input-textbox code="inventor.item.form.label.type" path="item.type" readonly="true"/>
			<acme:input-textbox code="inventor.item.form.label.link" path="item.link" readonly="true"/>
			<acme:input-money code="inventor.item.form.label.price" path="item.price" readonly="true"/>
			<acme:input-money code="inventor.quantity.list.label.computedPrice" path="computedPrice" readonly="true"/>
			<acme:submit code="inventor.item.form.button.update" action="/inventor/quantity/update"/>
			<acme:submit code="inventor.item.form.button.delete" action="/inventor/quantity/delete"/>		
		</jstl:when>
		
		<jstl:when test="${acme:anyOf(command,'show, update, delete' ) && published == true}">
			<acme:input-integer code="inventor.quantity.form.label.quantity" path="quantity" readonly="true"/>
			<acme:input-textbox code="inventor.item.form.label.name" path="item.name" readonly="true"/>			
			<acme:input-textbox code="inventor.item.form.label.code" path="item.code" readonly="true"/>
			<acme:input-textbox code="inventor.item.form.label.technology" path="item.technology" readonly="true"/>
			<acme:input-textarea code="inventor.item.form.label.description" path="item.description" readonly="true"/>
			<acme:input-textbox code="inventor.item.form.label.type" path="item.type" readonly="true"/>
			<acme:input-textbox code="inventor.item.form.label.link" path="item.link" readonly="true"/>
			<acme:input-money code="inventor.item.form.label.price" path="item.price" readonly="true"/>
			<acme:input-money code="inventor.quantity.list.label.computedPrice" path="computedPrice" readonly="true"/>
				
		</jstl:when>
		
		<jstl:when test="${command == 'create'}">
			<acme:input-integer code="inventor.quantity.form.label.quantity" path="quantity"/>		
			<acme:input-select code="inventor.quantity.form.label.select" path="item.name">
				<jstl:forEach items="${items}" var="item">
					<acme:input-option code="${item.name}" value="${item.name}"/>
				</jstl:forEach>
			</acme:input-select>
			<acme:submit code="inventor.quantity.form.button.create" action="/inventor/quantity/create?toolkitId=${toolkitId}"/>			
		</jstl:when>	
		
	</jstl:choose>
	
</acme:form>
