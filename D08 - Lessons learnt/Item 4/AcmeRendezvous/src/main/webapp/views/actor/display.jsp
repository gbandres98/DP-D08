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
<jstl:out value="${user.name}" />
<br />
<b><spring:message code="actor.surname" /></b>
<jstl:out value="${user.surname}" />
<br />
<b><spring:message code="actor.email" /></b>
<jstl:out value="${user.emailAddress}" />
<br />
<b><spring:message code="actor.phone" /></b>
<jstl:out value="${user.phoneNumber}" />
<br />
<b><spring:message code="actor.address" /></b>
<jstl:out value="${user.postalAddress}" />
<br />

<!--  Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true" name="rendezvouses" requestURI="${requestURI}" id="row">

<!-- Attributes -->

<spring:message code="rendezvous.name" var="name"/>
<display:column title="${name}" sortable="false">
	<a href="rendezvous/display.do?rendezvousId=${row.id}"><jstl:out value="${row.name}"/></a>
</display:column>

<spring:message code="rendezvous.description" var="description"/>
<display:column property="description" title="${description}" sortable="false"/>
 
<spring:message code="rendezvous.datePattern" var="datePattern"/>
<spring:message code="rendezvous.moment" var="moment"/>
<display:column property="moment" title="${moment}" sortable="false" format="${datePattern}"/>

<spring:message code="rendezvous.user" var="user"/>
<display:column title="${user}" sortable="false">
<a href="actor/display.do?userId=${row.user.id}"><jstl:out value="${row.user.name}"/> <jstl:out value="${row.user.surname}"/></a>
</display:column>

</display:table>