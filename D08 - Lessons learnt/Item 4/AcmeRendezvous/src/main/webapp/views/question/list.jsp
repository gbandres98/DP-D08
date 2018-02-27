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

<display:table pagesize="5" class="displaytag" keepStatus="true" name="questions" requestURI="${requestURI}" id="row">

<!-- Attributes -->

<spring:message code="question.text" var="text"/>
<display:column title="${text}" sortable="false">
	<jstl:out value="${row.text}"/>
</display:column>

	
<security:authorize access="hasRole('USER')">
	<jstl:if test="${userId!=null && rendezvous.finalVersion==false}">
	<display:column>
			<a href="question/user/edit.do?questionId=${row.id}">
				<spring:message	code="question.edit" />
			</a>
	</display:column>
	</jstl:if>
	
</security:authorize>

</display:table>
<br>
<security:authorize access="hasRole('USER')">
<jstl:if test="${userId!=null && rendezvous.finalVersion==false}">
			<a href="question/user/create.do?rendezvousId=${rendezvous.id}"> 
				<spring:message code="question.create" />
			</a>
</jstl:if>
</security:authorize>