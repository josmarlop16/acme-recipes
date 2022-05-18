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
	<acme:input-textbox code="any.chirp.list.label.creationMoment" path="creationMoment"/>
	<acme:input-textbox code="any.chirp.list.label.title" path="title"/>
	<acme:input-textbox code="any.chirp.list.label.author" path="author"/>
	<acme:input-textbox code="any.chirp.list.label.body" path="body"/>
	<acme:input-textbox code="any.chirp.list.label.emailAddress" path="emailAddress"/>
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:input-checkbox code="any.chirp.form.confirm" path="confirm"/>
		</jstl:when>
		</jstl:choose>
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="any.chirp.form.button.create" action = "/any/chirp/create"/>
		</jstl:when>
	</jstl:choose>
	
</acme:form>
