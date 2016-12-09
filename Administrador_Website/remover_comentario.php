<?php
      /* Informações do Banco de Dados*/
      $host = "138.68.15.190";
      $username = "root";
      $password = "asafaster";
      $database = "cinecampo";
      $table = "Comentarios";

      $field_id = $_POST['field_id'];

      /* Autenticação */
      $conn = mysql_connect("$host","$username","$password") or die ("Impossível conectar!");
      mysql_select_db("$database") or die ("Database inválida. Tente novamente.");

      $sql = "DELETE FROM Comentarios where identificador = $field_id";
      $result = mysql_query($sql);
      if($result){
		header("location:comentarios.php?insertSuccess=true");
		$a++;
	} 
	else {
		header("location:comentarios.php?insertFailed=true");
	}	
?>