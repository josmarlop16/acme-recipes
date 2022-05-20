<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="authenticated.currency.list.label.name" path="name"/>	
	<acme:input-select code="authenticated.currency.list.label.isDefault" path="isDefault">
		<acme:input-option code="true" value="true" selected="${isDefault == true}"/>
		<acme:input-option code="false" value="false" selected="${isDefault == false }"/>
	</acme:input-select>
	<acme:input-select code="authenticated.currency.list.label.accepted" path="accepted">
		<acme:input-option code="true" value="true" selected="${accepted == true}"/>
		<acme:input-option code="false" value="false" selected="${accepted == false }"/>
	</acme:input-select>	
	<acme:message code="administrator.currency.form.confirm.message"/>
	<acme:input-checkbox code="administrator.currency.form.confirm" path="confirm"/>

	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="administrator.currency.form.button.create" action = "/administrator/currency/create"/>
		</jstl:when>
	</jstl:choose>

</acme:form> 