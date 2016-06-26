<?php

require_once('./chat.php');


$id_send = $_POST['ID'];
$id_rec = $_POST['ID_REC'];
$message = $_POST['MESSAGE'];

$chat = new ChatMock($id_send);
$chat->addMessage($id_send,$message, $id_rec);