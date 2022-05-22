<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.quantity.list.label.quantity" path="quantity" width="30%"/>
	<acme:list-column code="inventor.item.list.label.code" path="item.code" width="30%"/>
	<acme:list-column code="inventor.item.form.label.name" path="item.name" width="50%"/>	
	
</acme:list>

<acme:button  test="${showCreate}" code="inventor.quantity.list.button.create" action="/inventor/quantity/create?toolkitId=${toolkitId}"/>

