<?php
    namespace models;

    class Member
    {
        private $name;
        private $rol;

        public function getName(){
            return $this->name;
        }
        public function setName($name){
            return $this->name = $name;
        }

        public function getRol(){
            return $this->rol;
        }
        public function setRol($rol){
            return $this->rol = $rol;
        }
    }

?>