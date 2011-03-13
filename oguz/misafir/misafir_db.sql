-- phpMyAdmin SQL Dump
-- version 3.3.2deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 13, 2011 at 07:39 PM
-- Server version: 5.1.41
-- PHP Version: 5.3.2-1ubuntu4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `misafir_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `egitim_durumu`
--

CREATE TABLE IF NOT EXISTS `egitim_durumu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adi` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `egitim_durumu`
--


-- --------------------------------------------------------

--
-- Table structure for table `ev_durumu`
--

CREATE TABLE IF NOT EXISTS `ev_durumu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kullanici_id` int(11) NOT NULL,
  `kisi_sayisi` int(11) NOT NULL,
  `sehre_konum_id` int(11) NOT NULL,
  `uygun_tarih_baslangic` date NOT NULL COMMENT 'uygun olunan tarih başlangıcı',
  `uygun_tarih_bitis` date NOT NULL COMMENT 'uygun olunan tarih bitişi',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ev_durumu`
--


-- --------------------------------------------------------

--
-- Table structure for table `fotograf`
--

CREATE TABLE IF NOT EXISTS `fotograf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `veri` blob NOT NULL,
  `durumu` smallint(6) NOT NULL COMMENT '1 çin var 0 için yok',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fotograf`
--


-- --------------------------------------------------------

--
-- Table structure for table `ilgi_alanlari`
--

CREATE TABLE IF NOT EXISTS `ilgi_alanlari` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adi` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ilgi_alanlari`
--


-- --------------------------------------------------------

--
-- Table structure for table `konum`
--

CREATE TABLE IF NOT EXISTS `konum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enlem` varchar(100) NOT NULL,
  `boylam` varchar(100) NOT NULL,
  `ulke_id` int(11) NOT NULL,
  `sehir_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `konum`
--


-- --------------------------------------------------------

--
-- Table structure for table `kullanici`
--

CREATE TABLE IF NOT EXISTS `kullanici` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ad` varchar(100) NOT NULL,
  `soyad` varchar(150) NOT NULL,
  `dogum_tarihi` date NOT NULL,
  `email` varchar(250) NOT NULL,
  `parola` varchar(250) NOT NULL,
  `konum_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kullanici`
--


-- --------------------------------------------------------

--
-- Table structure for table `kullanici_detaylari`
--

CREATE TABLE IF NOT EXISTS `kullanici_detaylari` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kullanici_id` int(11) NOT NULL,
  `meslek_id` int(11) NOT NULL,
  `egitim_durumu_id` int(11) NOT NULL,
  `ilgi_alanlari_id` int(11) NOT NULL,
  `foto_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kullanici_detaylari`
--


-- --------------------------------------------------------

--
-- Table structure for table `mesaj_kutusu`
--

CREATE TABLE IF NOT EXISTS `mesaj_kutusu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gonderen_id` int(11) NOT NULL,
  `tarih` date NOT NULL,
  `mesaj_icerigi` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mesaj_kutusu`
--


-- --------------------------------------------------------

--
-- Table structure for table `meslek`
--

CREATE TABLE IF NOT EXISTS `meslek` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meslek_adi` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `meslek`
--


-- --------------------------------------------------------

--
-- Table structure for table `misafir_ozellikleri`
--

CREATE TABLE IF NOT EXISTS `misafir_ozellikleri` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cinsiyet` varchar(3) NOT NULL COMMENT 'M/F,E/K',
  `sigara` tinyint(4) NOT NULL,
  `uyuma_duzeni_id` int(11) NOT NULL,
  `kurallar` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `misafir_ozellikleri`
--


-- --------------------------------------------------------

--
-- Table structure for table `sehre_konum`
--

CREATE TABLE IF NOT EXISTS `sehre_konum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mesafe` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sehre_konum`
--


-- --------------------------------------------------------

--
-- Table structure for table `ulke`
--

CREATE TABLE IF NOT EXISTS `ulke` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adi` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ulke`
--

