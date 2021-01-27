-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 04, 2018 at 04:30 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bukawarung`
--

-- --------------------------------------------------------

--
-- Table structure for table `id_suplier`
--

CREATE TABLE `id_suplier` (
  `id_suplier` int(10) NOT NULL,
  `nama_suplier` varchar(40) NOT NULL,
  `nama_manajer` varchar(40) NOT NULL,
  `no_telp` int(13) NOT NULL,
  `alamat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_barang`
--

CREATE TABLE `tb_barang` (
  `id_barang` int(10) NOT NULL,
  `id_kategori` int(10) NOT NULL,
  `nama_barang` varchar(20) NOT NULL,
  `harga_beli` double NOT NULL,
  `harga_jual` double NOT NULL,
  `stok` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_detail_pembelian`
--

CREATE TABLE `tb_detail_pembelian` (
  `no_trans_pembelian` int(11) NOT NULL,
  `no_faktur_pembelian` int(14) NOT NULL,
  `id_barang` int(13) NOT NULL,
  `harga_beli` double NOT NULL,
  `jumlah_barang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_detail_penjualan`
--

CREATE TABLE `tb_detail_penjualan` (
  `no_trans_penjualan` int(11) NOT NULL,
  `no_faktur_penjualan` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `harga_beli` double NOT NULL,
  `harga_jual` double NOT NULL,
  `diskon` float NOT NULL,
  `jumlah_barang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_detail_retur_pembelian`
--

CREATE TABLE `tb_detail_retur_pembelian` (
  `no_trans_retur_pembelian` int(11) NOT NULL,
  `id_barang` int(13) NOT NULL,
  `no_retur_pembelian` int(14) NOT NULL,
  `jumlah_barang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_detail_retur_penjualan`
--

CREATE TABLE `tb_detail_retur_penjualan` (
  `no_trans_retur_penjualan` int(11) NOT NULL,
  `no_retur_penjualan` int(14) NOT NULL,
  `id_barang` int(13) NOT NULL,
  `jumlah_barang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_kategori`
--

CREATE TABLE `tb_kategori` (
  `id_kategori` int(11) NOT NULL,
  `nama_kategori` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_member`
--

CREATE TABLE `tb_member` (
  `id_member` int(11) NOT NULL,
  `nama_member` varchar(10) NOT NULL,
  `jenis_kelamin` enum('L','P') NOT NULL,
  `tgl_daftar` date NOT NULL,
  `no_telp` int(13) NOT NULL,
  `alamat` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_pembelian`
--

CREATE TABLE `tb_pembelian` (
  `no_faktur_pembelian` int(14) NOT NULL,
  `id_suplier` int(10) NOT NULL,
  `id_user` int(10) NOT NULL,
  `no_invoive` int(20) NOT NULL,
  `tgl_transaksi` date NOT NULL,
  `total` double NOT NULL,
  `diskon` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_penjualan`
--

CREATE TABLE `tb_penjualan` (
  `no_faktur_penjualan` int(15) NOT NULL,
  `id_user` int(5) NOT NULL,
  `id_member` int(10) NOT NULL,
  `tgl_transaksi` date NOT NULL,
  `total` double NOT NULL,
  `diskon` double NOT NULL,
  `jumlah_barang` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_retur_pembelian`
--

CREATE TABLE `tb_retur_pembelian` (
  `no_retur_pembelian` int(14) NOT NULL,
  `id_user` int(10) NOT NULL,
  `no_faktur_pembelian` int(20) NOT NULL,
  `tgl_retur` date NOT NULL,
  `total` double NOT NULL,
  `status_retur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_retur_penjualan`
--

CREATE TABLE `tb_retur_penjualan` (
  `no_retur_penjualan` int(20) NOT NULL,
  `id_user` int(10) NOT NULL,
  `no_faktur_penjualan` int(20) NOT NULL,
  `tgl_retur` date NOT NULL,
  `total` double NOT NULL,
  `status_retur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `id_user` int(10) NOT NULL,
  `nama_user` varchar(30) NOT NULL,
  `jenis_kelamin` varchar(1) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `no_telpon` int(13) NOT NULL,
  `password` varchar(30) NOT NULL,
  `level_user` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `id_suplier`
--
ALTER TABLE `id_suplier`
  ADD PRIMARY KEY (`id_suplier`);

--
-- Indexes for table `tb_barang`
--
ALTER TABLE `tb_barang`
  ADD PRIMARY KEY (`id_barang`,`id_kategori`);

--
-- Indexes for table `tb_detail_pembelian`
--
ALTER TABLE `tb_detail_pembelian`
  ADD PRIMARY KEY (`no_trans_pembelian`,`no_faktur_pembelian`,`id_barang`);

--
-- Indexes for table `tb_detail_penjualan`
--
ALTER TABLE `tb_detail_penjualan`
  ADD PRIMARY KEY (`no_trans_penjualan`,`no_faktur_penjualan`,`id_barang`);

--
-- Indexes for table `tb_detail_retur_pembelian`
--
ALTER TABLE `tb_detail_retur_pembelian`
  ADD PRIMARY KEY (`no_trans_retur_pembelian`,`id_barang`,`no_retur_pembelian`);

--
-- Indexes for table `tb_detail_retur_penjualan`
--
ALTER TABLE `tb_detail_retur_penjualan`
  ADD PRIMARY KEY (`no_trans_retur_penjualan`,`no_retur_penjualan`,`id_barang`);

--
-- Indexes for table `tb_kategori`
--
ALTER TABLE `tb_kategori`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indexes for table `tb_member`
--
ALTER TABLE `tb_member`
  ADD PRIMARY KEY (`id_member`);

--
-- Indexes for table `tb_pembelian`
--
ALTER TABLE `tb_pembelian`
  ADD PRIMARY KEY (`no_faktur_pembelian`,`id_suplier`,`id_user`);

--
-- Indexes for table `tb_penjualan`
--
ALTER TABLE `tb_penjualan`
  ADD PRIMARY KEY (`no_faktur_penjualan`,`id_user`,`id_member`);

--
-- Indexes for table `tb_retur_pembelian`
--
ALTER TABLE `tb_retur_pembelian`
  ADD PRIMARY KEY (`no_retur_pembelian`,`id_user`,`no_faktur_pembelian`);

--
-- Indexes for table `tb_retur_penjualan`
--
ALTER TABLE `tb_retur_penjualan`
  ADD PRIMARY KEY (`no_retur_penjualan`,`id_user`,`no_faktur_penjualan`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id_user`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
