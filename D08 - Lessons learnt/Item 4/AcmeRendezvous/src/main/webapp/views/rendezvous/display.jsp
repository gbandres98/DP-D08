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

<jstl:if test="${rendezvous.picture!= null}">
	<div>
		<img src="${rendezvous.picture}" alt="picture" />
	</div>
</jstl:if>

<b><spring:message code="rendezvous.name" /></b>
<jstl:out value="${rendezvous.name}" />
<br />

<b><spring:message code="rendezvous.description" /></b>
<jstl:out value="${rendezvous.description}" />
<br />

<spring:message code="rendezvous.display.datePattern" var="datePattern" />

<b><spring:message code="rendezvous.moment" /></b>
<fmt:formatDate value="${rendezvous.moment}" pattern="${datePattern}" />
<br />

<jstl:if test="${rendezvous.adultOnly}">
	<spring:message code="rendezvous.adultOnly" />
	<br />
</jstl:if>

<jstl:if test="${rendezvous.GPSCoordinates!=null}">
	<b><spring:message code="gps.latitude" /> :</b>
	<jstl:out value="${rendezvous.GPSCoordinates.latitude}" />
	<br />

	<b><spring:message code="gps.longitude" /> :</b>
	<jstl:out value="${rendezvous.GPSCoordinates.longitude}" />
	<br />
</jstl:if>



<br />
<a href="announcement/list.do?rendezvousId=${rendezvous.id}"><b><spring:message
			code="rendezvous.display.announcement" /></b></a>
<br />

<br />
<a href="question/list.do?rendezvousId=${rendezvous.id}"><b><spring:message code="rendezvous.display.question" /></b></a>
<br />

<security:authorize access="hasRole('USER')">
	<jstl:if test="${rsvpJoined==true}">
		<spring:message code="rendezvous.rsvp.congrats" />
		<a href="rsvp/user/cancel.do?rsvpId=${rsvpId}"><spring:message code="rendezvous.rsvp.cancel" /></a>
		<br/>
	</jstl:if>
	
	<jstl:if test="${rsvpJoined==null && rendezvous.user.id!=userId && joinable}">
		<a href="rsvp/user/create.do?rendezvousId=${rendezvous.id }">
			<spring:message code="rendezvous.rsvp.join" />
		</a>
		<br/>
	</jstl:if>
	
	<jstl:if test="${rsvpId!=null && rsvpJoined==false && joinable}">
		<a href="rsvp/user/edit.do?rsvpId=${rsvpId}">
			<spring:message code="rendezvous.rsvp.continueJoining" />
		</a>
		<br/>
	</jstl:if>

	<jstl:if test="${rendezvous.user.id==userId && finalVersion!=1}">
		<a href="announcement/user/create.do?rendezvousId= ${rendezvous.id }">
			<spring:message code="announcement.create" />
		</a>
		<br/>
	</jstl:if>
</security:authorize>

<jstl:if test="${rendezvous.deleted==true}">
	<p class="deletedRendezvous">
		<b><spring:message code="rendezvous.deleted" /></b>
	</p>
	<br />
</jstl:if>


<br />

<jstl:if test="${userId!=null && rendezvous.user.id==userId}">
	<jstl:if
		test="${rendezvous.finalVersion==false && !rendezvous.deleted}">
		<a href="rendezvous/user/setFinal.do?rendezvousId=${rendezvous.id}"><spring:message
				code="rendezvous.setFinal" /></a>
		<br />
	</jstl:if>
	<jstl:if test="${finalVersion==1}">
		<p class="finalVersionSetted">
			<b><spring:message code="rendezvous.settedFinal" /></b>
		</p>
		<br />
	</jstl:if>

	<jstl:if test="${rendezvous.deleted==false}">
		<a href="rendezvous/user/delete.do?rendezvousId=${rendezvous.id}"><spring:message
				code="rendezvous.delete" /></a>
		<br />
	</jstl:if>

	<jstl:if test="${rendezvous.GPSCoordinates==null}">
		<a href="gpscoordinates/user/create.do?rendezvousId=${rendezvous.id}"><spring:message
				code="rendezvous.addGPS" /></a>
		<br />
	</jstl:if>
	<jstl:if test="${rendezvous.GPSCoordinates!=null && !rendezvous.finalVersion && !rendezvous.deleted}">
		<a
			href="gpscoordinates/user/edit.do?gpsCoordinatesId=${rendezvous.GPSCoordinates.id}&rendezvousId=${rendezvous.id}"><spring:message
				code="rendezvous.modifyGPS" /></a>
		<br />
	</jstl:if>
	<a href="rendezvous/user/similar.do?rendezvousId=${rendezvous.id}"><b><spring:message
				code="rendezvous.addSimilar" /></b></a>
	<br />
</jstl:if>
<a href="rsvp/list.do?rendezvousId=${rendezvous.id}"><b><spring:message
			code="rendezvous.display.rsvp" /></b></a>
<br />
<security:authorize access="hasRole('ADMINISTRATOR')">
	<a href="rendezvous/remove.do?rendezvousId=${rendezvous.id}"><b><spring:message
				code="rendezvous.remove" /></b></a>
</security:authorize>
<security:authorize access="hasAnyRole('USER', 'ADMINISTRATOR')">


	<br />
	<b><spring:message code="comment.management" /></b>

	<br />
	<a href="comment/list-Root.do?rendezvousId=${rendezvous.id}"><b><spring:message
				code="comment.list" /></b></a>
	<br />
</security:authorize>
<security:authorize access="hasRole('USER')">
		<jstl:if test="${puedeCrear}">
	<a href="comment/create.do?rendezvousId=${rendezvous.id}"><b><spring:message
				code="comment.create" /></b></a>
		</jstl:if>
</security:authorize>
<br />
<br />
<a href="rendezvous/list.do?rendezvousId=${rendezvous.id}"><b><spring:message
			code="rendezvous.viewSimilar" /></b></a>
