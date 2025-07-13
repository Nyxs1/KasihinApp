<?php
header('Content-Type: application/json');

// Mendapatkan parameter endpoint dari URL
$endpoint = $_GET['endpoint'] ?? null;

// Routing sederhana
switch ($endpoint) {
    case 'login':
        require __DIR__ . '/controllers/auth/login.php';
        break;

    case 'register':
        require __DIR__ . '/controllers/auth/register.php';
        break;

    case 'get_campaigns':
        require __DIR__ . '/controllers/campaign/get_all.php';
        break;

    case 'create_campaign':
        require __DIR__ . '/controllers/campaign/create.php';
        break;

    case 'donate':
        require __DIR__ . '/controllers/donation/donate.php';
        break;

    case 'donation_history':
        require __DIR__ . '/controllers/donation/history.php';
        break;

    case 'get_quests':
        require __DIR__ . '/controllers/quest/list.php';
        break;

    case 'submit_quest':
        require __DIR__ . '/controllers/quest/submit.php';
        break;

    case 'user_profile':
        require __DIR__ . '/controllers/user/profile.php';
        break;

    case 'update_user':
        require __DIR__ . '/controllers/user/update.php';
        break;

    default:
        echo json_encode([
            "message" => "KasihinApp API ready",
            "usage" => [
                "login" => "?endpoint=login",
                "register" => "?endpoint=register",
                "get_campaigns" => "?endpoint=get_campaigns",
                "donate" => "?endpoint=donate",
                "get_quests" => "?endpoint=get_quests",
                "user_profile" => "?endpoint=user_profile"
            ]
        ]);
}
