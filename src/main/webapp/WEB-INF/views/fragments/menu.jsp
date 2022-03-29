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

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.roles.Patron,acme.roles.Inventor"%>

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
		
		<acme:menu-option code="master.menu.components" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.list-components" action="/anonymous/item/list"/>		
		</acme:menu-option>
		<acme:menu-option code="master.menu.components" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.anonymous.list-components" action="/authenticated/item/list"/>		
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">
			<acme:menu-suboption code="master.menu.patron.dashboard" action="/patron/dashboard/show"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.inventor.tools" access="hasRole('Inventor')">
			<acme:menu-suboption code="master.menu.inventor.list-tools" action="/inventor/item/list"/>		
    </acme:menu-option>

		<acme:menu-option code="master.menu.toolkits" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.list-toolkits" action="/anonymous/toolkit/list"/>		
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.toolkits" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.list-toolkits" action="/authenticated/toolkit/list"/>		
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard/show"/>
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
			<acme:menu-suboption code="master.menu.user-account.become-patron" action="/authenticated/patron/create" access="!hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.user-account.patron" action="/authenticated/patron/update" access="hasRole('Patron')"/>
			
			
			<acme:menu-suboption code="master.menu.user-account.become-inventor" action="/authenticated/inventor/create" access="!hasRole('Inventor')"/>
			<acme:menu-suboption code="master.menu.user-account.inventor" action="/authenticated/inventor/update" access="hasRole('Inventor')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

