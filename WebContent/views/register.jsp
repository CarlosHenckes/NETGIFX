<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="t"%>
<t:template>
	<jsp:attribute name="title">Login</jsp:attribute>
	<jsp:attribute name="header">
		<style type="text/css">
			body {
				background-image: url(<c:url value="/resources/images/bg_register.jpg" />);
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
</style>
	</jsp:attribute>

	<jsp:body>
	<div class="container">
		<form action="<c:url value="/register" />" method="post">		
			<div class="row">
				<div class="form-group col-md-4">
					<label for="name">Usuário</label>
					<input type="text" id="name" name="nome" class="form-control" required />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-4">
					<label for="email">EMail</label>
					<input type="text" id="email" name="email" class="form-control" required />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-4">
					<label for="senha">Senha</label>
					<input type="password" id="senha" name="senha" class="form-control" required />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-4">
					<input type="submit" value="CADASTRAR" class="btn btn-primary" />
				</div>
			</div>
		</form>
	</div>
		
	</jsp:body>

</t:template>