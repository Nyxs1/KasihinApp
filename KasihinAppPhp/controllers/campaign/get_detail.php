<?php
include '../config/database.php';

$campaign_id = $_GET['campaign_id'];

$query = "SELECT campaigns.*, users.nama AS pembuat 
          FROM campaigns 
          LEFT JOIN users ON campaigns.user_id = users.id 
          WHERE campaigns.id = '$campaign_id' 
          LIMIT 1";

$result = mysqli_query($conn, $query);

if ($row = mysqli_fetch_assoc($result)) {
    echo json_encode(array("status" => true, "campaign" => $row));
} else {
    echo json_encode(array("status" => false, "message" => "Campaign tidak ditemukan"));
}
?>
