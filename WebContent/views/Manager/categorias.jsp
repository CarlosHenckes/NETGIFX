<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="t" %>

<t:template_manager>
	<jsp:attribute name="title">Gerênciar Aplicação</jsp:attribute>

	<jsp:attribute name="header">
		<style type="text/css">
		body {
			background-color: #fff;
		}
		</style>
	</jsp:attribute>
	
	<jsp:body>
		<div class="container">
			<h4>CADASTRAR CATEGORIAS</h4>
			
			<c:choose>
				<c:when test="${ not empty errmsg }">
					<div class="alert alert-danger">
						${errMsg}
					</div>
				</c:when>
			</c:choose>
			
			<c:choose>
				<c:when test="${ not empty msg }">
					<div class="alert alert-success">
						${msg}
					</div>
				</c:when>
			</c:choose>
			
			<form id="formcat" action="<c:url value="/manager/newCategory" />" method="post">
				<div class="row">
					<div class="col-md-6">
						<div class="row">
							<div class="col-md-10 form-group">
								<label for="categoria">Nome Categoria:</label>
								<input type="text" name="categoria" id="categoria" class="form-control" />
								<input type="hidden" id="idcategoria" />
							</div>
						</div>
						
							<input type="submit" class="btn btn-primary btnSalvar" value="SALVAR" />
					</div>
					<div class="col-md-6">
						<h6>Categorias Cadastradas: </h6>
						<ul class="nav flex-column">
							<c:forEach var="c" items="${ categorias }">
								<li class="nav-item">${ c.categoria }</li>
							</c:forEach>
						</ul>
					</div>
				</div>

			</form>
		</div>
		<script type="text/javascript">
			$(document).ready(function(){
				setTimeout(function(){
					$(".alert").hide(800);
				}, 3500);
				
				// block submit button
				$("#formcat").on("submit",function(){
						$("#btnSalvar").addClass("disabled");
					
				});
			});
		</script>
	</jsp:body>
</t:template_manager>