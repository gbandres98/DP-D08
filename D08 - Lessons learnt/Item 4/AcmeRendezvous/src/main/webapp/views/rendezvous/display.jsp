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

<jstl:if test="${rendezvous.adultOnly}">
<spring:message code="rendezvous.adultOnly"/>
<br/>
</jstl:if>

<jstl:if test="${rendezvous.GPSCoordinates!=null}">
<b><spring:message code="gps.latitude"/> :</b>
<jstl:out value="${rendezvous.GPSCoordinates.latitude}"/>
<br/>

<b><spring:message code="gps.longitude"/> :</b>
<jstl:out value="${rendezvous.GPSCoordinates.longitude}"/>
<br/>
</jstl:if>

<br/>
<a href="comment/list-Root.do?rendezvousId=${rendezvous.id}"><b><spring:message code="comment.list"/></b></a>
<br/>

<br/>
<a href="announcement/list.do?rendezvousId=${rendezvous.id}"><b><spring:message code="rendezvous.display.announcement"/></b></a>
<br/>

<security:authorize access="hasRole('USER')">
<jstl:if test="${userId!=null && rendezvous.user.id==userId}">
	<jstl:if test="${rendezvous.GPSCoordinates==null}">
		<a href="gpscoordinates/user/create.do?rendezvousId=${rendezvous.id}"><spring:message code="rendezvous.addGPS"/></a>
	</jstl:if>
	<jstl:if test="${rendezvous.GPSCoordinates!=null}">
		<a href="gpscoordinates/user/edit.do?gpsCoordinatesId=${rendezvous.GPSCoordinates.id}&rendezvousId=${rendezvous.id}"><spring:message code="rendezvous.modifyGPS"/></a>
	</jstl:if>
<br/>
</jstl:if>
<b><spring:message code="rendezvous.description"/></b>
<a href="comment/create.do?rendezvousId=${rendezvous.id}"><b><spring:message code="comment.create"/></b></a>
<br/>
</security:authorize>
<security:authorize access="hasRole('ADMINISTRATOR')">
<a href="rendezvous/remove.do?rendezvousId=${rendezvous.id}"><b><spring:message code="rendezvous.remove"/></b></a>
</security:authorize>