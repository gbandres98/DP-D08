<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!--  Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true" name="users" requestURI="/actor/list.do" id="row">

<!-- Attributes -->

	<spring:message code="actor.name" var="name"/>
	<display:column property="name" title="${name}" sortable="true"/>
	
	<spring:message code="actor.surname" var="surname"/>
	<display:column property="surname" title="${surname}" sortable="true"/>
	
	<display:column><button type="button" onclick="javascript: relativeRedir('actor/display.do?userId=${row.id}')" > <spring:message code="actor.view"/></button> </display:column>
	  
</display:table>


