-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 20, 2024 at 07:14 PM
-- Wersja serwera: 10.4.28-MariaDB
-- Wersja PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sklep`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `koszyk`
--

CREATE TABLE `koszyk` (
  `koszyk_id` int(11) NOT NULL,
  `kupujacy` text NOT NULL,
  `adres` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `koszyk`
--

INSERT INTO `koszyk` (`koszyk_id`, `kupujacy`, `adres`) VALUES
(1, 'kacperl', 'pomorska 149/153'),
(2, 'kacperl', 'pomorska 149/153');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `koszyk_produkty`
--

CREATE TABLE `koszyk_produkty` (
  `koszyk_id` int(11) NOT NULL,
  `produkt_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `koszyk_produkty`
--

INSERT INTO `koszyk_produkty` (`koszyk_id`, `produkt_id`) VALUES
(1, 7),
(1, 1),
(2, 5);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `login`
--

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL,
  `login` varchar(15) NOT NULL,
  `haslo` varchar(30) NOT NULL,
  `pseudonim` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`login_id`, `login`, `haslo`, `pseudonim`) VALUES
(1, 'kacper', 'legocki', 'kacperl'),
(18, 'jan', 'kowalski', 'JKowalski');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `produkt`
--

CREATE TABLE `produkt` (
  `produkt_id` int(11) NOT NULL,
  `nazwa` text NOT NULL,
  `ilosc` int(11) NOT NULL,
  `opis` text NOT NULL,
  `cena` int(11) NOT NULL,
  `grafika` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produkt`
--

INSERT INTO `produkt` (`produkt_id`, `nazwa`, `ilosc`, `opis`, `cena`, `grafika`) VALUES
(1, 'Monitor LG UltraGear 27GP850P', 1, 'Ekran:  27 \", IPS, 2560 x 1440\r\nCzas reakcji matrycy:  1 ms\r\nCzęstotliwość odświeżania obrazu:  165 Hz\r\nZłącza:  DisplayPort x 1, HDMI 2.0 x 2, USB 3.0 / 3.1 x 2, wyjście liniowe audio\r\n', 999, 'monitor.png'),
(2, 'Telewizor Sony XR-55X90S', 9, 'Ekran:  55 \", 4K UHD / 3840 x 2160\r\nSmart TV / Wi-Fi:  tak / tak\r\nCzęstotliwość odświeżania ekranu:  100 Hz / 120 Hz\r\nTechnologia obrazu: LED\r\nFunkcje:  HDR, Wi-Fi, Bluetooth\r\nZłącza:  optyczne, Ethernet, HDMI x4, USB x2\r\n', 2499, 'telewizor.png'),
(3, 'Laptop Apple MacBook Air M1', 1, 'Ekran:  13,3 \", 2560 x 1600 pikseli\r\nProcesor:  Apple M1\r\nPamięć:  8 GB DDR4 RAM\r\nDysk:  256 GB SSD\r\nGrafika:  Apple M1 (7-rdzeni)\r\nSystem operacyjny:  macOS Big Sur lub nowszy', 4999, 'macbook.png'),
(4, 'Smartwatch Samsung Galaxy Watch5 Pro', 35, 'Rodzaj:  uniwersalny\r\nRodzaj aktywności:  bieganie, fitness/siłownia\r\nŁączność bezprzewodowa:  NFC, Wi-Fi, Bluetooth 5.2\r\nPulsometr - Czujnik tętna:  tak\r\nRozmiar wyświetlacza:  1,36 \"', 999, 'smartwatch.png'),
(5, 'Konsola Sony PlayStation 5', 15, 'Typ konsoli:  PS5 z napędem\r\nPojemność dysku:  825 GB SSD\r\nKolor:  biały\r\nZawartość zestawu:  pad, kabel HDMI, kabel USB, podstawka', 2499, 'ps5.png'),
(6, 'Smartfon Samsung Galaxy S20 FE', 45, 'Wyświetlacz:  6,5 \", 2400 x 1080, AMOLED\r\nPojemność baterii:  4500 mAh\r\nPamięć RAM/wewnętrzna :  6 GB / 128 GB\r\nAparaty tylny/przedni:  12 Mpix + 12 Mpix + 8 Mpix / 32 Mpix\r\nSystem operacyjny:  Android 10\r\nProcesor:  8-rdzeniowy Snapdragon 865\r\n', 2999, 'smartfon.png'),
(7, 'Karta graficzna MSI GeForce RTX 3060 Ti', 4, 'Układ karty:  NVIDIA® GeForce RTX™ 3060 Ti\r\nPamięć:  8 GB\r\nRodzaj pamięci:  GDDR6\r\nZłącze karty:  PCI-Express 4.0 x 16\r\nZłącza:  DisplayPort x 3, HDMI 2.1 x 1', 2799, 'karta.png'),
(8, 'Dron DJI Ryze Tello', 0, 'Wbudowana kamera:  tak\r\nPojemność baterii:  1100 mAh\r\nPrzewidywany czas lotu:  13 min', 1399, 'dron.png');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zamowienia`
--

CREATE TABLE `zamowienia` (
  `zamowienie_id` int(11) NOT NULL,
  `koszyk_id` int(11) NOT NULL,
  `ilosc` int(11) NOT NULL,
  `adres` text NOT NULL,
  `kupujacy` text NOT NULL,
  `data_i_godzina` datetime NOT NULL DEFAULT current_timestamp(),
  `cena` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `zamowienia`
--

INSERT INTO `zamowienia` (`zamowienie_id`, `koszyk_id`, `ilosc`, `adres`, `kupujacy`, `data_i_godzina`, `cena`) VALUES
(6, 1, 2, 'pomorska 149/153', 'kacperl', '2024-03-20 19:11:21', 3798),
(7, 2, 1, 'pomorska 149/153', 'kacperl', '2024-03-20 19:11:35', 2499);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `koszyk`
--
ALTER TABLE `koszyk`
  ADD PRIMARY KEY (`koszyk_id`);

--
-- Indeksy dla tabeli `koszyk_produkty`
--
ALTER TABLE `koszyk_produkty`
  ADD KEY `koszyk_id` (`koszyk_id`),
  ADD KEY `produkt_id` (`produkt_id`);

--
-- Indeksy dla tabeli `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`login_id`),
  ADD UNIQUE KEY `login` (`login`);

--
-- Indeksy dla tabeli `produkt`
--
ALTER TABLE `produkt`
  ADD PRIMARY KEY (`produkt_id`);

--
-- Indeksy dla tabeli `zamowienia`
--
ALTER TABLE `zamowienia`
  ADD PRIMARY KEY (`zamowienie_id`),
  ADD KEY `koszyk_id` (`koszyk_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `login_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `produkt`
--
ALTER TABLE `produkt`
  MODIFY `produkt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT for table `zamowienia`
--
ALTER TABLE `zamowienia`
  MODIFY `zamowienie_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `koszyk`
--
ALTER TABLE `koszyk`
  ADD CONSTRAINT `koszyk_ibfk_1` FOREIGN KEY (`koszyk_id`) REFERENCES `zamowienia` (`koszyk_id`);

--
-- Constraints for table `koszyk_produkty`
--
ALTER TABLE `koszyk_produkty`
  ADD CONSTRAINT `koszyk_produkty_ibfk_1` FOREIGN KEY (`koszyk_id`) REFERENCES `koszyk` (`koszyk_id`),
  ADD CONSTRAINT `koszyk_produkty_ibfk_2` FOREIGN KEY (`produkt_id`) REFERENCES `produkt` (`produkt_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
