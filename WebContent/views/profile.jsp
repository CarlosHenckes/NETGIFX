<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="t" %>
<t:template>
	<jsp:attribute name="title">Meus favoritos</jsp:attribute>
	
	<jsp:attribute name="header">
		<style type="text/css">
			body {
				background-image: url(<c:url value="/resources/images/bg_favs.jpg" />);
				background-repeat: no-repeat;
				background-size: cover;
			}
			li.btn-signin {
				display: none;
			}
			h3 {
				color: White;
			}
			.caption h5 {
				color: #000; font-weight: bold;
			}
			.jumbo p, h4 {
				color: White;
			}
			a.glyphicon {
				text-decoration: none;
			}
			.pad {
				margin-top: 8px;
			}
		</style>
	</jsp:attribute>
	
	<jsp:body>
		<div class="container">
			<div class="row" style="background-color: rgba(255,255,255,0.10);">
				<div class="col-md-4 jumbo">
					<h4>Usuário: ${ user.nome }</h4>		
				</div>
				<div class="col-md-4 jumbo pad">
					<p>Email: ${ user.email }</p>					
				</div>
				<div class="col-md-4 jumbo pad">
					<p>Assinante desde: <tags:localDate date="${ user.dataCadastro }"/></p>
				</div>
			</div>
			
			<!-- FAVORITOS -->
			<h3>Meus favoritos</h3>
			
			<c:choose>
				<c:when test="${ fn:length(user.filmes) gt 0 }">
					<c:forEach var="filme" items="${ user.filmes }">	
					  <div class="col-sm-6 col-md-2">
					    <div class="thumbnail">
					      <img src="${ filme.previewImagePath }" alt="PLAY - ${ filme.titulo }">
					      	<div class="caption">
					        	<h5>${ filme.titulo }</h5>
					        	<p class="text-right"><a href="<c:url value="/watch?id=${ filme.id }" />" 
			        					class="glyphicon glyphicon-play-circle" role="button" style="font-size:1.5em;"></a></p>
					        </div>
					    </div>
					  </div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p style="color: White;">Adicione os filmes que você gosta à sua lista.</p>
				</c:otherwise>
			</c:choose>
				
		</div>
	</jsp:body>
</t:template>