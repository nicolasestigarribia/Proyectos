<?php  
    require_once("../config/autoload.php");
    require_once("../config/constants.php");
   

    use models\Band as Band;
    use daos\Bands as Bands;

    $repo = new Bands();   
    $id=$_POST["remove"];
    echo($id);
    if($_POST){
        $repo->delete($id);
    }
    
    //header("Location:" . ROOT_CLIENT . 'index.php' );
?>