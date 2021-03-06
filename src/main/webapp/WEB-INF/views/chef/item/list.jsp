<%--
- list.jsp
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

<acme:list>
	<acme:list-column code="chef.item.list.label.name" path="name" width="15%"/>
	<acme:list-column code="chef.item.list.label.code" path="code" width="20%"/>
	<acme:list-column code="chef.item.list.label.technology" path="technology" width="10%"/>
	<acme:list-column code="chef.item.list.label.description" path="description" width="20%"/>
	<acme:list-column code="chef.item.list.label.retailPrice" path="retailPrice" width="10%"/>
	<acme:input-textbox code="chef.item.list.label.technology" path="technology"/>
	<acme:list-column code="chef.item.list.label.published" path="published" width="10%"/>
	<acme:list-column code="chef.item.list.label.link" path="link" width="60%"/>
</acme:list>
<acme:button code="chef.item.form.button.create" action="/chef/item/create"/>