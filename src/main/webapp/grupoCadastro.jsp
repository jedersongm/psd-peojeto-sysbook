<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <body  id="fundo" class="textoPreto white-background">
        <%@include file="grupos.jsp" %>
        <div class="margin-top-body">
            <div class="container nav-justified">
                <a href="logado.jsp"><h1 class="text-center textoPreto">Social Movies</h1></a>
                <br>    
                <ul class="nav nav-tabs navbar-static-top" id="menuNav">
                    <li><a data-toggle="tab" href="#feed" id="home">Feed</a></li>
                    <li><a data-toggle="tab" href="#amigos">Amigos</a></li>
                    <li><a data-toggle="tab" href="#generos">Gêneros</a></li>
                        <c:if test="${sessionScope.user.tipo == true}">
                        <li><a data-toggle="tab" href="#cadastroFilmes">Cadastrar Filmes</a></li>
                        </c:if>
                    <li class="active"><a data-toggle="tab" href="#grupos">Grupos</a></li>
                    <li class="flutuarADireita"><a data-toggle="tab" href="#buscar">Buscar</a></li>
                </ul>

                <div class="tab-content">
                    
                    <c:if test="${sessionScope.user.tipo == true}">
                        <%@include file="cadastrarLivros.jsp" %>
                    </c:if>
                    <div id="grupos" class="tab-pane text-center active">
                        <br>
                        <c:if test="${sessionScope.grupos == null}">
                            <h3>Você ainda não faz parte de nenhum grupo!</h3>
                        </c:if>
                        <c:if test="${sessionScope.grupos != null}">
                            <h3>Seus grupos!</h3>
                        </c:if>
                        <br>
                        <div class="modal-dialog text-center">
                            <a href="#" id="newGroup">Criar novo grupo</a>
                        </div>
                        <div class="row">
                            <c:forEach items="${sessionScope.grupos}" var="g">
                                <div class="col-sm-3 modal-header">
                                    <br>
                                    <h2><a href="ControleVerGrupo?idGrupo=${g.id}">${g.nomeDoGrupo}</a></h2>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>