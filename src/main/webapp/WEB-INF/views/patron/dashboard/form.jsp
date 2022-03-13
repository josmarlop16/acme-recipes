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
	<acme:message code="patron.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr> 
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.total-number-of-proposed-patronages"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfProposedPatronages}"/>
		</td>
	</tr> 
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.total-number-of-accepted-patronages"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfAcceptedPatronages}"/>
		</td>
	</tr> 
	<tr>
	
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.total-number-of-denied-patronages"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfDeniedPatronages}"/>
		</td>
	</tr> 
	<tr>

		<th scope="row">
			<acme:message code="patron.dashboard.form.label.average-number-of-patronages"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfPatronages}"/>
		</td>
	</tr> 
	<tr>
		
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.deviation-of-patronages"/>
		</th>
		<td>
			<acme:print value="${deviationOfPatronages}"/>
		</td>
	</tr> 
	<tr>

		<th scope="row">
			<acme:message code="patron.dashboard.form.label.minimum-budget-of-proposed-patronages"/>
		</th>
		<td>
			<acme:print value="${minimumBudgetOfProposedPatronages}"/>
		</td>
		
	</tr> 
	<tr>
		
		
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.minimum-budget-of-accepted-patronages"/>
		</th>
		<td>
			<acme:print value="${minimumBudgetOfAcceptedPatronages}"/>
		</td>
		
	</tr> 
	<tr>
		
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.minimum-budget-of-denied-patronages"/>
		</th>
		<td>
			<acme:print value="${minimumBudgetOfDeniedPatronages}"/>
		</td>
	</tr> 
	<tr>


		<th scope="row">
			<acme:message code="patron.dashboard.form.label.maximum-budget-of-proposed-patronages"/>
		</th>
		<td>
			<acme:print value="${maximumBudgetOfProposedPatronages}"/>
		</td>
		
	</tr> 
	<tr>
		
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.maximum-budget-of-accepted-patronages"/>
		</th>
		<td>
			<acme:print value="${maximumBudgetOfAcceptedPatronages}"/>
		</td>
		
	</tr> 
	<tr>
		
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.maximum-budget-of-denied-patronages"/>
		</th>
		<td>
			<acme:print value="${maximumBudgetOfDeniedPatronages}"/>
		</td>
		
	</tr> 

</table>


<h2>
	<acme:message code="patron.dashboard.form.title.application-statuses"/>
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
					"PENDING", "ACCEPTED", "DENIED"
			],
			datasets : [
				{
					data : [
						<jstl:out value="${totalNumberOfProposedPatronages}"/>, 
						<jstl:out value="${totalNumberOfAcceptedPatronages}"/>, 
						<jstl:out value="${totalNumberOfDeniedPatronages}"/>
					]
				}
			]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 10
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
	
		var canvas, context;
	
		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>


<acme:return/>

