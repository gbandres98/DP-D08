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

<display:table pagesize="5" class="displaytag" keepStatus="true" name="answers" requestURI="${requestURI}" id="row">

<!-- Attributes -->

<spring:message code="answer.question" var="question" />
<display:column  title="${question}">
		<jstl:out value="${row.question.text}"/>
</display:column>

<spring:message code="answer.text" var="answer" />
<display:column title="${answer}">
		<jstl:out value="${row.text}"/>
</display:column>
</display:table>

