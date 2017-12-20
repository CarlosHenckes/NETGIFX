<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="t" %>

<t:template>
	<jsp:attribute name="title">Login</jsp:attribute>
	
	<jsp:attribute name="header">
		<style type="text/css">
			body {
				background-image: url(<c:url value="/resources/images/bg_watch.jpg" />);
				background-repeat: no-repeat;
				background-size: cover;
			}
			li.btn-signin {
				display: none;
			}
			.dets {
				width:49%; background-color: rgba(255,255,255,0.55); padding:8px;
			}
			.dets-left{
				float:left;
			}
			.dets-right{
				float:right;
			}
			p, h3 {color:White;}
			.btn-warning {
				width: 180px;
			}
		</style>
	</jsp:attribute>
	
	<jsp:body>
	<div class="container">
			<div style="border: 1px solid #999; padding: 25px; background-color: rgba(255,255,255,0.3);">
				<div class="row">
					<div class="col-md-5">
						<div style="max-width:400px; height: auto; background-image:url(<c:url value="${ movie.previewImagePath }" />); 
								background-repeat: no-repeat; background-size: cover;">
							<img id="movplayer" src="<c:url value="/resources/images/movie_player.png" />" style="max-width:400px; cursor: pointer;" class="img-responsive" />
						</div>
						
					</div>
					<div class="col-md-7">
						<h3>${ movie.titulo }</h3>
						<p>${ movie.descricao }</p>
						
						<div class="clearfix">
							<div class="text-center dets dets-left">
								${ movie.classificacao }</div>
							<div class="text-center dets dets-right">
								${ movie.idioma }</div>
						</div>
						
						<div class="text-center" style="margin-top:25px;">
							<button id="addFav" value="${ movie.id }"
									class="btn btn-sm btn-warning">Adicionar aos favoritos</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			$(function(){
				$("#movplayer").click(function(){
					$(this).attr("src","<c:url value="${ movie.fileImagePath }" />");
				});
				
				// add to favs
				$("#addFav").on('click', function(){
					$.ajax({
						url: '<c:url value="/watch/addToFavs?id=${ movie.id }" />',
						  beforeSend: function() {
							  $("#addFav").prop("disabled",true);
						  }
					}).done(function(){
						$("#addFav").html("Adicionado aos Favoritos");
					});
				});
				
			});
		</script>
	</jsp:body>

</t:template>

