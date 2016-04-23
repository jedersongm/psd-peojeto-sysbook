<%-- 
    Document   : user
    Created on : 10/04/2016, 10:39:06
    Author     : jederson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body class = templatemo-bg-image-2>
       
        <div class="container">
            
            <div class="row">
                <h1>${visitante.apelido}</h1>
                <div class="col-md-5">                    
                    <img src="${sessionScope.visitante.foto}" alt="${sessionScope.visitante.apelido}" class="img-circle img-responsive" width="80" height="60">
                </div>

                <div id="description" class="col-md-5">
                    <h2>Descrição</h2>
                    <fieldset>                
                        <strong>Nome: ${sessionScope.visitante.nome}</strong><br>
                        Data Nascimento:${sessionScope.visitante.dataNascimento}        
                    </fieldset> 
                    <fieldset>
                        Cidade: ${sessionScope.visitante.cidade}
                        <br>
                        Estado: ${sessionScope.visitante.estado}
                    </fieldset>     
                </div>
            </div>
        </div>
    
    <div class="row">
        <c:if test="${sessionScope.visitante.id != sessionScope.usuario.id}">  
            <c:if test="${sessionScope.visitante.id != sessionScope.usuario.id}">
                <div id="amizade" class="col-md-5">
                    <h2>Amizade</h2>
                    <c:if test="${status == 'amigo'}">
                        <p>Vocês já são amigos.</p>
                    </c:if>
                    <c:if test="${status == 'pendente'}">
                        <p>Solicitação de amizade enviada.</p>
                    </c:if>
                    <c:if test="${status == 'nada'}">
                        <p>Envie uma solicatação de amizade.</p>
                        <br><br>
                        <form action="EnviarConviteAmizade" method="post">
                            <button class="btn btn-openid">Enviar Solicitação</button>
                        </form>
                    </c:if>
                    <c:if test="${status == 'responder'}">
                        <h4>Solicitação aguardando sua resposta!</h4><br>
                        <p>Aceite ou recuse.</p>
                        <form action="AceitaSolicitacaoPeloPerfilVisitante" method="post">
                            <button class="btn-success" name="resposta" title="Aceitar" value="true"><span class="glyphicon glyphicon-thumbs-up"></span></button>
                            <button class="btn-danger" name="resposta"  title="Recusar" value="false"><span class="glyphicon glyphicon-thumbs-down"></span></button>
                        </form>
                    </c:if>
                </div>
            </c:if>                
            <c:if test="${sessionScope.usuario.tipo == true}">
                <c:if test="${visitante.tipo == false}">
                    <div id="adm" class="col-md-5">
                        <h2>Permissões</h2>
                        <a href="TornarAdmin?id=${sessionScope.visitante.id}"><button type="button" class="btn btn-info">Tornar Administrador</button></a>
                    </div>
                </c:if>     
                <c:if test="${visitante.tipo == true}"> 
                    <c:if test="${sessionScope.usuario.email == 'admin@sysbook.com'}">
                        <div id="adm">
                            <h2>Permissões</h2>
                            <a href="TornarNormal?id=${sessionScope.visitante.id}"><button type="button" class="btn btn-info">Remover Permissão Admin</button></a>
                        </div>
                    </c:if>
                </c:if>      
            </c:if>
        </div>
    </c:if>