<?php

require_once('./chat.php');


$id = $_POST['ID'];
//$id = 1;
$chat = new ChatMock($id);
$len = count($chat->getMessages());

for($i = 0; $i < $len ;$i++) {
print_r($chat->getMessages()[$i]['message']);
}
