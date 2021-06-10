<?php 
namespace daos;

use daos\iCategories as iCategories;
use models\Category as Categorie;

class Categories implements iCategories{
	
	private $categorieList = array();

	public function add(Categorie $newCategorie){
		$this->retrieveData();
		array_push($this->categorieList, $newCategorie);
		$this->saveData();
	}

	public function delete($id){
		$this->retrieveData();
		$newList = array();
		foreach ($this->categorieList as $categorie) {
			if($categorie->getId() != $id){
				array_push($newList, $categorie);
			}
		}

		$this->categorieList = $newList;
		$this->saveData();
	}

	public function getAll(){
		$this->retrieveData();
		return $this->categorieList;
	}

	
	public function getLastId(){
		return count($this->retrieveData());
	}

	public function saveData(){
		$arrayToEncode = array();

		foreach ($this->categorieList as $categorie) {
			$valueArray['id'] = $categorie->getId();
			$valueArray['name'] = $categorie->getName();
			$valueArray['isActive'] = $categorie->getIsActive();

			array_push($arrayToEncode, $valueArray);

		}
		$jsonContent = json_encode($arrayToEncode, JSON_PRETTY_PRINT);
		file_put_contents('../data/categories.json', $jsonContent);
	}

	public function retrieveData(){
		$this->categorieList = array();

		$jsonPath = $this->GetJsonFilePath();

		$jsonContent = file_get_contents($jsonPath);
		
		$arrayToDecode = ($jsonContent) ? json_decode($jsonContent, true) : array();

		foreach ($arrayToDecode as $valueArray) {
			$categorie = new Categorie();
			$categorie->setId($valueArray["id"]);
			$categorie->setName($valueArray["name"]);
			if($valueArray["isActive"] == true){
				$categorie->setIsActive(true);
			}
			else{
				$categorie->setIsActive(false);
			}

			array_push($this->categorieList, $categorie);
        }
        return $this->categorieList;
	}

    public function GetJsonFilePath(){

        $initialPath = "data/categories.json";
        if(file_exists($initialPath)){
            $jsonFilePath = $initialPath;
        }else{
            $jsonFilePath = "../".$initialPath;
        }

        return $jsonFilePath;
	}
}

 ?>