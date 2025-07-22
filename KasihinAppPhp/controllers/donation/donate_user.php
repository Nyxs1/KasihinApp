<?php
include '../../config/database.php';

// Ambil data dari POST request aplikasi Android
$dari_user_id = $_POST['dari_user_id'] ?? 0;
$ke_user_id = $_POST['ke_user_id'] ?? 0;
$jumlah = $_POST['jumlah'] ?? 0;
$pesan = $_POST['pesan'] ?? '';

$response = array();

// Validasi input dasar
if (empty($dari_user_id) || empty($ke_user_id) || empty($jumlah) || $jumlah < 20) {
    $response['status'] = false;
    $response['message'] = "Data tidak lengkap atau jumlah poin kurang dari 20.";
    echo json_encode($response);
    exit;
}

// 1. Cek apakah pengirim memiliki cukup poin
$cekPoinQuery = "SELECT poin FROM users WHERE id = $dari_user_id";
$poinResult = mysqli_query($conn, $cekPoinQuery);
$pengirim = mysqli_fetch_assoc($poinResult);

if (!$pengirim || $pengirim['poin'] < $jumlah) {
    $response['status'] = false;
    $response['message'] = "Poin Anda tidak mencukupi untuk donasi ini.";
    echo json_encode($response);
    exit;
}

// 2. Cek apakah penerima adalah 'influencer'
$cekRoleQuery = "SELECT role FROM users WHERE id = $ke_user_id";
$roleResult = mysqli_query($conn, $cekRoleQuery);
$penerima = mysqli_fetch_assoc($roleResult);

if (!$penerima || $penerima['role'] !== 'konten_kreator') {
    $response['status'] = false;
    $response['message'] = "Donasi poin hanya bisa dikirimkan ke Konten Kreator.";
    echo json_encode($response);
    exit;
}

// 3. Mulai transaksi database untuk memastikan semua proses aman
mysqli_begin_transaction($conn);

try {
    // Kurangi poin pengirim
    $updatePengirimQuery = "UPDATE users SET poin = poin - $jumlah WHERE id = $dari_user_id";
    mysqli_query($conn, $updatePengirimQuery);

    // Tambah poin penerima
    $updatePenerimaQuery = "UPDATE users SET poin = poin + $jumlah WHERE id = $ke_user_id";
    mysqli_query($conn, $updatePenerimaQuery);

    // Catat transaksi di tabel baru kita
    $pesan = mysqli_real_escape_string($conn, $pesan);
    $insertDonasiQuery = "INSERT INTO user_donations (dari_user_id, ke_user_id, jumlah, pesan) VALUES ($dari_user_id, $ke_user_id, $jumlah, '$pesan')";
    mysqli_query($conn, $insertDonasiQuery);

    // Jika semua berhasil, simpan perubahan secara permanen
    mysqli_commit($conn);

    $response['status'] = true;
    $response['message'] = "Donasi poin berhasil dikirim!";

} catch (mysqli_sql_exception $exception) {
    // Jika ada satu saja yang gagal, batalkan semua perubahan
    mysqli_rollback($conn);
    
    $response['status'] = false;
    $response['message'] = "Terjadi kesalahan pada server. Donasi dibatalkan.";
}

header('Content-Type: application/json');
echo json_encode($response);

?>