<?php 
    require_once("../config/autoload.php");
    require_once("../config/constants.php");

    use models\Band as Band;
    use daos\Bands as Bands;

    $repo = new Bands();    // no pude hacerlo andar
    if($_POST)
    {
    
    }
    
    header("Location:" . ROOT_CLIENT . 'index.php' );
?>