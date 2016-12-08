<?php

/* Informações do Banco de Dados*/
$host = "138.68.15.190";
$username = "root";
$password = "asafaster";
$database = "cinecampo";
$table = "Breves";

/* Autenticação */
$conn = mysql_connect("$host","$username","$password") or die ("Impossível conectar!");
mysql_select_db("$database") or die ("Database inválida. Tente novamente.");

$field_nome = $_POST['field_nome'];
$field_sinopse = $_POST['field_sinopse'];
$field_duracao = $_POST['field_duracao'];
$field_genero = $_POST['field_genero'];
$field_imagem = $_POST['field_imagem'];
$field_trailer = $_POST['field_trailer'];


if (empty($_POST['field_sinopse']) || empty($_POST['field_nome']) || empty($_POST['field_duracao']) || empty($_POST['field_genero'])){
	header("location:homepage.php?insertEmpty=true");
}

else {
	
	mysql_set_charset('utf8');
	$field_nome = mysql_real_escape_string($field_nome);
	$field_sinopse = mysql_real_escape_string($field_sinopse);
	$field_duracao = mysql_real_escape_string($field_duracao);
	$field_genero = mysql_real_escape_string($field_genero);
	$field_trailer = mysql_real_escape_string($field_trailer);
	$field_imagem = mysql_real_escape_string($field_imagem); 

	$mysql_code = "INSERT INTO $table (nome, sinopse, duracao, genero, imagemurl, videourl) 
	VALUES ('$field_nome','$field_sinopse','$field_duracao', '$field_genero','$field_imagem','$field_trailer')";
	
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
