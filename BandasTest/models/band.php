<?php
    namespace models;

class Band
    {
        private $id;
        private $name;
        private $bio;
        private array $members;
        private $category;
        
        function __construct()
        {
            $this->members = array();
        }

        public function getId(){
            return $this->id;
        }
        public function setId($id){
            return $this->id = $id;
        }

        public function getBio(){
            return $this->bio;
        }
        public function setBio($bio){
            return $this->bio = $bio;
        }

        public function getName(){
            return $this->name;
        }
        public function setName($name){
            return $this->name = $name;
        }

        public function setMembers($members)
        {
            $this->members = $members;
        }

        public function getMembers()
        {
            return $this->members;
        }

        public function setCategory($category)
        {
            $this->category = $category;
        }

        public function getCategory()
        {
            return $this->category;
        }
    }

?>