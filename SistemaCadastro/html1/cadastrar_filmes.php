<?php

/* Informações do Banco de Dados*/
$host = "localhost";
$username = "root";
$password = "asafaster";
$database = "cinecampo";
$table = "CadastrarFilme";

/* Autenticação */
$conn = mysql_connect("$host","$username","$password") or die ("Impossível conectar!");
mysql_select_db("$database") or die ("Database inválida. Tente novamente.");

$field_nome_filme = $_POST['field_nome_filme'];
$field_sinopse = $_POST['field_sinopse'];
$field_duracao = $_POST['field_duracao'];
$field_genero = $_POST['field_genero'];


if (empty($_POST['field_sinopse']) || empty($_POST['field_nome_filme']) || empty($_POST['field_duracao']) || empty($_POST['field_genero'])){
	header("location:homepage.php?insertEmpty=true");
}

else {
	$field_nome_filme = mysql_real_escape_string($field_nome_filme);
	$field_sinopse = mysql_real_escape_string($field_sinopse);
	$field_duracao = mysql_real_escape_string($field_duracao);
	$field_genero = mysql_real_escape_string($field_genero);
	/* IMAGEM 
	$imagem_nome =$_FILES['field_imagem']['name']; 
	$imagem_content = addslashes(file_get_contents($_FILES['field_imagem']['tmp_name']));
	
	list($field_id_viagem,$field_id_funcionario,$field_id_veiculo) = explode("/", $field_genero);
	list($string_id_viagem,$id_viagem) = explode (":", $field_id_viagem);
	list($string_id_funcionario,$id_funcionario) = explode (":", $field_id_funcionario);
	list($string_id_funcionario,$id_veiculo) = explode (":", $field_id_veiculo);
	*/
	$mysql_code = "INSERT INTO $table (nomeFilme, sinopse, duracao, genero) 
	VALUES ('$field_nome_filme','$field_sinopse','$field_duracao', '$field_genero')";
	
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
