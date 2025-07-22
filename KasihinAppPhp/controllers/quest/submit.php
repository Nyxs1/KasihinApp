<?php
include '../config/database.php';

$user_id = $_POST['user_id'];
$quest_id = $_POST['quest_id'];
$link_konten = $_POST['link_konten'];

$query = "INSERT INTO quest_submissions (user_id, quest_id, link_konten) 
          VALUES ('$user_id', '$quest_id', '$link_konten')";

$response = array();
if (mysqli_query($conn, $query)) {
    $response['status'] = true;
    $response['message'] = "Tugas berhasil dikirim";
} else {
    $response['status'] = false;
    $response['message'] = "Gagal mengirim tugas";
}

echo json_encode($response);
?>
