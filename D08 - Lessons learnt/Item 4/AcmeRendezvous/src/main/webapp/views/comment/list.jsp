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

<display:table  pagesize="5" class="displaytag" keepStatus="true"
	name="survivalClasses" requestURI="${requestURI}" id="row">

	<!-- Action links -->
	<!-- Attributes -->

	<spring:message code="survivalClass.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<spring:message code="survivalClass.description"
		var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" />

	<spring:message code="survivalClass.organizationDate"
		var="organizationDateHeader" />
	<spring:message code="master.page.date.format" var="dateFormat" />
	<display:column property="organizationDate"
		title="${organizationDateHeader}" 
		format="{0,date,${dateFormat}}" />

	<spring:message code="survivalClass.location" var="locationHeader" />
	<display:column property="location" title="${locationHeader}" />

	<spring:message code="survivalClass.trip" var="tripsHeader" />
	<display:column property="trip.title" title="${tripsHeader}" />

	<security:authorize access="hasRole('EXPLORER')">
		<display:column>
			<jstl:choose>

				<jstl:when test="${registered}">
					<a
						href="survivalClass/explorer/unAttend.do?survivalClassId=${row.id}">
						<spring:message code="survivalClass.unAttend" />
					</a>
<a href="survivalClass/display.do?survivalClassId=<jstl:out value="${row.getId()}"/>"><spring:message
					code="survivalClass.display" /></a>
				</jstl:when>
				<jstl:otherwise>

					<a
						href="survivalClass/explorer/attend.do?survivalClassId=${row.id}">
						<spring:message code="survivalClass.attend" />
					</a>
<br />
<a href="survivalClass/display.do?survivalClassId=<jstl:out value="${row.getId()}"/>"><spring:message
					code="survivalClass.display" /></a>
				</jstl:otherwise>

			</jstl:choose>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('MANAGER')">
		<display:column>
			<%-- <input type="button" name="edit"
				value="<spring:message code="survivalClass.edit" />"
				onclick="javascript: relativeRedir('survivalClass/manager/edit.do?survivalClassId=${row.id}')" />
		 --%>

			
<a href="survivalClass/display.do?survivalClassId=<jstl:out value="${row.getId()}"/>"><spring:message
					code="survivalClass.display" /></a>
					
			<br />
			<a
				href="survivalClass/manager/edit.do?survivalClassId=<jstl:out value="${row.getId()}"/>"><spring:message
					code="survivalClass.edit" /></a>
			<br />
		</display:column>
	</security:authorize>
</display:table>


