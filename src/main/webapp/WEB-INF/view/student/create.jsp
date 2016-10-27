<%@ include file="/WEB-INF/view/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="action" value="${student.id > 0 ? 'Edit' : 'Add'}" scope="page" />
<script	src="http://localhost:8080/university/static/js/datepicker.min.js" /></script>
<link href="http://localhost:8080/university/static/css/datepicker.min.css"	rel="stylesheet" />
<title>${action} Student</title>
</head>
<body>
	<div class="container">
		<h1>${action} student</h1>
		<form name="editForm" method="post" role="form">
			<input type="hidden" name="id" value="${0+student.id}" />
			<div class="form-group ${errorMap.firstNameCSS}">
				<label for="firstName" class="control-label">Name</label>
				<input type="text" id="firstName" name="firstName" value="${student.firstName}" 
					class="form-control" aria-describedby="helpBlockFirstName" required="true" />
				<span id="helpBlockFirstName" class="help-block">${errorMap.firstName}</span>
			</div>
			<div class="form-group ${errorMap.lastNameCSS}">
				<label for="lastName" class="control-label">Last Name</label>
				<input type="text" id="lastName" name="lastName" value="${student.lastName}" 
					class="form-control" aria-describedby="helpBlockLastName" required="true" />
					<span id="helpBlockLastName" class="help-block">${errorMap.lastName}</span>
			</div>
			<div class="form-group ${errorMap.sexCSS}">
				<label class="radio-inline">
				<input type="radio" name="sex" value="male" ${student.sex=='MALE'?'checked':''} 
					aria-describedby="helpBlockSex" required="true" />male
				</label>
				<label class="radio-inline">
				<input type="radio" id="sex" name="sex" value="female" ${student.sex=='FEMALE'?'checked':''}
					aria-describedby="helpBlockSex" required="true" />female
				</label>
				<span id="helpBlockSex" class="help-block">${errorMap.sex}</span>
			</div>
			<div class="form-group ${errorMap.birthDateCSS}">
				<label class="control-label">BirthDate</label>
				<input type="date" name="birthDate"	value="<fmt:formatDate type="date" 
					pattern="yyyy-MM-dd" value="${student.birthDate}"/>" aria-describedby="helpBlockBirthDate" required="true"/>
				<span id="helpBlockBirthDate" class="help-block">${errorMap.birthDate}</span>	
			</div>
			<input type='text' class="datepicker-here" data-position="right top" />
			<div class="form-group ${errorMap.idGroupCSS}">
				<label class="control-label">Group</label>
				<input type="text" name="idGroup" value="${student.idGroup}" class="form-control" 
					aria-describedby="helpBlockGroup" required="true" />
				<span id="helpBlockGroup" class="help-block">${errorMap.idGroup}</span>	
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-success">Save</button>
				<a class="btn btn-default" id="cancel" onClick="history.back();">Cancel</a>	
			</div>
		</form>
	</div>
</body>
</html>