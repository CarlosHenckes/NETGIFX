<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:template>
	<jsp:attribute name="title">Login</jsp:attribute>
	
	<jsp:attribute name="header">
	<style type="text/css">
		body {
				background-image: url(<c:url value="/resources/images/bg_content.jpg" />);
				background-repeat: no-repeat;
				background-size: cover;
			}
		.btn-signin {
			display: none;
		}
		
		.afterlogin {
			display: none;
		}
	</style>
	</jsp:attribute>
	
	<jsp:body>
		<div class="container text-center">
			<span style="font-size: 3.2em; color: white; font-family: Calibri;">Quem
				está assistindo?</span>
			<div class="row" style="padding: 25px;">
				<div class="col-sm"></div>
				<div class="col-sm">
					<a href="content.xhtml"><img src="images/perfil.png" /></a><br />
					<span style="font-size: 1.3em; color: white; padding: 15px 0 0;">Eu</span>
				</div>
				<div class="col-sm">
					<img src="images/addprofile.png" /><br /> <span
						style="font-size: 1.3em; color: white; padding: 15px 0 0;">Adicionar
						perfil</span>
				</div>
				<div class="col-sm"></div>
			</div>
			<div class="row" style="padding: 50px;">
				<div class="align-items-center" style="width: 100%;">
					<button type="button" class="btn btn-outline-secondary">GERENCIAR
						PERFIOS</button>
				</div>
			</div>

		</div>
	</jsp:body>
</t:template>