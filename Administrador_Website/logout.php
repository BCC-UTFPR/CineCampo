<?php 
    session_start();
    session_destroy();
    unset ($_SESSION['usuario']);
    unset ($_SESSION['senha']);
    header('location:index.php');

?> 