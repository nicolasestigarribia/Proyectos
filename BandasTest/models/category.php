<?php
    namespace models;

    class Category
    {
        private $id;
        private $name;
        private $isActive;

        public function getId(){
            return $this->id;
        }
        public function setId($id){
            return $this->id = $id;
        }

        public function getName(){
            return $this->name;
        }
        public function setName($name){
            return $this->name = $name;
        }

        public function getIsActive(){
            return $this->isActive;
        }
        public function setIsActive($isActive){
            return $this->isActive = $isActive;
        }
    }

?>