<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<jstl:if test="${rendezvous.picture!= null}">
	<div>
		<img src="${rendezvous.picture}" alt="picture" />
	</div>
</jstl:if>

<b><spring:message code="rendezvous.name"/></b>
<jstl:out value="${rendezvous.name}"/>
<br/>

<b><spring:message code="rendezvous.description"/></b>
<jstl:out value="${rendezvous.description}"/>
<br/>

<spring:message code="rendezvous.display.datePattern" var="datePattern"/>

<b><spring:message code="rendezvous.moment"/></b>
<fmt:formatDate value="${rendezvous.moment}" pattern="${rendezvous.moment}"/>
<br/>

<jstl:if test="${!rendezvous.adultOnly}">
<spring:message code="rendezvous.adultOnly"/>
</jstl:if>





<security:authorize access="hasRole('USER')">


</security:authorize>