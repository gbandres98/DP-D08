<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMINISTRATOR')">
<!-- Listing grid -->

<b><spring:message code="administrator.avgRpU"/></b>
<fmt:formatNumber value="${avgRendezvousesperUser}" pattern="####0.00"/>
<br/>
<b><spring:message code="administrator.sdRpU"/></b>
<fmt:formatNumber value="${sdRendezvousesperUser}" pattern="####0.00"/>
<br/><br/><br/>

<b><spring:message code="administrator.ratioURvsUNR"/></b>
<fmt:formatNumber value="${ratioUserRendezvousvsUserNoRendezvous}" pattern="####0.00"/>
<br/><br/><br/>


<b><spring:message code="administrator.avgRSVPpR"/></b>
<fmt:formatNumber value="${avgRSVPperRendezvous}" pattern="####0.00"/>
<br/>
<b><spring:message code="administrator.sdRSVPpR"/></b>
<fmt:formatNumber value="${sdRSVPperRendezvous}" pattern="####0.00"/>
<br/><br/><br/>

<b><spring:message code="administrator.avgRSVPpU"/></b>
<fmt:formatNumber value="${avgRSVPperUser}" pattern="####0.00"/>
<br/>
<b><spring:message code="administrator.sdRSVPpU"/></b>
<fmt:formatNumber value="${sdRSVPperUser}" pattern="####0.00"/>
<br/><br/><br/>

<b><spring:message code="administrator.toptenbyRSVP"/></b>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="toptenbyRSVP" requestURI="${requestURI}" id="row">
	
	<b><spring:message code="administrator.toptenbyRSVP" var="title"/></b>
	<display:column title="${title}">
		<a href="rendezvous/display.do?rendezvousId=${row.id}">${row.name}</a>
	</display:column>
</display:table>
<br/><br/><br/>

<b><spring:message code="administrator.avgApR"/></b>
<fmt:formatNumber value="${avgAnnoucementsperRendezvous}" pattern="####0.00"/>
<br/>
<b><spring:message code="administrator.sdApR"/></b>
<fmt:formatNumber value="${sdAnnoucementsperRendezvous}" pattern="####0.00"/>
<br/><br/><br/>

<b><spring:message code="administrator.RaAA"/></b>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="RendezaboveAverageAnnoun" requestURI="${requestURI}" id="row">
	
	<b><spring:message code="administrator.RaAA" var="title"/></b>
	<display:column title="${title}">
		<a href="rendezvous/display.do?rendezvousId=${row.id}">${row.name}</a>
	</display:column>
</display:table>
<br/><br/><br/>

<b><spring:message code="administrator.RaAR"/></b>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="RendezaboveAverageRendez" requestURI="${requestURI}" id="row">
	
	<b><spring:message code="administrator.RaAR" var="title"/></b>
	<display:column title="${title}">
		<a href="rendezvous/display.do?rendezvousId=${row.id}">${row.name}</a>
	</display:column>
</display:table>
<br/><br/><br/>

<b><spring:message code="administrator.avgQpR"/></b>
<fmt:formatNumber value="${avgQuestionsperRendezvous}" pattern="####0.00"/>
<br/>
<b><spring:message code="administrator.sdQpR"/></b>
<fmt:formatNumber value="${sdQuestionsperRendezvous}" pattern="####0.00"/>
<br/><br/><br/>

<b><spring:message code="administrator.avgANpR"/></b>
<fmt:formatNumber value="${avgAnswersperRendezvous}" pattern="####0.00"/>
<br/>
<b><spring:message code="administrator.sdANpR"/></b>
<fmt:formatNumber value="${sdAnswersperRendezvous}" pattern="####0.00"/>
<br/><br/><br/>

<b><spring:message code="administrator.avgRepC"/></b>
<fmt:formatNumber value="${avgReplysperComment}" pattern="####0.00"/>
<br/>
<b><spring:message code="administrator.sdRepC"/></b>
<fmt:formatNumber value="${sdReplysperComment}" pattern="####0.00"/>
<br/><br/><br/>


</security:authorize>
	