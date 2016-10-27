<%@ include file="/WEB-INF/view/header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<spring:message code="label.students.add" var="labelStudentAdd" />
<spring:message code="label.students.update" var="labelStudentUpdate" />
<spring:message code="label.person.firstName" var="labelFirstName" />
<spring:message code="label.person.lastName" var="labelLastName" />
<spring:message code="label.person.gender" var="labelGender" />
<spring:message code="label.person.birthDate" var="labelBirthDate" />
<spring:message code="label.person.tel" var="labelTel" />
<spring:message code="label.person.note" var="labelNote" />
<spring:message code="label.person.photo" var="labelPhoto" />
<spring:message code="title.update" var="update" />
<spring:message code="title.add" var="add" />
<spring:message code="title.cancel" var="cancel" />
<spring:message code="date.format" var="dateFormat" />

<spring:eval expression="student.id == 0 ? labelStudentAdd : labelStudentUpdate" var="action" />
<spring:eval expression="student.id == 0 ? add : update" var="submitButtonAction" />
<spring:eval 
	expression="student.photo == null ? '../resources/images/person/noname-image.jpg' : '../students/photo/${student.id}'" var="logoImage" />

<script src="../resources/js/datepicker.min.js"></script>
<script src="../resources/js/preview-file.js"></script>
<link rel="stylesheet" href="../resources/css/datepicker.min.css" />

<title>${action}</title>
<script>

jQuery(function($){
	$("#tel").mask("+38 (999) 999-99-99"); 
});

</script>

</head>
<body>
	<div class="container">
		<h1>${action}</h1>

		<form:form modelAttribute="student" id="studentUpdateForm"
			cssClass="form-horizontal" method="post"
			enctype="multipart/form-data">
			<c:if test="${not empty message}">
				<div id="message" class="alert alert-danger">${message.message}</div>
			</c:if>

			<div class="form-group ${errors['firstName']}">
				<form:label path="firstName" cssClass="control-label col-sm-2">${labelFirstName}:</form:label>
				<div class="col-sm-10">
					<form:input path="firstName" cssClass="form-control"
						aria-describedby="firstName.errors" required="true" />
					<form:errors id="firstName.errors" path="firstName"
						cssClass="help-block" />
				</div>
			</div>

			<div class="form-group ${errors['lastName']}">
				<form:label path="lastName" cssClass="control-label col-sm-2">${labelLastName}:</form:label>
				<div class="col-sm-10">
					<form:input path="lastName" cssClass="form-control" required="true" />
					<form:errors id="lastName.errors" path="lastName"
						cssClass="help-block" />
				</div>
			</div>

			<div class="form-group ${errors['birthDate']}">
				<form:label path="birthDate" cssClass="control-label col-sm-2">${labelBirthDate}:</form:label>
				<div class="col-sm-10">
					<form:input path="birthDate"
						cssClass="form-control datepicker-here"
						data-date-format="dd-mm-yyyy" required="true" />
					<form:errors path="birthDate" cssClass="error" />
				</div>
			</div>

			<div class="form-group ${errors['sex']}">
				<form:label path="sex" cssClass="control-label col-sm-2">${labelGender}:</form:label>
				<div class="col-sm-10">
					<form:label path="sex" cssClass="radio-inline">
						<form:radiobutton path="sex" value="MALE" required="true" />male
						</form:label>
					<form:label path="sex" cssClass="radio-inline">
						<form:radiobutton path="sex" value="FEMALE" required="true" />female
						</form:label>
					<form:errors path="sex" cssClass="error" />
				</div>
			</div>

			<div class="form-group ${errors['tel']}">
				<form:label path="tel" cssClass="control-label col-sm-2">${labelTel}:</form:label>
				<div class="col-sm-10">
					<form:input path="tel" cssClass="form-control" required="true"
						placeholder="+38 (___) ___-__-__" />
					<form:errors path="tel" cssClass="error" />
				</div>
			</div>

			<div class="form-group ${errors['note']}">
				<form:label path="note" cssClass="control-label col-sm-2">${labelNote}:</form:label>
				<div class="col-sm-10">
					<form:textarea path="note" cssClass="form-control" maxlength="255" />
					<form:errors path="tel" cssClass="error" element="div" />
				</div>
			</div>
			
			<div class="form-group">
			    <label for="preview" class="control-label col-sm-2">${labelPhoto}:</label>
				<div class="col-sm-10"">
					<img id="preview" src="${logoImage}" width="200" class="img-thumbnail">
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input id="photo" name="photo" type="file" accept="image/*" 
							onchange="previewFile()" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-success">${submitButtonAction}</button>
				</div>
			</div>

		</form:form>
	</div>
</body>
</html>