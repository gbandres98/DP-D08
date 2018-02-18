<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

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

<!--  Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true" name="comments" requestURI="${requestURI}" id="row">

<!-- Attributes -->

<spring:message code="comment.picture" var="picture"/>
<display:column property="picture" title="${picture}" sortable="false"/>

<spring:message code="comment.text" var="text"/>
<display:column property="text" title="${text}" sortable="false"/>
 

<spring:message code="comment.moment" var="moment"/>
<display:column property="moment" title="${moment}" sortable="false" format="${datePattern}"/>

<spring:message code="comment.user" var="user"/>
<display:column property="user" title="${user}" sortable="false"/>
<display:column property="parentComment" title="${parentComment}" sortable="false"/>

<jstl:if test="${empty row.parentComment}">
<display:column>ver respuestas</display:column></jstl:if>

</display:table>


