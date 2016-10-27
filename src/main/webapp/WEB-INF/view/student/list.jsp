<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="label.students.list.title" var="labelTitle"/>
<spring:message code="label.person.firstName" var="labelFirstName"/>
<spring:message code="label.person.lastName" var="labelLastName"/>
<spring:message code="label.person.gender" var="labelGender"/>
<spring:message code="label.person.birthDate" var="labelBirthDate"/>
<spring:message code="label.person.tel" var="labelTel"/>
<spring:message code="label.person.note" var="labelNote"/>

<spring:message code="title.edit" var="edit"/>
<spring:message code="title.view" var="view"/>
<spring:message code="title.action" var="action"/>
<spring:message code="date.format" var="dateFormat"/>

<spring:url value="/students" var="showStudentUrl"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<title>${labelTitle}</title>
</head>
<body>
<div class="container">
  <h1>${labelTitle}</h1>
  <table class="table table-hover">
    <thead>
      <tr>
        <th>#</th>
        <th>${labelFirstName}</th>
        <th>${labelLastName}</th>
        <th>${labelBirthDate}</th>
        <th>${labelGender}</th>
        <th>${labelTel}</th>
        <th>${labelNote}</th>
        <th>${action}</th>
      </tr>
    </thead>
    <tbody>
    <c:set var="count" value="0" scope="page" />
     <c:forEach items="${students}" var="item">
      <tr>
      	<c:set var="count" value="${count + 1}" scope="page"/>
      	<td>${count}</td>
        <td>${item.firstName}</td>
        <td>${item.lastName}</td>
        <td><fmt:formatDate type="date" pattern="${dateFormat}" value="${item.birthDate}"/></td>
        <td>${item.sex}</td>
        <td>${item.tel}</td>
        <td>${item.note}</td>
        <td><a href="${showStudentUrl}/${item.id}" class="btn btn-default btn-xs">
  				<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> ${view}</a>&nbsp;
        <a href="${showStudentUrl}/${item.id}?form" class="btn btn-default btn-xs">
        		<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> ${edit}</a></td>
      </tr>
     </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>