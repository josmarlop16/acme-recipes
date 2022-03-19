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
			<acme:print value="${totalNumberOfPatronagesGroupedByPatronageStatus}"/>
		</td>
	</tr> 
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-retailprice-components-groupedby-technology"/>
		</th>
		<td>
			<acme:print value="${averageRetailPriceOfComponentGroupedByTechnologyAndCurrency}"/>
		</td>
	</tr>

	<tr>
		<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-retailprice-tools-groupedby-currency"/>
		</th>
		<td>
			<acme:print value="${averageRetailPriceOfToolGroupedByCurrency}"/>
		</td>
	</tr> 
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-budget-patronages-groupedby-status"/>
		</th>
		<td>
			<acme:print value="${averageBudgetOfPatronagesGroupedByPatronageStatus}"/>
		</td>
	</tr> 

	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-retailprice-components-groupedby-technology"/>
		</th>
		<td>
			<acme:print value="${deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency}"/>
		</td>
	</tr> 
 
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-retailprice-tools-groupedby-currency"/>
		</th>
		<td>
			<acme:print value="${deviationRetailPriceOfToolGroupedByCurrency}"/>
		</td>
	</tr>  
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-budget-patronages-groupedby-status"/>
		</th>
		<td>
			<acme:print value="${deviationBudgetOfPatronagesGroupedByPatronageStatus}"/>
		</td>
	</tr> 

	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-retailprice-components-groupedby-technology"/>
		</th>
		<td>
			<acme:print value="${minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency}"/>
		</td>
		
	</tr>

	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-retailprice-tools-groupedby-currency"/>
		</th>
		<td>
			<acme:print value="${minimumRetailPriceOfToolGroupedByCurrency}"/>
		</td>
		
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-budget-of-patronages-groupedby-status"/>
		</th>
		<td>
			<acme:print value="${minimumBudgetOfPatronagesGroupedByPatronageStatus}"/>
		</td>
		
	</tr> 

	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-retailprice-components-groupedby-technology"/>
		</th>
		<td>
			<acme:print value="${maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency}"/>
		</td>
		
	</tr> 

	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-retailprice-tools-groupedby-currency"/>
		</th>
		<td>
			<acme:print value="${maximumRetailPriceOfToolGroupedByCurrency}"/>
		</td>
		
	</tr>  
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-budget-of-patronages-groupedby-status"/>
		</th>
		<td>
			<acme:print value="${maximumBudgetOfPatronagesGroupedByPatronageStatus}"/>
		</td>
		
	</tr> 


</table>


<acme:return/>

