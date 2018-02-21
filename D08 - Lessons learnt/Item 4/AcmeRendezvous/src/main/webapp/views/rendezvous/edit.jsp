

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

<form:form action="rendezvous/user/edit.do" modelAttribute="rendezvous">
	
	<form:hidden path="id" />
	<form:hidden path="version" /> 
	<form:hidden path="user" />
	<form:hidden path="questions"/>
	<form:hidden path="announcements"/>
	<form:hidden path="rendezvouses"/>
	  
	<acme:textbox code="rendezvous.name" path="name"/>
	
	<acme:textarea code="rendezvous.description" path="description"/>
	
	<spring:message code="rendezvous.moment" var="moment"/>
	<form:label path="moment">${moment}</form:label>
	<form:input path="moment" placeholder="dd/mm/yyyy HH:MM"/>
	<form:errors cssClass="error" path="moment"/>
	<br />
	
	<acme:textbox code="rendezvous.picture" path="picture"/>

	<input type="checkbox" id="adultOnly" name="adultOnly" value="True"><spring:message code="rendezvous.adultOnly"/>
	<br/>
	
	<acme:submit name="save" code="rendezvous.save"/>
	
	<acme:cancel url="rendezvous/user/list.do" code="rendezvous.cancel"/>
	
</form:form>
	