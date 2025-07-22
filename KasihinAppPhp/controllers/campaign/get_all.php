<?php
include '../config/database.php';

$query = "SELECT campaigns.*, users.nama AS pembuat FROM campaigns 
          LEFT JOIN users ON campaigns.user_id = users.id ORDER BY campaigns.created_at DESC";
$result = mysqli_query($conn, $query);

$data = array();
while ($row = mysqli_fetch_assoc($result)) {
    $data[] = $row;
}

echo json_encode(array("status" => true, "campaigns" => $data));
?>
