<?php
require_once '../../vendor/autoload.php';

// Memuat variabel dari .env menggunakan josegonzalez/dotenv
$loader = new josegonzalez\Dotenv\Loader(__DIR__ . '/../../.env');
$loader->parse()->putenv();

use Firebase\JWT\JWT;
// --- PERBAIKI PATH INI ---
include '../../config/database.php';

// Ambil data login
$email = $_POST['email'] ?? '';
$password = $_POST['password'] ?? '';

// Simple protection
$email = mysqli_real_escape_string($conn, $email);
$password = mysqli_real_escape_string($conn, $password);

// Query user
$query = "SELECT * FROM users WHERE email='$email' AND password='$password'";
$result = mysqli_query($conn, $query);

$response = [];

if (mysqli_num_rows($result) > 0) {
    $user = mysqli_fetch_assoc($result);

    // JWT payload
    $payload = [
        'iss' => 'kasihin-app',
        'aud' => 'kasihin-client',
        'iat' => time(),
        'exp' => time() + (60 * 60 * 24), // berlaku 1 hari
        'sub' => $user['id'],
        'email' => $user['email'],
        'role' => $user['role']
    ];
    
    // --- TAMBAHKAN KODE INI UNTUK MENGAMBIL JWT_SECRET ---
    $jwt_secret = getenv('JWT_SECRET') ?: 'defaultsecret';

    // Encode JWT
    $jwt = JWT::encode($payload, $jwt_secret, 'HS256');

    // ... sisa kode Anda tetap sama ...
    
    // Response
    $response['status'] = true;
    $response['message'] = 'Login berhasil';
    $response['token'] = $jwt;
    $response['user'] = $user;
} else {
    $response['status'] = false;
    $response['message'] = "Email atau password salah";
}

header('Content-Type: application/json');

echo json_encode($response);
?>