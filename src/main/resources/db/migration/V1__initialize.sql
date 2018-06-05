-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Jun 04, 2018 at 07:23 PM
-- Server version: 5.6.34-log
-- PHP Version: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `adulting`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'social'),
(2, 'health'),
(3, 'schedule'),
(4, 'money'),
(5, 'learning'),
(6, 'homeandlawn'),
(7, 'generosity');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(97),
(97),
(97);

-- --------------------------------------------------------

--
-- Table structure for table `quest`
--

CREATE TABLE `quest` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `points` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `quest`
--

INSERT INTO `quest` (`id`, `name`, `points`, `category_id`) VALUES
(59, 'Intentionally take a \"mental health\" day', 50, 2),
(7, 'Go to the gym today', 50, 2),
(8, 'Get 10k steps today', 50, 2),
(9, 'Her birthday is coming up... call your mother', 50, 3),
(10, 'Pay Monthly Utility Bills', 50, 4),
(11, 'Pay Your Taxes', 100, 4),
(12, 'Attend a conference', 50, 5),
(13, 'Mow the lawn', 50, 6),
(14, 'Don\'t forget your anniversary', 50, 3),
(15, 'Donate clothes to a charity', 50, 7),
(16, 'Schedule time to read', 50, 3),
(17, 'Spend less than you make for the month', 50, 4),
(18, 'Spring Cleaning', 50, 6),
(19, 'Conquer the dishes', 50, 6),
(20, 'Choose the healthy option at a restaurant', 50, 2),
(21, 'Learn a new skill online', 50, 5),
(22, 'Take a class', 50, 5),
(23, 'Invest some savings', 50, 4),
(24, 'Help out a neighbor', 50, 7),
(25, 'Volunteer an hour', 50, 7),
(55, 'Go to that one party... you know the one', 50, 1),
(60, 'Meet someone new', 50, 1),
(63, 'Go on a date', 50, 1),
(64, 'Have a 30 minute face to face conversation with someone', 50, 1),
(65, 'Attend a group activity', 50, 1),
(66, 'Go for a 30 minute job or bike ride', 50, 2),
(67, 'Schedule your Doctor / Dentist appointment', 50, 3),
(68, 'Get a checkup', 50, 2),
(69, 'Have something on the calendar that you look forward to.', 50, 3),
(70, 'Balance your checking account for the month', 50, 4),
(71, 'Put in to or start a 401k', 50, 4),
(72, 'Practice with a musical instrument', 50, 5),
(73, 'Get a certification through your work', 50, 5),
(74, 'Take 30 minutes to reflect on your decisions for the year', 50, 5),
(75, 'Trim / Pull the weeds', 50, 6),
(76, 'Clean out the junk closet / drawer', 50, 6),
(78, 'Actually get rid of that thing you said you were going to', 50, 6),
(79, 'Catch up on laundry', 50, 6);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `experience` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `password` varchar(120) NOT NULL,
  `username` varchar(20) NOT NULL,
  `verify` varchar(120) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `experience`, `level`, `password`, `username`, `verify`) VALUES
(96, 9999, 10, 'ca161480943dbef425b69d4b499c6781bc977ee1a0b1c451df0725869fa5e844', 'AdultAdmin', 'ca161480943dbef425b69d4b499c6781bc977ee1a0b1c451df0725869fa5e844'),
(95, 350, 2, '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', 'test', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `quest`
--
ALTER TABLE `quest`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4foeblydmua10qb2oeuane6fb` (`category_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
