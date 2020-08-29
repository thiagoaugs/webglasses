<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<fmt:setLocale value="pt_BR"/>
	<fmt:setBundle basename="messages_webutils" var="bundle"/>
	
	<head>
		<meta charset="UTF-8">
	
		<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" />		
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/webutils.css">
		
		<title><fmt:message key="header.htmlerrors.title" bundle="${bundle}"/></title>		
	</head>
	
	<body>
		<form id="frmError">
			<div class="errorPageMessage">
				<div class="errorPageMessageMain">
					<div class="errorPageMessageMainIcon">
						<img src="${pageContext.request.contextPath}/images/error.png" />
					</div>
					<div class="errorPageMessageMainMsg">
						<fmt:message key="genericerror.mensagem.principal" bundle="${bundle}"/>
					</div>
				</div>
				<div class="errorPageMessageDetails" >
					<fmt:message key="genericerror.mensagem.acao" bundle="${bundle}"/><br/>
					[<a href="${pageContext.request.contextPath}/index.html"><fmt:message key="genericerror.mensagem.voltar" bundle="${bundle}"/></a>]
				</div>
			</div>
		</form>	
	</body>
</html>