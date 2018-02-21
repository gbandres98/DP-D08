

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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="gpscoordinates/user/edit.do" modelAttribute="gpsCoordinates">
	
	<form:hidden path="id" />
	<form:hidden path="version" /> 
	<input type="hidden" name="rendezvousId" value="${rendezvousId}">
	  
	<acme:textbox code="gps.latitude" path="latitude"/>
	
	<acme:textbox code="gps.longitude" path="longitude"/>
	
	<acme:submit name="save" code="gps.save"/>
	
	<acme:cancel url="rendezvous/display.do?rendezvousId=${rendezvousId}" code="gps.cancel"/>
	
</form:form>
	