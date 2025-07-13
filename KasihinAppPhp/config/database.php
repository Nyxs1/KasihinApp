<?php
$host = "localhost";
$user = "root";
$pass = "R1341";
$db = "kasihin_db";

$conn = mysqli_connect($host, $user, $pass, $db);
if (!$conn) {
    die("Koneksi gagal: " . mysqli_connect_error());
}
?>
