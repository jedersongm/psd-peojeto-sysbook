<%-- 
    Document   : user
    Created on : 10/04/2016, 10:39:06
    Author     : jederson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body class = "tab-content">
    <div class="container-fluid" >
        <div class="titulo">${livroRecomendado.titulo}</div>
        <img src="${livroRecomendado.fotoCapa}" alt="${livroRecomendado.titulo}" class="img-circle3">
    </div>

    <div class="container">
        <div id="description">
            <h3>Descrição</h3>
            <fieldset>                
                <strong>Título: ${livroRecomendado.titulo}</strong><br>
                ISBN: ${livroRecomendado.isbn}<br>
                Autores:${livroRecomendado.autores}<br>        

                Tema: ${livroRecomendado.tema}<br>

                Editora: ${livroRecomendado.editora}<br>
            </fieldset>          

        </div>

    </div>

    <c:if test="${convidado.id != sessionScope.usuario.id}">
        <div class="row">
            <div id="recomendation" class="col-md-5">
                <!--<h3>Recomendações</h3>-->
                <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#recomendar"><span class="glyphicon glyphicon-tag"></span> Recomendar</button>
            </div>

            <div id="avaliation" class="col-md-5">
                <h3>Avaliação</h3>
                <form action="#" method="post">
                    <div class="form-group">
                        <label class="control-label" for="avaliation">Rate This</label>
                        <input id="avaliation" class="rating rating-loading form-control" data-min="0" data-max="5" data-step="1"/>
                        <button type="submit" class="btn btn-primary" value=""></button>
                    </div>
                </form>
            </div>

        </div>
    </c:if>


    <!-- Modal -->
    <div id="recomendar" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Selecione as pessoas</h4>
                </div>
                <div class="modal-body">
                    <form action="RecomendarLivro" method="post" role="form">
                        <input type="hidden" name="isbn" value="${livro.isbn}"/>
                        <c:forEach items="${amigos}" var="user">
                            <label class="checkbox">
                                <input type="checkbox" name="users" value="${user.email}">${user.apelido}
                            </label>
                        </c:forEach>
                        <button type="submit" class="btn btn-success">Enviar</button>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>

    <script>
        // initialize with defaults
        $("#avaliation").rating();

        // with plugin options
        $("#avaliation").rating(
                {min: 1, max: 5, step: 1, size: 'lg'}
        );
    </script>   