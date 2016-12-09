<?php
      /* Informações do Banco de Dados*/
      $host = "138.68.15.190";
      $username = "root";
      $password = "asafaster";
      $database = "cinecampo";
      $table = "Breves";

      $field_id = $_POST['field_id'];

      /* Autenticação */
      $conn = mysql_connect("$host","$username","$password") or die ("Impossível conectar!");
      mysql_select_db("$database") or die ("Database inválida. Tente novamente.");

      $sql = "DELETE FROM Breves where identificador = $field_id";
      $result = mysql_query($sql);
      if($result){
		header("location:filmes_breves.php?insertSuccess=true");
		$a++;
	} 
	else {
		header("location:filmes_breves.php?insertFailed=true");
	}	
?>