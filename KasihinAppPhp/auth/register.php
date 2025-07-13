<?php
include '../config/database.php';

$nama = $_POST['nama'];
$email = $_POST['email'];
$password = $_POST['password'];
$role = $_POST['role'];

$response = array();

// Cek email unik
$cek = mysqli_query($conn, "SELECT * FROM users WHERE email='$email'");
if (mysqli_num_rows($cek) > 0) {
    $response['status'] = false;
    $response['message'] = "Email sudah terdaftar";
} else {
    $query = "INSERT INTO users (nama, email, password, role) VALUES ('$nama', '$email', '$password', '$role')";
    if (mysqli_query($conn, $query)) {
        $response['status'] = true;
        $response['message'] = "Registrasi berhasil";
    } else {
        $response['status'] = false;
        $response['message'] = "Gagal registrasi";
    }
}

echo json_encode($response);
?>
