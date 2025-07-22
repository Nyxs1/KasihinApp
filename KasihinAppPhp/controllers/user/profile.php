<?php
include '../config/database.php';

$user_id = $_GET['user_id'];
$query = "SELECT * FROM users WHERE id = $user_id LIMIT 1";
$result = mysqli_query($conn, $query);

if ($row = mysqli_fetch_assoc($result)) {
    echo json_encode(array("status" => true, "user" => $row));
} else {
    echo json_encode(array("status" => false, "message" => "User tidak ditemukan"));
}
?>
