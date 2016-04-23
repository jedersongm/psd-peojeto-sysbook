<%-- 
    Document   : user
    Created on : 10/04/2016, 10:39:06
    Author     : jederson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body class = templatemo-bg-image-2>
    <div class="container-fluid" >
        <div class="titulo">${livro.titulo}</div>
        <img src="${livro.fotoCapa}" alt="${livro.titulo}" class="img-circle3">
    </div>
    
    <div class="container">
        <div id="description">
            <h3>Descrição</h3>
            <fieldset>                
                <strong>Título: ${livro.titulo}</strong><br>
                Autores:${livro.autores}<br>        
            
                Tema: ${livro.tema}<br>
                
                Editora: ${livro.editora}<br>
            </fieldset>          

        </div>
   
    </div>
            
    <c:if test="${convidado.id != sessionScope.usuario.id}">
            <div id="amizade">
                <h2>Recomendações</h2>
                
                
                <a href="#" role="button" class="btn" data-toggle="modal">Recomendar</a>
 
                
            </div>
            <div class="col-sm-8">
		<div class="form-group">
			<label class="control-label">Rate This</label>
			<div class="rating-container rating-lg rating-animate"><div class="clear-rating clear-rating-active" title="Clear"><i class="glyphicon glyphicon-minus-sign"></i></div><div class="rating"><span class="empty-stars"><span class="star"><i class="glyphicon glyphicon-star-empty"></i></span><span class="star"><i class="glyphicon glyphicon-star-empty"></i></span><span class="star"><i class="glyphicon glyphicon-star-empty"></i></span><span class="star"><i class="glyphicon glyphicon-star-empty"></i></span><span class="star"><i class="glyphicon glyphicon-star-empty"></i></span></span><span style="width: 0%;" class="filled-stars"><span class="star"><i class="glyphicon glyphicon-star"></i></span><span class="star"><i class="glyphicon glyphicon-star"></i></span><span class="star"><i class="glyphicon glyphicon-star"></i></span><span class="star"><i class="glyphicon glyphicon-star"></i></span><span class="star"><i class="glyphicon glyphicon-star"></i></span></span></div><div class="caption"><span class="label label-default">Not Rated</span></div><input id="w15" class="hide" name="rating_21" data-krajee-rating="rating_7fae0bbe" type="text"></div>		</div>
	</div>
            
            
    </c:if>