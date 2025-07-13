<?php
// Load .env
$envPath = __DIR__ . '/../.env';
if (!file_exists($envPath)) {
    die("❌ File .env tidak ditemukan!");
}
$lines = file($envPath, FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
foreach ($lines as $line) {
    if (strpos(trim($line), '#') === 0 || !str_contains($line, '=')) continue;
    list($name, $value) = explode('=', $line, 2);
    $_ENV[trim($name)] = trim($value);
}

// Setup daftar koneksi
$connections = [
    [
        'label' => 'Primary',
        'host'  => $_ENV['MYSQL_HOST1'],
        'port'  => $_ENV['MYSQL_PORT1'],
        'user'  => $_ENV['MYSQL_USER1'],
        'pass'  => $_ENV['MYSQL_PASS1'],
        'db'    => $_ENV['MYSQL_DB']
    ],
    [
        'label' => 'Backup',
        'host'  => $_ENV['MYSQL_HOST2'],
        'port'  => $_ENV['MYSQL_PORT2'],
        'user'  => $_ENV['MYSQL_USER2'],
        'pass'  => $_ENV['MYSQL_PASS2'],
        'db'    => $_ENV['MYSQL_DB']
    ]
];

/** @var mysqli|null $conn */
$conn = null;
$activeLabel = null;

foreach ($connections as $config) {
    try {
        $testConn = @mysqli_connect(
            $config['host'],
            $config['user'],
            $config['pass'],
            $config['db'],
            (int)$config['port']
        );

        if ($testConn instanceof mysqli) {
            $conn = $testConn;
            $activeLabel = "{$config['label']} ({$config['user']}@{$config['host']}:{$config['port']})";
            break;
        }
    } catch (mysqli_sql_exception $e) {
        error_log("❌ Gagal konek ke {$config['label']} DB: " . $e->getMessage());
    }
}

// Kalau dua-duanya gagal
if (!$conn) {
    die("❌ Gagal konek ke semua database (primary & backup).");
}

// echo "✅ Berhasil konek ke: <b>$activeLabel</b>";
return $conn;
