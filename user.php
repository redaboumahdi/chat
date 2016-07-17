<?php

require_once('./chat.php');

class User {
	private $id;
	private $message;
	private $ami = [];
	
	public function __construct($j){
		$this->id = $j;
		$chat = new ChatMock($this->id);
		$res = $chat->getMessages(); 
		$temp1 = new SplFixedArray(count($res));
		for($i = 0; $i < count($res); $i++){
			$temp1[$i] = json_encode($res[$i]);		
		}
		$temp2 = array('nombre'=>count($res), 'messages'=> $temp1);
		$temp3 = json_encode($temp2);
		echo($temp3);
	}
	
	 



}