-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 21, 2017 at 09:07 PM
-- Server version: 5.7.20-0ubuntu0.17.10.1
-- PHP Version: 7.1.11-0ubuntu0.17.10.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spring`
--

-- --------------------------------------------------------

--
-- Table structure for table `Course`
--

CREATE TABLE `Course` (
  `courseCode` varchar(255) NOT NULL,
  `credits` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `postConditions` varchar(10000) DEFAULT NULL,
  `preConditions` varchar(10000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Course`
--

INSERT INTO `Course` (`courseCode`, `credits`, `name`, `postConditions`, `preConditions`) VALUES
('AP', 4, 'Advanced Programming', '\"Students are able to demonstrate the knowledge of basic principles of Object Oriented Programming such as encapsulation (classes and objects) and interfaces and polymorphism and inheritance; by implementing programs ranging over few hundreds lines of code. : Implement basic event driven programming and exception handling and threading. : Students are able to analyze the problem in terms of use cases and create object oriented design for it. Students are able to present the design in UML. : Students are able to select and use a few key design pattern to solve a given problem in hand. : Students are able to use common tools for testing (e.g. JUnit) and debugging and source code control as an integral part of program development.\"', '\"Introduction to Programming (CSE101) : Data Structures & Algorithms (CSE102)\"'),
('CO', 4, 'Computer Organization', '\"Write program in assembly language(RISC ISA) - Compare performance between several implementations of a computer program. : Analyse processor performance for different implementation strategies : eg. single vs. multicycle pipelined vs. non-pipelined execution. : Simulate and compare performance of cache memory compare caches with different configuration. : Analyse basic I/O operation and their performance.\"', '\"Introduction to Programming (CSE101) : Digital Circuits(CSE111)\"'),
('DM', 4, 'Discrete Mathematics', ' \"Be able to read interpret and write some basic mathematical notation(s) : be able to recognize and/or construct examples of mathematical objects introduced during the course such as the sets and functions : have been introduced to several mathematical models (for e.g. propositional logic and trees) including some of those underlying computing and information technology : have had the opportunity to develop capacity in knowing what constitutes a valid argument and in constructing valid arguments/proofs : have had an opportunity to develop the problem-solving skills\"', ' \"A good knowledge of elementary mathematics especially algebra calculus and basic programming language.\"'),
('ES', 4, 'Key Concepts in Economic Sociology', '\"Economic Sociology on the other hand encourages students to see rational self-interest and action as embedded in society and influenced by group dynamics along with legal institutions and  power or organizations and even culture. It justifies the application of independent sociological methods and concepts to the examination and understanding of the Economy as a sphere of production and consumption and exchange of goods and services which is observable in all human societies not only the modern and advanced. It thereby encourages students to relate economic actions to all other forms of social actions observable in society – the religious and the familial and the technological and the political. Economic sociology identifies the obligatory and symbolic and power seeking motivations underlying human economic action not only the useful or utilitarian\"', ''),
('IDE', 4, 'Introduction to Digital Ethonography', '\"This course is intended to be an introductory course and will systematically introduce learners to the basics of Digital Ethnography. The course will first introduce learners to the fundamentals of Ethnography as a research method and will then help facilitate its application to the Digital world.\"', ''),
('IMS', 4, 'Introduction to Media in Society : A Public Sphere Approach', '\"Describe and analyze the role that media and journalism play in contemporary society : Demonstrate an understanding of the key issues in media studies and articulate the notion of public sphere and civic participation in India : Interpret and explain change in media’s role in relation to the other trends particularly digitalization and globalization : Independently and critically analyze the potential of online technologies to enhance civic and citizens’ participation\"', ''),
('ISI', 4, 'Introduction to Social Informatics', '\"Social informatics is an interdisciplinary field of research dedicated to studying the design with use and effects of information technologies. Social informatics seeks to understand how social contexts shape the form and character of technology. Likewise it also studies how technology influences power relationships and restructures social and organizational networks. In this course we will be developing our understanding of these issues by engaging critically with various controversial topics relating to information and communication technologies. This course urges students to go beyond the “merely technical” aspects of ICTs and consider the social relations that are an integral part of designing and adopting a technology or a technological system. It also challenges students to think critically about technological change and acquire a more sophisticated understanding of the process. \"', ''),
('M-III', 4, 'Math-III', '\"Students are able to apply concepts of continuity and differentiability and extrema and integrability of multivariable functions and evaluate various integrals (line and double and triple and surface integrals) : Students are able to work with vector fields and evaluate line and surface integrals and calculate quantities such as work and circulation and flux across plane curves and surfaces : be able to carry out vector derivative operations such as gradient and divergence and curl and understand and apply Green\'s and  Stoke\'s and divergence theorems. : Students are able to evaluate derivatives and integrals of complex functions including the applications of Cauchy’s theorem.Students are able to determine convergence of complex series and power series and understand and apply Taylor series to represent complex functions.\"', ' '),
('NT', 4, 'Number Theory', '\"To be able to write down formal Mathematical proofs. To be able to describe properties of some special : numbertheorectic functions and their growth rates. : To be able to explain properties of numbers of some special forms. : To be able to describe complete solutions of some special Diophanti ne equationsand to be able to explain insolvability of some special non-linear Diophantineequations.To be able to explain the proof : of Lagrange\'s four squares theorem.To be able to explain basic theory of partitions : To be able to explain elementary results on distributions of prime numbers : To be able to apply numbertheoretic techniques to simply computations. \"', ' '),
('PO', 4, 'Introduction to Poetry', '\"Major schools of poetry post 19th century. : Major English language poets from the British and American canon and post romantic era. : Evolution of English poetry in India along with famous poets. : Figures of speech and different forms of poetry.\"', ''),
('PoK', 4, 'Perspectives of Knowledge', ' \"Identify the meta-issues concerning knowledge and critically reflect on them. : Demonstrate awareness of the diversity of epistemic and sociology of knowledge traditions. : Get acquainted with a set of specific arguments methodologies and approaches to questions regarding knowledge. : Develop an awareness of themselves as knowledge producers in the information society.\"', ' '),
('Psy', 4, 'Introduction to Psychology', ' \"Students would have learnt about concepts of General Psychology. : Students would learn about basics of how people think feel and behave. : Students would learn the history and schools of Psychology. : Students would be taught how to practice Mindfulness and would be able to practice it by their own. : Students would get to learn basics concepts of therapies and some practical skills of how to practice them. : Student would be able to practice the concepts of Cognitive behavior Therapy and be able to practically study their own thoughts and manage them.\"', ' '),
('RA', 4, 'Real Analysis I', '\"Students are familiar with the real number system and can determine convergence-divergence of sequences and series and are able to construct epsilon-delta proofs related to limits and continuity. : Students are familiar with the concepts of differentiation and integration and are able to apply the methods of single-variable calculus. : Students are able to apply concepts of continuity and differentiability and  extrema and integrability of multivariable functions and evaluate various integrals (line and double and  triple and surface integrals)\"', ' '),
('S&S', 4, 'Signal & Systems', '\"Students are able to classify basic signal representation; continuous time and discrete-time signals : Students are able to determine the response of an LTI system to continuous-time or discrete-time input signals. : Students are able to represent both continuous-time and discrete-time periodic signals as Fourier series. : Students are able to analyze continuous-time signals and systems using Fourier transform and Laplace transform. : Students are able to analyze discrete-time signals and systems using discrete-time Fourier transform and Z-transform.\"', 'MTH100 Maths I'),
('TA', 4, 'Theatre Appreciation', '\"Student have Knowledge of Biographies and Techniques proposed by Theatre Masters : Students have ability to Overcome inhibitions in communication : Students have ability to put up a mime show : Students have some ability to perform a play\"', ''),
('TPEE', 4, 'Theory & Practice of Engineering Ethics', '\"Demonstrate a clear understanding of meta-ethical theories. : Apply meta-ethical theories to engineering and IT scenarios. : Develop a finessed multi-perspectival understanding of moral issues. : Evaluate moral statements and practices clearly and critically.\"', '');

-- --------------------------------------------------------

--
-- Table structure for table `CourseEvent`
--

CREATE TABLE `CourseEvent` (
  `courseEventID` int(11) NOT NULL,
  `dayOfWeek` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `endTime` time DEFAULT NULL,
  `eventType` int(11) DEFAULT NULL,
  `startTime` time DEFAULT NULL,
  `course_courseCode` varchar(255) DEFAULT NULL,
  `room_roomName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CourseEvent`
--

INSERT INTO `CourseEvent` (`courseEventID`, `dayOfWeek`, `description`, `endTime`, `eventType`, `startTime`, `course_courseCode`, `room_roomName`) VALUES
(1, 1, 'Why the fuck do we have description?', '15:00:00', 1, '13:30:00', 'DM', 'C21'),
(3, 4, 'Why the fuck do we have description?', '13:00:00', 1, '11:30:00', 'DM', 'C21'),
(5, 4, 'description', '17:30:00', 3, '16:00:00', 'DM', 'LR1'),
(6, 4, 'description', '17:30:00', 3, '16:00:00', 'DM', 'LR2'),
(7, 0, 'Why the fuck do we have description?', '12:00:00', 1, '11:00:00', 'AP', 'C21'),
(9, 2, 'Why the fuck do we have description?', '12:00:00', 1, '11:00:00', 'AP', 'C21'),
(11, 3, 'Why the fuck do we have description?', '12:00:00', 1, '11:00:00', 'AP', 'C21'),
(13, 0, 'description', '13:00:00', 3, '12:00:00', 'AP', 'LR1'),
(14, 0, 'description', '13:00:00', 3, '12:00:00', 'AP', 'LR2'),
(15, 0, 'description', '13:00:00', 3, '12:00:00', 'AP', 'S01'),
(16, 3, 'description', '13:00:00', 2, '12:00:00', 'AP', 'L21'),
(17, 3, 'description', '13:00:00', 2, '12:00:00', 'AP', 'L22'),
(18, 2, 'Why the fuck do we have description?', '16:00:00', 1, '14:30:00', 'CO', 'C21'),
(20, 3, 'Why the fuck do we have description?', '17:30:00', 1, '16:00:00', 'CO', 'C01'),
(22, 2, 'description', '14:30:00', 3, '13:30:00', 'CO', 'LR2'),
(23, 2, 'description', '14:30:00', 3, '13:30:00', 'CO', 'LR3'),
(24, 2, 'description', '14:30:00', 3, '13:30:00', 'CO', 'C22'),
(25, 1, 'Why the fuck do we have description?', '16:00:00', 1, '15:00:00', 'M-III', 'C21'),
(27, 3, 'Why the fuck do we have description?', '16:00:00', 1, '15:00:00', 'M-III', 'C21'),
(29, 4, 'Why the fuck do we have description?', '16:00:00', 1, '15:00:00', 'M-III', 'C21'),
(31, 1, 'Why the fuck do we have description?', '12:00:00', 1, '11:00:00', 'S&S', 'C21'),
(33, 3, 'Why the fuck do we have description?', '15:00:00', 1, '14:00:00', 'S&S', 'C21'),
(35, 4, 'Why the fuck do we have description?', '15:00:00', 1, '14:00:00', 'S&S', 'C21'),
(37, 0, 'Why the fuck do we have description?', '16:00:00', 1, '15:00:00', 'RA', 'C02'),
(39, 1, 'Why the fuck do we have description?', '11:00:00', 1, '10:00:00', 'RA', 'C02'),
(41, 3, 'Why the fuck do we have description?', '11:00:00', 1, '10:00:00', 'RA', 'C02'),
(43, 0, 'Why the fuck do we have description?', '11:00:00', 1, '10:00:00', 'NT', 'C12'),
(45, 2, 'Why the fuck do we have description?', '11:00:00', 1, '10:00:00', 'NT', 'C12'),
(47, 4, 'Why the fuck do we have description?', '10:30:00', 1, '09:30:00', 'NT', 'C12'),
(49, 0, 'Why the fuck do we have description?', '17:30:00', 1, '16:00:00', 'PoK', 'C24'),
(51, 2, 'Why the fuck do we have description?', '17:30:00', 1, '16:00:00', 'PoK', 'C24'),
(53, 0, 'Why the fuck do we have description?', '17:30:00', 1, '16:00:00', 'Psy', 'C01'),
(55, 2, 'Why the fuck do we have description?', '17:30:00', 1, '16:00:00', 'Psy', 'C01'),
(57, 1, 'Why the fuck do we have description?', '17:30:00', 1, '16:00:00', 'TPEE', 'C22'),
(59, 4, 'Why the fuck do we have description?', '17:30:00', 1, '16:00:00', 'TPEE', 'C22'),
(61, 0, 'Why the fuck do we have description?', '17:30:00', 1, '16:00:00', 'TA', 'C12'),
(63, 2, 'Why the fuck do we have description?', '17:30:00', 1, '16:00:00', 'TA', 'C12'),
(65, 1, 'Why the fuck do we have description?', '17:30:00', 1, '16:00:00', 'ES', 'C24'),
(67, 4, 'Why the fuck do we have description?', '17:30:00', 1, '16:00:00', 'ES', 'C24'),
(69, 0, 'Why the fuck do we have description?', '17:30:00', 1, '16:00:00', 'IDE', 'C11'),
(71, 2, 'Why the fuck do we have description?', '17:30:00', 1, '16:00:00', 'IDE', 'C11'),
(73, 1, 'Why the fuck do we have description?', '17:30:00', 1, '16:00:00', 'IMS', 'C03'),
(75, 4, 'Why the fuck do we have description?', '17:30:00', 1, '16:00:00', 'IMS', 'C03'),
(77, 1, 'Why the fuck do we have description?', '17:30:00', 1, '16:00:00', 'ISI', 'C13'),
(79, 4, 'Why the fuck do we have description?', '17:30:00', 1, '16:00:00', 'ISI', 'C13'),
(81, 1, 'Why the fuck do we have description?', '18:00:00', 1, '16:30:00', 'PO', 'C02');

-- --------------------------------------------------------

--
-- Table structure for table `Course_courseNumber`
--

CREATE TABLE `Course_courseNumber` (
  `Course_courseCode` varchar(255) NOT NULL,
  `courseNumber` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Course_departments`
--

CREATE TABLE `Course_departments` (
  `Course_courseCode` varchar(255) NOT NULL,
  `departments` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Course_departments`
--

INSERT INTO `Course_departments` (`Course_courseCode`, `departments`) VALUES
('CO', 0),
('M-III', 1),
('RA', 1),
('NT', 1),
('PoK', 4),
('Psy', 4),
('TPEE', 4),
('TA', 4),
('ES', 4),
('IDE', 4),
('IMS', 4),
('ISI', 4),
('PO', 4);

-- --------------------------------------------------------

--
-- Table structure for table `Event`
--

CREATE TABLE `Event` (
  `eventID` int(11) NOT NULL,
  `checkWhy` bit(1) DEFAULT NULL,
  `creationTime` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `endTime` time DEFAULT NULL,
  `isCancelled` bit(1) DEFAULT NULL,
  `isCourseEvent` bit(1) DEFAULT NULL,
  `isPending` bit(1) DEFAULT NULL,
  `isRejected` bit(1) DEFAULT NULL,
  `startTime` time DEFAULT NULL,
  `tagline` varchar(255) DEFAULT NULL,
  `course_courseCode` varchar(255) DEFAULT NULL,
  `creators_email` varchar(255) DEFAULT NULL,
  `room_roomName` varchar(255) DEFAULT NULL,
  `roomCapacity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Event`
--

INSERT INTO `Event` (`eventID`, `checkWhy`, `creationTime`, `date`, `description`, `endTime`, `isCancelled`, `isCourseEvent`, `isPending`, `isRejected`, `startTime`, `tagline`, `course_courseCode`, `creators_email`, `room_roomName`, `roomCapacity`) VALUES
(30, b'0', '2017-11-18 18:06:01.226377', '1970-01-01', 'useless as fuck', '16:00:00', b'0', b'1', b'0', b'0', '15:00:00', 'Class aa jaya karo bc', 'M-III', 'sarthok sircar@iiitd.ac.in', 'C21', NULL),
(32, b'0', '2017-11-18 18:06:01.226377', '1970-01-01', 'useless as fuck', '12:00:00', b'0', b'1', b'0', b'1', '11:00:00', 'Class aa jaya karo bc', 'S&S', 'anubha gupta@iiitd.ac.in', 'C21', NULL),
(83, b'0', '2017-11-18 18:06:01.226377', '3917-12-19', 'THis is cool', '14:00:00', b'0', b'0', b'0', b'0', '13:00:00', NULL, NULL, NULL, NULL, NULL),
(84, b'0', '2017-11-18 18:06:01.226377', '3917-12-18', 'WoW Des', '15:30:00', b'0', b'0', b'0', b'0', '15:00:00', NULL, NULL, NULL, NULL, NULL),
(85, b'0', '2017-11-18 18:06:01.226377', '3917-12-18', 'olko Desc', '17:30:00', b'0', b'0', b'0', b'0', '15:30:00', NULL, NULL, NULL, NULL, NULL),
(86, b'0', '2017-11-18 18:06:01.226377', '3917-12-18', 'olko Desc', '17:30:00', b'0', b'0', b'0', b'0', '15:30:00', NULL, NULL, NULL, NULL, NULL),
(88, b'0', '2017-11-18 10:47:52.000000', '3917-12-25', 'useless as fuck', '11:00:00', b'0', b'0', b'0', b'0', '09:30:00', 'Class aa jaya karo bc', NULL, 'ravi@iiitd.ac.in', 'C21', NULL),
(89, b'0', '2017-11-18 10:55:21.000000', '3917-12-25', 's', '10:00:00', b'0', b'0', b'0', b'0', '09:00:00', 'Mst', NULL, 'ravi@iiitd.ac.in', 'C01', NULL),
(90, b'0', '2017-11-18 11:23:24.000000', '3917-12-29', 'useless as fuck', '13:00:00', b'0', b'0', b'0', b'0', '11:00:00', 'Class aa jaya karo bc', NULL, 'ravi@iiitd.ac.in', 'C21', NULL),
(91, b'0', '2017-11-18 11:24:36.000000', '3917-12-24', 'useless as fuck', '12:00:00', b'0', b'0', b'0', b'0', '10:00:00', 'Class aa jaya karo bc', NULL, 'ravi@iiitd.ac.in', 'C21', NULL),
(92, b'0', '2017-11-18 13:15:30.000000', '2017-12-17', 'wow', '14:30:00', b'0', b'0', b'0', b'0', '12:30:00', 'wow', NULL, 'ravi@iiitd.ac.in', 'C02', NULL),
(104, b'0', '2017-11-28 09:03:39.000000', '2018-01-01', 'This is a random event ', '18:30:00', b'0', b'0', b'0', b'0', '18:00:00', 'Random Event', NULL, 'praveen@iiitd.ac.in', 'S01', NULL),
(105, b'0', '2017-11-28 09:10:49.000000', '2017-12-30', 'TEST', '15:30:00', b'0', b'0', b'0', b'0', '12:00:00', 'TEST', NULL, 'praveen@iiitd.ac.in', 'C21', NULL),
(107, b'0', '2017-11-28 09:52:05.000000', '2017-12-30', 'edfasdf', '14:30:00', b'0', b'0', b'0', b'0', '12:00:00', 'randra', NULL, 'student@iiitd.ac.in', 'C12', NULL),
(109, b'0', '2017-11-28 09:58:36.000000', '2017-12-28', 'weijdsfolk', '17:00:00', b'0', b'0', b'0', b'0', '14:30:00', 'ewfohdjkx', NULL, 'ravi@iiitd.ac.in', 'C01', NULL),
(110, b'0', '2017-11-28 09:58:44.000000', '2017-12-28', 'weijdsfolk', '17:00:00', b'0', b'0', b'0', b'0', '14:30:00', 'ewfohdjkx', NULL, 'ravi@iiitd.ac.in', 'C01', NULL),
(111, b'0', '2017-11-28 09:58:45.000000', '2017-12-28', 'weijdsfolk', '17:00:00', b'0', b'0', b'0', b'0', '14:30:00', 'ewfohdjkx', NULL, 'ravi@iiitd.ac.in', 'C01', NULL),
(114, b'0', '2017-11-28 10:02:36.000000', '2017-12-29', 'try', '15:30:00', b'0', b'0', b'0', b'0', '12:30:00', 'trial', NULL, 'samaresh chatterjee@iiitd.ac.in', 'C13', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `Faculty_Project`
--

CREATE TABLE `Faculty_Project` (
  `courseCode` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Faculty_Project`
--

INSERT INTO `Faculty_Project` (`courseCode`, `email`) VALUES
('IMS', 'aasim khan@iiitd.ac.in'),
('Psy', 'akshay kumar@iiitd.ac.in'),
('ES', 'amrit srinivasan@iiitd.ac.in'),
('S&S', 'anubha gupta@iiitd.ac.in'),
('NT', 'anuradha sharma@iiitd.ac.in'),
('DM', 'dhongoon chang@iiitd.ac.in'),
('TA', 'manohar khushlani@iiitd.ac.in'),
('CO', 'naveen prakash@iiitd.ac.in'),
('PoK', 'raj ayyar@iiitd.ac.in'),
('TPEE', 'raj ayyar@iiitd.ac.in'),
('RA', 'samaresh chatterjee@iiitd.ac.in'),
('M-III', 'sarthok sircar@iiitd.ac.in'),
('PO', 'saumya kulshreshtha@iiitd.ac.in'),
('IDE', 'shriram venkatraman@iiitd.ac.in'),
('ISI', 'venkata ratandeep suri@iiitd.ac.in'),
('AP', 'vivek kumar@iiitd.ac.in');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(115),
(115);

-- --------------------------------------------------------

--
-- Table structure for table `Room`
--

CREATE TABLE `Room` (
  `roomName` varchar(255) NOT NULL,
  `capacity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Room`
--

INSERT INTO `Room` (`roomName`, `capacity`) VALUES
('C01', 0),
('C02', 0),
('C03', 0),
('C11', 0),
('C12', 0),
('C13', 0),
('C21', 0),
('C22', 0),
('C24', 0),
('L21', 0),
('L22', 0),
('LR1', 0),
('LR2', 0),
('LR3', 0),
('S01', 0);

-- --------------------------------------------------------

--
-- Table structure for table `Students_AuditedCourse`
--

CREATE TABLE `Students_AuditedCourse` (
  `courseCode` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Students_AuditedCourse`
--

INSERT INTO `Students_AuditedCourse` (`courseCode`, `email`) VALUES
('ISI', 'siddharth16268@iiitd.ac.in');

-- --------------------------------------------------------

--
-- Table structure for table `Students_RegisteredCourse`
--

CREATE TABLE `Students_RegisteredCourse` (
  `courseCode` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Students_RegisteredCourse`
--

INSERT INTO `Students_RegisteredCourse` (`courseCode`, `email`) VALUES
('AP', 'praveen@iiitd.ac.in'),
('AP', 'siddharth16268@iiitd.ac.in'),
('DM', 'siddharth16268@iiitd.ac.in'),
('IDE', 'siddharth16268@iiitd.ac.in'),
('M-III', 'siddharth16268@iiitd.ac.in'),
('Psy', 'siddharth16268@iiitd.ac.in'),
('RA', 'siddharth16268@iiitd.ac.in'),
('Psy', 'siddhartha16269@iiitd.ac.in'),
('DM', 'student@iiitd.ac.in'),
('ES', 'student@iiitd.ac.in'),
('NT', 'student@iiitd.ac.in');

-- --------------------------------------------------------

--
-- Table structure for table `Students_ShoppingCourse`
--

CREATE TABLE `Students_ShoppingCourse` (
  `courseCode` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `DTYPE` varchar(31) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `rollNumber` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`DTYPE`, `email`, `name`, `password`, `rollNumber`) VALUES
('Faculty', 'aasim khan@iiitd.ac.in', 'Aasim Khan', NULL, NULL),
('Student', 'abhishek16004@iiitd.ac.in', 'Abhishek Gupta', 'password', NULL),
('Faculty', 'akshay kumar@iiitd.ac.in', 'Akshay Kumar', NULL, NULL),
('Faculty', 'amrit srinivasan@iiitd.ac.in', 'Amrit Srinivasan', NULL, NULL),
('Faculty', 'anubha gupta@iiitd.ac.in', 'Anubha Gupta', NULL, NULL),
('Faculty', 'anuradha sharma@iiitd.ac.in', 'Anuradha Sharma', NULL, NULL),
('Faculty', 'dhongoon chang@iiitd.ac.in', 'Dhongoon Chang', NULL, NULL),
('Faculty', 'manohar khushlani@iiitd.ac.in', 'Manohar Khushlani', NULL, NULL),
('Faculty', 'naveen prakash@iiitd.ac.in', 'Naveen Prakash', NULL, NULL),
('Student', 'praveen@iiitd.ac.in', 'praveen', 'thisisit', NULL),
('Faculty', 'raj ayyar@iiitd.ac.in', 'Raj Ayyar', NULL, NULL),
('Admin', 'ravi@iiitd.ac.in', 'Ravi Bhasin', 'RaVi', NULL),
('Faculty', 'samaresh chatterjee@iiitd.ac.in', 'Samaresh Chatterjee', '123', NULL),
('Faculty', 'sarthok sircar@iiitd.ac.in', 'Sarthok Sircar', '123', NULL),
('Faculty', 'saumya kulshreshtha@iiitd.ac.in', 'Saumya Kulshreshtha', '123', NULL),
('Faculty', 'shriram venkatraman@iiitd.ac.in', 'Shriram Venkatraman', '123', NULL),
('Student', 'siddharth16268@iiitd.ac.in', 'Siddharth Yadav', '[{Sid@123}]', '2016268'),
('Student', 'siddhartha16269@iiitd.ac.in', 'Siddhartha Jain', 'g-Y87^k)', '2016269'),
('Student', 'student@iiitd.ac.in', 'student', 'thisispass', NULL),
('Faculty', 'venkata ratandeep suri@iiitd.ac.in', 'Venkata Ratandeep Suri', NULL, NULL),
('Faculty', 'vivek kumar@iiitd.ac.in', 'Vivek Kumar', '123', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Course`
--
ALTER TABLE `Course`
  ADD PRIMARY KEY (`courseCode`);

--
-- Indexes for table `CourseEvent`
--
ALTER TABLE `CourseEvent`
  ADD PRIMARY KEY (`courseEventID`),
  ADD KEY `FKa185jp4np3fblyrv7yirbogno` (`course_courseCode`),
  ADD KEY `FKpmu6fos27wyy2862wimivehym` (`room_roomName`);

--
-- Indexes for table `Course_courseNumber`
--
ALTER TABLE `Course_courseNumber`
  ADD KEY `FKlxqlr3ahef7sis3j6s1xxg03w` (`Course_courseCode`);

--
-- Indexes for table `Course_departments`
--
ALTER TABLE `Course_departments`
  ADD KEY `FKntecqryykm2lbxo5kshe0ygri` (`Course_courseCode`);

--
-- Indexes for table `Event`
--
ALTER TABLE `Event`
  ADD PRIMARY KEY (`eventID`),
  ADD KEY `FK1cif1xjv953oua8qvs8fka0ir` (`course_courseCode`),
  ADD KEY `FKl8ppt092tlkpfrnd7is2cikcq` (`creators_email`),
  ADD KEY `FK554o5ngc4nck0aik8liwnxbp4` (`room_roomName`);

--
-- Indexes for table `Faculty_Project`
--
ALTER TABLE `Faculty_Project`
  ADD PRIMARY KEY (`courseCode`,`email`),
  ADD KEY `FKo4bmrkcsxqp808e11rn8yip32` (`email`);

--
-- Indexes for table `Room`
--
ALTER TABLE `Room`
  ADD PRIMARY KEY (`roomName`);

--
-- Indexes for table `Students_AuditedCourse`
--
ALTER TABLE `Students_AuditedCourse`
  ADD PRIMARY KEY (`courseCode`,`email`),
  ADD KEY `FKe4svcytpp1k1arpewkc7levpl` (`email`);

--
-- Indexes for table `Students_RegisteredCourse`
--
ALTER TABLE `Students_RegisteredCourse`
  ADD PRIMARY KEY (`courseCode`,`email`),
  ADD KEY `FK9evv7vde77h10uroxycv8n9mj` (`email`);

--
-- Indexes for table `Students_ShoppingCourse`
--
ALTER TABLE `Students_ShoppingCourse`
  ADD PRIMARY KEY (`courseCode`,`email`),
  ADD KEY `FKe5321qsq9jj1gpr2bwxaqejag` (`email`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`email`),
  ADD UNIQUE KEY `UK_715ufu2gm9u5mhdxbf79ghall` (`rollNumber`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `CourseEvent`
--
ALTER TABLE `CourseEvent`
  ADD CONSTRAINT `FKa185jp4np3fblyrv7yirbogno` FOREIGN KEY (`course_courseCode`) REFERENCES `Course` (`courseCode`),
  ADD CONSTRAINT `FKpmu6fos27wyy2862wimivehym` FOREIGN KEY (`room_roomName`) REFERENCES `Room` (`roomName`);

--
-- Constraints for table `Course_courseNumber`
--
ALTER TABLE `Course_courseNumber`
  ADD CONSTRAINT `FKlxqlr3ahef7sis3j6s1xxg03w` FOREIGN KEY (`Course_courseCode`) REFERENCES `Course` (`courseCode`);

--
-- Constraints for table `Course_departments`
--
ALTER TABLE `Course_departments`
  ADD CONSTRAINT `FKntecqryykm2lbxo5kshe0ygri` FOREIGN KEY (`Course_courseCode`) REFERENCES `Course` (`courseCode`);

--
-- Constraints for table `Event`
--
ALTER TABLE `Event`
  ADD CONSTRAINT `FK1cif1xjv953oua8qvs8fka0ir` FOREIGN KEY (`course_courseCode`) REFERENCES `Course` (`courseCode`),
  ADD CONSTRAINT `FK554o5ngc4nck0aik8liwnxbp4` FOREIGN KEY (`room_roomName`) REFERENCES `Room` (`roomName`),
  ADD CONSTRAINT `FKl8ppt092tlkpfrnd7is2cikcq` FOREIGN KEY (`creators_email`) REFERENCES `User` (`email`);

--
-- Constraints for table `Faculty_Project`
--
ALTER TABLE `Faculty_Project`
  ADD CONSTRAINT `FKbkk4he7s96lulvmvljlfdxaip` FOREIGN KEY (`courseCode`) REFERENCES `Course` (`courseCode`),
  ADD CONSTRAINT `FKo4bmrkcsxqp808e11rn8yip32` FOREIGN KEY (`email`) REFERENCES `User` (`email`);

--
-- Constraints for table `Students_AuditedCourse`
--
ALTER TABLE `Students_AuditedCourse`
  ADD CONSTRAINT `FKe4svcytpp1k1arpewkc7levpl` FOREIGN KEY (`email`) REFERENCES `User` (`email`),
  ADD CONSTRAINT `FKoivjfh5nh6qeu7un8p775kjsb` FOREIGN KEY (`courseCode`) REFERENCES `Course` (`courseCode`);

--
-- Constraints for table `Students_RegisteredCourse`
--
ALTER TABLE `Students_RegisteredCourse`
  ADD CONSTRAINT `FK9evv7vde77h10uroxycv8n9mj` FOREIGN KEY (`email`) REFERENCES `User` (`email`),
  ADD CONSTRAINT `FKm75rkq5gfnad1nlrsq3j7ih6t` FOREIGN KEY (`courseCode`) REFERENCES `Course` (`courseCode`);

--
-- Constraints for table `Students_ShoppingCourse`
--
ALTER TABLE `Students_ShoppingCourse`
  ADD CONSTRAINT `FK1oyxl6l6qj0g4ilfi9awx0qsi` FOREIGN KEY (`courseCode`) REFERENCES `Course` (`courseCode`),
  ADD CONSTRAINT `FKe5321qsq9jj1gpr2bwxaqejag` FOREIGN KEY (`email`) REFERENCES `User` (`email`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
