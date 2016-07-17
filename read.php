<?php

require_once('./chat.php');

$id = $_POST["ID"];
$idconv = $_POST["ID_CONV"];


$chat = new chatMock($id);

$chat->readMessage($idconv);