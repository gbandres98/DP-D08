

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

<b><spring:message code="answer.question"/>:</b> <jstl:out value="${answer.question.text}"/>
<br/>
<form:form action="answer/user/edit.do" modelAttribute="answer">
	
	<form:hidden path="id" />
	<form:hidden path="version" /> 
	<form:hidden path="question"/>
	<input type="hidden" name="rsvpId" value="${rsvpId}">
	
	<acme:textarea code="answer.text" path="text"/>
	
	<acme:submit name="save" code="answer.save"/>
	
	<acme:cancel url="rendezvous/display.do?rendezvousId=${answer.question.rendezvous.id}" code="answer.cancel"/>
	
</form:form>
	