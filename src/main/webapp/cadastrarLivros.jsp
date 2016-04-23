<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="tag1"%>
<body class="templatemo-bg-image-2">
    <div class="container">
        <div class="col-md-12">	
            <form id="formCliente" class="form-horizontal templatemo-login-form-2" enctype="multipart/form-data" role="form" action="CadastrarLivros" method="post">

                <div class="row">

                    <div class="col-md-12">
                        <h1><strong>Cadastrar Livro
                                </div>
                                <div class="row3">
                                    <div class="templatemo-one-signin col-md-12">
                                        <div class="templatemo-one-signin col-md-12">
                                        <div class="imgLivro">
                                            <img src="${livro.foto}" alt="" id="imagem" class="imgLivro">
                                        </div>
                                        <br>

                                        <div class="adicionar-foto">
                                            <input type="file" required onchange="readURL(this)" accept="image/*" class="foto" name="foto" >
                                        </div>

                                    </div>

                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-12">		          	
                                            <label for="isbn" class="control-label">ISBN</label>
                                            <div class="templatemo-input-icon-container">
                                                <i class="fa fa-user"></i>
                                                <input name="isbn" type = "text"  class="form-control" id="isbnLivro" placeholder = "ISBN" required>
                                            </div>


                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <div class="col-md-6">		          	
                                            <label for="tirulo" class="control-label">Título</label>
                                            <div class="templatemo-input-icon-container">
                                                <i class="fa fa-user"></i>
                                                <input name="titulo" type = "text"  class="form-control" id="titulo" placeholder = "título" required>
                                            </div>		            		            		            
                                        </div> 
                                        <div class="col-md-6">	


                                            <label for="anoPublicacao" class="control-label">Ano de Publicação</label>
                                            <div class="templatemo-input-icon-container">
                                                <i class="fa fa-lock"></i>

                                                <input name="anoPublicacao" type = "text" class = "form-control" id = "anoPublicacao" placeholder = "Ano de publicação" required>
                                            </div>

                                        </div>  
                                    </div>


                                    <div class="form-group">
                                        <div class="col-md-6">
                                            <label for="editora" class="control-label">Editora</label>
                                            <div class="templatemo-input-icon-container">
                                                <i class="fa fa-user"></i>
                                                <input name="editora" type = "text"  class="form-control" id="editora" placeholder = "editora" required>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="tema" class="control-label">Tema</label>
                                            <div class="templatemo-input-icon-container">
                                                <i class="fa fa-lock"></i>

                                                <input name="tema" type = "text" class = "form-control" id = "tema" placeholder = "tema" required>
                                            </div>
                                        </div>                      	
                                    </div>
                                        
                                    <div class="form-group">

                                        <br>
                                        <br>

                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <input type="submit" class="botao">
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
                                    function atualizafoto() {
                                        var fotoDigitada = document.forms["formCliente"]["clifoto"].value;
                                        document.forms["formCliente"]["mostraFoto"].src = fotoDigitada;
                                    }
                                    window.onload = function () {
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

