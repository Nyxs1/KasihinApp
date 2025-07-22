<?php
include '../../config/database.php';

// Query untuk mengambil semua user yang BUKAN 'person' (misal: 'influencer')
$query = "SELECT id, nama, email, role, poin, created_at FROM users WHERE role != 'person' ORDER BY nama ASC";
$result = mysqli_query($conn, $query);

$data = array();
if ($result) {
    while ($row = mysqli_fetch_assoc($result)) {
        $data[] = $row;
    }
}

header('Content-Type: application/json');
echo json_encode(array("status" => true, "users" => $data));
?>