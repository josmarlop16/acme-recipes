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
	<acme:input-textbox code="any.peep.list.label.instantiationMoment" path="instantiationMoment"/>
	<acme:input-textbox code="any.peep.list.label.heading" path="heading"/>
	<acme:input-textbox code="any.peep.list.label.writer" path="writer"/>
	<acme:input-textbox code="any.peep.list.label.text" path="text"/>
	<acme:input-textbox code="any.peep.list.label.emailAddress" path="emailAddress"/>
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:input-checkbox code="any.peep.form.confirm" path="confirm"/>
		</jstl:when>
		</jstl:choose>
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="any.peep.form.button.create" action = "/any/peep/create"/>
		</jstl:when>
	</jstl:choose>
	
</acme:form>
