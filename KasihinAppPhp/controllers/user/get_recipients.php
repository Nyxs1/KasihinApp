<?php
include '../../config/database.php';

// Query untuk mengambil semua user yang BUKAN 'person' (misal: 'influencer')
$query = "SELECT id, nama, email, role, poin, bio_user FROM users WHERE role != 'person' ORDER BY nama ASC";
$result = mysqli_query($conn, $query);

$data = array();
if ($result) {
    while ($row = mysqli_fetch_assoc($result)) {
            $row['id'] = (int)$row['id'];
        $row['poin'] = (int)$row['poin'];
        $data[] = $row;
    }
}

header('Content-Type: application/json');
echo json_encode(array("status" => true, "users" => $data));
?>