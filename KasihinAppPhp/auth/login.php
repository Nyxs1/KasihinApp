<?php
require_once '../vendor/autoload.php';
use Firebase\JWT\JWT;
include '../config/database.php';
session_start();


// Load secret dari .env
$jwt_secret = $_ENV['JWT_SECRET'] ?? 'defaultsecret';

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

    // Encode JWT
    $jwt = JWT::encode($payload, $jwt_secret, 'HS256');

    // Simpan session
    $_SESSION['user_id'] = $user['id'];
    $_SESSION['session_id'] = session_id();

    // Info log
    $log_id = uniqid('log_', true);
    $ip = $_SERVER['REMOTE_ADDR'] ?? 'unknown';
    $agent = $_SERVER['HTTP_USER_AGENT'] ?? 'unknown';

    // Simpan ke user_logs
    $insertLog = "INSERT INTO user_logs 
        (id, session_id, user_id, ip_address, device_info, token, is_active) 
        VALUES 
        ('$log_id', '" . session_id() . "', '{$user['id']}', '$ip', '$agent', '$jwt', TRUE)";

    mysqli_query($conn, $insertLog);

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
