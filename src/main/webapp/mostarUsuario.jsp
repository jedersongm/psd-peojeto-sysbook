<%-- 
    Document   : updateUsuario
    Created on : 28/02/2016, 10:46:25
    Author     : jederson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="br.edu.ifpb.ads.psd.projeto.converterInformacao.ConverterData"%>
<!DOCTYPE html>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<body class="templatemo-bg-image-2">
        <div class="container">
        <div class="col-md-12">	
       <form id="formCliente" class="form-horizontal templatemo-login-form-2" enctype="multipart/form-data" role="form" action="PerfilUsuario" method="post">

                    <div class="row">
                                    
                        <div class="col-md-12">
                            <h1><strong> Dados do Usuário
                    </div>
                    <div class="row">
                        <c:forEach items="${lista}" var="user">
                        <div class="templatemo-one-signin col-md-12">
                            <div class="imgPerfil">
                                <img src="${user.foto}" alt="" id="imagem" class="imagem-perfil">
                            </div>
                            <br>
                            
                            <div class="adicionar-foto">
                                <input type="file" required onchange="readURL(this)" accept="image/*" class="foto" name="foto" value="${user.foto}" >
                            </div>
                            
                    </div>
                            <div class="form-group">
                                <div class="col-md-12">	
                                    
                                    <label for="nome" class="control-label">Nome completo</label>
                                    <div class="templatemo-input-icon-container">
                                        <i class="fa fa-user"></i>
                                        <input name="nome" type = "text"  class="form-control" id="nomeCompleto" placeholder = "${user.nome}" value="${user.nome}" required>
                                    </div>
                                    
                                   
                                </div>
                            </div>
                                
                          
                            <div class="form-group">
                                <div class="col-md-6">		          	
                                    <label for="apelido" class="control-label">Apelido</label>
                                    <div class="templatemo-input-icon-container">
                                        <i class="fa fa-user"></i>
                                        <input name="apelido" type = "text"  class="form-control" id="apelido" placeholder = "${user.apelido}" value="${user.apelido}" required>
                                    </div>		            		            		            
                                </div> 
                                <div class="col-md-6">	
                                    
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${sessionScope.usuario.dataNascimento}" var="dataFormatada" />
                                    <label for="dataNascimento" class="control-label">Data Nascimento</label>
                                    <div class="templatemo-input-icon-container">
                                        <i class="fa fa-lock"></i>
                                        <input name="dataNascimento" type = "text" class = "form-control" id = "dataNascimento" placeholder = "${user.dataFormatada}"  value = "${user.dataFormatada}" required>
                                    </div>

                                </div>  
                            </div>
                            <div class="form-group">
                                <div class="col-md-6">
                                    <label for="email" class="control-label">E-mail</label>
                                    <div class="templatemo-input-icon-container">
                                        <i class="fa fa-user"></i>
                                        <input name="email" type = "email"  class="form-control" id="email" placeholder = "${user.email}" value = "${user.email}" readonly="true">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <label for="senha" class="control-label">Senha</label>
                                    <div class="templatemo-input-icon-container">
                                        <i class="fa fa-lock"></i>

                                        <input name="senha" type = "password" class = "form-control" id = "password" placeholder = "${user.senha}" value="${user.senha}" required>
                                    </div>
                                </div>                      	
                            </div>
                            
                            <div class="form-group">
                                <div class="col-md-6">
                                    <label for="estado" class="control-label">Estado</label>
                                    <div class="templatemo-input-icon-container">
                                        <i class="fa fa-lock"></i>

                                        <input name="estado" type = "text" class = "form-control" id = "estado" placeholder = "${user.estado}" value="${user.estado}" required>
                                    </div>
                                </div>    
                                
                                <div class = "col-md-6">
                                    <label for="cidade" class="control-label">Cidade</label>
                                    <div class="templatemo-input-icon-container">
                                        <i class="fa fa-lock"></i>

                                        <input name="cidade" type = "text" class = "form-control" id = "cidade" placeholder = "${user.cidade}" value="${user.cidade}" required>
                                    </div>
                                </div>                              
                                
                            </div>
                       
                  

                    <div class="form-group">
                        <div class = "col-md-6">

                            
                        </div>
                        
               </c:forEach>             
            <div class="form-group">
                <div class="col-md-12">
                    <button type="submit" class="botao"><span class="glyphicon glyphicon-hdd"></span> Salvar </button>
                </div>
            </div>
                    </div>

        </div>

</form>		      		      
</div>				 	
</div>
</div>
<link rel="icon" href="images/video.ico" type="image/x-icon">

        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet" type="text/css">
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
        <link href="css/templatemo_style.css" rel="stylesheet" type="text/css">
        <script src ="js/jQuery.min.js" type="text/javascript"></script>
        <script src="js/maskedinput.min.js" type="text/javascript"></script>
        <link href="css/configuracao.css" rel="stylesheet" type="text/css">        
        <script>
            $(function ($) {
                $("#dataNascimento").mask("99/99/9999", {placeholder: "dd/mm/aaaa"});
            });
        </script>
        <script>            
        function atualizafoto(){
        var fotoDigitada = document.forms["formCliente"]["clifoto"].value;
            document.forms["formCliente"]["mostraFoto"].src =   fotoDigitada;
              }
              window.onload = function(){
                  document.forms["formCliente"]["clifoto"].oninput = atualizafoto;
              };
        </script>
        <script>
            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        var imagem = document.getElementById('imagem');
                        imagem.src = e.target.result;
                    };

                    reader.readAsDataURL(input.files[0]);
                }
            }
            window.addEventListener('load', load);
        </script>
        
    
