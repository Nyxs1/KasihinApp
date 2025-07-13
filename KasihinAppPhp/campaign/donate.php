<?php
include '../config/database.php';

$user_id = $_POST['user_id'];
$campaign_id = $_POST['campaign_id'];
$jumlah = $_POST['jumlah'];

$response = array();

// Simpan donasi
$query = "INSERT INTO donations (dari_user_id, ke_campaign_id, jumlah) 
          VALUES ('$user_id', '$campaign_id', '$jumlah')";

if (mysqli_query($conn, $query)) {
    // Update poin & campaign terkumpul
    mysqli_query($conn, "UPDATE users SET poin = poin - $jumlah WHERE id = $user_id");
    mysqli_query($conn, "UPDATE campaigns SET terkumpul = terkumpul + $jumlah WHERE id = $campaign_id");
    
    $response['status'] = true;
    $response['message'] = "Donasi berhasil";
} else {
    $response['status'] = false;
    $response['message'] = "Donasi gagal";
}

echo json_encode($response);
?>
