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

	<acme:input-money code="inventor.toolkit.list.label.retailPriceEUR" path="EUR"/>
	<acme:input-money code="inventor.toolkit.list.label.retailPriceUSD" path="USD"/>
	<acme:input-money code="inventor.toolkit.list.label.computedPrice" path="GBP"/>
	<acme:input-money code="inventor.toolkit.list.label.computedPrice" path="computedPrice"/>

	<acme:input-url code="inventor.toolkit.list.label.link" path="link"/>
	
	<jstl:choose>
	
		<jstl:when test="${acme:anyOf(command,'show' ) && published == true}">
			<acme:input-double code="inventor.toolkit.list.label.eur.price" path="EUR"/>
			<acme:input-double code="inventor.toolkit.list.label.usd.price" path="USD"/>
			<acme:input-double code="inventor.toolkit.list.label.gbp.price" path="GBP"/>
		</jstl:when>
		
		<jstl:when test="${published == false}">
		
		</jstl:when>
		
	</jstl:choose>
	
	<acme:input-select code="inventor.toolkit.list.label.published" path="published">
		<acme:input-option code="true" value="true" selected="${published == true}"/>
		<acme:input-option code="false" value="false" selected="${published == false }"/>
	</acme:input-select>	
	
	<jstl:choose>
	
		<jstl:when test="${acme:anyOf(command,'show, update, delete, publish' ) && published == false}">
			<acme:submit code="inventor.toolkit.form.button.publish" action="/inventor/toolkit/publish"/>
			<acme:submit code="inventor.toolkit.form.button.update" action="/inventor/toolkit/update"/>
			<acme:submit code="inventor.toolkit.form.button.delete" action="/inventor/toolkit/delete"/>
		</jstl:when>
		
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.toolkit.form.button.create" action="/inventor/toolkit/create"/>
		</jstl:when>
		
	</jstl:choose>
	
	<acme:button code="inventor.toolkit.form.button.item" action="/inventor/item/list-by-toolkitId?toolkitId=${toolkitId}"/>

</acme:form>