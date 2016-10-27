<%@ include file="/WEB-INF/view/header.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="label.students.info" var="labelStudentInfo" />
<spring:message code="label.person.firstName" var="labelFirstName" />
<spring:message code="label.person.lastName" var="labelLastName" />
<spring:message code="label.person.gender" var="labelGender" />
<spring:message code="label.person.birthDate" var="labelBirthDate" />
<spring:message code="label.person.tel" var="labelTel" />
<spring:message code="label.person.note" var="labelNote" />
<spring:message code="title.edit" var="edit" />
<spring:message code="title.cancel" var="cancel" />
<spring:message code="date.format" var="dateFormat" />

<spring:url value="/students" var="showStudentsUrl"/>
<spring:url value="/students/photo" var="showStudentPhoto"/>
<spring:url value="/resources/images" var="pathImages"/>
<spring:eval 
	expression="student.photo == null ? '${pathImages}/person/noname-image.jpg' : '${showStudentPhoto}/${student.id}'" 
	var="logoImage" />

<title>${labelStudentInfo}</title>
</head>
<body>
	<div class="container">
		<h1>${labelStudentInfo}</h1>
		<c:if test="${not empty message}">
			<div id="message" class="alert alert-success">${message.message}</div>
		</c:if>
		<p><img src="${logoImage}" width="200"></img></p>
		<p>${student.firstName}</p>
		<p>${student.lastName}</p>
		<p>
			<fmt:formatDate pattern="${dateFormat}" value="${student.birthDate}" />
		</p>
		<p>${student.sex}
		<p>
		<p>${student.tel}</p>
		<p>${student.note}</p>
		
		<a href="${showStudentsUrl}/${student.id}?form" class="btn btn-default">${edit}</a> <a
			href="${showStudentsUrl}" class="btn btn-default">${cancel}</a>
	</div>
</body>
</html>