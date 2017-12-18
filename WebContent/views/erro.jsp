<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="t"%>
<t:template>
	<jsp:attribute name="title">Login</jsp:attribute>
	<jsp:attribute name="header">
		<style type="text/css">
			body {
				background-image: url(<c:url value="/resources/images/bg_erro.jpg" />);
				background-repeat: no-repeat;
				background-size: cover;
			}
			
			.btn-signin {
				display: none;
			}
			
			label {
				color: White;
				font-size: 1.4em;
			}
			
			li.afterlogin {
				display: none;
			}
			.row {
				margin-left: 35px;
			}
			h3, h4 {
				color: White;
			}
</style>
	</jsp:attribute>

	<jsp:body>
	<div class="container">
		<h3>Erro</h3>
		<h4>Um erro ocorreu e não foi possível atender ao seu pedido. Tenta novamente mais tarde.</h4>
	</div>
		
	</jsp:body>

</t:template>