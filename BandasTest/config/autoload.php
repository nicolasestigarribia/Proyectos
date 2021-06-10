<?php 

spl_autoload_register(function($class) {

    $path = ROOT . '/' . $class . '.php';
    
    $path = strtolower($path);

    $path = str_replace('\\', '/', $path);
    
    include($path);

});