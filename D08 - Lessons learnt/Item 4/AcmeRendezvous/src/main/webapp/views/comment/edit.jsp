

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



				<!-- Create comment-->


	<%-- <form:hidden path="id" />
	<form:hidden path="version" /> 
	<form:hidden path="replies" />
	--%>
	<form:form action="comment/edit.do" modelAttribute="comment">
	<form:hidden path="rendezvous" />
	<form:hidden path="user" /> 
	<form:hidden path="parentComment" />  
	<form:hidden path="moment" />  
	
	<acme:textarea code="comment.text" path="text"/>
	
	<acme:textbox code="comment.picture" path="picture"/>

	<acme:submit name="save" code="comment.save"/>

	<security:authorize access="hasRole('ADMINISTRATOR')">
	<acme:submit name="delete" code="comment.delete"/>
	</security:authorize>
	
	<acme:cancel url="" code="comment.cancel"/>
	
	</form:form>
	