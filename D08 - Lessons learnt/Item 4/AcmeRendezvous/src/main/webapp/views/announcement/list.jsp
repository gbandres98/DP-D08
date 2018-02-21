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


<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="announcements" requestURI="${requestURI}" id="row">

	<spring:message code="announcement.title" var="title" />
	<display:column property="title" title="${title}" sortable="false" />

	<spring:message code="announcement.description" var="description" />
	<display:column property="description" title="${description}"
		sortable="false" />

	<spring:message code="announcement.moment" var="moment" />
	<display:column property="moment" title="${moment}" sortable="false" />

	<jstl:if test="${requestURI=='announcement/user/list.do' }">
		<display:column title="Rendezvous">
			<a href="rendezvous/display.do?rendezvousId=${row.rendezvous.id}"><jstl:out
					value="${row.rendezvous.name}" /></a>
		</display:column>
	</jstl:if>


</display:table>




