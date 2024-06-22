-- --------------------------------------------------------
-- Máy chủ:                      127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Phiên bản:           12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for db_dulichviet
CREATE DATABASE IF NOT EXISTS `db_dulichviet` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_dulichviet`;

-- Dumping structure for table db_dulichviet.chi_tiet_lich_trinh
CREATE TABLE IF NOT EXISTS `chi_tiet_lich_trinh` (
  `ngaykh` datetime(6) NOT NULL,
  `ma_tour` varchar(10) NOT NULL,
  `mahdv` varchar(10) NOT NULL,
  PRIMARY KEY (`mahdv`,`ma_tour`,`ngaykh`),
  KEY `FK2hykf64diom7p9522vlqm3rnc` (`ngaykh`),
  KEY `FK8gvjef2ktxfg6c6rq0hynrw8l` (`ma_tour`),
  CONSTRAINT `FK2hykf64diom7p9522vlqm3rnc` FOREIGN KEY (`ngaykh`) REFERENCES `lich_trinh` (`ngaykh`),
  CONSTRAINT `FK8gvjef2ktxfg6c6rq0hynrw8l` FOREIGN KEY (`ma_tour`) REFERENCES `tour` (`ma_tour`),
  CONSTRAINT `FKc4gptjbyrei8ca7sgotn4hohd` FOREIGN KEY (`mahdv`) REFERENCES `huong_dan_vien` (`mahdv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.chi_tiet_lich_trinh: ~0 rows (approximately)

-- Dumping structure for table db_dulichviet.chi_tiet_tien_nghi
CREATE TABLE IF NOT EXISTS `chi_tiet_tien_nghi` (
  `ma_tien_nghi` varchar(10) NOT NULL,
  `ma_phong` varchar(10) NOT NULL,
  PRIMARY KEY (`ma_phong`,`ma_tien_nghi`),
  KEY `FKqsv5nl7a6f3rsfxpgqykj2q2j` (`ma_tien_nghi`),
  CONSTRAINT `FKjqrg904e5298ve60e2niiruxf` FOREIGN KEY (`ma_phong`) REFERENCES `phong` (`ma_phong`),
  CONSTRAINT `FKqsv5nl7a6f3rsfxpgqykj2q2j` FOREIGN KEY (`ma_tien_nghi`) REFERENCES `tien_nghi` (`ma_tien_nghi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.chi_tiet_tien_nghi: ~4 rows (approximately)
INSERT INTO `chi_tiet_tien_nghi` (`ma_tien_nghi`, `ma_phong`) VALUES
	('TN05', 'P01'),
	('TN11', 'P01'),
	('TN14', 'P01'),
	('TN23', 'P01');

-- Dumping structure for table db_dulichviet.danh_gia
CREATE TABLE IF NOT EXISTS `danh_gia` (
  `madg` varchar(10) NOT NULL,
  `danh_gia` varchar(200) NOT NULL,
  `hinh_anh` varchar(250) NOT NULL,
  `id` int NOT NULL,
  PRIMARY KEY (`madg`),
  KEY `FK1l3tlaiwcjyouikar7furvuef` (`id`),
  CONSTRAINT `FK1l3tlaiwcjyouikar7furvuef` FOREIGN KEY (`id`) REFERENCES `tai_khoan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.danh_gia: ~0 rows (approximately)

-- Dumping structure for table db_dulichviet.dich_vu
CREATE TABLE IF NOT EXISTS `dich_vu` (
  `madv` varchar(10) NOT NULL,
  `giadv` decimal(38,2) NOT NULL,
  `tendv` varchar(100) NOT NULL,
  PRIMARY KEY (`madv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.dich_vu: ~0 rows (approximately)

-- Dumping structure for table db_dulichviet.hinh_anh
CREATE TABLE IF NOT EXISTS `hinh_anh` (
  `id` int NOT NULL AUTO_INCREMENT,
  `img` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.hinh_anh: ~12 rows (approximately)
INSERT INTO `hinh_anh` (`id`, `img`) VALUES
	(1, '/images/PhuQuoc1.jpg'),
	(2, '/images/PhuQuoc2.jpg'),
	(3, '/images/PhuQuoc3.jpg'),
	(4, '/images/PhuQuoc4.jpg'),
	(5, '/images/PhuQuoc5.jpg'),
	(6, '/images/PhuQuoc6.jpg'),
	(7, '/images/DaNang1.jpg'),
	(8, '/images/PhanThiet1.jpg'),
	(9, '/images/PhuYen1.jpg'),
	(10, '/images/PhuYen2.jpg'),
	(11, '/images/PhanThiet2.jpg'),
	(12, '/images/DaNang2.jpg');

-- Dumping structure for table db_dulichviet.hinh_anh_phong
CREATE TABLE IF NOT EXISTS `hinh_anh_phong` (
  `ma_phong` varchar(10) NOT NULL,
  `id` int NOT NULL,
  PRIMARY KEY (`id`,`ma_phong`),
  KEY `FKoc1u6e4v3fhy0b1emuwkedggr` (`ma_phong`),
  CONSTRAINT `FKjkdp4kdyjiqqvglxk1qn4jo2b` FOREIGN KEY (`id`) REFERENCES `hinh_anh` (`id`),
  CONSTRAINT `FKoc1u6e4v3fhy0b1emuwkedggr` FOREIGN KEY (`ma_phong`) REFERENCES `phong` (`ma_phong`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.hinh_anh_phong: ~0 rows (approximately)

-- Dumping structure for table db_dulichviet.hoa_don
CREATE TABLE IF NOT EXISTS `hoa_don` (
  `mahd` varchar(10) NOT NULL,
  `id` int NOT NULL,
  `ma_tour` varchar(10) NOT NULL,
  `ngaylap` datetime(6) NOT NULL,
  `tong_tien` decimal(38,2) NOT NULL,
  PRIMARY KEY (`mahd`),
  KEY `FKoc4j6h78n325ylvvdlx0enns9` (`id`),
  KEY `FKsoigwbsw1m2ge57fx5y3rlvu7` (`ma_tour`),
  CONSTRAINT `FKoc4j6h78n325ylvvdlx0enns9` FOREIGN KEY (`id`) REFERENCES `tai_khoan` (`id`),
  CONSTRAINT `FKsoigwbsw1m2ge57fx5y3rlvu7` FOREIGN KEY (`ma_tour`) REFERENCES `tour` (`ma_tour`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.hoa_don: ~0 rows (approximately)

-- Dumping structure for table db_dulichviet.huong_dan_vien
CREATE TABLE IF NOT EXISTS `huong_dan_vien` (
  `mahdv` varchar(255) NOT NULL,
  `tenhdv` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mahdv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.huong_dan_vien: ~0 rows (approximately)

-- Dumping structure for table db_dulichviet.images
CREATE TABLE IF NOT EXISTS `images` (
  `ma_tour` varchar(10) NOT NULL,
  `id` int NOT NULL,
  PRIMARY KEY (`id`,`ma_tour`),
  KEY `FKkslp3uba1y56cxssuh34vi8eg` (`ma_tour`),
  CONSTRAINT `FK7o7yb7h87mky7053665l9ut00` FOREIGN KEY (`id`) REFERENCES `hinh_anh` (`id`),
  CONSTRAINT `FKkslp3uba1y56cxssuh34vi8eg` FOREIGN KEY (`ma_tour`) REFERENCES `tour` (`ma_tour`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.images: ~12 rows (approximately)
INSERT INTO `images` (`ma_tour`, `id`) VALUES
	('Tour01', 1),
	('Tour01', 2),
	('Tour01', 3),
	('Tour01', 4),
	('Tour01', 5),
	('Tour01', 6),
	('Tour02', 7),
	('Tour02', 12),
	('Tour03', 8),
	('Tour03', 11),
	('Tour04', 9),
	('Tour04', 10);

-- Dumping structure for table db_dulichviet.khach_san
CREATE TABLE IF NOT EXISTS `khach_san` (
  `maks` varchar(255) NOT NULL,
  `dia_chi` varchar(255) DEFAULT NULL,
  `tenks` varchar(255) DEFAULT NULL,
  `madg` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`maks`),
  KEY `FK6ljew9835u1mk6vl32ub6m9sk` (`madg`),
  CONSTRAINT `FK6ljew9835u1mk6vl32ub6m9sk` FOREIGN KEY (`madg`) REFERENCES `danh_gia` (`madg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.khach_san: ~23 rows (approximately)
INSERT INTO `khach_san` (`maks`, `dia_chi`, `tenks`, `madg`) VALUES
	('KS01', 'Hội An, Việt Nam', 'La Siesta Hoi An Resort & Spa', NULL),
	('KS02', 'Quận Hoàn Kiếm, Hà Nội', 'Hanoi Traveller House', NULL),
	('KS03', 'TP. Hồ Chí Minh, Việt Nam', 'La Siesta Premium Saigon', NULL),
	('KS04', 'Đà Nẵng, Việt Nam', 'Furama Resort Danang', NULL),
	('KS05', 'Nha Trang, Việt Nam', 'InterContinental Nha Trang', NULL),
	('KS06', 'Huế, Việt Nam', 'Pilgrimage Village Boutique Resort & Spa', NULL),
	('KS07', 'Phú Quốc, Việt Nam', 'Salinda Resort Phu Quoc Island', NULL),
	('KS08', 'Hội An, Việt Nam', 'Anantara Hoi An Resort', NULL),
	('KS09', 'Sapa, Việt Nam', 'Topas Ecolodge', NULL),
	('KS10', 'Hạ Long, Việt Nam', 'Vinpearl Resort & Spa Ha Long', NULL),
	('KS11', 'Cần Thơ, Việt Nam', 'Victoria Can Tho Resort', NULL),
	('KS12', 'Hà Nội, Việt Nam', 'Sofitel Legend Metropole Hanoi', NULL),
	('KS13', 'TP. Hồ Chí Minh, Việt Nam', 'Park Hyatt Saigon', NULL),
	('KS14', 'Đà Lạt, Việt Nam', 'Ana Mandara Villas Dalat Resort & Spa', NULL),
	('KS15', 'Phan Thiết, Việt Nam', 'Mia Resort Mui Ne', NULL),
	('KS16', 'Côn Đảo, Việt Nam', 'Six Senses Con Dao', NULL),
	('KS17', 'Ninh Bình, Việt Nam', 'Emeralda Resort Ninh Binh', NULL),
	('KS18', 'Quy Nhơn, Việt Nam', 'AVANI Quy Nhon Resort & Spa', NULL),
	('KS19', 'Phú Quốc, Việt Nam', 'JW Marriott Phu Quoc Emerald Bay Resort & Spa', NULL),
	('KS20', 'Vũng Tàu, Việt Nam', 'The Grand Ho Tram Strip', NULL),
	('KS21', 'Buôn Ma Thuột, Việt Nam', 'Muong Thanh Luxury Buon Ma Thuot Hotel', NULL),
	('KS22', 'Hải Phòng, Việt Nam', 'Pearl River Hotel Hai Phong', NULL),
	('KS23', 'Hà Nội, Việt Nam', 'Lotte Hotel Hanoi', NULL);

-- Dumping structure for table db_dulichviet.khuyen_mai
CREATE TABLE IF NOT EXISTS `khuyen_mai` (
  `makm` varchar(10) NOT NULL,
  `phan_tramkm` int DEFAULT NULL,
  `tenkm` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`makm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.khuyen_mai: ~0 rows (approximately)

-- Dumping structure for table db_dulichviet.lich_trinh
CREATE TABLE IF NOT EXISTS `lich_trinh` (
  `ngaykh` datetime(6) NOT NULL,
  PRIMARY KEY (`ngaykh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.lich_trinh: ~0 rows (approximately)

-- Dumping structure for table db_dulichviet.lien_he
CREATE TABLE IF NOT EXISTS `lien_he` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dia_chi` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `sdt` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.lien_he: ~0 rows (approximately)

-- Dumping structure for table db_dulichviet.loai_phong
CREATE TABLE IF NOT EXISTS `loai_phong` (
  `ma_loai` varchar(255) NOT NULL,
  `ten_loai` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ma_loai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.loai_phong: ~4 rows (approximately)
INSERT INTO `loai_phong` (`ma_loai`, `ten_loai`) VALUES
	('LP01', 'Phòng Standard (STD)'),
	('LP02', 'Phòng Superior(SUP)'),
	('LP03', 'Phòng Deluxe (DLX)'),
	('LP04', 'Phòng Suite(SUT)');

-- Dumping structure for table db_dulichviet.loai_tour
CREATE TABLE IF NOT EXISTS `loai_tour` (
  `ma_loai_tour` varchar(10) NOT NULL,
  `loai_tour` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ma_loai_tour`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.loai_tour: ~2 rows (approximately)
INSERT INTO `loai_tour` (`ma_loai_tour`, `loai_tour`) VALUES
	('LT01', 'Tiết kiệm'),
	('LT02', 'Tiêu chuẩn'),
	('LT03', 'Cao cấp');

-- Dumping structure for table db_dulichviet.nhan_vien
CREATE TABLE IF NOT EXISTS `nhan_vien` (
  `manv` bigint NOT NULL AUTO_INCREMENT,
  `dia_chi` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `id` int NOT NULL,
  `sdt` varchar(255) DEFAULT NULL,
  `ten_nhan_vien` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`manv`),
  KEY `FKm21x62x5p8cyauywn7yuv4oxi` (`id`),
  CONSTRAINT `FKm21x62x5p8cyauywn7yuv4oxi` FOREIGN KEY (`id`) REFERENCES `tai_khoan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.nhan_vien: ~0 rows (approximately)

-- Dumping structure for table db_dulichviet.phong
CREATE TABLE IF NOT EXISTS `phong` (
  `ma_phong` varchar(255) NOT NULL,
  `gia` int DEFAULT NULL,
  `mo_ta` varchar(255) DEFAULT NULL,
  `maks` varchar(255) DEFAULT NULL,
  `ma_loai` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ma_phong`),
  KEY `FKqlnmn8rts146yhmiiv1p3b0ww` (`maks`),
  KEY `FKmi6hxy9sqjexyb48f6r4cdux9` (`ma_loai`),
  CONSTRAINT `FKmi6hxy9sqjexyb48f6r4cdux9` FOREIGN KEY (`ma_loai`) REFERENCES `loai_phong` (`ma_loai`),
  CONSTRAINT `FKqlnmn8rts146yhmiiv1p3b0ww` FOREIGN KEY (`maks`) REFERENCES `khach_san` (`maks`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.phong: ~1 rows (approximately)
INSERT INTO `phong` (`ma_phong`, `gia`, `mo_ta`, `maks`, `ma_loai`) VALUES
	('P01', 1918000, '2 người - Sức chứa tối đa của phòng 2 - Số khách tiêu chuẩn 2 - Cho phép ở thêm người lớn 2 trẻ em thỏa mãn 2 khách tối đa có thể mất thêm phí - Chi tiết phí phụ thu vui lòng xem tại “Giá cuối cùng”', 'KS04', 'LP02');

-- Dumping structure for table db_dulichviet.phuong_tien
CREATE TABLE IF NOT EXISTS `phuong_tien` (
  `ma_phuong_tien` varchar(10) NOT NULL,
  `ten_phuong_tien` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ma_phuong_tien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.phuong_tien: ~2 rows (approximately)
INSERT INTO `phuong_tien` (`ma_phuong_tien`, `ten_phuong_tien`) VALUES
	('PT01', 'Máy bay'),
	('PT02', 'Ô tô');

-- Dumping structure for table db_dulichviet.tai_khoan
CREATE TABLE IF NOT EXISTS `tai_khoan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pass_word` varchar(255) NOT NULL,
  `tentk` varchar(255) NOT NULL,
  `id_user` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6gudvg8gq4g95faih9ol99aj4` (`tentk`),
  KEY `FKg9gtme1qx7benvoa9lrhef0ot` (`id_user`),
  CONSTRAINT `FKg9gtme1qx7benvoa9lrhef0ot` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.tai_khoan: ~1 rows (approximately)
INSERT INTO `tai_khoan` (`id`, `pass_word`, `tentk`, `id_user`) VALUES
	(1, '123', 'Hai', 1);

-- Dumping structure for table db_dulichviet.thanh_pho
CREATE TABLE IF NOT EXISTS `thanh_pho` (
  `matp` varchar(10) NOT NULL,
  `tentp` varchar(50) DEFAULT NULL,
  `ma_tinh` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`matp`),
  KEY `FK96m29fh0yb4mp0pvsgpwr1l43` (`ma_tinh`),
  CONSTRAINT `FK96m29fh0yb4mp0pvsgpwr1l43` FOREIGN KEY (`ma_tinh`) REFERENCES `tinh` (`ma_tinh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.thanh_pho: ~85 rows (approximately)
INSERT INTO `thanh_pho` (`matp`, `tentp`, `ma_tinh`) VALUES
	('TP01', 'Hà Nội', 'T01'),
	('TP02', 'Hồ Chí Minh', 'T02'),
	('TP03', 'Đà Nẵng', 'T03'),
	('TP04', 'Hải Phòng', 'T04'),
	('TP05', 'Cần Thơ', 'T05'),
	('TP06', 'Đà Lạt', 'T06'),
	('TP07', 'Nha Trang', 'T07'),
	('TP08', 'Vũng Tàu', 'T08'),
	('TP09', 'Huế', 'T09'),
	('TP10', 'Quy Nhơn', 'T10'),
	('TP11', 'Buôn Ma Thuột', 'T11'),
	('TP12', 'Pleiku', 'T12'),
	('TP13', 'Phan Thiết', 'T13'),
	('TP14', 'Rạch Giá', 'T14'),
	('TP15', 'Long Xuyên', 'T15'),
	('TP16', 'Cà Mau', 'T16'),
	('TP17', 'Vị Thanh', 'T17'),
	('TP18', 'Sóc Trăng', 'T18'),
	('TP19', 'Bạc Liêu', 'T19'),
	('TP20', 'Sa Đéc', 'T20'),
	('TP21', 'Trà Vinh', 'T21'),
	('TP22', 'Vĩnh Long', 'T22'),
	('TP23', 'Cao Lãnh', 'T23'),
	('TP24', 'Tây Ninh', 'T24'),
	('TP25', 'Bến Tre', 'T25'),
	('TP26', 'Tân An', 'T26'),
	('TP27', 'Mỹ Tho', 'T27'),
	('TP28', 'Thủ Dầu Một', 'T28'),
	('TP29', 'Biên Hòa', 'T29'),
	('TP30', 'Bà Rịa', 'T30'),
	('TP31', 'Long Thành', 'T29'),
	('TP32', 'Bảo Lộc', 'T06'),
	('TP33', 'Tuy Hòa', 'T33'),
	('TP34', 'Tam Kỳ', 'T34'),
	('TP35', 'Quảng Ngãi', 'T35'),
	('TP36', 'Kon Tum', 'T36'),
	('TP37', 'Quảng Trị', 'T37'),
	('TP38', 'Đông Hà', 'T38'),
	('TP39', 'Phan Rang-Tháp Chàm', 'T39'),
	('TP40', 'Đồng Hới', 'T40'),
	('TP41', 'Thanh Hóa', 'T41'),
	('TP42', 'Vinh', 'T42'),
	('TP43', 'Hà Tĩnh', 'T43'),
	('TP44', 'Nam Định', 'T44'),
	('TP45', 'Thái Bình', 'T45'),
	('TP46', 'Hưng Yên', 'T46'),
	('TP47', 'Hạ Long', 'T47'),
	('TP48', 'Móng Cái', 'T48'),
	('TP49', 'Bắc Giang', 'T49'),
	('TP50', 'Lạng Sơn', 'T50'),
	('TP51', 'Cao Bằng', 'T51'),
	('TP52', 'Hà Giang', 'T52'),
	('TP53', 'Tuyên Quang', 'T53'),
	('TP54', 'Yên Bái', 'T54'),
	('TP55', 'Lào Cai', 'T55'),
	('TP56', 'Điện Biên Phủ', 'T56'),
	('TP57', 'Lai Châu', 'T57'),
	('TP58', 'Sơn La', 'T58'),
	('TP59', 'Hòa Bình', 'T59'),
	('TP60', 'Bắc Ninh', 'T60'),
	('TP61', 'Phủ Lý', 'T61'),
	('TP62', 'Hải Dương', 'T62'),
	('TP63', 'Thái Nguyên', 'T63'),
	('TP64', 'Vĩnh Yên', 'T22'),
	('TP65', 'Bắc Kạn', 'T49'),
	('TP66', 'Hà Đông', 'T01'),
	('TP67', 'Sơn Tây', 'T01'),
	('TP68', 'Tam Điệp', 'T44'),
	('TP69', 'Bỉm Sơn', 'T41'),
	('TP70', 'Sầm Sơn', 'T41'),
	('TP71', 'Ninh Bình', 'T44'),
	('TP72', 'Thái Bình', 'T45'),
	('TP73', 'Mỹ Lộc', 'T44'),
	('TP74', 'Phú Lý', 'T61'),
	('TP75', 'Chí Linh', 'T62'),
	('TP76', 'Đông Triều', 'T47'),
	('TP77', 'Quảng Yên', 'T47'),
	('TP78', 'Cẩm Phả', 'T47'),
	('TP79', 'Uông Bí', 'T47'),
	('TP80', 'Mạo Khê', 'T47'),
	('TP81', 'Hà Tiên', 'T14'),
	('TP82', 'Châu Đốc', 'T15'),
	('TP83', 'Tịnh Biên', 'T15'),
	('TP84', 'Tri Tôn', 'T15'),
	('TP85', 'Vĩnh Châu', 'T19');

-- Dumping structure for table db_dulichviet.tien_nghi
CREATE TABLE IF NOT EXISTS `tien_nghi` (
  `ma_tien_nghi` varchar(255) NOT NULL,
  `tentn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ma_tien_nghi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.tien_nghi: ~25 rows (approximately)
INSERT INTO `tien_nghi` (`ma_tien_nghi`, `tentn`) VALUES
	('TN01', 'Giường đơn'),
	('TN02', 'Tủ lạnh nhỏ'),
	('TN03', 'TV'),
	('TN04', 'Giường đôi'),
	('TN05', 'Bồn tắm'),
	('TN06', 'Tủ lạnh lớn'),
	('TN07', 'TV màn hình phẳng'),
	('TN08', 'Phòng khách riêng biệt'),
	('TN09', 'Giường King size'),
	('TN10', 'Phòng ăn riêng'),
	('TN11', 'Máy lạnh'),
	('TN12', 'Két an toàn'),
	('TN13', 'Máy pha cà phê/ấm đun nước'),
	('TN14', 'Bàn làm việc'),
	('TN15', 'Dịch vụ phòng 24/7'),
	('TN16', 'Wifi miễn phí'),
	('TN17', 'Dịch vụ đánh thức'),
	('TN18', 'Dịch vụ giặt ủi'),
	('TN19', 'Minibar'),
	('TN20', 'Dịch vụ spa/massage tại phòng'),
	('TN21', 'Bếp nhỏ với đồ dùng nấu ăn'),
	('TN22', 'Máy giặt/sấy'),
	('TN23', 'Ban công riêng'),
	('TN24', 'Hồ bơi riêng'),
	('TN25', 'Phòng tắm hơi/sauna');

-- Dumping structure for table db_dulichviet.tinh
CREATE TABLE IF NOT EXISTS `tinh` (
  `ma_tinh` varchar(10) NOT NULL,
  `ten_tinh` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ma_tinh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.tinh: ~63 rows (approximately)
INSERT INTO `tinh` (`ma_tinh`, `ten_tinh`) VALUES
	('T01', 'Hà Nội'),
	('T02', 'Hồ Chí Minh'),
	('T03', 'Đà Nẵng'),
	('T04', 'Hải Phòng'),
	('T05', 'Cần Thơ'),
	('T06', 'Lâm Đồng'),
	('T07', 'Khánh Hòa'),
	('T08', 'Bà Rịa - Vũng Tàu'),
	('T09', 'Thừa Thiên - Huế'),
	('T10', 'Bình Định'),
	('T11', 'Đắk Lắk'),
	('T12', 'Gia Lai'),
	('T13', 'Bình Thuận'),
	('T14', 'Kiên Giang'),
	('T15', 'An Giang'),
	('T16', 'Cà Mau'),
	('T17', 'Hậu Giang'),
	('T18', 'Sóc Trăng'),
	('T19', 'Bạc Liêu'),
	('T20', 'Đồng Tháp'),
	('T21', 'Trà Vinh'),
	('T22', 'Vĩnh Long'),
	('T23', 'Đồng Tháp'),
	('T24', 'Tây Ninh'),
	('T25', 'Bến Tre'),
	('T26', 'Long An'),
	('T27', 'Tiền Giang'),
	('T28', 'Bình Dương'),
	('T29', 'Đồng Nai'),
	('T30', 'Bà Rịa - Vũng Tàu'),
	('T31', 'Đồng Nai'),
	('T32', 'Lâm Đồng'),
	('T33', 'Phú Yên'),
	('T34', 'Quảng Nam'),
	('T35', 'Quảng Ngãi'),
	('T36', 'Kon Tum'),
	('T37', 'Quảng Trị'),
	('T38', 'Quảng Trị'),
	('T39', 'Ninh Thuận'),
	('T40', 'Quảng Bình'),
	('T41', 'Thanh Hóa'),
	('T42', 'Nghệ An'),
	('T43', 'Hà Tĩnh'),
	('T44', 'Nam Định'),
	('T45', 'Thái Bình'),
	('T46', 'Hưng Yên'),
	('T47', 'Quảng Ninh'),
	('T48', 'Quảng Ninh'),
	('T49', 'Bắc Giang'),
	('T50', 'Lạng Sơn'),
	('T51', 'Cao Bằng'),
	('T52', 'Hà Giang'),
	('T53', 'Tuyên Quang'),
	('T54', 'Yên Bái'),
	('T55', 'Lào Cai'),
	('T56', 'Điện Biên'),
	('T57', 'Lai Châu'),
	('T58', 'Sơn La'),
	('T59', 'Hòa Bình'),
	('T60', 'Bắc Ninh'),
	('T61', 'Hà Nam'),
	('T62', 'Hải Dương'),
	('T63', 'Thái Nguyên');

-- Dumping structure for table db_dulichviet.tin_tuc
CREATE TABLE IF NOT EXISTS `tin_tuc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hinh_anh` varchar(255) DEFAULT NULL,
  `noi_dung` text,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.tin_tuc: ~0 rows (approximately)

-- Dumping structure for table db_dulichviet.tour
CREATE TABLE IF NOT EXISTS `tour` (
  `ma_tour` varchar(255) NOT NULL,
  `ngaykh` datetime(6) DEFAULT NULL,
  `noi_khoi_hanh` varchar(255) DEFAULT NULL,
  `soluong` int DEFAULT NULL,
  `makm` varchar(10) DEFAULT NULL,
  `ma_loai_tour` varchar(10) DEFAULT NULL,
  `ma_phong` varchar(255) DEFAULT NULL,
  `ma_phuong_tien` varchar(10) DEFAULT NULL,
  `ma_tinh` varchar(10) DEFAULT NULL,
  `ten_tour` varchar(255) DEFAULT NULL,
  `secondary_image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ma_tour`),
  KEY `FKbgbao0o41xwsyiw4rovnaeky5` (`makm`),
  KEY `FK2qwa9clc28r87lyi126y7m0t8` (`ma_loai_tour`),
  KEY `FKac4fovj8ptohhs19j09udhjs4` (`ma_phong`),
  KEY `FKf6w102p5ntgqexar2mnfm3hlo` (`ma_phuong_tien`),
  KEY `FKpdl5nef9irm1h6grb49e9tegm` (`ma_tinh`),
  CONSTRAINT `FK2qwa9clc28r87lyi126y7m0t8` FOREIGN KEY (`ma_loai_tour`) REFERENCES `loai_tour` (`ma_loai_tour`),
  CONSTRAINT `FKac4fovj8ptohhs19j09udhjs4` FOREIGN KEY (`ma_phong`) REFERENCES `phong` (`ma_phong`),
  CONSTRAINT `FKbgbao0o41xwsyiw4rovnaeky5` FOREIGN KEY (`makm`) REFERENCES `khuyen_mai` (`makm`),
  CONSTRAINT `FKf6w102p5ntgqexar2mnfm3hlo` FOREIGN KEY (`ma_phuong_tien`) REFERENCES `phuong_tien` (`ma_phuong_tien`),
  CONSTRAINT `FKpdl5nef9irm1h6grb49e9tegm` FOREIGN KEY (`ma_tinh`) REFERENCES `tinh` (`ma_tinh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.tour: ~4 rows (approximately)
INSERT INTO `tour` (`ma_tour`, `ngaykh`, `noi_khoi_hanh`, `soluong`, `makm`, `ma_loai_tour`, `ma_phong`, `ma_phuong_tien`, `ma_tinh`, `ten_tour`, `secondary_image_url`) VALUES
	('Tour01', '2024-06-14 00:00:00.000000', 'Phú Quốc', 40, NULL, 'LT03', 'P01', 'PT02', 'T14', 'Vinpearl Resort & Spa Phú Quốc', NULL),
	('Tour02', '2024-06-16 11:39:35.000000', 'TP.HCM', 30, NULL, 'LT01', 'P01', 'PT01', 'T02', 'Tour Đà Nẵng 4N3Đ: HCM - Hội An - Quảng Bình - Huế', NULL),
	('Tour03', '2024-06-16 12:11:04.000000', 'TP.HCM', 20, NULL, 'LT03', 'P01', 'PT02', 'T02', 'Tour Cao Cấp Phan Thiết 3N2Đ: HCM - Núi Tà Cú - Mango Beach - KDL Bàu Trắng - Suối Tiên', NULL),
	('Tour04', '2024-06-16 12:20:12.000000', 'Huế', 25, NULL, 'LT03', 'P01', 'PT02', 'T33', 'Tour Miền Trung 3N3Đ: Đảo Kỳ Co - Gành Đá Đĩa - Đầm Ô Loan Xe Giường Nằm', NULL);

-- Dumping structure for table db_dulichviet.user
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `dia_chi` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `ho_ten` varchar(255) DEFAULT NULL,
  `sdt` int DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.user: ~0 rows (approximately)
INSERT INTO `user` (`id_user`, `dia_chi`, `email`, `ho_ten`, `sdt`) VALUES
	(1, 'Thủ Đức', 'vanhai@gmail.com', 'Hà Văn Hải', 123456789);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
