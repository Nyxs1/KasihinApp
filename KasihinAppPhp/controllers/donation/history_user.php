<?php
include '../../config/database.php';

// 1. Ambil ID pengguna dari parameter URL (misal: .../history_user.php?user_id=1)
$user_id = $_GET['user_id'] ?? 0;

$response = ["status" => false, "history" => []];

// Pastikan user_id valid sebelum menjalankan query
if (!empty($user_id)) {
    // 2. Query diubah dengan menambahkan WHERE clause untuk memfilter berdasarkan ID pengirim
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
              WHERE
                ud.ke_user_id = ? 
              ORDER BY 
                ud.tanggal DESC 
              LIMIT 5"; // Batasi 10 riwayat terakhir dari pengguna ini

    // 3. Gunakan Prepared Statement untuk keamanan
    $stmt = $conn->prepare($query);
    $stmt->bind_param("i", $user_id); // 'i' berarti tipe datanya integer
    $stmt->execute();
    $result = $stmt->get_result();
    
    $history = [];
    if ($result) {
        while ($row = mysqli_fetch_assoc($result)) {
            $row['jumlah'] = (int)$row['jumlah'];
            $history[] = $row;
        }
    }
    
    $response["status"] = true;
    $response["history"] = $history;
    $stmt->close();
}

header('Content-Type: application/json');
echo json_encode($response);
?>