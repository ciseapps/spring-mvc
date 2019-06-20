-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 08, 2019 at 02:29 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spring_cms`
--

-- --------------------------------------------------------

--
-- Table structure for table `app_config`
--

CREATE TABLE `app_config` (
  `configuration_id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `configuration` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `app_constant`
--

CREATE TABLE `app_constant` (
  `app_variable_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Table structure for table `app_menu`
--

CREATE TABLE `app_menu` (
  `menu_id` int(12) NOT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `menu_icon` varchar(255) DEFAULT NULL,
  `module_name` text,
  `type_menu` varchar(1) DEFAULT NULL,
  `seq_number` int(10) DEFAULT NULL,
  `parent_id` int(10) DEFAULT NULL,
  `status` enum('A','D') DEFAULT 'A',
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `app_menu`
--

INSERT INTO `app_menu` (`menu_id`, `menu_name`, `menu_icon`, `module_name`, `type_menu`, `seq_number`, `parent_id`, `status`, `created_by`, `created_date`, `modified_by`, `modified_date`) VALUES
(40, 'Control Panel', NULL, '#cpanel', '', 2, 0, 'A', NULL, NULL, NULL, NULL),
(41, 'Configuration', NULL, 'config', '', 1, 40, 'A', NULL, NULL, NULL, NULL),
(42, 'Menu', NULL, 'menu', '', 1, 40, 'A', NULL, NULL, NULL, NULL),
(43, 'Role', NULL, 'role', '', 3, 40, 'A', NULL, NULL, NULL, NULL),
(44, 'Role Menu', NULL, 'rolmen', '', 4, 40, 'A', NULL, NULL, NULL, NULL),
(45, 'Master', NULL, '#master', '', 3, 0, 'A', NULL, NULL, NULL, NULL),
(46, 'Department', NULL, 'department', '', 1, 45, 'A', NULL, NULL, NULL, NULL),
(47, 'Jabatan', NULL, 'jabatan', '', 2, 45, 'A', NULL, NULL, NULL, NULL),
(48, 'Employee', NULL, 'employee', '', 4, 45, 'A', NULL, NULL, NULL, NULL),
(49, 'Product', NULL, 'product', '', 5, 45, 'A', NULL, NULL, NULL, NULL),
(50, 'Vendor', NULL, 'vendor', '', 5, 45, 'A', NULL, NULL, NULL, NULL),
(51, 'Vendor Product', NULL, 'venpro', '', 3, 0, 'A', NULL, NULL, NULL, NULL),
(52, 'Procurement', NULL, 'procurement', '', 4, 0, 'A', NULL, NULL, NULL, NULL),
(53, 'Approval', NULL, '#approval', '', 0, 0, 'A', NULL, NULL, NULL, NULL),
(54, 'Head Dept', NULL, 'approval/head_dept', '', 2, 53, 'A', NULL, NULL, NULL, NULL),
(55, 'Head Proc', NULL, 'approval/head_prc', '', 4, 53, 'A', NULL, NULL, NULL, NULL),
(56, 'Receive', NULL, 'approval/receive', '', 5, 53, 'A', NULL, NULL, NULL, NULL),
(57, 'Pickup', NULL, 'approval/pickup', '', 10, 53, 'A', NULL, NULL, NULL, NULL),
(83, 'Dashboard', NULL, 'home', '', 1, 0, 'A', NULL, NULL, NULL, NULL),
(143, 'Receive Approve', NULL, 'approval/receive_approve', '', 5, 53, 'A', NULL, NULL, NULL, NULL),
(149, 'Add Request', NULL, 'add_request', '', 1, 53, 'A', NULL, NULL, NULL, NULL),
(151, 'Procurement', NULL, 'approval/prc_admin', '', 3, 53, 'A', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `app_role`
--

CREATE TABLE `app_role` (
  `role_id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `role_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `app_role`
--

INSERT INTO `app_role` (`role_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `role_name`) VALUES
(115, NULL, NULL, NULL, NULL, 'A', 'Super Admin'),
(116, NULL, NULL, NULL, NULL, 'A', 'Procurement'),
(118, NULL, NULL, NULL, NULL, 'A', 'Requester'),
(128, 'superadmin@gmail.com', '2019-06-04 15:22:15.705000', NULL, NULL, 'A', 'Kadep Requester'),
(129, 'superadmin@gmail.com', '2019-06-04 15:22:26.228000', NULL, NULL, 'A', 'Kadep Procurement');

-- --------------------------------------------------------

--
-- Table structure for table `app_role_menu`
--

CREATE TABLE `app_role_menu` (
  `role_menu_id` int(12) NOT NULL,
  `role_id` int(11) NOT NULL DEFAULT '0',
  `menu_id` int(12) NOT NULL DEFAULT '0',
  `status` enum('A','D') DEFAULT 'A',
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `app_role_menu`
--

INSERT INTO `app_role_menu` (`role_menu_id`, `role_id`, `menu_id`, `status`, `created_by`, `created_date`, `modified_by`, `modified_date`) VALUES
(59, 115, 40, 'A', NULL, NULL, 'request@gmail.com', '2019-06-04 05:07:02'),
(60, 115, 41, 'A', NULL, NULL, NULL, NULL),
(61, 115, 42, 'A', NULL, NULL, NULL, NULL),
(63, 115, 40, 'A', NULL, NULL, NULL, NULL),
(64, 115, 41, 'A', NULL, NULL, NULL, NULL),
(65, 115, 42, 'A', NULL, NULL, NULL, NULL),
(66, 115, 43, 'A', NULL, NULL, NULL, NULL),
(67, 115, 44, 'A', NULL, NULL, NULL, NULL),
(69, 115, 45, 'A', NULL, NULL, NULL, NULL),
(70, 115, 48, 'A', NULL, NULL, NULL, NULL),
(71, 115, 46, 'A', NULL, NULL, 'request@gmail.com', '2019-06-04 05:08:05'),
(72, 115, 47, 'A', NULL, NULL, NULL, NULL),
(73, 115, 49, 'A', NULL, NULL, NULL, NULL),
(74, 115, 50, 'A', NULL, NULL, NULL, NULL),
(75, 115, 52, 'A', NULL, NULL, NULL, NULL),
(76, 115, 51, 'A', NULL, NULL, NULL, NULL),
(122, 115, 54, 'A', 'superadmin@gmail.com', '2019-06-04 14:59:54', NULL, NULL),
(84, 116, 83, 'A', NULL, NULL, NULL, NULL),
(85, 117, 83, 'A', NULL, NULL, 'request@gmail.com', '2019-06-04 05:08:15'),
(86, 115, 83, 'A', NULL, NULL, NULL, NULL),
(123, 115, 55, 'A', 'superadmin@gmail.com', '2019-06-04 15:00:08', NULL, NULL),
(124, 115, 56, 'A', 'superadmin@gmail.com', '2019-06-04 15:00:18', NULL, NULL),
(125, 115, 57, 'A', 'superadmin@gmail.com', '2019-06-04 15:00:27', NULL, NULL),
(126, 115, 53, 'A', 'superadmin@gmail.com', '2019-06-04 15:01:15', NULL, NULL),
(144, 115, 143, 'A', 'superadmin@gmail.com', '2019-06-04 18:53:20', NULL, NULL),
(150, 115, 149, 'A', 'superadmin@gmail.com', '2019-06-06 21:39:54', NULL, NULL),
(152, 115, 151, 'A', 'superadmin@gmail.com', '2019-06-07 15:59:51', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `app_user`
--

CREATE TABLE `app_user` (
  `user_id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `external_id` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `app_user`
--

INSERT INTO `app_user` (`user_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `external_id`, `password`, `role_id`, `username`) VALUES
(90, 'zuliadin@gmail.com', '2019-06-04 03:32:14.115000', 'zuliadin@gmail.com', '2019-06-04 04:47:44.118000', 'A', '3671110208990001', '123456', 115, 'procurement@gmail.com'),
(92, 'zuliadin@gmail.com', '2019-06-04 03:37:52.170000', 'superadmin@gmail.com', '2019-06-04 14:59:03.884000', 'A', '3671110208990003', '123456', 117, 'supervisior@gmail.com'),
(93, 'zuliadin@gmail.com', '2019-06-04 03:38:17.522000', 'superadmin@gmail.com', '2019-06-08 07:14:01.457000', 'A', '3671110208990004', '123456', 115, 'superadmin@gmail.com'),
(102, 'zuliadin@gmail.com', '2019-06-04 04:39:29.600000', 'request@gmail.com', '2019-06-04 04:49:45.475000', 'A', '3671110208990009', '123456', 115, 'request@gmail.com'),
(130, 'superadmin@gmail.com', '2019-06-04 15:27:49.287000', NULL, NULL, 'A', '3671110208990020', '123456', 118, 'ndita@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `exm_productivity`
--

CREATE TABLE `exm_productivity` (
  `id` bigint(20) NOT NULL,
  `product` varchar(30) NOT NULL,
  `sale` double NOT NULL,
  `status` int(11) NOT NULL,
  `user` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(159),
(159),
(159),
(159),
(159),
(159),
(159),
(159),
(159),
(159);

-- --------------------------------------------------------

--
-- Table structure for table `prc_department`
--

CREATE TABLE `prc_department` (
  `department_id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `department_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `prc_department`
--

INSERT INTO `prc_department` (`department_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `department_name`) VALUES
(2, 'zuliadin@gmail.com', '2019-05-30 01:59:45.978000', 'zuliadin@gmail.com', '2019-06-02 16:51:46.915000', 'A', 'Keuangan'),
(81, 'zuliadin@gmail.com', '2019-06-02 16:51:37.996000', NULL, NULL, 'A', 'Procurement');

-- --------------------------------------------------------

--
-- Table structure for table `prc_jabatan`
--

CREATE TABLE `prc_jabatan` (
  `jabatan_id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `jabatan_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `prc_jabatan`
--

INSERT INTO `prc_jabatan` (`jabatan_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `jabatan_name`) VALUES
(3, 'zuliadin@gmail.com', '2019-05-30 01:59:52.693000', 'zuliadin@gmail.com', '2019-06-02 16:53:37.485000', 'A', 'Supervisior'),
(82, 'zuliadin@gmail.com', '2019-06-02 16:53:28.997000', 'request@gmail.com', '2019-06-04 04:52:58.533000', 'A', 'Dept Head');

-- --------------------------------------------------------

--
-- Table structure for table `prc_karyawan`
--

CREATE TABLE `prc_karyawan` (
  `nik` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `department_id` bigint(20) NOT NULL,
  `jabatan_id` bigint(20) NOT NULL,
  `nama` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `prc_karyawan`
--

INSERT INTO `prc_karyawan` (`nik`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `department_id`, `jabatan_id`, `nama`) VALUES
('3671110208990001', 'zuliadin@gmail.com', '2019-06-04 03:32:14.115000', 'zuliadin@gmail.com', '2019-06-04 04:47:44.086000', 'A', 2, 3, 'Procurement'),
('3671110208990003', 'zuliadin@gmail.com', '2019-06-04 03:37:52.170000', 'superadmin@gmail.com', '2019-06-04 14:59:03.838000', 'A', 2, 3, 'Supervisior'),
('3671110208990004', 'zuliadin@gmail.com', '2019-06-04 03:38:17.522000', 'superadmin@gmail.com', '2019-06-08 07:14:01.416000', 'A', 2, 3, 'Zuliadin'),
('3671110208990009', 'zuliadin@gmail.com', '2019-06-04 04:39:29.600000', 'request@gmail.com', '2019-06-04 04:49:45.462000', 'A', 2, 3, 'Requester'),
('3671110208990020', 'superadmin@gmail.com', '2019-06-04 15:27:49.287000', NULL, NULL, 'A', 2, 3, 'Nova tias nindita');

-- --------------------------------------------------------

--
-- Table structure for table `prc_product`
--

CREATE TABLE `prc_product` (
  `product_id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `product_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `prc_product`
--

INSERT INTO `prc_product` (`product_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `product_name`) VALUES
(5, NULL, NULL, 'request@gmail.com', '2019-06-04 04:55:10.047000', 'A', 'BUKU SEKOLAH'),
(9, NULL, NULL, 'request@gmail.com', '2019-06-04 04:55:14.540000', 'A', 'PULPEN');

-- --------------------------------------------------------

--
-- Table structure for table `prc_request`
--

CREATE TABLE `prc_request` (
  `request_id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `approve_kdep_date` datetime(6) DEFAULT NULL,
  `approve_pickup_date` datetime(6) DEFAULT NULL,
  `approve_svp_date` datetime(6) DEFAULT NULL,
  `harga` double NOT NULL,
  `invoice_doc` varchar(255) DEFAULT NULL,
  `invoice_id` varchar(20) DEFAULT NULL,
  `nik_kdep` varchar(255) DEFAULT NULL,
  `nik_pickup` varchar(255) DEFAULT NULL,
  `nik_receive` varchar(255) DEFAULT NULL,
  `nik_svp` varchar(255) DEFAULT NULL,
  `qty` bigint(20) NOT NULL,
  `receive_date` datetime(6) DEFAULT NULL,
  `status_proc` int(255) NOT NULL DEFAULT '0',
  `ven_req_doc` varchar(255) DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `apv_kdep_date` datetime(6) DEFAULT NULL,
  `apv_pickup_date` datetime(6) DEFAULT NULL,
  `apv_svp_date` datetime(6) DEFAULT NULL,
  `nik_req` varchar(255) DEFAULT NULL,
  `vendor_id` bigint(20) DEFAULT NULL,
  `request_date` datetime(6) DEFAULT NULL,
  `nik_kdep_proc` varchar(255) DEFAULT NULL,
  `nik_receive_approve` varchar(255) DEFAULT NULL,
  `apv_kdep_proc_date` datetime(6) DEFAULT NULL,
  `receive_approve_date` datetime(6) DEFAULT NULL,
  `apv_receive_date` datetime(6) DEFAULT NULL,
  `nik_proc` varchar(255) DEFAULT NULL,
  `apv_proc_date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `prc_request`
--

INSERT INTO `prc_request` (`request_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `approve_kdep_date`, `approve_pickup_date`, `approve_svp_date`, `harga`, `invoice_doc`, `invoice_id`, `nik_kdep`, `nik_pickup`, `nik_receive`, `nik_svp`, `qty`, `receive_date`, `status_proc`, `ven_req_doc`, `due_date`, `product_id`, `apv_kdep_date`, `apv_pickup_date`, `apv_svp_date`, `nik_req`, `vendor_id`, `request_date`, `nik_kdep_proc`, `nik_receive_approve`, `apv_kdep_proc_date`, `receive_approve_date`, `apv_receive_date`, `nik_proc`, `apv_proc_date`) VALUES
(156, 'superadmin@gmail.com', '2019-06-08 04:43:34.536000', 'superadmin@gmail.com', '2019-06-08 05:13:54.660000', 'A', NULL, NULL, NULL, 21600, NULL, NULL, '3671110208990004', '3671110208990004', '3671110208990004', NULL, 9, '2019-06-08 05:13:43.171000', 7, NULL, NULL, 5, '2019-06-08 05:02:13.438000', '2019-06-08 05:13:54.660000', NULL, '3671110208990004', 10, '2019-06-08 04:43:34.535000', '3671110208990004', '3671110208990004', '2019-06-08 05:13:37.979000', NULL, '2019-06-08 05:13:48.514000', '3671110208990004', '2019-06-08 05:13:22.231000'),
(157, 'superadmin@gmail.com', '2019-06-08 05:23:23.039000', NULL, NULL, 'A', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 1, NULL, '2019-06-01', 5, NULL, NULL, NULL, '3671110208990004', NULL, '2019-06-08 05:23:23.039000', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(158, 'superadmin@gmail.com', '2019-06-08 07:11:26.368000', NULL, NULL, 'A', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 90, NULL, 1, NULL, NULL, 5, NULL, NULL, NULL, '3671110208990004', NULL, '2019-06-08 07:11:26.368000', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `prc_vendor`
--

CREATE TABLE `prc_vendor` (
  `vendor_id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `pic_telp` varchar(255) DEFAULT NULL,
  `telp` varchar(255) DEFAULT NULL,
  `vendor_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `prc_vendor`
--

INSERT INTO `prc_vendor` (`vendor_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `alamat`, `email`, `fax`, `pic`, `pic_telp`, `telp`, `vendor_name`) VALUES
(6, NULL, NULL, 'request@gmail.com', '2019-06-04 04:56:39.097000', 'A', 'Jakarta', 'indobook@gmail.com', '081284190271', 'Zuliadin', '081284190270', '081284190270', 'Indonesia Book'),
(10, NULL, NULL, NULL, NULL, 'A', 'Jakarta Utara, Mangga 2', 'mos@gmail.com', '0217777445', 'Moch Mos', '0217777444', '0217777444', 'Mitra Office Supplies'),
(121, 'superadmin@gmail.com', '2019-06-04 14:39:41.288000', NULL, NULL, 'A', '', '', '', '', '', '', 'Vendor ABC');

-- --------------------------------------------------------

--
-- Table structure for table `prc_vendor_product`
--

CREATE TABLE `prc_vendor_product` (
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `harga` double NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `vendor_id` bigint(20) NOT NULL,
  `vendor_product_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `prc_vendor_product`
--

INSERT INTO `prc_vendor_product` (`created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `harga`, `product_id`, `vendor_id`, `vendor_product_id`) VALUES
('zuliadin@gmail.com', '2019-06-01 23:03:46.003000', NULL, NULL, 'A', 2000, 5, 6, 33),
('zuliadin@gmail.com', '2019-06-01 23:03:54.102000', NULL, NULL, 'A', 1000, 9, 6, 34),
('zuliadin@gmail.com', '2019-06-01 23:04:04.203000', 'zuliadin@gmail.com', '2019-06-01 23:47:44.489000', 'A', 2400, 5, 10, 35),
('zuliadin@gmail.com', '2019-06-01 23:04:15.994000', 'zuliadin@gmail.com', '2019-06-01 23:47:36.125000', 'A', 900, 9, 10, 36);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `app_config`
--
ALTER TABLE `app_config`
  ADD PRIMARY KEY (`configuration_id`);

--
-- Indexes for table `app_constant`
--
ALTER TABLE `app_constant`
  ADD PRIMARY KEY (`app_variable_id`) USING BTREE;

--
-- Indexes for table `app_menu`
--
ALTER TABLE `app_menu`
  ADD PRIMARY KEY (`menu_id`) USING BTREE;

--
-- Indexes for table `app_role`
--
ALTER TABLE `app_role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `app_role_menu`
--
ALTER TABLE `app_role_menu`
  ADD PRIMARY KEY (`role_menu_id`) USING BTREE,
  ADD KEY `FK3je5sxn5tvcqoc30gg633aaps` (`menu_id`),
  ADD KEY `FKakrwoey9djdodkn4v17237q0p` (`role_id`);

--
-- Indexes for table `app_user`
--
ALTER TABLE `app_user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UK_apf0n2yl6t4hh7wvweo8m3j6` (`external_id`);

--
-- Indexes for table `exm_productivity`
--
ALTER TABLE `exm_productivity`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `prc_department`
--
ALTER TABLE `prc_department`
  ADD PRIMARY KEY (`department_id`);

--
-- Indexes for table `prc_jabatan`
--
ALTER TABLE `prc_jabatan`
  ADD PRIMARY KEY (`jabatan_id`);

--
-- Indexes for table `prc_karyawan`
--
ALTER TABLE `prc_karyawan`
  ADD PRIMARY KEY (`nik`),
  ADD KEY `FK6bb7dwyync9dmpq8agwmee0sa` (`department_id`),
  ADD KEY `FKk6h30k5awnptkbv8235ey4sme` (`jabatan_id`);

--
-- Indexes for table `prc_product`
--
ALTER TABLE `prc_product`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `prc_request`
--
ALTER TABLE `prc_request`
  ADD PRIMARY KEY (`request_id`),
  ADD KEY `FKhpfumirhhd7dlbjedry5i4svh` (`vendor_id`),
  ADD KEY `FK6lehy8jg8r9c8vjgegw25gw2p` (`product_id`);

--
-- Indexes for table `prc_vendor`
--
ALTER TABLE `prc_vendor`
  ADD PRIMARY KEY (`vendor_id`);

--
-- Indexes for table `prc_vendor_product`
--
ALTER TABLE `prc_vendor_product`
  ADD PRIMARY KEY (`vendor_product_id`),
  ADD KEY `FKn5iqpwgpgiomsfxlhj0wcqjc` (`product_id`),
  ADD KEY `FKr4guswma43m03px2j3y17n48c` (`vendor_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `app_constant`
--
ALTER TABLE `app_constant`
  MODIFY `app_variable_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `app_menu`
--
ALTER TABLE `app_menu`
  MODIFY `menu_id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=152;

--
-- AUTO_INCREMENT for table `app_role_menu`
--
ALTER TABLE `app_role_menu`
  MODIFY `role_menu_id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=249;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `prc_karyawan`
--
ALTER TABLE `prc_karyawan`
  ADD CONSTRAINT `FK6bb7dwyync9dmpq8agwmee0sa` FOREIGN KEY (`department_id`) REFERENCES `prc_department` (`department_id`),
  ADD CONSTRAINT `FK7x3r8u4giqovpukdbd2wpy03i` FOREIGN KEY (`nik`) REFERENCES `app_user` (`external_id`),
  ADD CONSTRAINT `FKk6h30k5awnptkbv8235ey4sme` FOREIGN KEY (`jabatan_id`) REFERENCES `prc_jabatan` (`jabatan_id`);

--
-- Constraints for table `prc_request`
--
ALTER TABLE `prc_request`
  ADD CONSTRAINT `FK6lehy8jg8r9c8vjgegw25gw2p` FOREIGN KEY (`product_id`) REFERENCES `prc_product` (`product_id`);

--
-- Constraints for table `prc_vendor_product`
--
ALTER TABLE `prc_vendor_product`
  ADD CONSTRAINT `FKn5iqpwgpgiomsfxlhj0wcqjc` FOREIGN KEY (`product_id`) REFERENCES `prc_product` (`product_id`),
  ADD CONSTRAINT `FKr4guswma43m03px2j3y17n48c` FOREIGN KEY (`vendor_id`) REFERENCES `prc_vendor` (`vendor_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
