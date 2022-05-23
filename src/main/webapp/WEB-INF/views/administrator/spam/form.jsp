<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="authenticated.spam.list.label.spamTerm" path="spamTerm"/>	
	<acme:input-integer readonly="true" code="authenticated.spam.list.label.threshold" path="threshold"/>
	<acme:input-select code="authenticated.spam.list.label.isStrong" path="isStrong">
		<acme:input-option code="true" value="true" selected="${accepted == true}"/>
		<acme:input-option code="false" value="false" selected="${accepted == false }"/>
	</acme:input-select>	
	<jstl:choose>
		<jstl:when test="${command == 'show'}">
			<acme:submit code="administrator.spam.form.button.update" action = "/administrator/spam/update"/>
		</jstl:when>
	</jstl:choose>

</acme:form> 