<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <body  id="fundo" class="textoPreto white-background">
        
        <div class="margin-top-body">
            <div class="container nav-justified">
                <a href="logado.jsp"><h1 class="text-center textoPreto">Social Movies</h1></a>
                <br>    
                <ul class="nav nav-tabs navbar-static-top" id="menuNav">
                    <li class="active"><a data-toggle="tab" href="#topicos">Tópicos</a></li>
                    <li><a data-toggle="tab" href="#participantes">Usuários</a></li>
                    <c:if test="${participa == true}">
                        <li><a data-toggle="tab" href="#criar">Criar tópico</a></li>
                    </c:if>
                        <c:if test="${participa == false}">
                        <li><a data-toggle="tab" href="#participar">Participar</a></li>
                        </c:if>
                        <c:if test="${grupoSelecionado.idUsuario == user.id}">
                        <li><a data-toggle="tab" href="#remover">Remover</a></li>
                        </c:if>
                    <li class="flutuarADireita"><a data-toggle="tab" href="#buscar">Buscar</a></li>
                </ul>

                <div class="tab-content">
                    <div id="topicos" class="tab-pane fade text-center in active">
                        <section class="text-center margin-top table-responsive modal-header modal-dialog">
                            <h2><a>${grupoSelecionado.nomeDoGrupo}</a></h2>
                            <p>${grupoSelecionado.descricao}</p>
                            <br>
                        </section>
                        <c:if test="${topicos != null}"><h3>Tópicos</h3></c:if>
                        <br><br>
                        <c:if test="${participa == true}">
                            <div class="row modal-header">
                            <c:forEach items="${topicos}" var="t">
                                <div class="media-left col-sm-3">
                                    <h4><a href="ControleVerTopico?idTopico=${t.id}">${t.nomeDoTopico}</a></h4>
                                </div>
                            </c:forEach>
                        </div>
                        </c:if>
                        <c:if test="${participa == false}">
                            <h4>Só para usuarios que participam do grupo</h4>
                            <h5><a data-toggle="tab" href="#participar">Clique aqui</a> e venha participar</h5>
                        </c:if>
                    </div>
                    <div id="participantes" class="tab-pane fade text-center">
                        <div class="margin-top table-responsive modal-header">
                            <h3>Usuários do grupo</h3>
                            <br><br><br>
                            <div class="row">
                                <c:forEach items="${usuariosDoGrupo}" var="us">
                                    <div class="col-sm-2">
                                        <img src="${us.foto}" alt="${us.apelido}" title="${us.apelido}" class="img-solicitacao img-circle">
                                        <c:if test="${us.id != user.id}"> 
                                            <h4 class="text-capitalize"><a href="ControlePerfilVisitante?idDoUsuario=${us.id}">${us.apelido}</a></h4>
                                            </c:if>
                                            <c:if test="${us.id == user.id}"> 
                                            <h4 class="text-capitalize"><a href="editarPerfil.jsp">Você</a></h4>
                                        </c:if>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div id="criar" class="tab-pane fade text-center">
                        <div class="text-center margin-top table-responsive modal-header modal-dialog">
                            <br>
                            <h3>Criar novo tópico</h3>
                            <br>
                            <div class="form-group">
                                <form action="ControleCriarTopico" method="post">
                                    <input class="botaoMedio margin-top" name="nomeDoTopico" type="text" placeholder="Nome do tópico">
                                    <input class="botaoMedio margin-top" name="nomeDoFilme" type="text" placeholder="Nome do Filme" list="listaDeFilmes">
                                    <datalist id="listaDeFilmes">
                                        <c:forEach items="${nomeDosFilmes}" var="a">
                                            <option value="${a}"></option>
                                        </c:forEach>
                                    </datalist>
                                    <input class="botaoPequeno margin-top" type="submit" value="Criar">
                                </form>
                            </div>
                        </div>
                    </div>
                    <c:if test="${participa == false}">
                        <div id="participar" class="tab-pane text-center fade">
                            <br>
                            <h3>Clique em participar e faça parte deste grupo!</h3>
                            <div class="form-group modal-header modal-dialog">
                                <form action="ControleParticiparGrupo" method="post">
                                    <input type="submit" value="Participar" class="botaoPequeno margin-top">
                                </form>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${grupoSelecionado.idUsuario == user.id}">
                        <div id="remover" class="tabe-pane text-center fade">
                            <br>
                            <h3>Deseja realmente remover esse grupo?</h3>
                            <div class="form-group modal-header modal-dialog">
                                <form action="ControleRemoveGrupo" method="post">
                                    <input class="botaoPequeno margin-top" type="submit" value="Remover">  
                                </form>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>