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
	
		<jstl:when test="${acme:anyOf(command,'show' ) && published == true}">
			<acme:input-textbox code="chef.toolkit.list.label.title" path="title" readonly="true"/>
			<acme:input-textbox code="chef.toolkit.list.label.code" path="code" readonly="true"/>
			<acme:input-textbox code="chef.toolkit.list.label.assembly-notes" path="assemblyNotes" readonly="true"/>
			<acme:input-textarea code="chef.toolkit.list.label.description" path="description" readonly="true"/>
			<acme:input-url code="chef.toolkit.list.label.link" path="link" readonly="true"/>
			<acme:input-money code="chef.toolkit.list.label.retailPriceEUR" path="EUR" readonly="true"/>
			<acme:input-money code="chef.toolkit.list.label.retailPriceUSD" path="USD" readonly="true"/>
			<acme:input-money code="chef.toolkit.list.label.retailPriceGBP" path="GBP" readonly="true"/> 
			<acme:input-money code="chef.toolkit.list.label.computedPrice" path="computedPrice" readonly="true"/>
			<acme:input-select  code="chef.toolkit.list.label.published" path="published" readonly="true">
				<acme:input-option code="true" value="true" selected="${published == true}"/>
				<acme:input-option code="false" value="false" selected="${published == false }"/>
		</acme:input-select>	
		</jstl:when>
		
		<jstl:when test="${published == false}">
			<acme:input-textbox code="chef.toolkit.list.label.title" path="title"/>
			<acme:input-textbox code="chef.toolkit.list.label.code" path="code"/>
			<acme:input-textbox code="chef.toolkit.list.label.assembly-notes" path="assemblyNotes"/>
			<acme:input-textarea code="chef.toolkit.list.label.description" path="description"/>
			<acme:input-url code="chef.toolkit.list.label.link" path="link"/>
			<acme:input-money code="chef.toolkit.list.label.retailPriceEUR" path="EUR" readonly="true"/>
			<acme:input-money code="chef.toolkit.list.label.retailPriceUSD" path="USD" readonly="true"/>
			<acme:input-money code="chef.toolkit.list.label.retailPriceGBP" path="GBP" readonly="true"/>
			<acme:input-money code="chef.toolkit.list.label.computedPrice" path="computedPrice" readonly="true"/>
			<acme:input-select code="chef.toolkit.list.label.published" path="published" readonly="true">
				<acme:input-option code="true" value="true" selected="${published == true}"/>
				<acme:input-option code="false" value="false" selected="${published == false }"/>
			</acme:input-select>	
		</jstl:when>
		
	</jstl:choose>

	<jstl:choose>
	
		<jstl:when test="${acme:anyOf(command,'show, update, delete, publish' ) && published == false}">
			<acme:submit code="chef.toolkit.form.button.publish" action="/chef/toolkit/publish"/>
			<acme:submit code="chef.toolkit.form.button.update" action="/chef/toolkit/update"/>
			<acme:submit code="chef.toolkit.form.button.delete" action="/chef/toolkit/delete"/>
		</jstl:when>
		
		<jstl:when test="${command == 'create'}">
			<acme:submit code="chef.toolkit.form.button.create" action="/chef/toolkit/create"/>
		</jstl:when>
		
	</jstl:choose>
	
	<jstl:choose>
		<jstl:when test="${command == 'show'}">
			<acme:button code="chef.toolkit.form.button.item" action="/chef/quantity/list?toolkitId=${toolkitId}"/>
		</jstl:when>
	</jstl:choose>

</acme:form>