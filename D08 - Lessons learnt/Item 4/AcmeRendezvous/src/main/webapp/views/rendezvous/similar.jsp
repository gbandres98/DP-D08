

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

<form:form action="rendezvous/user/similar.do" modelAttribute="similarForm">
	
	<form:hidden path="rendezvous" />
	
	<form:select path="similar">
		<form:option
			label="----"
			value="0" />
		<form:options
			items="${rendezvouses}"
			itemLabel="name"
			itemValue="id" />
	</form:select>
	
	<acme:submit name="save" code="rendezvous.save"/>
	
	<acme:cancel url="rendezvous/user/list.do" code="rendezvous.cancel"/>
	
</form:form>
	