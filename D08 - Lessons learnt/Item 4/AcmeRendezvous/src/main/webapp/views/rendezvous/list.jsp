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
<display:column property="name" title="${name}" sortable="false"/>

<spring:message code="rendezvous.description" var="description"/>
<display:column property="description" title="${description}" sortable="false"/>
 
<spring:message code="rendezvous.datePattern" var="datePattern"/>

<spring:message code="rendezvous.moment" var="moment"/>
<display:column property="moment" title="${moment}" sortable="false" format="${datePattern}"/>



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
