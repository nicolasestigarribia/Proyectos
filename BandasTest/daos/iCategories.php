<?php
    namespace daos;

    use models\Category as Category;

interface iCategories
    {
        function getAll();
        function add(Category $newCategorie);
        function retrieveData();
        function saveData();
    }
?>