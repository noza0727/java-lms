-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 25, 2019 at 02:13 PM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `ID` varchar(100) NOT NULL,
  `FirstName` varchar(100) DEFAULT NULL,
  `LastName` varchar(100) DEFAULT NULL,
  `Password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`ID`, `FirstName`, `LastName`, `Password`) VALUES
('U101', 'Damir', 'Vilyamov', 'U101'),
('U133', 'Kamila', 'Aripova', 'U133'),
('U140', 'Noza', 'Noza', 'U140'),
('U181', 'admin', 'admin', 'U181');

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `isbn` varchar(10) NOT NULL,
  `title` varchar(100) NOT NULL,
  `author` varchar(50) NOT NULL,
  `genre` varchar(50) NOT NULL,
  `publish_year` varchar(4) NOT NULL,
  `amount` int(100) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`isbn`, `title`, `author`, `genre`, `publish_year`, `amount`) VALUES
('0062073486', 'And Then There Were None', 'Agatha Christie', 'Mystery', '2011', 3),
('0062339273', 'True Story', 'Michael Finkel', 'Memoir', '2015', 1),
('0262033844', 'Introduction to Algorithms', 'Thomas Cormen', 'Computer Science', '2009', 3),
('0449912493', 'Heaven Has No Favorites', 'Erich Maria Remarque', 'Novel', '2014', 0),
('0451191145', 'Atlas Shrugged', 'Ayn Rand', 'Dystopia', '1996', 4),
('0655521895', 'End-to-End Encryption', 'Gerardus Blokdyk', 'Computer Science', '2019', 2),
('0691129088', 'Game Theory', 'Steven Tadelis', 'Mathematics', '2013', 0),
('074324754X', 'The Glass Castle', 'Jeannette Walls', 'Memoir', '2006', 1),
('0980232775', 'Linear Algebra', 'Gilbert Strang', 'Mathematics', '2017', 2),
('1119566258', 'Data Science for Dummies', 'Ulrika Jagare', 'Computer Science', '2019', 0),
('1405923539', 'Unnatural Causes', 'Richard Shepherd', 'Biography', '2019', 3),
('1501135929', 'Shoe Dog', 'Phil Knight', 'Memoir', '2018', 3),
('1590172000', 'Beware of Pity', 'Stefan Zweig', 'Novel', '2006', 2),
('1605353809', 'Neuroscience', 'Dale Purves', 'Science', '2017', 5);

-- --------------------------------------------------------

--
-- Table structure for table `librarians`
--

CREATE TABLE `librarians` (
  `id` varchar(100) NOT NULL DEFAULT '0',
  `firstname` varchar(100) NOT NULL DEFAULT ' none',
  `lastname` varchar(100) NOT NULL DEFAULT 'none',
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `librarians`
--

INSERT INTO `librarians` (`id`, `firstname`, `lastname`, `password`) VALUES
('L1810101', 'Vilyamov', 'Damir', 'U1810101'),
('L1810133', 'Kamila', 'Aripova', 'L1810133'),
('L1810182', 'Kobilbek', 'Umarbekov', 'L1810182');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `id` varchar(100) NOT NULL DEFAULT 'none',
  `firstname` varchar(100) NOT NULL DEFAULT 'none',
  `lastname` varchar(100) NOT NULL DEFAULT 'none',
  `password` varchar(100) NOT NULL DEFAULT 'none',
  `year` varchar(100) NOT NULL DEFAULT 'none',
  `department` varchar(100) NOT NULL DEFAULT 'none'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`id`, `firstname`, `lastname`, `password`, `year`, `department`) VALUES
('U1810101', 'Damir', 'Vilyamov', 'U1810101', 'Sophomore', 'SOCIE'),
('U1810133', 'Kamila', 'Aripova', 'U1810133', 'Sophomore', 'SOCIE'),
('U1810140', 'Shakhnoza', 'Yormukhamedova', 'U1810140', 'Sophomore', 'SOCIE'),
('U1810182', 'Kobilbek', 'Umarbekov', 'U1810182', 'Sophomore', 'SOCIE');

-- --------------------------------------------------------

--
-- Table structure for table `u1810101`
--

CREATE TABLE `u1810101` (
  `bookIsbn` varchar(100) NOT NULL DEFAULT 'none',
  `issueDate` varchar(100) DEFAULT '00.00.00',
  `bookTitle` varchar(100) DEFAULT 'none',
  `bookAuthor` varchar(100) DEFAULT 'none',
  `bookGenre` varchar(100) DEFAULT 'none',
  `bookYear` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `u1810101`
--

INSERT INTO `u1810101` (`bookIsbn`, `issueDate`, `bookTitle`, `bookAuthor`, `bookGenre`, `bookYear`) VALUES
('0062073486', '25/12/2019 17:53:57', 'And Then There Were None', 'Agatha Christie', 'Mystery', 2011);

-- --------------------------------------------------------

--
-- Table structure for table `u1810133`
--

CREATE TABLE `u1810133` (
  `bookIsbn` varchar(100) NOT NULL DEFAULT 'none',
  `issueDate` varchar(100) DEFAULT '00.00.00',
  `bookTitle` varchar(100) DEFAULT 'none',
  `bookAuthor` varchar(100) DEFAULT 'none',
  `bookGenre` varchar(100) DEFAULT 'none',
  `bookYear` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `u1810133`
--

INSERT INTO `u1810133` (`bookIsbn`, `issueDate`, `bookTitle`, `bookAuthor`, `bookGenre`, `bookYear`) VALUES
('0062073486', '25/12/2019 17:53:57', 'And Then There Were None', 'Agatha Christie', 'Mystery', 2011);

-- --------------------------------------------------------

--
-- Table structure for table `u1810140`
--

CREATE TABLE `u1810140` (
  `bookIsbn` varchar(100) NOT NULL DEFAULT 'none',
  `issueDate` varchar(100) DEFAULT '00.00.00',
  `bookTitle` varchar(100) DEFAULT 'none',
  `bookAuthor` varchar(100) DEFAULT 'none',
  `bookGenre` varchar(100) DEFAULT 'none',
  `bookYear` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `u1810140`
--

INSERT INTO `u1810140` (`bookIsbn`, `issueDate`, `bookTitle`, `bookAuthor`, `bookGenre`, `bookYear`) VALUES
('0262033844', '25/12/2019 12:43:53', 'Introduction to Algorithms', 'Thomas Cormen', 'Computer Science', 2009),
('0655521895', '25/12/2019 17:18:11', 'End-to-End Encryption', 'Gerardus Blokdyk', 'Computer Science', 2019);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD UNIQUE KEY `adminID` (`ID`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`isbn`);

--
-- Indexes for table `librarians`
--
ALTER TABLE `librarians`
  ADD UNIQUE KEY `libID` (`id`),
  ADD UNIQUE KEY `ID` (`id`),
  ADD KEY `FirstName` (`firstname`),
  ADD KEY `LastName` (`lastname`),
  ADD KEY `firstname_2` (`firstname`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `u1810101`
--
ALTER TABLE `u1810101`
  ADD PRIMARY KEY (`bookIsbn`);

--
-- Indexes for table `u1810133`
--
ALTER TABLE `u1810133`
  ADD PRIMARY KEY (`bookIsbn`);

--
-- Indexes for table `u1810140`
--
ALTER TABLE `u1810140`
  ADD PRIMARY KEY (`bookIsbn`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
