<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<fmt:setLocale value="pt_BR" />
<fmt:setBundle basename="messages_administrador" var="bundle" />
<head>
<meta charset="UTF-8">
<title><fmt:message key="header.title" bundle="${bundle}" /></title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/reset.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/flexsolutions.css">

<script
	src="${pageContext.request.contextPath}/resources/js/flexsolutions.js"
	type="text/javascript"></script>
</head>

<body class="login"
	onload="document.getElementById('j_username').focus();">
	<div id="page">
		<div id="header">

			<!-- Banner Aplicacao e Dados Usuario -->
			<div id="headerBanner">
				<div id="headerBannerRow">
					<!-- Banner esquerda -->
					<div id="bannerEsquerda">
						<a id="bannerLinkHomeWebglass" href="/flexsolutions"></a> <img
							id="bannerEsquerdaImg"
							src="${pageContext.request.contextPath}/resources/images/topo_esq.jpg">
					</div>
					<div id="bannerDadosUsuario">
						<div id="bannerDadosUsuarioNome">&nbsp;</div>
						<div id="bannerDadosUsuarioPerfis">&nbsp;</div>
					</div>

					<!-- Banner Direita -->
					<div id="bannerDireita">
						<img id="bannerDireitaImg"
							src="${pageContext.request.contextPath}/resources/images/topo_dir.jpg">
					</div>
				</div>
			</div>

			<!-- Menu -->
			<div id="headerMenu">&nbsp;</div>

		</div>


		<div id="content">
			<form action="j_security_check" method="post">
				<input id="sistema" type="hidden" name="sistema"
					value="ADMINISTRADOR" />
				<!-- Logo Sanepar -->
				<div id="logoSystemGlass"></div>

				<!-- Login -->
				<fieldset>
					<div>
						<!-- UserName -->
						<label><fmt:message key="login.chave" bundle="${bundle}" /></label>
						<input type="text" id="j_username" name="j_username" tabindex="1"
							onkeypress="nextElementOnEnter(event, this);" /><br />
						<!-- Password -->
						<label><fmt:message key="login.senha" bundle="${bundle}" /></label>
						<input type="password" id="j_password" name="j_password"
							tabindex="2" /><br />
					</div>
					<input type="submit"
						value="<fmt:message key='bt.enviar' bundle='${bundle}' />"
						tabindex="3" />
				</fieldset>

				<c:if test="${msgLoginModule != null && msgLoginModule != ''}">
					<!-- Mensagem de Erro no Login -->
					<div id="msgLogin">${msgLoginModule}</div>
				</c:if>

				<div id="obsLogin">
					<fmt:message key="login.sessiontimeoutmsg" bundle="${bundle}" />
					<span id="ramal" style="font-weight: bolder;"><fmt:message
							key="login.ramalduvidas" bundle="${bundle}" /></span>
				</div>
			</form>
		</div>


		<div id="footer">
			<fmt:message key="footer.copyright" bundle="${bundle}" />
		</div>
	</div>
</body>
</html>