<?php
    namespace daos;

    use Models\Band as Band;

    interface iBands
    {
        function getAll();
        function retrieveData();
        function saveData();
    }
?>