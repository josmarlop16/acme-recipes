<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox readonly="true" code="authenticated.currency.list.label.name" path="name"/>	
	<acme:input-select code="authenticated.currency.list.label.isDefault" path="isDefault">
		<acme:input-option code="true" value="true" selected="${isDefault == true}"/>
		<acme:input-option code="false" value="false" selected="${isDefault == false }"/>
	</acme:input-select>
	
	<acme:input-select code="authenticated.currency.list.label.accepted" path="accepted">
		<acme:input-option code="true" value="true" selected="${accepted == true}"/>
		<acme:input-option code="false" value="false" selected="${accepted == false }"/>
	</acme:input-select>	

	<jstl:choose>
		<jstl:when test="${command == 'show'}">
			<acme:submit code="administrator.currency.form.button.update" action = "/administrator/currency/update"/>
		</jstl:when>
	</jstl:choose>

</acme:form> 