<%-- 
    Document   : user
    Created on : 10/04/2016, 10:39:06
    Author     : jederson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body class = templatemo-bg-image-2>
    <div class="container-fluid">
        <h1>${visitante.apelido}</h1>
        <img src="${sessionScope.visitante.foto}" alt="${sessionScope.visitante.apelido}" class="img-circle img-responsive" width="80" height="60">
    </div>
    <!--
    <nav class="navbar navbar-left">
        <div class="container-fluid ">    
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Descrição</a></li>
    <c:if test="${sessionScope.visitante.id != sessionScope.usuario.id}">
    <li><a href="#">Amizade</a></li>                    
        <c:if test="${sessionScope.usuario.tipo == true}">
        <li><a href="#admin">Admin</a></li> 
        </c:if>        
    </c:if>
</ul>
</div>
</nav>    
    -->
    <div class="container">
        <div id="description">
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
        <c:if test="${sessionScope.visitante.id != sessionScope.usuario.id}">
            <div id="amizade">
                <h2>Amizade</h2>
                <c:if test="${status == 'amigo'}">
                    <h3>Vocês já são amigos.</h3>
                </c:if>
                <c:if test="${status == 'pendente'}">
                    <h3>Solicitação de amizade enviada.</h3>
                </c:if>
                <c:if test="${status == 'nada'}">
                    <h3>Vocês ainda não são amigos!</h3><br>
                    <p>Envie uma solicatação de amizade.</p>
                    <br><br>
                    <form action="EnviarConviteAmizade" method="post">
                        <button class="btn btn-openid">Enviar Solicitação</button>
                    </form>
                </c:if>
                <c:if test="${status == 'responder'}">
                    <h3>Solicitação aguardando sua resposta!</h3><br>
                    <p>Aceite ou recuse.</p>
                    <br><br>
                    <form action="ControleAceitaSolicitacaoPeloPerfilVisitante" method="post">
                        <button class="botaoPequeno" name="resposta" value="true">Aceitar</button>
                        <button class="botaoPequeno" name="resposta" value="false">Recusar</button>
                    </form>
                </c:if>
            </div>
        </c:if>
        <c:if test="${convidado.tipo == false}">
            <div id="adm">
                <h2>Permissões</h2>
                <a href="TornarAdmin?id=${sessionScope.visitante.id}"><button type="button" class="btn btn-default">Tornar Administrador</button></a>
            </div>
        </c:if>
        <c:if test="${convidado.tipo == true}"> 
            <c:if test="${sessionScope.usuario.email == 'admin@sysbook.com'}">
                <div id="adm">
                    <h2>Permissões</h2>
                    <a href="TornarNormal?id=${sessionScope.visitante.id}"><button type="button" class="btn btn-default">Remover Permissão Admin</button></a>
                </div>
            </c:if>
        </c:if>
    </div>