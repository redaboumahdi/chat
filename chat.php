<?php
class ChatMock {

  // Holds the database connection
  private $dbConnection;
  private $id;
  
  //----- Database connection details --/
  //-- Change these to your database values
  
  private $_dbHost = 'localhost';
  
  private $_dbUsername = 'root';
  
  private $_dbPassword = '';
  
  public $_databaseName = 'ondagaia';
  

  public function __construct($i) {
    $this->dbConnection = new mysqli($this->_dbHost, $this->_dbUsername, 
        $this->_dbPassword, $this->_databaseName);

    if ($this->dbConnection->connect_error) {
      die('Connection error.');
    }
    $this->id = $i;
  }

  /**
   * Get the list of messages from the chat
   * 
   * @return array
   */
  public function getMessages() {
    $messages = array();
    $query = "
        SELECT 
          *
        FROM `chat`
        WHERE id_send = $this->id   OR id_rec = $this->id
        "
        ;

    // Execute the query
    $resultObj = $this->dbConnection->query($query);
    // Fetch all the rows at once.
    while ($row = $resultObj->fetch_assoc()) {
      $messages[] = $row;
    }
    
    return $messages;
  }
  
  public function getMessage($id_conv){
  
      $messages = array();
    $query = "
        SELECT 
          *
        FROM `chat`
        WHERE ( id_send = $this->id AND id_rec = $id_conv )   OR ( id_rec = $this->id AND id_send = $id_conv )
        "
        ;

    // Execute the query
    $resultObj = $this->dbConnection->query($query);
    // Fetch all the rows at once.
    while ($row = $resultObj->fetch_assoc()) {
      $messages[] = $row;
    }
    
    return $messages;
  
  
  
  
  
  }

  /**
   * Add a new message to the chat table
   * 
   * @param Integer $userId The user who sent the message
   * @param String $message The Actual message
   * @return bool|Integer The last inserted id of success and false on fail
   */
  public function addMessage($userId, $message, $userIDREC) {
    $addResult = false;
    
    $cUserId = (int) $userId;
    $userIDREC = (int) $userIDREC;
    // Escape the message with mysqli real escape
    $cMessage = $this->dbConnection->real_escape_string($message);
    
    $query = "
      INSERT INTO `chat`
      VALUES ({$cUserId}, {$userIDREC},'{$cMessage}', now(),1)";
    $result = $this->dbConnection->query($query);
    
    if ($result !== false) {
      // Get the last inserted row id.
      $addResult = $this->dbConnection->insert_id;
    } else {
      echo $this->dbConnection->error;
    }
    
    return $addResult;
  }
  
  public function readMessage($idconv){
  		$query = "
		UPDATE chat SET readMessage = 0
		WHERE id_send = $idconv AND id_rec= $this->id	
  		";
  		$result = $this->dbConnection->query($query);
    if ($result !== false) {
      // Get the last inserted row id.
      echo "alright !!";
    } else {
      echo $this->dbConnection->error;
    }
  
  }

}

