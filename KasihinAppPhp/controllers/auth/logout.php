<?php
// Memuat semua library dari composer
require_once '../../vendor/autoload.php';

// Memuat variabel dari .env
$loader = new josegonzalez\Dotenv\Loader(__DIR__ . '/../../.env');
$loader->parse()->putenv();

// Menggunakan library JWT
use Firebase\JWT\JWT;
use Firebase\JWT\Key;

// Memuat koneksi database
include '../../config/database.php';

// Memulai sesi untuk dihancurkan nanti
session_start();

// Mengatur header respons sebagai JSON
header('Content-Type: application/json');

// Mengambil kunci rahasia JWT dari .env
$jwt_secret = getenv('JWT_SECRET') ?: 'defaultsecret';
$response = [];

// 1. Dapatkan Authorization Header dari request
$headers = getallheaders();
$authHeader = $headers['Authorization'] ?? '';

// 2. Ekstrak token dari format "Bearer <token>"
if (preg_match('/Bearer\s(\S+)/', $authHeader, $matches)) {
    $token = $matches[1];

    try {
        // 3. Validasi token (memastikan token asli dan tidak kedaluwarsa)
        $decoded = JWT::decode($token, new Key($jwt_secret, 'HS256'));
        $user_id = $decoded->sub; // Ambil ID pengguna dari token

        // 4. Perbarui tabel user_logs untuk menonaktifkan sesi
        // Menggunakan prepared statement untuk keamanan dari SQL Injection
        $stmt = $conn->prepare(
            "UPDATE user_logs 
             SET logout_time = NOW(), is_active = FALSE 
             WHERE user_id = ? AND token = ? AND is_active = TRUE"
        );
        $stmt->bind_param("is", $user_id, $token);

        if ($stmt->execute() && $stmt->affected_rows > 0) {
            session_destroy(); // Hancurkan sesi PHP
            $response['status'] = true;
            $response['message'] = "Logout berhasil";
        } else {
            $response['status'] = false;
            $response['message'] = "Tidak ada sesi aktif yang cocok dengan token ini.";
        }
        $stmt->close();

    } catch (Exception $e) {
        // Jika token tidak valid (error, kedaluwarsa, dll)
        http_response_code(401); // Unauthorized
        $response['status'] = false;
        $response['message'] = "Token tidak valid atau kedaluwarsa.";
    }
} else {
    // Jika header tidak ada atau formatnya salah
    http_response_code(400); // Bad Request
    $response['status'] = false;
    $response['message'] = "Header otorisasi tidak ditemukan atau format salah.";
}

// 5. Kirim respons kembali ke aplikasi
echo json_encode($response);
?>