CREATE TABLE usuario
(
  id serial NOT NULL,
  email character varying(60) NOT NULL UNIQUE,
  nome character varying(100) NOT NULL,
  apelido character varying(60) NOT NULL,
  senha character varying(30) NOT NULL,
  cidade character varying(30) NOT NULL,
  estado character varying(2) NOT NULL,
  foto character varying(200),
  dataNascimento date NOT NULL,
  tipo boolean NOT NULL,

  CONSTRAINT usuario_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE usuario
  OWNER TO postgres;

 create table livro(
  id serial NOT NULL,
  isbn varchar(60) not null unique,
  titulo varchar(100) not null,
  anoPublicacao varchar (4) not null,
  editora varchar(60) not null,
  fotoCapa varchar(60),
  tema varchar(60),
  PRIMARY KEY (id)
);


create table grupo(
  id serial NOT NULL UNIQUE,
  emailUsuario character varying(60) NOT NULL,
  nome varchar(100) not null,
  descricao varchar (4) not null,
  PRIMARY KEY (id, emailUsuario),
  FOREIGN KEY (emailUsuario) REFERENCES usuario (email) ON DELETE RESTRICT ON UPDATE CASCADE
);

create table participagrupo(
  emailUsuario character varying(60) not null,
  idGrupo int not null,
  useradmin boolean,
  PRIMARY KEY(emailUsuario,idGrupo),
  FOREIGN KEY(emailUsuario) REFERENCES usuario(email) ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY(idGrupo) REFERENCES grupo(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

create table amizade(
  emailUsuario character varying(60) NOT NULL,
  emailAmigo character varying(60) NOT NULL,
  isAmigo boolean NOT NULL,
  since character varying(10) NOT NULL,
  PRIMARY KEY (emailUsuario, emailAmigo),
  FOREIGN KEY (emailUsuario) REFERENCES usuario (email) ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY (emailAmigo) REFERENCES usuario (email) ON DELETE RESTRICT ON UPDATE CASCADE
);

create table autores(
  id serial NOT NULL,
  nomeAutor varchar(100) not null,
  isbnLivro varchar(60) not null,
  PRIMARY KEY (nomeAutor,isbnLivro),
  FOREIGN KEY (isbnLivro) REFERENCES livro (isbn) ON DELETE RESTRICT ON UPDATE CASCADE
);

create table topico(
    id serial NOT NULL UNIQUE,
    isbnLivro varchar(60) not NULL,
    idGrupo integer NOT NULL,
    title varchar(50) NOT NULL,
    PRIMARY KEY(isbnLivro, idGrupo),
    FOREIGN KEY (isbnLivro) REFERENCES livro (isbn) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (idGrupo) REFERENCES grupo (id) ON DELETE RESTRICT ON UPDATE CASCADE
);

create table comentario(
  id serial NOT NULL,
  id_topico integer NOT NULL,
  emailUsuario varchar(30) not null,
  descricao varchar(255) NOT NULL,
  PRIMARY KEY (id, id_topico, emailUsuario),
  FOREIGN KEY (id_topico) REFERENCES topico (id) ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY (emailUsuario) REFERENCES usuario (email) ON DELETE RESTRICT ON UPDATE CASCADE
);

create table recomendalivro(
    emailUsuario varchar(30) not null,
    emailDestino varchar(30) not null,
    isbnLivro varchar(60) not NULL,
    visualized boolean not null,
    PRIMARY KEY(emailUsuario, emailDestino, isbnLivro),
    FOREIGN KEY (emailUsuario) REFERENCES usuario (email) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (emailDestino) REFERENCES usuario (email) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (isbnLivro) REFERENCES livro (isbn) ON DELETE RESTRICT ON UPDATE CASCADE
    
);

INSERT INTO usuario(email, nome, apelido, senha, cidade, estado, foto,dataNascimento, tipo) VALUES('admin@sysbook.com','Administrador','admin','123','Cajazeiras','PB','imagensPerfil/Admin.jpg','0002-11-30',true);
