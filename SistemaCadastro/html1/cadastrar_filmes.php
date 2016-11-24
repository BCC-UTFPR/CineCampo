<?php

/* Informações do Banco de Dados*/
$host = "localhost";
$username = "root";
$password = "asafaster";
$database = "cinecampo";
$table = "CadastrarFilme";

/* Criação da Tabela CadastrarFilme

create table CadastrarFilme(id int primary key not null auto_increment, nomeFilme varchar(255), sinopse varchar(255), duracao varchar(255), genero varchar(255), inicioSessao varchar(255), fimSessao varchar(255), sessaoUm varchar(255), sessaoDois varchar(255), tresD varchar(255), imagem blob, nome_imagem varchar(255));

*/

/* Autenticação */
$conn = mysql_connect("$host","$username","$password") or die ("Impossível conectar!");
mysql_select_db("$database") or die ("Database inválida. Tente novamente.");

$field_nome_filme = $_POST['field_nome_filme'];
$field_sinopse = $_POST['field_sinopse'];
$field_duracao = $_POST['field_duracao'];
$field_genero = $_POST['field_genero'];
$field_inicio_sessoes = $_POST['field_inicio_sessoes'];
$field_termino_sessoes = $_POST['field_termino_sessoes'];
$field_sala_1 = $_POST['field_sala_1'];
$field_sala_2 = $_POST['field_sala_2'];
$field_sala3d= $_POST['field_sala3d'];
$field_imagem = $_POST['field_imagem'];


if (empty($_POST['field_sinopse']) || empty($_POST['field_nome_filme']) || empty($_POST['field_duracao']) || empty($_POST['field_genero']) || empty($_POST['field_inicio_sessoes']) || empty($_POST['field_termino_sessoes'])  ){
	header("location:homepage.php?insertEmpty=true");
}

else {

	$field_sala3d = 'SIM';
	if(empty($_POST['field_sala_1'])){
		$field_sala_1 = 'NAO';
	}
	if(empty($_POST['field_sala_2'])){
		$field_sala_2 = 'NAO';
	}
	$field_nome_filme = mysql_real_escape_string($field_nome_filme);
	$field_sinopse = mysql_real_escape_string($field_sinopse);
	$field_duracao = mysql_real_escape_string($field_duracao);
	$field_genero = mysql_real_escape_string($field_genero);
	$field_inicio_sessoes = mysql_real_escape_string($field_inicio_sessoes);
	$field_termino_sessoes = mysql_real_escape_string($field_termino_sessoes);
	$field_sala_1 = mysql_real_escape_string($field_sala_1);
	$field_sala_2 = mysql_real_escape_string($field_sala_2);
	$field_sala3d = mysql_real_escape_string($field_sala3d);
	$imagem_nome =$_FILES['field_imagem']['name']; 
	$imagem_content = addslashes(file_get_contents($_FILES['field_imagem']['tmp_name']));
	
	$mysql_code = "INSERT INTO $table (nomeFilme, sinopse, duracao, genero, inicioSessao, fimSessao, sessaoUm, sessaoDois, tresD, imagem, nome_imagem) 
	VALUES ('$field_nome_filme','$field_sinopse','$field_duracao', '$field_genero', '$field_inicio_sessoes','$field_termino_sessoes','$field_sala_1','$field_sala_2','$field_sala3d','$imagem_content','$imagem_nome' )";
	
	$query = mysql_query($mysql_code);
	
	if($query){
		header("location:homepage.php?insertSuccess=true");
		$a++;
	} 
	
	else {
		header("location:homepage.php?insertFailed=true");
	}	
}
?>
