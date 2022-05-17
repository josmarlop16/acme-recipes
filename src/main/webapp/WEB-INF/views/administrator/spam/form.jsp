<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="authenticated.spam.list.label.spamTerm" path="spamTerm"/>	
	<acme:input-integer code="authenticated.spam.list.label.threshold" path="threshold"/>
	<acme:input-select code="authenticated.spam.list.label.isStrong" path="isStrong">
		<acme:input-option code="true" value="true" selected="${accepted == true}"/>
		<acme:input-option code="false" value="false" selected="${accepted == false }"/>
	</acme:input-select>	
	<acme:message code="administrator.spam.form.confirm.message"/>
	<acme:input-checkbox code="administrator.spam.form.confirm" path="confirm"/>

	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="administrator.spam.form.button.create" action = "/administrator/spam/create"/>
		</jstl:when>
	</jstl:choose>

</acme:form> 