-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Waktu pembuatan: 13 Jul 2025 pada 01.50
-- Versi server: 8.0.30
-- Versi PHP: 8.3.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+07:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kasihin_db`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `campaigns`
--

CREATE TABLE `campaigns` (
  `id` int NOT NULL,
  `nama_campaign` varchar(100) NOT NULL,
  `deskripsi` text,
  `tipe` enum('non-profit','perusahaan') DEFAULT 'non-profit',
  `gambar` varchar(255) DEFAULT NULL,
  `target` int DEFAULT '0',
  `terkumpul` int DEFAULT '0',
  `user_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `donations`
--

CREATE TABLE `donations` (
  `id` int NOT NULL,
  `dari_user_id` int NOT NULL,
  `ke_campaign_id` int NOT NULL,
  `jumlah` int NOT NULL,
  `tanggal` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `poin_history`
--

CREATE TABLE `poin_history` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `sumber` varchar(100) NOT NULL,
  `jumlah` int NOT NULL,
  `tanggal` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `quests`
--

CREATE TABLE `quests` (
  `id` int NOT NULL,
  `nama` varchar(100) NOT NULL,
  `deskripsi` text,
  `jenis` enum('blog','video') NOT NULL,
  `deadline` date NOT NULL,
  `poin_reward` int DEFAULT '20',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `quest_submissions`
--

CREATE TABLE `quest_submissions` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `quest_id` int NOT NULL,
  `link_konten` text NOT NULL,
  `status` enum('pending','approved','rejected') DEFAULT 'pending',
  `submitted_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `nama` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('person','influencer') DEFAULT 'person',
  `poin` int DEFAULT '1000',
  `bio_user` TEXT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Struktur dari tabel `users_logs`
--

CREATE TABLE user_logs (
    id VARCHAR(255) PRIMARY KEY,              -- simpan signature JWT (atau UUID)
    session_id VARCHAR(128) NOT NULL,         -- id dari session (kalau pakai session-based auth)
    user_id INT NOT NULL,                     -- foreign key ke tabel users
    ip_address VARCHAR(45),                   -- IPv4/IPv6 support
    device_info TEXT,                         -- user-agent, OS info, dll
    login_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,         -- waktu login
    logout_time TIMESTAMP NULL DEFAULT NULL,                -- waktu logout
    is_active BOOLEAN DEFAULT TRUE,           -- masih login atau udah logout
    token TEXT,                               -- token JWT kalau pakai token-based auth
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `nama`, `email`, `password`, `role`, `poin`, `bio_user`) VALUES
(1, 'Endrico', 'endrico@mail.com', '123endrico', 'person', 1000, 'Halo! Saya Endrico, seorang donatur.'),
(2, 'Anas', 'anas@mail.com', '123anas', 'influencer', 1000, 'Content creator yang suka berbagi kebaikan.'),
(3, 'Fachrizal', 'rizal@mail.com', '123rizal', 'person', 1000, 'Ayo berdonasi bersama!'),
(4, 'Putra', 'putra@mail.com', '123putra', 'influencer', 1000, 'Seorang influencer yang peduli sesama.');


CREATE TABLE `user_donations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dari_user_id` int NOT NULL,
  `ke_user_id` int NOT NULL,
  `jumlah` int NOT NULL,
  `pesan` TEXT NULL,
  `tanggal` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`dari_user_id`) REFERENCES `users`(`id`),
  FOREIGN KEY (`ke_user_id`) REFERENCES `users`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `campaigns`
--
ALTER TABLE `campaigns`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeks untuk tabel `donations`
--
ALTER TABLE `donations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dari_user_id` (`dari_user_id`),
  ADD KEY `ke_campaign_id` (`ke_campaign_id`);

--
-- Indeks untuk tabel `poin_history`
--
ALTER TABLE `poin_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeks untuk tabel `quests`
--
ALTER TABLE `quests`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `quest_submissions`
--
ALTER TABLE `quest_submissions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `quest_id` (`quest_id`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `campaigns`
--
ALTER TABLE `campaigns`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `donations`
--
ALTER TABLE `donations`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `poin_history`
--
ALTER TABLE `poin_history`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `quests`
--
ALTER TABLE `quests`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `quest_submissions`
--
ALTER TABLE `quest_submissions`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `campaigns`
--
ALTER TABLE `campaigns`
  ADD CONSTRAINT `campaigns_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE SET NULL;

--
-- Ketidakleluasaan untuk tabel `donations`
--
ALTER TABLE `donations`
  ADD CONSTRAINT `donations_ibfk_1` FOREIGN KEY (`dari_user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `donations_ibfk_2` FOREIGN KEY (`ke_campaign_id`) REFERENCES `campaigns` (`id`);

--
-- Ketidakleluasaan untuk tabel `poin_history`
--
ALTER TABLE `poin_history`
  ADD CONSTRAINT `poin_history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `quest_submissions`
--
ALTER TABLE `quest_submissions`
  ADD CONSTRAINT `quest_submissions_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `quest_submissions_ibfk_2` FOREIGN KEY (`quest_id`) REFERENCES `quests` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
