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
	<div class="progress" style="height: 4px;">
	  <div id="prog" class="progress-bar bg-info" role="progressbar" style="width: 0%; height: 3px;"
	  		aria-valuenow="0" aria-valuemin="0" aria-valuemax="60"></div>
	</div>
		<div class="container">
			<h4>CADASTRAR FILMES</h4>
			
			<c:choose>
				<c:when test="${ not empty errmsg }">
					<div class="alert alert-danger">
						${ errMsg }
					</div>
				</c:when>
			</c:choose>
			
			<c:choose>
				<c:when test="${ not empty msg }">
					<div class="alert alert-success">
						${ msg }
					</div>
				</c:when>
			</c:choose>
			
			<form method="post" id="formcad" action="<c:url value="/manager/newMovie" />" enctype="multipart/form-data">

				<div class="row">
					<div class="col-md-6">
						<div class="row">
							<div class="col-md-10 form-group">
								<label for="titulo">Título:</label>
								<input type="text" name="titulo" id="titulo" class="form-control" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-10 form-group">
								<label for="categoria">Categoria:</label>
								<select id="categoria" class="form-control" name="categoria">
									<option>... selecione a categoria</option>
									<c:forEach var="c" items="${ categorias }">
										<option value="${ c.id }">${ c.categoria }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-10 form-group">
								<label for="sinopse">Sinopse:</label>
								<textarea rows="2" name="descricao" cols="" class="form-control"></textarea>
							</div>
						</div>
						<div class="row">
							<div class="col-md-10 form-group">
								<label for="sinopse">Classificação:</label>
								<select class="form-control" name="classificacao">
									<option>... seleciona a classificação</option>
									<option value="livre">livre</option>
									<option value="acima de 13 anos">acima de 13 anos</option>
									<option value="acima de 14 anos">acima de 14 anos</option>
									<option value="acima de 15 anos">acima de 15 anos</option>
									<option value="acima de 16 anos">acima de 16 anos</option>
									<option value="acima de 17 anos">acima de 17 anos</option>
									<option value="acima de 18 anos">acima de 18 anos</option>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-10 form-group">
								<label for="sinopse">Idioma:</label>
								<select class="form-control" name="idioma">
									<option>... selecione o idioma</option>
									<c:forEach var="i" items="${ idiomas }">
										<option value="${ i }">${ i }</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row">
							<div class="col-md-10 form-group">
								<label for="file">Selecionar video: </label> <input type="file"
									name="file" id="file" class="form-control" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<img src="<c:url value="/resources/images/dot.png" />" id="prev" />
							</div>
						</div>
	
					</div>
				</div>
				<div class="row float-right">
					<div class="col-md-12">
						<input type="submit" id="btnSalvar" value="SALVAR" class="btn btn-primary" />
					</div>
				</div>
						
			</form>
		</div>
		<script type="text/javascript">
			$(function(){
				// observe file input change
				$("#file").change(function() {
				  readURL(this);
				});
				
				// block submit button
				$("#formcad").on("submit",function(){
					$("#btnSalvar").addClass("disabled");
					var w = 0;
					setInterval(() => {
						$("#prog").width(w);
						w = w + 12;
					}, 1000);	
				});
				
				// clean message box
				setTimeout(function(){
					$(".alert").hide(800);
				}, 3500);
			});
			function readURL(input) {
			  if (input.files && input.files[0]) {
			    var reader = new FileReader();

			    reader.onload = function(e) {
			      $('#prev').attr('src', e.target.result);
			    }

			    reader.readAsDataURL(input.files[0]);
			  }
			}
		</script>
	</jsp:body>
</t:template_manager>