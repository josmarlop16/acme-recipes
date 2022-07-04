<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.roles.Patron,acme.roles.Chef"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.jorge-toledo-vega" action="https://github.com/jvegax"/>
			<acme:menu-suboption code="master.menu.anonymous.jose-antonio-marquez-lopez" action="https://github.com/josmarlop16"/>
			<acme:menu-suboption code="master.menu.anonymous.antonio-mira-otero" action="https://github.com/Anton1o007"/>
			<acme:menu-suboption code="master.menu.anonymous.jose-caceres-gomez" action="https://github.com/joscacgom"/>
			<acme:menu-suboption code="master.menu.anonymous.julian-otane-parra" action="https://github.com/jonatep"/>
			<acme:menu-suboption code="master.menu.anonymous.luis-rodriguez-garcia" action="https://github.com/LuisUsrDev"/>
		</acme:menu-option>
		
		
        <!-- Any principals -->
		<acme:menu-option code="master.menu.peeps" access="permitAll()">
			<acme:menu-suboption code="master.menu.any.list-peeps" action="/any/peep/list"/>	
			<acme:menu-suboption code="master.menu.any.create-peeps" action="/any/peep/create"/>		
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.toolkits" access="permitAll()">
			<acme:menu-suboption code="master.menu.any.list-toolkits" action="/any/toolkit/list"/>	
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.items" access="permitAll()">
			<acme:menu-suboption code="master.menu.any.list-tools" action="/any/item/list-tool"/>	
			<acme:menu-suboption code="master.menu.any.list-components" action="/any/item/list-component"/>		
		</acme:menu-option>
		
		<!-- Authenticated principals -->
		<acme:menu-option code="master.menu.authenticated.configuration" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.configuration.list-spam" action="/authenticated/spam/list"/>
			<acme:menu-suboption code="master.menu.authenticated.configuration.list-currency" action="/authenticated/currency/list"/>				
		</acme:menu-option>
    
    <acme:menu-option code="master.menu.announcements" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.list-announcements" action="/authenticated/announcement/list"/>
			<acme:menu-suboption code="master.menu.administrator.create-announcements" action="/administrator/announcement/create" access="hasRole('Administrator')"/>	
		</acme:menu-option> 
		

    <!-- Chef -->
		<acme:menu-option code="master.menu.chef" access="hasRole('Chef')">	
			<acme:menu-suboption code="master.menu.chef.list-tools" action="/chef/item/list"/>
			<acme:menu-suboption code="master.menu.chef.list-components" action="/chef/item/list-component"/>
			<acme:menu-suboption code="master.menu.chef.list-toolkits" action="/chef/toolkit/list"/>
      <acme:menu-suboption code="master.menu.chef.list-patronage-reports" action="/chef/patronage-report/list"/>
			<acme:menu-suboption code="master.menu.chef.list-patronages" action="/chef/patronage/list"/>
		</acme:menu-option>


		
		<acme:menu-option code="master.menu.userAccounts" action="/any/user-account/list" access="permitAll()">	
		</acme:menu-option>
		
		<!-- Patron -->
		<acme:menu-option code="master.menu.epicure" access="hasRole('Epicure')">
			<acme:menu-suboption code="master.menu.epicure.dashboard" action="/epicure/dashboard/show"/>
			<acme:menu-suboption code="master.menu.epicure.list-patronages" action="/epicure/patronage/list"/>
			<acme:menu-suboption code="master.menu.epicure.create-patronages" action="/epicure/patronage/create"/>
			<acme:menu-suboption code="master.menu.epicure.list-patronage-reports" action="/epicure/patronage-report/list"/>
			
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/administrator-dashboard/show"/>
			<acme:menu-suboption code="master.menu.authenticated.configuration.list-spam" action="/administrator/spam/list"/>
			<acme:menu-suboption code="master.menu.authenticated.configuration.list-currency" action="/administrator/currency/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
		</acme:menu-option>

	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>		
			<acme:menu-suboption code="master.menu.user-account.become-epicure" action="/authenticated/epicure/create" access="!hasRole('Epicure')"/>
			<acme:menu-suboption code="master.menu.user-account.epicure" action="/authenticated/epicure/update" access="hasRole('Epicure')"/>
			<acme:menu-suboption code="master.menu.user-account.become-chef" action="/authenticated/chef/create" access="!hasRole('Chef')"/>
			<acme:menu-suboption code="master.menu.user-account.chef" action="/authenticated/chef/update" access="hasRole('Chef')"/>
		</acme:menu-option>
		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

