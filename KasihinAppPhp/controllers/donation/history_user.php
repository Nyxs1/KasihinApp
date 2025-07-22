<?php
include '../../config/database.php';

// Ambil 5 donasi terbaru, gabungkan dengan tabel users untuk mendapatkan nama
$query = "SELECT 
            ud.jumlah, 
            ud.pesan, 
            ud.tanggal, 
            pengirim.nama as dari_nama,
            penerima.nama as ke_nama 
          FROM 
            user_donations ud
          JOIN 
            users pengirim ON ud.dari_user_id = pengirim.id
          JOIN 
            users penerima ON ud.ke_user_id = penerima.id
          ORDER BY 
            ud.tanggal DESC 
          LIMIT 5";

$result = mysqli_query($conn, $query);
$history = [];
if ($result) {
    while ($row = mysqli_fetch_assoc($result)) {
        $row['jumlah'] = (int)$row['jumlah'];
        $history[] = $row;
    }
}

header('Content-Type: application/json');
echo json_encode(["status" => true, "history" => $history]);
?>