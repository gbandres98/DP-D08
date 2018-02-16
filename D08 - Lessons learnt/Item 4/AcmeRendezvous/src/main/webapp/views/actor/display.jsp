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

<b><spring:message code="actor.name" /></b>
<jstl:out value="${actor.name}" />
<br />
<b><spring:message code="actor.surname" /></b>
<jstl:out value="${actor.surname}" />
<br />
<b><spring:message code="actor.email" /></b>
<jstl:out value="${actor.email}" />
<br />
<b><spring:message code="actor.phone" /></b>
<jstl:out value="${actor.phone}" />
<br />
<b><spring:message code="actor.address" /></b>
<jstl:out value="${actor.address}" />
<br />

<a href="socialIdentity/actor/list.do"><spring:message code="actor.socialIdentities" /></a>
<jstl:if test="${existCurricula}">
	<spring:message code="actor.curricula" var="urlCurricula" />
	<a href="curriculum/display.do?rangerId=${actor.id }"><jstl:out
			value="${urlCurricula}" /></a>

</jstl:if>
<jstl:if test="${rol=='ranger'&&isOwner}">
	<jstl:choose>
		<jstl:when test="${!existCurricula}">
	
	<spring:message code="actor.curricula.create" var="createCurricula" />
	<a href="curriculum/ranger/create.do"><jstl:out
			value="${createCurricula}" /></a>
	
	</jstl:when>
		<jstl:otherwise>
	
	<spring:message code="actor.curricula.edit" var="editCurricula" />
	<a href="curriculum/ranger/edit.do"><jstl:out
			value="${editCurricula}" /></a>
	
	</jstl:otherwise>
	</jstl:choose>


</jstl:if>