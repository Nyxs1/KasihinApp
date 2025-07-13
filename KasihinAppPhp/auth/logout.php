<?php
require_once '../vendor/autoload.php';
use Firebase\JWT\JWT;
use Firebase\JWT\Key;

include '../config/database.php';
session_start();

header('Content-Type: application/json');

$jwt_secret = $_ENV['JWT_SECRET'] ?? 'defaultsecret';
$response = [];

$headers = getallheaders();
$authHeader = $headers['Authorization'] ?? '';

if (preg_match('/Bearer\s(\S+)/', $authHeader, $matches)) {
    $token = $matches[1];

    try {
        $decoded = JWT::decode($token, new Key($jwt_secret, 'HS256'));

        $user_id = $decoded->sub;

        // Update log record
        $query = "UPDATE user_logs 
                  SET logout_time = NOW(), is_active = FALSE 
                  WHERE user_id = '$user_id' AND token = '$token' AND is_active = TRUE 
                  ORDER BY login_time DESC LIMIT 1";

        $result = mysqli_query($conn, $query);

        if ($result) {
            session_destroy(); // Optional: destroy PHP session
            $response['status'] = true;
            $response['message'] = "Logout berhasil";
        } else {
            $response['status'] = false;
            $response['message'] = "Gagal update log logout";
        }
    } catch (Exception $e) {
        $response['status'] = false;
        $response['message'] = "Token tidak valid: " . $e->getMessage();
    }
} else {
    $response['status'] = false;
    $response['message'] = "Authorization header tidak ditemukan atau format salah";
}

echo json_encode($response);
