<?php

/* Informações do Banco de Dados*/
$host = "localhost";
$username = "fronche2_admin";
$password = "adminadmin";
$database = "fronche2_engsoftware";
$table = "Gastos";

/* Autenticação */
$conn = mysql_connect("$host","$username","$password") or die ("Impossível conectar!");
mysql_select_db("$database") or die ("Database inválida. Tente novamente.");

$field_descricao = $_POST['field_descricao'];
$field_tipo = $_POST['field_tipo'];
$field_valor = $_POST['field_valor'];
$field_viagem = $_POST['viagem_list'];


if (empty($_POST['field_tipo']) || empty($_POST['field_descricao']) || empty($_POST['field_valor']) || empty($_FILES['field_imagem']['name']) || empty($_POST['viagem_list'])){
	header("location:homepage.php?insertEmpty=true");
}

else {
	$field_descricao = mysql_real_escape_string($field_descricao);
	$field_tipo = mysql_real_escape_string($field_tipo);
	$field_valor = mysql_real_escape_string($field_valor);
	
	$imagem_nome =$_FILES['field_imagem']['name']; 
	$imagem_content = addslashes(file_get_contents($_FILES['field_imagem']['tmp_name']));
	
	list($field_id_viagem,$field_id_funcionario,$field_id_veiculo) = explode("/", $field_viagem);
	list($string_id_viagem,$id_viagem) = explode (":", $field_id_viagem);
	list($string_id_funcionario,$id_funcionario) = explode (":", $field_id_funcionario);
	list($string_id_funcionario,$id_veiculo) = explode (":", $field_id_veiculo);

	
	$mysql_code = "INSERT INTO $table (Imagem_Content, Imagem_Nome, DescricaoGastos, TipoGasto, ValorGasto, Viagem_ID_Viagem, Viagem_Funcionario_ID_Funcionario,Viagem_Veiculo_ID_Veiculo) 
	VALUES ('$imagem_content','$imagem_nome','$field_descricao', '$field_tipo', '$field_valor', $id_viagem, $id_funcionario, $id_veiculo)";
	
	$query = mysql_query($mysql_code);
	
	if($query){
		header("location:homepage.php?insertSuccess=true");

	} 
	
	else {
		header("location:homepage.php?insertFailed=true");
	}	
}
?>
