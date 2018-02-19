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

<jstl:if test="${comments.size()==0}">
<jstl:choose>
 <jstl:when test="${empty ParentComment.parentComment }">
 <button type="button" onclick="javascript: relativeRedir('comment/list-Root.do?rendezvousId=${ParentComment.rendezvous.id}')" > <spring:message code="comment.parentComment"/></button> 
  </jstl:when> 
  <jstl:when test="${not empty ParentComment.parentComment && not empty ParentComment.parentComment.parentComment }">
 <button type="button" onclick="javascript: relativeRedir('comment/list-Answer.do?commentId=${ParentComment.parentComment.parentComment.id}')" > <spring:message code="comment.parentComment"/></button> 
  </jstl:when>
  <jstl:otherwise> <button type="button" onclick="javascript: relativeRedir('comment/list-Root.do?rendezvousId=${ParentComment.rendezvous.id}')" > <spring:message code="comment.parentComment"/></button> 
  </jstl:otherwise>
  </jstl:choose>

 </jstl:if>
  
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
<%-- <display:column property="parentComment" title="${parentComment}" sortable="false"/> --%>
<jstl:choose>
 <jstl:when test="${empty row.parentComment.parentComment && not empty row.parentComment }">
 <display:column><button type="button" onclick="javascript: relativeRedir('comment/list-Root.do?rendezvousId=${row.rendezvous.id}')" > <spring:message code="comment.parentComment"/></button> </display:column>
  </jstl:when> 
  <jstl:when test="${not empty row.parentComment }">
  <display:column><button type="button" onclick="javascript: relativeRedir('comment/list-Answer.do?commentId=${row.parentComment.parentComment.id}')" > <spring:message code="comment.parentComment"/></button> </display:column>
  </jstl:when>
  <jstl:otherwise></jstl:otherwise>
  </jstl:choose>
<display:column><button type="button" onclick="javascript: relativeRedir('comment/list-Answer.do?commentId=${row.id}')" > <spring:message code="comment.answers"/></button> </display:column>

<display:column><button type="button" onclick="javascript: relativeRedir('comment/create.do?rendezvousId=${row.rendezvous.id}&commentId=${row.id}')" > <spring:message code="comment.create"/></button> </display:column>
<security:authorize access="hasRole('ADMINISTRATOR')">
	<display:column><button type="button" onclick="javascript: relativeRedir('comment/delete.do?commentId=${row.id}')"> </display:column>
		<spring:message code="comment.delete" />
	</a>
	<br/>
</security:authorize>

</display:table>


