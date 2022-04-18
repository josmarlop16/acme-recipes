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

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	
	<tr> 
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-of-components"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfComponents}"/>
		</td>
	</tr> 
	<tr> 
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-of-tools"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfTools}"/>
		</td>
	</tr>
	<tr> 
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-of-patronages"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPatronages}"/>
		</td>
	</tr> 
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-of-patronages-groupedby-status"/>
		</th>
		<td>
			<jstl:forEach var="entry" items="${totalNumberOfPatronagesGroupedByPatronageStatus}">
				<acme:print value="${entry.key}:${entry.value} "/>	
			</jstl:forEach>
		</td>
	</tr> 
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-retailprice-components-groupedby-technology"/>
		</th>
		<td>
		<jstl:forEach var="entry" items="${averageRetailPriceOfComponentGroupedByTechnologyAndCurrency}">
				<acme:print value="${entry.key}:${entry.value} "/>	
		</jstl:forEach>
			
		</td>
	</tr>

	<tr>
		<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-retailprice-tools-groupedby-currency"/>
		</th>
		<td>
		<jstl:forEach var="entry" items="${averageRetailPriceOfToolGroupedByCurrency}">
				<acme:print value="${entry.key}:${entry.value} "/>	
		</jstl:forEach>
			
		</td>
	</tr> 
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-budget-patronages-groupedby-status"/>
		</th>
		<td>
			<jstl:forEach var="entry" items="${averageBudgetOfPatronagesGroupedByPatronageStatus}">
					<acme:print value="${entry.key}:${entry.value} "/>	
			</jstl:forEach>
			
		</td>
	</tr> 

	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-retailprice-components-groupedby-technology"/>
		</th>
		<td>
		<jstl:forEach var="entry" items="${deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency}">
					<acme:print value="${entry.key}:${entry.value} "/>	
		</jstl:forEach>
			
		</td>
	</tr> 
 
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-retailprice-tools-groupedby-currency"/>
		</th>
		<td>
		<jstl:forEach var="entry" items="${deviationRetailPriceOfToolGroupedByCurrency}">
					<acme:print value="${entry.key}:${entry.value} "/>	
		</jstl:forEach>
			
		</td>
	</tr>  
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-budget-patronages-groupedby-status"/>
		</th>
		<td>
		<jstl:forEach var="entry" items="${deviationBudgetOfPatronagesGroupedByPatronageStatus}">
					<acme:print value="${entry.key}:${entry.value} "/>	
		</jstl:forEach>
			
		</td>
	</tr> 

	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-retailprice-components-groupedby-technology"/>
		</th>
		<td>
		<jstl:forEach var="entry" items="${minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency}">
					<acme:print value="${entry.key}:${entry.value} "/>	
		</jstl:forEach>
			
		</td>
		
	</tr>

	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-retailprice-tools-groupedby-currency"/>
		</th>
		<td>
		<jstl:forEach var="entry" items="${minimumRetailPriceOfToolGroupedByCurrency}">
					<acme:print value="${entry.key}:${entry.value} "/>	
		</jstl:forEach>
			
		</td>
		
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-budget-of-patronages-groupedby-status"/>
		</th>
		<td>
		<jstl:forEach var="entry" items="${minimumBudgetOfPatronagesGroupedByPatronageStatus}">
					<acme:print value="${entry.key}:${entry.value} "/>	
		</jstl:forEach>
		
		</td>
		
	</tr> 

	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-retailprice-components-groupedby-technology"/>
		</th>
		<td>
		<jstl:forEach var="entry" items="${maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency}">
					<acme:print value="${entry.key}:${entry.value} "/>	
		</jstl:forEach>
			
		</td>
		
	</tr> 

	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-retailprice-tools-groupedby-currency"/>
		</th>
		<td>
		<jstl:forEach var="entry" items="${maximumRetailPriceOfToolGroupedByCurrency}">
					<acme:print value="${entry.key}:${entry.value} "/>	
		</jstl:forEach>
			
		</td>
		
	</tr>  
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-budget-of-patronages-groupedby-status"/>
		</th>
		<td>
		<jstl:forEach var="entry" items="${maximumBudgetOfPatronagesGroupedByPatronageStatus}">
					<acme:print value="${entry.key}:${entry.value} "/>	
		</jstl:forEach>
			
		</td>
		
	</tr> 


</table>


<acme:return/>

