<?php 
namespace daos;

use models\Band as Band;
use daos\iBands as iBands;
use models\Member as Member;;

class Bands implements iBands{
	
	private $bandList = array();

	public function update(){
		$this->retrieveData();
		//array_push($this->bandList, $newBand);
		$this->saveData();
	}

	public function getAll(){
		$this->retrieveData();
		return $this->bandList;
	}

	public function saveData(){
		$arrayToEncode = array();
		$miembroarreglo = array();

		foreach ($this->bandList as $band) {
			$valuesArray = array();
			$valuesArray2 = array();
			$valuesArray3 = array();
			$valueArray['id'] = $band->getId();
			$valueArray['Name'] = $band->getName();
			$valueArray['bio'] = $band->getBio();

			foreach($band->getMembers() as $miembro){
				$valuesArray2['name'] = $miembro->getName();
				$valuesArray2['rol'] = $miembro->getRol();
				array_push($valuesArray3,$valuesArray2);
			}

			$valueArray['members'] = $valuesArray3;
			$valueArray['category'] = $band->getCategory();

			array_push($arrayToEncode, $valueArray);

		}

		$jsonContent = json_encode($arrayToEncode,JSON_PRETTY_PRINT);
		file_put_contents('../data/bands.json', $jsonContent);
	}

	public function delete($id){
		$this->retrieveData();
		$newList = array();
		foreach ($this->bandList as $band) {
			if($band->getId() != $id){
				array_push($newList, $band);
			}
		}

		$this->bandList = $newList;
		$this->saveData();
	}

	public function retrieveData(){
		$this->bandList = array();

		$jsonPath = $this->GetJsonFilePath();

		$jsonContent = file_get_contents($jsonPath);
		
		$arrayToDecode = ($jsonContent) ? json_decode($jsonContent, true) : array();
		foreach ($arrayToDecode as $valueArray) {
			$banda = new Band();
			$miembros = array();
			$banda->setId($valueArray["id"]);
            $banda->setName($valueArray["Name"]);
			$banda->setBio($valueArray["bio"]);
			$banda->setCategory($valueArray["category"]);
			foreach($valueArray["members"] as $miembroarreglo){
				$miembro = new Member();
				$miembro->setName($miembroarreglo["name"]);
				$miembro->setRol($miembroarreglo["rol"]);
				array_push($miembros,$miembro);
			}
			$banda->setMembers($miembros);

			array_push($this->bandList , $banda);
		}
        return $this->bandList;
	}

    public function GetJsonFilePath(){

        $initialPath = "data/bands.json";
        if(file_exists($initialPath)){
            $jsonFilePath = $initialPath;
        }else{
            $jsonFilePath = "../".$initialPath;
        }

        return $jsonFilePath;
	}
}

 ?>
