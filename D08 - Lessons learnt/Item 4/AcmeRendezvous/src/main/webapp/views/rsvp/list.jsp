<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<!--  Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true" name="rsvps" requestURI="${requestURI}" id="row">

<!-- Attributes -->

<spring:message code="rsvp.user" var="user"/>
<display:column title="${user}" sortable="false">
<a href="actor/display.do?userId=${row.user.id}"><jstl:out value="${row.user.name}"/> <jstl:out value="${row.user.surname}"/></a>
</display:column>

<display:column>
<jstl:if test="${row.joined=='TRUE'}">
<a href="answer/list.do?rendezvousId=${row.rendezvous.id}&userId=${row.user.id}">
<spring:message	code="rsvp.answer" />
</a>
</jstl:if>	
</display:column>

</display:table>

