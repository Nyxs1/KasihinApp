<?php
include '../config/database.php';

$user_id = $_GET['user_id'];
$query = "SELECT * FROM poin_history WHERE user_id = $user_id ORDER BY tanggal DESC";
$result = mysqli_query($conn, $query);

$data = array();
while ($row = mysqli_fetch_assoc($result)) {
    $data[] = $row;
}

echo json_encode(array("status" => true, "history" => $data));
?>
