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
	<acme:input-textbox code="inventor.patronageReport.list.label.seqNumber" path="seqNumber"/>
	<acme:input-textbox code="inventor.patronageReport.list.label.creation" path="creation"/>
	<acme:input-textbox code="inventor.patronageReport.list.label.memorandum" path="memorandum"/>
	<acme:input-textarea code="inventor.patronageReport.list.label.optionalLink" path="optionalLink"/>
	<acme:input-checkbox code="inventor.patronageReport.form.confirm" path="confirm"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command,'show, update, delete' ) && confirm == true}">
			<acme:submit code="inventor.patronageReport.form.button.update" action="/inventor/patronage-report/update"/>
			<acme:submit code="inventor.patronageReport.form.button.delete" action="/inventor/patronage-report/delete"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.patronageReport.form.button.create" action="/inventor/patronage-report/create"/>
		</jstl:when>
	</jstl:choose>
</acme:form>