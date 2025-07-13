<?php
include '../config/database.php';

$email = $_POST['email'];
$password = $_POST['password'];

$query = "SELECT * FROM users WHERE email='$email' AND password='$password'";
$result = mysqli_query($conn, $query);

$response = array();
if (mysqli_num_rows($result) > 0) {
    $user = mysqli_fetch_assoc($result);
    $response['status'] = true;
    $response['user'] = $user;
} else {
    $response['status'] = false;
    $response['message'] = "Email atau password salah";
}

echo json_encode($response);
?>
