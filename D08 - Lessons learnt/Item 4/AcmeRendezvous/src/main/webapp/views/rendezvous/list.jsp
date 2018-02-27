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

<display:table pagesize="5" class="displaytag" keepStatus="true" name="rendezvouses" requestURI="${requestURI}" id="row">

<!-- Attributes -->

<spring:message code="rendezvous.name" var="name"/>
<display:column title="${name}" sortable="false">
	<a href="rendezvous/display.do?rendezvousId=${row.id}"><jstl:out value="${row.name}"/></a>
</display:column>

<spring:message code="rendezvous.description" var="description"/>
<display:column title="${description}" sortable="false">
	<jstl:out value="${row.description}"/>
</display:column>
 
<spring:message code="rendezvous.datePattern" var="datePattern"/>
<spring:message code="rendezvous.moment" var="moment"/>
<display:column property="moment" title="${moment}" sortable="false" format="${datePattern}"/>

<spring:message code="rendezvous.user" var="user"/>
<display:column title="${user}" sortable="false">
<a href="actor/display.do?userId=${row.user.id}"><jstl:out value="${row.user.name}"/> <jstl:out value="${row.user.surname}"/></a>
</display:column>


<security:authorize access="hasRole('USER')">
	<jstl:if test="${userId!=null }">
	<display:column>
		<jstl:if test="${row.user.id == userId}">
			<a href="rendezvous/user/edit.do?rendezvousId=${row.id}">
				<spring:message	code="rendezvous.edit" />
			</a>
		</jstl:if>
	</display:column>
	</jstl:if>
	
</security:authorize>

</display:table>

<security:authorize access="hasRole('USER')">
	<a href="rendezvous/user/create.do"> 
		<spring:message code="rendezvous.create" />
	</a>
	<br/>
</security:authorize>
