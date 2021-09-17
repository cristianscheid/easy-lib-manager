CREATE DATABASE easylib_manager;

\c easylib_manager;

CREATE TABLE definicoes (
valor_multa NUMERIC NOT NULL,
prazo_emprestimo BIGINT NOT NULL
);

CREATE TABLE usuario (
id BIGSERIAL NOT NULL PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
sobrenome VARCHAR(50) NOT NULL,
login VARCHAR(50) NOT NULL,
senha VARCHAR(50) NOT NULL,
cpf VARCHAR(14) NOT NULL
);

CREATE TABLE cliente (
id BIGSERIAL NOT NULL PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
sobrenome VARCHAR(50) NOT NULL,
cpf VARCHAR(14) NOT NULL,
email VARCHAR(150),
telefone VARCHAR(14),
celular VARCHAR(15)
);

CREATE TABLE autor (
id BIGSERIAL NOT NULL PRIMARY KEY,
nome_completo VARCHAR(150) NOT NULL
);

CREATE TABLE editora (
id BIGSERIAL NOT NULL PRIMARY KEY,
nome VARCHAR(50) NOT NULL
);

CREATE TABLE categoria (
id BIGSERIAL NOT NULL PRIMARY KEY,
nome VARCHAR(50) NOT NULL
);

CREATE TABLE livro (
id BIGSERIAL NOT NULL PRIMARY KEY,
isbn VARCHAR(13) NOT NULL,
ano INT NOT NULL,
titulo VARCHAR(150) NOT NULL,
is_disponivel BOOLEAN NOT NULL,
autor_id BIGINT REFERENCES autor (id),
editora_id BIGINT REFERENCES editora (id),
categoria_id BIGINT REFERENCES categoria (id)
);

CREATE TABLE emprestimo (
id BIGSERIAL NOT NULL PRIMARY KEY,
data_emprestimo DATE NOT NULL,
data_devolucao DATE,
cliente_id BIGINT REFERENCES cliente (id),
livro_id BIGINT REFERENCES livro (id)
);

--DEFINICOES
INSERT INTO definicoes VALUES ('0.00', '15');

--USUARIO admin
INSERT INTO usuario VALUES ('1','null', 'null', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'null');

--CATEGORIA
INSERT INTO categoria (nome) VALUES ('Selecione');
INSERT INTO categoria (nome) VALUES ('Biografias');
INSERT INTO categoria (nome) VALUES ('Coleções');
INSERT INTO categoria (nome) VALUES ('Comportamento');
INSERT INTO categoria (nome) VALUES ('Contos');
INSERT INTO categoria (nome) VALUES ('Crítica Literária');
INSERT INTO categoria (nome) VALUES ('Ficção Científica');
INSERT INTO categoria (nome) VALUES ('Folclore');
INSERT INTO categoria (nome) VALUES ('Genealogia');
INSERT INTO categoria (nome) VALUES ('Humor');
INSERT INTO categoria (nome) VALUES ('Infantojuvenis');
INSERT INTO categoria (nome) VALUES ('Jogos');
INSERT INTO categoria (nome) VALUES ('Jornais');
INSERT INTO categoria (nome) VALUES ('Literatura Brasileira');
INSERT INTO categoria (nome) VALUES ('Literatura Estrangeira');
INSERT INTO categoria (nome) VALUES ('Livros Raros');
INSERT INTO categoria (nome) VALUES ('Manuscritos');
INSERT INTO categoria (nome) VALUES ('Poesia');
INSERT INTO categoria (nome) VALUES ('Outros Assuntos');
INSERT INTO categoria (nome) VALUES ('Administração');
INSERT INTO categoria (nome) VALUES ('Agricultura');
INSERT INTO categoria (nome) VALUES ('Antropologia');
INSERT INTO categoria (nome) VALUES ('Arqueologia');
INSERT INTO categoria (nome) VALUES ('Arquitetura');
INSERT INTO categoria (nome) VALUES ('Artes');
INSERT INTO categoria (nome) VALUES ('Astronomia');
INSERT INTO categoria (nome) VALUES ('Biologia');
INSERT INTO categoria (nome) VALUES ('Botânica');
INSERT INTO categoria (nome) VALUES ('Brasil');
INSERT INTO categoria (nome) VALUES ('Ciência Política');
INSERT INTO categoria (nome) VALUES ('Ciências Exatas');
INSERT INTO categoria (nome) VALUES ('Cinema');
INSERT INTO categoria (nome) VALUES ('Comunicação');
INSERT INTO categoria (nome) VALUES ('Contabilidade');
INSERT INTO categoria (nome) VALUES ('Decoração');
INSERT INTO categoria (nome) VALUES ('Dicionários');
INSERT INTO categoria (nome) VALUES ('Didáticos');
INSERT INTO categoria (nome) VALUES ('Direito');
INSERT INTO categoria (nome) VALUES ('Documentos');
INSERT INTO categoria (nome) VALUES ('Ecologia');
INSERT INTO categoria (nome) VALUES ('Economia');
INSERT INTO categoria (nome) VALUES ('Engenharia');
INSERT INTO categoria (nome) VALUES ('Enciclopédias');
INSERT INTO categoria (nome) VALUES ('Ensino de Idiomas');
INSERT INTO categoria (nome) VALUES ('Filosofia');
INSERT INTO categoria (nome) VALUES ('Fotografia');
INSERT INTO categoria (nome) VALUES ('Geografia');
INSERT INTO categoria (nome) VALUES ('Guerra');
INSERT INTO categoria (nome) VALUES ('História do Brasil');
INSERT INTO categoria (nome) VALUES ('História Geral');
INSERT INTO categoria (nome) VALUES ('Informática');
INSERT INTO categoria (nome) VALUES ('Linguística');
INSERT INTO categoria (nome) VALUES ('Medicina');
INSERT INTO categoria (nome) VALUES ('Moda');
INSERT INTO categoria (nome) VALUES ('Música');
INSERT INTO categoria (nome) VALUES ('Pecuária');
INSERT INTO categoria (nome) VALUES ('Pedagogia');
INSERT INTO categoria (nome) VALUES ('Pintura');
INSERT INTO categoria (nome) VALUES ('Psicologia');
INSERT INTO categoria (nome) VALUES ('Saúde');
INSERT INTO categoria (nome) VALUES ('Sociologia');
INSERT INTO categoria (nome) VALUES ('Teatro');
INSERT INTO categoria (nome) VALUES ('Turismo');
INSERT INTO categoria (nome) VALUES ('Artesanato');
INSERT INTO categoria (nome) VALUES ('Auto ajuda');
INSERT INTO categoria (nome) VALUES ('Culinária');
INSERT INTO categoria (nome) VALUES ('Esoterismo');
INSERT INTO categoria (nome) VALUES ('Esportes');
INSERT INTO categoria (nome) VALUES ('Hobbies');
INSERT INTO categoria (nome) VALUES ('Religião');
INSERT INTO categoria (nome) VALUES ('Sexualidade');
INSERT INTO categoria (nome) VALUES ('Revistas');
INSERT INTO categoria (nome) VALUES ('Gibis');