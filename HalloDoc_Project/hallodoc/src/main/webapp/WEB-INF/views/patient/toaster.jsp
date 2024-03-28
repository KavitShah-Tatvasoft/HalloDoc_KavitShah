<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="<c:url value="/resources/css/toaster.css" />">
<script src="<c:url value="/resources/js/toaster.js" />"></script>
</head>
<body>

	<div class="toast active">

		<div class="toast-content">
			<i class="fas fa-solid fa-check check"></i>

			<div class="message">
				<span class="text text-1">Success</span> <span class="text text-2">Your
					changes has been saved</span>
			</div>
		</div>
		<i class="fa-solid fa-xmark close"></i>

		<!-- Remove 'active' class, this is just to show in Codepen thumbnail -->
		<div class="progress active"></div>
	</div>

</body>
</html>