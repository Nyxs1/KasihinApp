<?php
include '../config/database.php';

$query = "SELECT * FROM quests ORDER BY created_at DESC";
$result = mysqli_query($conn, $query);

$data = array();
while ($row = mysqli_fetch_assoc($result)) {
    $data[] = $row;
}

echo json_encode(array("status" => true, "quests" => $data));
?>
