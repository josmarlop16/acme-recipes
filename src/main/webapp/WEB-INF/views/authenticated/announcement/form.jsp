<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-moment code="authenticated.announcements.list.label.creation" path="creation"/>
	<acme:input-textbox code="authenticated.announcements.form.label.title" path="title"/>	
	<acme:input-textarea code="authenticated.announcements.form.label.body" path="body"/>
	<acme:input-textarea code="authenticated.announcements.list.label.critical" path="critical"/>
	<acme:input-url code="authenticated.announcements.form.label.link" path="optionalLink"/>
</acme:form>