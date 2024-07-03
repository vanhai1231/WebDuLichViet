-- --------------------------------------------------------
-- Máy chủ:                      127.0.0.1
-- Server version:               8.4.0 - MySQL Community Server - GPL
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

-- Dumping structure for table db_dulichviet.chi_tiet_khuyen_mai
CREATE TABLE IF NOT EXISTS `chi_tiet_khuyen_mai` (
  `makm` varchar(255) NOT NULL,
  `ma_tour` varchar(10) NOT NULL,
  PRIMARY KEY (`makm`,`ma_tour`),
  KEY `FKons2wmbyblrngwdo5l5b9faqx` (`ma_tour`),
  CONSTRAINT `FK3mlu8riq0lrlruotek09mnp40` FOREIGN KEY (`makm`) REFERENCES `khuyen_mai` (`makm`),
  CONSTRAINT `FKons2wmbyblrngwdo5l5b9faqx` FOREIGN KEY (`ma_tour`) REFERENCES `tour` (`ma_tour`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.chi_tiet_khuyen_mai: ~2 rows (approximately)
INSERT INTO `chi_tiet_khuyen_mai` (`makm`, `ma_tour`) VALUES
	('KM96288628', 'Tour04'),
	('KM25472906', 'Tour07');

-- Dumping structure for table db_dulichviet.chi_tiet_lich_trinh
CREATE TABLE IF NOT EXISTS `chi_tiet_lich_trinh` (
  `ma_tour` varchar(10) NOT NULL,
  `mahdv` varchar(10) NOT NULL,
  `id` int NOT NULL,
  `ngaykh` datetime(6) NOT NULL,
  PRIMARY KEY (`id`,`mahdv`,`ma_tour`),
  KEY `FKc4gptjbyrei8ca7sgotn4hohd` (`mahdv`),
  KEY `FK8gvjef2ktxfg6c6rq0hynrw8l` (`ma_tour`),
  CONSTRAINT `FK8gvjef2ktxfg6c6rq0hynrw8l` FOREIGN KEY (`ma_tour`) REFERENCES `tour` (`ma_tour`),
  CONSTRAINT `FKc4gptjbyrei8ca7sgotn4hohd` FOREIGN KEY (`mahdv`) REFERENCES `huong_dan_vien` (`mahdv`),
  CONSTRAINT `FKmcj8jrig23etu7eb3ic529s2q` FOREIGN KEY (`id`) REFERENCES `lich_trinh` (`id`)
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

-- Dumping structure for table db_dulichviet.chi_tiet_wishlist
CREATE TABLE IF NOT EXISTS `chi_tiet_wishlist` (
  `ma_tour` varchar(10) NOT NULL,
  `wishlistid` int NOT NULL,
  PRIMARY KEY (`ma_tour`,`wishlistid`),
  KEY `FKt3epy3hq5npma01830qn0wj5l` (`wishlistid`),
  CONSTRAINT `FKhjy9mgyt9t1qb7ljd3lr5ru3s` FOREIGN KEY (`ma_tour`) REFERENCES `tour` (`ma_tour`),
  CONSTRAINT `FKt3epy3hq5npma01830qn0wj5l` FOREIGN KEY (`wishlistid`) REFERENCES `wishlist` (`wishlistid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.chi_tiet_wishlist: ~3 rows (approximately)
INSERT INTO `chi_tiet_wishlist` (`ma_tour`, `wishlistid`) VALUES
	('Tour02', 1),
	('Tour03', 1),
	('Tour04', 1),
	('Tour07', 2);

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

-- Dumping structure for table db_dulichviet.diem_den
CREATE TABLE IF NOT EXISTS `diem_den` (
  `id` int NOT NULL,
  `hinh_anh` varchar(200) NOT NULL,
  `mo_ta` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ten_diem_den` varchar(200) NOT NULL,
  `ma_tinh` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqxm7toxct9i4kdgciikhldb95` (`ma_tinh`),
  CONSTRAINT `FKqxm7toxct9i4kdgciikhldb95` FOREIGN KEY (`ma_tinh`) REFERENCES `tinh` (`ma_tinh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.diem_den: ~59 rows (approximately)
INSERT INTO `diem_den` (`id`, `hinh_anh`, `mo_ta`, `ten_diem_den`, `ma_tinh`) VALUES
	(1, '/images/VuonHoaDaLat.jpg', 'Nằm trong khu vực trung tâm thành phố, vườn hoa thành phố Đà Lạt là địa điểm du lịch Đà Lạt thu hút đông đảo du khách đến tham quan, chụp hình. Vườn hoa được xây dựng từ năm 1966 và đến năm 1985, vườn hoa được khôi phục lại để trồng các loại hoa mới.', 'Vườn hoa thành phố Đà Lạt', 'T06'),
	(2, '/images/CayThongCoDon.jpg', 'Thông luôn là biểu tượng từ bao đời của xứ sở ngàn hoa. Cây thông cô đơn mọc ven hồ Suối Vàng sừng sững và đơn độc giữa khung cảnh cực kỳ nên thơ và lãng mạn với một hồ trước rộng trải dài, ẩn sau là rừng thông bạt ngàn xanh ngát. Đến đây, du khách chỉ cần đứng và đưa máy ảnh lên chụp là đã đẹp, chẳng cần tạo dáng gì nhiều.', 'Cây thông cô đơn', 'T06'),
	(3, '/images/ChoDem.jpg', 'Từ lâu, chợ đêm Đà Lạt đã thành điểm đến quen thuộc mà bất cứ ai khi đến du lịch Đà Lạt đều muốn lui tới. Chợ đêm Đà Lạt, hay còn được gọi là chợ Âm Phủ, tọa lạc ngay trung tâm thành phố, rất gần các bến xe khách và quảng trường thành phố.', 'Chợ đêm Đà Lạt ', 'T06'),
	(4, '/images/QuangTruong.jpg', 'Tọa lạc tại vị trí trung tâm của thành phố, trên tuyến đường Trần Phú, quảng trường 2/4 không chỉ là biểu tượng văn hoá, lịch sử quan trọng mà còn là điểm đến ưa thích của du khách và dân địa phương. Đến đây, bạn không chỉ đắm chìm trong vẻ đẹp tự nhiên của biển cả mà còn đắm say trong nhịp sống sôi động của thành phố Nha Trang.', 'Quảng trường 2/4 ', 'T07'),
	(5, '/images/NhaHat.jpg', 'Giờ mở cửa tham khảo: 9:00 - 19:00, tuy nhiên giờ mở cửa của nhà hát Đó sẽ được điều chỉnh theo thời gian diễn ra của các sự kiện, buổi biểu diễn.', ' Nhà hát Đó', 'T07'),
	(6, '/images/NhaTho.jpg', 'Nhà thờ Đá Nha Trang, còn được biết đến với tên gọi Nhà thờ Chánh Tòa Núi, là một công trình tôn giáo và kiến trúc nổi bật tọa lạc trên đỉnh đồi Cả, Nha Trang. Nhà thờ Đá Nha Trang là một tác phẩm kiến trúc tinh tế, nằm trên độ cao 12m so với mặt đất, với chiều dài 36m và chiều rộng 20m. Tổng thể kiến trúc của nhà thờ mang sự vững chắc nhờ các khối đá lập thể nhuộm màu sắc của thời gian. ', 'Nhà thờ Đá Nha Trang', 'T07'),
	(7, '/images/Hue.jpg', 'Nhắc đến quần thể di tích cố đô Huế, bạn không thể bỏ qua Đại Nội, một dấu ấn văn hóa lịch sử độc đáo thuộc quần thể di tích cố đô Huế. Đại Nội là khu vực bao gồm Hoàng Thành và Tử Cấm Thành với hơn 100 công trình kiến trúc được xây dựng từ thời vua nhà Nguyễn, chứng kiến bao thăng trầm của các triều đại phong kiến Việt Nam, và đã được tổ chức UNESCO công nhận là di sản văn hóa thế giới gần 30 năm trước.', 'Đại Nội Huế', 'T09'),
	(8, '/images/CayNgoDong.jpg', 'Nếu đã trót đắm say những thước phim đậm chất thơ của bộ phim Mắt Biếc, thì có lẽ hình ảnh cây ngô đồng đã không còn quá xa lạ gì với những mọt phim đúng không nhỉ?', 'Cây Ngô Đồng Phim Mắt Biếc', 'T09'),
	(9, '/images/SongHuong.jpg', 'Mỗi thành phố đều có một dòng sông gắn liền như linh hồn của nơi đó.  cũng vậy. Đây là dòng sông đã đi vào lịch sử, thi ca, đi vào lòng mỗi người dân Huế. Với chiều dài khoảng 80km,  chảy qua rất nhiều địa điểm nổi tiếng của trung tâm thành phố Huế, bao lấy quần thể di tích cố đô, làm tăng thêm vẻ đẹp uy nghiêm, cổ kính của các công trình ấy.', 'Sông Hương', 'T09'),
	(10, '/images/PhoCo.jpg', 'Một lần du lịch Huê mộng mơ, hãy đến Bao Vinh để cảm nhận chút hồn quê với những ngôi nhà cổ kính phảng phất trong lòng thành phố. Bao Vinh, ngày nay vẫn được biết đến như một phố cổ, địa điểm du lịch Huế này đã tồn tại từ xa xưa, đã từng là một phố cảng trong chuỗi cảng thị Thanh Hà - Bao Vinh sầm uất từ đầu thế kỷ XVII. Dấu tích còn lại của một thời vàng son ấy là những ngôi nhà cổ đã hơn trăm năm lịch sử.', 'Phố Cổ Bao Vinh Huế', 'T09'),
	(11, '/images/GomSu.jpg', 'Địa điểm du lịch Hà Nội này mang trong mình cả bầu trời văn hóa và lịch sử dân tộc. Đến với con đường gốm sứ, bạn như lạc trong một bức tranh rực rỡ đang tô điểm cho vẻ đẹp của thủ đô nghìn năm văn hiến.', 'Con Đường Gốm Sứ Hà Nội', 'T01'),
	(12, '/images/PhoHaNoi.jpg', 'Không còn gì phải bàn cãi khi con phố Phan Đình Phùng được xem là một trong những con phố lãng mạn nhất Hà Nội. Các cây xanh trên phố, đặc biệt là những cây sấu đã trải qua hàng thế kỷ, tạo nên một tán lá xanh ngút ngàn, như vòng tay ôm trọn cả phố. Đến Hà Nội mùa thu, hãy thử bước đi trong tà áo dài, mua vài nhánh hoa của cô chú ven đường và làm cho mình vài kiểu ảnh thật "thơ" sẽ là trải nghiệm không thể nào quên đâu đấy.', 'Phố Phan Đình Phùng', 'T01'),
	(13, '/images/DaiQuanSat.jpg', 'Ngay từ những ngày đầu ra mắt,  đã “gây sốt” với diện mạo vô cùng đáng yêu, các hoạt động tương tác thú vị và (dĩ nhiên) là tầm nhìn toàn cảnh thành phố (siêu lung linh lúc lên đèn). Vườn Sky Dating và khu chụp ảnh Love Photo đã biến Đài Quan Sát Lotte Hà Nội Sky trở thành địa điểm du lịch Hà Nội “nóng xình xịch” dành cho các cặp đôi. Chưa hết, bạn còn có trải nghiệm “thót tim” khi dạo bước trên những tấm kính cường lực vững chắc của đài quan sát trong suốt đầu tiên ở Đông Nam Á. Đừng quên dùng bữa tối siêu lãng mạn ở đây và dành thời gian mua sắm, thư giãn, giải trí ở trung tâm thương mại bên dưới nhé!', 'Đài Quan Sát Lotte Hà Nội Sky', 'T01'),
	(14, '/images/QuocTuGiam.jpg', ' là quần thể di tích về trường đại học đầu tiên của Việt Nam, bao gồm khu Văn Miếu – Quốc Tử Giám, hồ Văn và vườn Giám. Được xây dựng vào thời vua Lý Thánh Tông (khoảng năm 1070), Văn Miếu là nơi thờ Chu Công, Khổng Tử và Tứ Phối. Quốc Tử Giám - trường đại học đầu tiên tại Việt Nam - được vua Lý Nhân Tông cho thành lập thêm vào năm 1076. Toạ lạc ở phía Nam Hoàng Thành Thăng Long, Văn Miếu - Quốc Tử Giám là địa điểm lui tới thường xuyên của giới sinh viên, học sinh Hà Thành, nhằm cầu nguyện điều may mắn trên con đường khoa cử. Nếu muốn tìm hiểu về một Đại Việt nghìn năm văn hiến và mục sở thị kiến trúc đặc trưng của thời đầu nhà Nguyễn, bạn nhất định không được "lỡ hẹn" với địa điểm du lịch Hà Nội này đâu đấy. ', 'Văn Miếu - Quốc Tử Giám', 'T01'),
	(15, '/images/KhueVanCac.jpg', 'Nằm trong Quốc Tử Giám,  được xây dựng vào năm 1805, bởi Tổng trấn Nguyễn Văn Thành triều Nguyễn đương thời. Đây là một lầu vuông tám mái, cao gần chín thước, bao gồm bốn mái hạ và bốn mái thượng. Gác Văn Khuê - có nghĩa là "vẻ đẹp của sao Khuê" - dựng trên một nền vuông cao lát gạch Bát Tràng, sở hữu kiến trúc rất hài hòa và độc đáo. Tầng dưới là 4 trụ gạch vuông, mặt trụ có chạm trổ hoa văn sắc sảo. Tầng trên là gỗ sơn son thếp vàng. Phần mái lợp (góc mái và bờ nóc) làm bằng chất liệu vôi cát hoặc đất nung có độ bền cao. ', ' Khuê Văn Các ', 'T01'),
	(16, '/images/DaoNgoc.jpg', 'Ấn tượng đầu tiên về Đảo Ngọc - hay còn có tên Cù Lao Bãi Ngựa - là cấu trúc tự nhiên vừa độc đáo vừa bắt mắt. Địa điểm này được tạo nên bởi những cây vẹt, cây đước lâu năm, đan tán vào nhau tạo thành một “thành luỹ” thuần xanh, có công dụng ngăn nước biển và tạo nên đường đi bộ. Khám phá phía bên trong Đảo Ngọc, #teamKlook sẽ được dịp trầm trồ trước hồ nước mặn trong vắt, lặng yên như tờ, cực kỳ phù hợp với các hoạt động thể chất như chèo thuyền Kayak, câu cá hoặc đi xe đạp nước. Đảo Ngọc ở Vũng Tàu là địa điểm du lịch trên-cả-tuyệt-vời dành cho gia đình có trẻ nhỏ hoặc nhóm du lịch tập thể, nhóm bạn, công ty… ', 'Đảo Ngọc Vũng Tàu (Cù Lao Bãi Ngựa)', 'T08'),
	(17, '/images/HoMay.jpg', 'Nếu cảm thấy bối rối trước hàng loạt hoạt động  mà thời gian vi vu hạn hẹp thì bạn nên chọn. Đây là tổ hợp du lịch, khám phá thiên nhiên, vui chơi, giải trí, ăn uống, công viên nước, tham quan công trình tâm linh, di tích văn hoá, lịch sử - đảm bảo mang đến cho bạn trải nghiệm vi vu trọn vẹn cảm xúc nhất. Khu Du Lịch Hồ Mây toạ lạc ở độ cao 210m, ngay trên Núi Lớn, nên sở hữu khí hậu quanh năm mát mẻ, phong cảnh hữu tình, sẽ là trải nghiệm đáng nhớ dành cho các cặp đôi, nhóm bạn hoặc gia đình có trẻ nhỏ. ', 'Khu Du Lịch Hồ Mây Vũng Tàu', 'T08'),
	(18, '/images/HonBa.jpg', 'Du lịch biển đảo ở Vũng Tàu có thể không được đánh giá cao bằng những thiên đường nghỉ dưỡng như ,  hay . Tuy nhiên, không phải vì thế mà thành phố này lại vắng bóng các hòn đảo đẹp. Hòn Bà là một ví dụ tiêu biểu.  Còn có tên gọi khác là hòn Archinard hay hòn Ba Viên Đạn, Hòn Bà nằm lẻ loi giữa biển; chân đảo đón sóng nổi bọt trắng xoá, sở hữu nét đẹp hoang sơ và giản dị đến nao lòng. Phần đông du khách ghé thăm Hòn Bà là để cúng viếng Miếu Bà - được xây dựng từ năm 1881 - và tận hưởng không gian xanh không vương vấn muộn phiền. #teamKlook có thể đến được đảo Hòn Bà bằng thuyền, ghe khi nước lớn và đi bộ qua “con đường rẽ đôi biển” khi nước ròng.', 'Hòn Bà - Vũng Tàu', 'T08'),
	(19, '/images/HoTram.jpg', 'Không khí sôi động, nhịp sống nhanh ở trung tâm thành phố Vũng Tàu khiến bạn cảm thấy “choáng ngợp”? Vậy thì hãy di chuyển khoảng 30km về phía Đông Bắc để đến khu vực biển , nơi được xem là “trung tâm đầu não” các khu nghỉ dưỡng của tỉnh và cũng là thiên đường du lịch biển phía Nam. Hàng dừa nối đuôi nhau chạy trên bãi biển cát trắng, nắng vàng soi chiếu xuống mặt biển lấp lánh tựa gương soi, không khí mát mẻ pha lẫn vị mằn mặn của đại dương tại các làng chài địa phương,… tất cả đã tạo nên trải nghiệm nghỉ dưỡng hiện đại nhưng không hệ tách biệt với văn hoá truyền thống ở Hồ Tràm. Chọn ngay một  hay  ưng ý và “chill” thôi.', 'Hồ Tràm - Vũng Tàu', 'T08'),
	(20, '/images/NhaThoDucBa.jpg', 'Cũng nằm ngay trung tâm quận 1, nhà thờ Đức Bà là địa điểm du lịch thành phố Hồ Chí Minh mà bạn nhất định phải ghé đến. Nhà thờ Đức Bà là một công trình kiến trúc độc đáo mang đậm phong cách Châu Âu, là nơi sinh hoạt và tổ chức các buổi Thánh lễ cho những người theo đạo Công giáo ở Sài Thành.', 'Nhà thờ Đức Bà', 'T02'),
	(21, '/images/DinhDocLap.jpg', 'Dinh Độc Lập hay còn gọi là hội trường Thống Nhất, đây là một công trình được xây dựng bởi người Pháp, từ thời Pháp thuộc. Đối với người dân Sài Gòn, Dinh Độc Lập là một di tích lịch sử mang ý nghĩa hòa bình và toàn vẹn lãnh thổ. Nơi đây đã được công nhận là 1 trong 10 di tích quốc gia đặc biệt của Việt Nam vào năm 2009.', 'Dinh Độc Lập', 'T02'),
	(22, '/images/BaoTang.jpg', 'Bảo tàng lịch sử Việt Nam được xây dựng và hoạt động từ những năm đầu thế kỷ 20, là nơi lưu giữ và bảo tồn những hình ảnh, cổ vật từ thuở sơ khai đến nay. Bảo tàng lịch sử Việt Nam thu hút phần lớn những du khách yêu lịch sử và kiến trúc pha trộn giữa 2 phong cách Á – Âu độc đáo.', 'Bảo tàng lịch sử Việt Nam', 'T02'),
	(23, '/images/DiaDao.jpg', 'Địa đạo Củ Chi là di tích lịch sử nằm ở ngoại ô, cách trung tâm Tp.HCM khoảng 70km. Đây là một trong những điểm nên đến ở Sài Gòn được yêu thích, đặc biệt là đối với các du khách nước ngoài.', 'Địa đạo Củ Chi', 'T02'),
	(24, '/images/NhaCongTu.jpg', 'Không chỉ tại Bạc Liêu, công trình cổ này còn nổi tiếng khắp miền Tây. Nằm ngay cạnh dòng sông Bạc Liêu và tọa lạc tại số 13 cung đường Điện Biên Phủ, ngôi nhà bề thế này được xây dựng trong vòng 2 năm (1917 - 1919). Với khối kiến trúc hoành tráng bậc nhất thời bấy giờ, người dân địa phương gọi nơi đây bằng cái tên thú vị “nhà lớn”.', 'Nhà công tử Bạc Liêu', 'T19'),
	(25, '/images/ThapCo.jpg', 'Cách trung tâm thành phố Bạc Liêu khoảng 20km, tháp cổ Vĩnh Hưng là một khối kiến trúc được tìm thấy bởi học giả người Pháp - Lunet de Lajonquiere vào năm 1911. Không chỉ đậm dấu ấn của nền văn hóa Óc Eo, đây còn là một trong những số ít công trình cổ còn tồn tại mà còn là nơi lưu giữ khá nhiều hiện vật quý (tượng, đá quý, đồ gồm…).', ' Tháp cổ Vĩnh Hưng', 'T19'),
	(26, '/images/KhuLuuNiem.jpg', 'Cùng với nhà công tử Bạc Liêu, khu lưu niệm nhạc sĩ Cao Văn Lầu là địa điểm du lịch Bạc Liêu nhất định phải check-in khi đặt chân đến thành phố này. Khu lưu niệm nằm trải rộng trên mảnh đất trồng rất nhiều cây xanh có tổng diện tích lên đến 12.000m2. Công trình này được chia thành nhiều khu vực như khu mộ cố nhạc sĩ, khu trưng bày hình ảnh, sân khấu ngoài trời…', 'Khu lưu niệm nhạc sĩ Cao Văn Lầu', 'T19'),
	(27, '/images/ChuaGhositaram.jpg', 'Nằm tại huyện Vĩnh Lợi, chùa Ghositaram là một địa điểm du lịch Bạc Liêu phản ánh rõ nét tín ngưỡng Phật giáo của người dân địa phương. Với khối kiến trúc lộng lẫy tựa như một viện bảo tàng thu nhỏ, ngôi chùa này là một trong những công trình Khmer đẹp nhất khu vực đồng bằng sông Cửu Long.', 'Chùa Ghositaram', 'T19'),
	(28, '/images/GhenRangTienSa.jpg', 'Tương tự bãi tắm Quy Nhơn, Ghềnh Ráng Tiên Sa là địa điểm du lịch Bình Định nằm gần trung tâm thành phố (chỉ cách khoảng 3km về phía Đông Nam). Bạn có thể di chuyển bằng xe máy, ô tô, xe khách hoặc máy bay để đến Quy Nhơn, sau đó, bắt xe tiếp ra Ghềnh Ráng nằm ở số 3 Hàn Mặc Tử, phường Ghềnh Ráng.', 'Ghềnh Ráng Tiên Sa', 'T10'),
	(29, '/images/EoGio.jpg', 'Cách nội đô Quy Nhơn khoảng 20km, Eo Gió là một tọa độ sơn thủy hữu tình với những mỏm núi đá phủ đầy cây bụi “vòng tay” ôm trọn eo biển xanh màu ngọc bích. Nếu bạn yêu thích du lịch bụi thì đây là một trong những địa điểm check-in ở Bình Định lý tưởng nhất.', ' Eo Gió', 'T10'),
	(30, '/images/DamThiNai.jpg', 'Nằm trong địa phận huyện Phù Cát, đầm Thị Nại là đầm nước mặn có diện tích lớn nhất tỉnh Bình Định. Từng là hải cảng quan trọng, đầm được bồi đắp bởi hai dòng sông Hà Thanh và sông Kôn. Với diện tích hơn 5000ha, đầm là nơi sinh sống của hệ động thực vật cực phong phú.', 'Đầm Thị Nại', 'T10'),
	(31, '/images/SamSon.jpg', 'Cứ mỗi độ hè về, biển Sầm Sơn Thanh Hóa luôn chào đón rất nhiều du khách trong và ngoài nước tới đây vui chơi, nghỉ dưỡng. Biển Sầm Sơn thuộc thành phố Sầm Sơn, chỉ cách thành phố Thanh Hóa 16km. Cung đường di chuyển đến bãi biển Sầm Sơn rất gần và dễ di chuyển. Khi tới nơi, mở ra trước mắt bạn sẽ là bãi biển hình trăng khuyết với nền cát trắng mịn và từng đợt sóng xô bờ. Ngoài ra, cũng có rất nhiều trò chơi dưới nước tại biển Sầm Sơn cho bạn trải nghiệm như dù bay, mô tô,...hoặc nhanh tay lưu lại khoảnh khắc bình minh hay hoàng hôn tại bãi biển xinh đẹp này cũng sẽ mang lại cho bạn ký ức khó quên.', 'Biển Sầm Sơn Thanh Hóa', 'T41'),
	(32, '/images/SuoiCaThan.jpg', 'Suối Cá Thần là một điểm đến mà bạn không nên bỏ qua khi đi du lịch Thanh Hóa.  Con suối nằm dưới chân núi Trường Sinh thuộc bản Lương Ngọc, xã Cẩm Lương, huyện Cẩm Thủy. Trên đường di chuyển đến địa điểm này, bạn sẽ được chiêm ngưỡng những ngôi nhà sàn mộc mạc của người dân tộc Mường nằm ẩn khuất sau hàng cây đại ngàn và khi đến suối, bạn sẽ phải trầm trồ, thích thú khi chứng kiến hàng ngàn con cá chen chúc, bơi lội dưới dòng nước trong vắt. Và đặc biệt hơn, bạn sẽ được người dân nơi đây kể cho nghe về những câu chuyện kỳ bí liên quan tới đàn cá tại dòng suối này. Đây sẽ là trải nghiệm cực kỳ thú vị đó. Ngoài ra, giá vé để vào tham quan Suối Cá Thần là 20.000đ/người lớn và 10.000đ/trẻ em nha.', 'Suối Cá Thần Cẩm Lương Thanh Hóa', 'T41'),
	(33, '/images/LamKinh.jpg', 'Khu di tích Lam Kinh nằm trên QL47, thuộc xã Xuân Lam, huyện Thọ Xuân, cách thành phố Thanh Hóa khoảng 52km. Nơi đây đã được công nhận là di tích quốc gia đặc biệt vào năm 2013. Toàn bộ khu di tích rộng khoảng hơn 30ha gồm phần lăng, đền miếu và hành cung của các vua thời Hậu Lê mỗi lần về bái yết tổ tiên. Dù đã trải qua hàng trăm năm lịch sử, dù một vài khu vực trong khu di tích đã xuống cấp nhưng vẫn không thể nào làm vơi bớt đi sự trang nghiêm, hào hùng của một thời quá khứ. Tham quan khu di tích Lam Kinh, bạn sẽ được đắm mình trong không gian thanh tịnh, hoài cổ và lắng nghe những câu chuyện hào hùng về cha ông ta thuở trước. Để vào khu di tích, bạn cần phải mua vé với giá là 30.000đ/người lớn và 15.000đ/trẻ em.', 'Khu di tích Lam Kinh', 'T41'),
	(34, '/images/ThanhNhaHo.jpg', 'Thành nhà Hồ toạ lạc tại địa phận xã Vĩnh Tiến, xã Vĩnh Long, huyện Vĩnh Lộc. Nơi đây được Hồ Quý Ly xây dựng từ năm 1397. Tính đến nay đã gần 600 năm với bao thăng trầm lịch sử và sương gió thời gian, nhưng 4 bức tường thành nổi bật với 4 cổng Đông, Tây, Nam, Bắc vẫn còn giữ được kiến trúc như ban đầu. Tham quan Thành nhà Hồ, bạn sẽ được mở rộng kiến thức về một giai đoạn lịch sử đầy biến động của đất nước, khâm phục cha ông vì những công trình kiến trúc với kỹ thuật xây dựng tài ba. Hiện nay, giá vé tham quan khu di tích này là 40.000đ/người lớn và 20.000đ/trẻ em.', 'Thành nhà Hồ', 'T41'),
	(35, '/images/CauHamRong.jpg', 'Cầu Hàm Rồng với chiều dài 150m được coi là chứng nhân lịch sử của quân và dân Thanh Hóa trong những năm tháng chống Mỹ cứu nước. Hiện nay, khi đứng trên cầu, bạn có thể ngắm nhìn dòng sông Mã lặng lẽ trôi, phóng tầm mắt ra xa để thu trọn sự sừng sững của núi Ngọc. Cây cầu nằm cách trung tâm thành phố Thanh Hóa chỉ 5km tại thị trấn Tào Xuyên, huyện Hoằng Hóa, nên nếu có dịp bạn hãy tranh thủ ghé qua nơi đây để ngắm nhìn cây cầu lúc bao phủ bởi ánh hoàng hôn hay lúc thành phố lên đèn và cảm thán về ý chí kiên cường của những con người xứ Thanh trong quá khứ.', 'Cầu Hàm Rồng ', 'T41'),
	(36, '/images/CucPhuong.jpg', 'Vườn quốc gia Cúc Phương nằm trên địa phận của ba tỉnh Ninh Bình, Hòa Bình và Thanh Hóa. Tại tỉnh Thanh Hoá, nơi đây thuộc khu vực xã Thạch Lâm, huyện Thạch Thành. Với những ai yêu thích thiên nhiên thì đây là một điểm đến không thể bỏ qua bởi bạn sẽ như được đắm mình vào sự bao la của thiên nhiên đại ngàn với hệ sinh thái động thực vật rất phong phú. Nơi đây cũng có rất nhiều địa điểm chờ bạn khám phá như: động Người Xưa, động Trăng Khuyết, đỉnh Mây Bạc, hồ Yên Quang, bản Mường,...Mỗi điểm đến đều mang những câu chuyện riêng gắn liền với sự hình thành của vườn quốc gia Cúc Phương, rất thú vị. Giá vé vào tham quan vườn quốc gia Cúc Phương là 60.000 đ/người lớn, 20.000 đ/học sinh - sinh viên, và 10.000đ/trẻ em. ', 'Vườn quốc gia Cúc Phương', 'T41'),
	(37, '/images/BaNaHills.jpg', 'Nằm ở độ cao 1.487 mét so với mực nước biển, khu du lịch Bà Nà Hills mở ra “khung trời Châu Âu giữa lòng phố thị”. Nơi đây là điểm đến yêu thích của các tín đồ Instagram, khi quy tụ hàng loạt toạ độ sống ảo đẹp lung linh. Kể “sương sương” thôi thì đã có Cầu Vàng, công trình từng được Tạp chí Time bình chọn là 1 trong 100 điểm đến hàng đầu thế giới, Làng Pháp, Vườn Hoa Le Jardin D’Amour, Bảo Tàng Tượng Sáp và Hầm Rượu Debay. Không thể không nhắc khoảnh khắc “chill cực” khi được lướt giữa mây màn trên tuyến cáp treo dài nhất thế giới, hướng tầm mắt về khung cảnh hùng vỹ của núi Chúa. ', 'Sun World Bà Nà Hills ', 'T03'),
	(38, '/images/SonTra.jpg', 'Còn có biệt danh “nàng tiên xanh”,  rộng đến 3.439 héc-ta -  sở hữu khí hậu quanh năm mát mẻ và hệ sinh thái động thực vật đa dạng bậc nhất Đà Nẵng. Vi vu Bán Đảo Sơn Trà mà chưa thu hoạch được “bộ ảnh nghìn likes” thì có gì đó… hơi sai.', 'Bán Đảo Sơn Trà Đà Nẵng', 'T03'),
	(39, '/images/TheGioiUpNguoc.jpg', 'Sẽ ra sao nếu thế giới đột nhiên đảo ngược? Con người ngủ phải ngủ trên trần nhà hay chật vật nấu ăn từ góc 180 độ? Đó chính là trải nghiệm mới mẻ, hài hước và hứng khởi mà bạn sẽ có được khi đến Upside Down World - Thế Giới Úp Ngược ở Đà Nẵng. Không chỉ có nội thất bắt mắt được bố trí theo cách “chẳng giống ai”, các căn phòng tại đây còn sở hữu chủ đề đa dạng cho #teamKlook thoả thích khám phá; đơn cử như Nhà Hàng Cổ Điển, Nhà Doraemon hay Hello Kitty. Upside Down World  cũng là nơi tài năng diễn xuất tiềm ẩn trong bạn toả sáng, để cho ra đời nhiều bức ảnh ngộ nghĩnh để đời. ', 'Upside Down World - Thế Giới Úp Ngược', 'T03'),
	(40, '/images/BaoTangDieuKhac.jpg', 'Được xây dựng vào đầu thế kỷ XX,  (hay Cổ Viện Chàm) gây ấn tượng bằng lối kiến trúc Gothic cổ điểm - với vách tường phủ sơn vàng, cửa kính đón nắng ấm và mái vòm chóp nhọn. Trải qua nhiều lần tu sửa, Bảo Tàng Điêu Khắc Chăm Đà Nẵng đang trưng bày hơn 2.000 hiện vật, trên dưới 500 tác phẩm điêu khắc từ thời đại Chăm Pa được khai quật  từ Hà Tĩnh đến Tây Nguyên.', 'Bảo Tàng Điêu Khắc Chăm Đà Nẵng - Cổ Viện Chàm', 'T03'),
	(41, '/images/RanNamO.jpg', 'Từ trung tâm thành phố Đà Nẵng, bạn đi về hướng Tây Bắc khoảng 17km là đến được Rạn Nam Ô - bãi biển hoang sơ kiêm địa điểm dã ngoại đang “số xình xịch” trong cộng đồng mê du lịch ở Đà Thành. Còn mới mẻ đối với du khách phương xa, ít ai ngờ rằng Rạn Nam Ô là địa hẹn “đưa nhau đi trốn” quen thuộc của người dân địa phương từ tận những năm 60 của thế kỷ trước.', 'Rạn Nam Ô Đà Nẵng - Điểm Đến Tuyệt Vời Cho Team Biển Đảo', 'T03'),
	(42, '/images/BaoTangKyUc.jpg', 'Tọa lạc tại 160 Nguyễn Duy Trinh, phường Hòa Hải, quận Ngũ Hành Sơn, thành phố Đà Nẵng, Bảo tàng Ký ức Điêu khắc Đá Mỹ nghệ Non Nước là điểm đến không thể bỏ qua cho những ai yêu thích nghệ thuật điêu khắc đá và muốn tìm hiểu về lịch sử, văn hóa của làng đá Non Nước.', ' Bảo Tàng Ký Ức Điêu Khắc Đá Mỹ Nghệ Non Nước Đà Nẵng', 'T03'),
	(43, '/images/BienTanThanh.jpg', 'Khác với vùng biển miền Bắc, miền Trung… với làn nước trong xanh và bãi cát trắng, biển Tân Thành sở hữu vẻ đẹp rất riêng với bãi cát đen dài hơn 7km. Dọc theo bãi biển là cầu cảng màu xanh lam đặc trưng và các khu lưới người dân dựng lên để đánh bắt cá, nghêu…', ' Biển Tân Thành (biển Gò Công)', 'T27'),
	(44, '/images/CauMyThuan.jpg', 'Cầu nối liền hai tỉnh Tiền Giang và Vĩnh Long. Dù bạn di chuyển bằng xe máy hay ô tô thì cũng đều có thể sắp xếp lịch trình đi ngang đây và chiêm ngưỡng vẻ đẹp của cây cầu. Đặc biệt, khi màn đêm buông xuống thì cầu sẽ trở nên nổi bật hơn với hệ thống đèn chiếu sáng và trang trí đấy!', 'Cầu Mỹ Thuận', 'T27'),
	(45, '/images/NhaThoChanhToa.jpg', 'Nhà thờ tọa lạc tại số 32 đường Hùng Vương, P.7, TP. Mỹ Tho. Có tuổi đời hơn 100 năm và trải qua nhiều lần trùng tu nhưng nhà thờ chánh tòa Tiền Giang vẫn giữ được nét đẹp cổ kính với những họa tiết tinh xảo và lối kiến trúc Hy Lạp phục hưng đặc trưng.', 'Nhà thờ chánh tòa Tiền Giang', 'T27'),
	(46, '/images/ChoNoiCaiBe.jpg', 'Đi du lịch miền Tây mà không nhắc đến chợ nổi thì thật là thiếu sót. Bạn có biết danh sách địa điểm du lịch Tiền Giang luôn có tên chợ nổi Cái Bè? Hãy thử một lần dậy sớm, đi ghe ra chợ nổi Cái Bè, thưởng thức một tô bún riêu nóng hổi, nhâm nhi ly cà phê sữa đá và ngắm bình minh miền sông nước. Còn gì tuyệt vời hơn!', 'Chợ nổi Cái Bè', 'T27'),
	(47, '/images/NuiLua.jpg', 'Cách trung tâm thành phố hơn 20km, núi lửa Chư Đăng Ya là tọa độ yêu thích của hội mê khám phá thiên nhiên. Cụm từ “Chư Đăng Ya” trong tiếng dân tộc J’rai có nghĩa là “củ gừng dại” và liên quan đến một câu chuyện cổ. Nằm giữa những thửa ruộng chữ nhật của ngôi làng Ploi lagri, đây vốn là một ngọn núi lửa đã ngừng hoạt động hàng triệu năm.', 'Núi lửa Chư Đăng Ya', 'T12'),
	(48, '/images/BienHo.jpg', 'Còn được gọi bằng cái tên khác - hồ T’nưng, Biển Hồ là địa điểm du lịch Gia Lai không thể bỏ qua khi đến phố núi này. Nằm giữa cao nguyên ở độ cao 800m so với mực nước biển, nơi này bao gồm hai hồ nước ngọt tự nhiên được ôm trọn bởi những cánh rừng thông xanh mướt và núi nón trùng điệp.', 'Biển hồ', 'T12'),
	(49, '/images/ThacHangEn.jpg', 'Địa điểm du lịch Gia Lai này hay còn được gọi bằng một cái tên khác là thác K50. Vốn là thượng nguồn của dòng sông Côn, thác Hang Én nằm bên ẩn sâu trong những cánh rừng nguyên sinh thuộc khu bảo tồn Kon Chư Răng. Với độ cao hơn 50m và chiều rộng tối đa 100m, dòng thác này đẹp như một dải lụa xanh bạc giữa đại ngàn.', ' Thác Hang Én', 'T12'),
	(50, '/images/ChoNoi.jpg', 'Chợ nổi Cà Mau là một nét văn hoá đặc trưng của người miền Tây và cũng là yếu tố thu hút khách thăm quan tới nơi đây. Chợ nổi Cà Mau có vị trí ‘’đắc địa’’ nằm ở trung tâm tỉnh Cà Mau và được ví như ‘’trái tim’’ của vùng đất sông nước này.', 'Chợ nổi Cà Mau - Nét đặc trưng của vùng sông nước', 'T16'),
	(51, '/images/DamDoi.jpg', 'Sẽ thật là thiếu sót khi nhắc tới địa điểm du lịch ở Cà Mau mà không nhắc tới Đầm Dơi. Đây là một khu rừng đước ngập mặn được thiên nhiên ưu ái ban cho nét đẹp hoang sơ khó kiếm.Đến đây, bạn sẽ được tận hưởng khung cảnh xanh, mát cây cối bao phủ bốn bề, du khách ngồi trên ghe và xuôi theo dòng chảy giữa những tán cây để thưởng thức nét đẹp tuyệt hảo của miền Tây sông nước.', 'Đầm Dơi - Địa điểm check-in lý tưởng ở Cà Mau', 'T16'),
	(52, '/images/MuiCaMau.jpg', 'Một trong những địa điểm du lịch Cà Mau được bao du khách mong ngóng ghé qua đó chính là Mũi Cà Mau. Đây chính là cực Nam của Tổ Quốc. Khi đến đây, bạn được chiêm ngưỡng cột mốc tọa độ quốc gia với chiếc thuyền căng đầy gió, con tàu của chúng ta luôn hướng ra biển khơi.', 'Mũi Cà Mau', 'T16'),
	(53, '/images/ThacBaTang.jpg', 'Thác Ba Tầng cách thị xã Gia Nghĩa 8 km ngược hướng thành phố Buôn Ma Thuột. Có tên gọi “Ba Tầng” là do thác có cấu trúc chia thành 3 tầng rõ rệt, nước chảy từ cao xuống thấp tạo nên khung cảnh tuyệt đẹp với những lớp bọt trắng xóa. Ngoài tên gọi trên, dòng thác còn có cái tên khác là thác Thủy Tiên.', 'Thác Ba Tầng', 'T29'),
	(54, '/images/ThacLuuLy.jpg', 'Thác Lưu Ly được mệnh danh là cô gái đẹp giữa núi Đắk Nông, nơi này cũng được nhiều khách du lịch tìm đến để tham quan. Dù quy mô không lớn như thác Liêng Nung nhưng Lưu Ly vẫn mang vẻ đẹp rất riêng, đằm thắm hơn, dịu dàng hơn.', 'Thác Lưu Ly', 'T29'),
	(55, '/images/HoTay.jpg', 'Hồ Tây Đắk Mil Làm một hồ Bán Nguyệt nhân tạo áo được xây dựng ảnh vào năm 1940 do thực dân pháp quy hoạch với mục đích trồng những dự án cà phê. Hồ cách trung tâm thành phố Buôn Ma Thuột 60km và cách thị xã Gia Nghĩa khoảng 65km.', 'Hồ Tây Đắk Mil', 'T29'),
	(56, '/images/TaDung.jpg', 'Vườn quốc gia nằm trên xã Đắk Som, huyện Đắk G’long, tỉnh Đắk Nông (cách thị trấn Gia Nghĩa hơn 50km). Tại đây tỉ lệ che phủ rừng đạt đến 85% với hệ sinh thái vô cùng đa dạng.Đặc biệt, nơi này còn là mái nhà chung của 37 loài động vật quý hiếm thuộc danh sách bảo vệ nghiêm ngặt. Nổi bật nhất là 3 loại đặc hữu cho Việt Nam gồm Voọc bạc trung bộ, Vượn má hung (Hylobates gabriellae) và Chà vá chân đen. Ngoài ra, Tà Đùng là vườn quốc gia duy nhất tại Việt Nam có sự xuất hiện của loài Hươu vàng (hay còn gọi là hươu đầm lầy).', 'Vườn Quốc gia Tà Đùng', 'T29'),
	(57, '/images/DoSon.jpg', 'Bãi biển Đồ Sơn được biết đến là một trong những bãi biển đẹp nhất tại miền Bắc. Vì vậy, nếu bạn có dịp ghé thăm Hải Phòng thì đừng bỏ lỡ điểm du lịch này. Bãi biển này được chia làm các khu I, II và III, tuy nhiên, nhiều du khách chủ yếu tắm biển và vui chơi ở khu II vì đây là khu vực tắm đẹp, biển thoai thoải, cát mịn. ', 'Bãi biển Đồ Sơn', 'T04'),
	(58, '/images/CatBa.jpg', 'Cát Bà là một huyện đảo cách trung tâm thành phố Hải Phòng khoảng 9km. Trong những dịp hè, lượng du khách đến tham quan khu du lịch Hải Phòng này rất đông. Thời điểm này, du khách có thể thỏa sức vui chơi dưới làn nước trong lành, thiên nhiên tươi mát. Khu du lịch Cát Bà Hải Phòng được thiên nhiên ưu ái với thời tiết mát mẻ quanh năm. Vì vậy đây cũng là điểm đến lý tưởng trong các điểm du lịch Hải Phòng được nhiều du khách vô cùng yêu thích. ', 'Đảo Cát Bà', 'T04'),
	(59, '/images/BachLongVy.jpg', 'Nếu bạn muốn khám phá hòn đảo xa nhất tại Vịnh Bắc Bộ thì đừng quên ghé đảo Bạch Long Vỹ tại Hải Phòng. Hòn đảo này cách đất liền khoảng 110km. Tại đây có rất nhiều vùng bãi triều, bãi biển khá hoang sơ thuận tiện cho việc du lịch và khám phá của du khách. ', 'Đảo Bạch Long Vỹ', 'T04');

-- Dumping structure for table db_dulichviet.hinh_anh
CREATE TABLE IF NOT EXISTS `hinh_anh` (
  `id` int NOT NULL AUTO_INCREMENT,
  `img` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.hinh_anh: ~23 rows (approximately)
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
	(12, '/images/DaNang2.jpg'),
	(13, '/images/YenTu1.jpg'),
	(14, '/images/YenTu2.jpg'),
	(15, '/images/TourDaLat4.jpg'),
	(16, '/images/TourDaLat.jpg'),
	(17, '/images/TourDaLat2.jpg'),
	(18, '/images/TourDaLat3.jpg'),
	(19, '/images/Hue01.jpg'),
	(20, '/images/Hue02.jpg'),
	(21, '/images/HaLong01.jpg'),
	(22, '/images/HaLong02.jpg'),
	(23, '/images/HaLong03.jpg');

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
  `dia_chi` varchar(250) DEFAULT NULL,
  `sdt` varchar(10) DEFAULT NULL,
  `so_nguoi_lon` int DEFAULT NULL,
  `so_tre_em` int DEFAULT NULL,
  `trang_thai` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mahd`),
  KEY `FKoc4j6h78n325ylvvdlx0enns9` (`id`),
  KEY `FKsoigwbsw1m2ge57fx5y3rlvu7` (`ma_tour`),
  CONSTRAINT `FKoc4j6h78n325ylvvdlx0enns9` FOREIGN KEY (`id`) REFERENCES `tai_khoan` (`id`),
  CONSTRAINT `FKsoigwbsw1m2ge57fx5y3rlvu7` FOREIGN KEY (`ma_tour`) REFERENCES `tour` (`ma_tour`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.hoa_don: ~7 rows (approximately)
INSERT INTO `hoa_don` (`mahd`, `id`, `ma_tour`, `ngaylap`, `tong_tien`, `dia_chi`, `sdt`, `so_nguoi_lon`, `so_tre_em`, `trang_thai`) VALUES
	('9213169313', 2, 'Tour08', '2024-06-24 14:13:16.874000', 10000.00, 'Số 01 , đường N17', '0357608667', 1, 3, NULL),
	('9322869166', 2, 'Tour08', '2024-06-25 20:42:37.479000', 30000.00, 'tp.HCM', '0357608667', 3, 1, NULL),
	('9323059170', 2, 'Tour08', '2024-06-25 20:45:25.651000', 50000.00, 'tp.HCM', '0357608667', 5, 2, NULL),
	('9365365359', 2, 'Tour08', '2024-06-26 08:31:13.652000', 20000.00, 'Bình Dương', '0123456789', 2, 2, NULL),
	('9725362671', 2, 'Tour07', '2024-06-30 12:30:04.135000', 10000000.00, 'tp.HCM', '0357608667', 1, 3, NULL),
	('9733170987', 3, 'Tour08', '2024-06-30 14:40:04.988000', 10000.00, 'Số 01 , đường N17', '0123456789', 1, 0, NULL),
	('9893615637', 2, 'Tour08', '2024-07-02 11:14:43.567000', 10000.00, 'Bình Dương', '0357608667', 1, 0, NULL);

-- Dumping structure for table db_dulichviet.huong_dan_vien
CREATE TABLE IF NOT EXISTS `huong_dan_vien` (
  `mahdv` varchar(255) NOT NULL,
  `tenhdv` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mahdv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.huong_dan_vien: ~1 rows (approximately)
INSERT INTO `huong_dan_vien` (`mahdv`, `tenhdv`) VALUES
	('HDV01', 'Đặng Dũng');

-- Dumping structure for table db_dulichviet.images
CREATE TABLE IF NOT EXISTS `images` (
  `ma_tour` varchar(10) NOT NULL,
  `id` int NOT NULL,
  PRIMARY KEY (`id`,`ma_tour`),
  KEY `FKkslp3uba1y56cxssuh34vi8eg` (`ma_tour`),
  CONSTRAINT `FK7o7yb7h87mky7053665l9ut00` FOREIGN KEY (`id`) REFERENCES `hinh_anh` (`id`),
  CONSTRAINT `FKkslp3uba1y56cxssuh34vi8eg` FOREIGN KEY (`ma_tour`) REFERENCES `tour` (`ma_tour`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.images: ~23 rows (approximately)
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
	('Tour04', 10),
	('Tour05', 13),
	('Tour05', 14),
	('Tour06', 15),
	('Tour06', 16),
	('Tour06', 17),
	('Tour06', 18),
	('Tour07', 19),
	('Tour07', 20),
	('Tour08', 21),
	('Tour08', 22),
	('Tour08', 23);

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
  `so_lan` bit(1) DEFAULT NULL,
  `ngay_bat_dau` date DEFAULT NULL,
  `ngay_ket_thuc` date DEFAULT NULL,
  `id_tai_khoan` int DEFAULT NULL,
  PRIMARY KEY (`makm`),
  KEY `FK2d9xsw350gcs8ixif0j1567ol` (`id_tai_khoan`),
  CONSTRAINT `FK2d9xsw350gcs8ixif0j1567ol` FOREIGN KEY (`id_tai_khoan`) REFERENCES `tai_khoan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.khuyen_mai: ~1 rows (approximately)
INSERT INTO `khuyen_mai` (`makm`, `phan_tramkm`, `tenkm`, `so_lan`, `ngay_bat_dau`, `ngay_ket_thuc`, `id_tai_khoan`) VALUES
	('KM25472906', 15, 'mới ', b'0', '2024-06-30', '2024-07-01', NULL),
	('KM96288628', 15, 'hi', b'0', '2024-07-02', '2024-07-03', NULL);

-- Dumping structure for table db_dulichviet.lich_trinh
CREATE TABLE IF NOT EXISTS `lich_trinh` (
  `id` int NOT NULL,
  `thong_tin` varchar(255) DEFAULT NULL,
  `ngaykh` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
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

-- Dumping data for table db_dulichviet.loai_tour: ~3 rows (approximately)
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

-- Dumping data for table db_dulichviet.phong: ~0 rows (approximately)
INSERT INTO `phong` (`ma_phong`, `gia`, `mo_ta`, `maks`, `ma_loai`) VALUES
	('P01', 1918000, '2 người - Sức chứa tối đa của phòng 2 - Số khách tiêu chuẩn 2 - Cho phép ở thêm người lớn 2 trẻ em thỏa mãn 2 khách tối đa có thể mất thêm phí - Chi tiết phí phụ thu vui lòng xem tại “Giá cuối cùng”', 'KS04', 'LP02');

-- Dumping structure for table db_dulichviet.phuong_tien
CREATE TABLE IF NOT EXISTS `phuong_tien` (
  `ma_phuong_tien` varchar(10) NOT NULL,
  `ten_phuong_tien` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ma_phuong_tien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.phuong_tien: ~3 rows (approximately)
INSERT INTO `phuong_tien` (`ma_phuong_tien`, `ten_phuong_tien`) VALUES
	('PT01', 'Máy bay'),
	('PT02', 'Ô tô'),
	('PT03', 'Xe Máy');

-- Dumping structure for table db_dulichviet.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(250) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.role: ~2 rows (approximately)
INSERT INTO `role` (`id`, `description`, `name`) VALUES
	(1, 'ADMIN', 'ADMIN'),
	(2, 'USER', 'USER');

-- Dumping structure for table db_dulichviet.taikhoan_role
CREATE TABLE IF NOT EXISTS `taikhoan_role` (
  `taikhoan_id` int NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`taikhoan_id`,`role_id`),
  KEY `FKdrro5o46wubuoefwc22wckllb` (`role_id`),
  CONSTRAINT `FKdrro5o46wubuoefwc22wckllb` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKna4mvw5e31wn8bswdtwhl7lg5` FOREIGN KEY (`taikhoan_id`) REFERENCES `tai_khoan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.taikhoan_role: ~3 rows (approximately)
INSERT INTO `taikhoan_role` (`taikhoan_id`, `role_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 2);

-- Dumping structure for table db_dulichviet.tai_khoan
CREATE TABLE IF NOT EXISTS `tai_khoan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pass_word` varchar(255) NOT NULL,
  `tentk` varchar(50) NOT NULL,
  `id_user` int DEFAULT NULL,
  `id_tai_khoan` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6gudvg8gq4g95faih9ol99aj4` (`tentk`),
  KEY `FKg9gtme1qx7benvoa9lrhef0ot` (`id_user`),
  CONSTRAINT `FKg9gtme1qx7benvoa9lrhef0ot` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.tai_khoan: ~3 rows (approximately)
INSERT INTO `tai_khoan` (`id`, `pass_word`, `tentk`, `id_user`, `id_tai_khoan`) VALUES
	(1, '$2a$10$Rx6fe7C9M/GIgg8zaH7hgOv7RtlJ23W6lAHadNMyVLQMwCetKj9ee', 'Hai', 1, 0),
	(2, '$2a$10$8EPHS4GAmV6ohvqjOOc9buHuckWOjq4rwIkN.PcUL8yuwQhbU1Pwm', 'vanhai11203@gmail.com', 2, 0),
	(3, '$2a$10$Rx6fe7C9M/GIgg8zaH7hgOv7RtlJ23W6lAHadNMyVLQMwCetKj9ee', 'hello', 3, 0);

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
	('T29', 'Đắk Nông'),
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
  `tac_gia` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.tin_tuc: ~3 rows (approximately)
INSERT INTO `tin_tuc` (`id`, `hinh_anh`, `noi_dung`, `title`, `tac_gia`) VALUES
	(1, 'thanhpho.jpg', 'Chỉ số Chi phí Sinh hoạt Toàn cầu thường niên do Cơ quan Tình báo Kinh tế (EIU), công ty nghiên cứu kinh tế thị trường thuộc tập đoàn Economist có trụ sở tại Anh, công bố danh sách 10 thành phố đắt đỏ nhất thế giới 2023.', '10 thành phố đắt đỏ nhất thế giới 2023', NULL),
	(2, 'Blog2.jpg', 'Khách sạn Meliá Paris Champs Elysées nằm ngay trên đại lộ Victor Hugo yên bình. Lưu trú tại khách sạn này, du khách thuận tiện di chuyển tới các địa điểm ở thủ đô Paris như Khải Hoàn Môn (1km), tháp Eiffel (2km), quảng trường Place Charles de Gaulle (1.3km). Bên cạnh đó, điểm lưu trú này còn nằm cạnh ga tàu điện ngầm Victor Hugo, gần tuyến đường sắt RER, xe buýt và dễ dàng tham gia một chuyến tàu du ngoạn – Bateau Mouche trên sông Seine thơ mộng.', 'Trải nghiệm không gian sống sang trọng và thanh lịch tại Meliá Paris Champs Elysées', NULL),
	(3, 'Blog3.jpg', 'Khu nghỉ dưỡng Six Senses Côn Đảo', 'Top 5 khách sạn – resort Côn Đảo có giá ưu đãi đặc biệt cho kỳ nghỉ Tết thăng hoa', NULL);

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
  `gia_tour` int DEFAULT NULL,
  `formatted_price` varchar(255) DEFAULT NULL,
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

-- Dumping data for table db_dulichviet.tour: ~8 rows (approximately)
INSERT INTO `tour` (`ma_tour`, `ngaykh`, `noi_khoi_hanh`, `soluong`, `makm`, `ma_loai_tour`, `ma_phong`, `ma_phuong_tien`, `ma_tinh`, `ten_tour`, `secondary_image_url`, `gia_tour`, `formatted_price`) VALUES
	('Tour01', '2024-06-14 00:00:00.000000', 'Phú Quốc', 40, NULL, 'LT03', 'P01', 'PT02', 'T14', 'Vinpearl Resort & Spa Phú Quốc', NULL, 3000000, NULL),
	('Tour02', '2024-06-16 11:39:35.000000', 'TP.HCM', 30, NULL, 'LT01', 'P01', 'PT01', 'T02', 'Tour Đà Nẵng 4N3Đ: HCM - Hội An - Quảng Bình - Huế', NULL, 2990000, NULL),
	('Tour03', '2024-06-16 12:11:04.000000', 'TP.HCM', 20, NULL, 'LT03', 'P01', 'PT02', 'T02', 'Tour Cao Cấp Phan Thiết 3N2Đ: HCM - Núi Tà Cú - Mango Beach - KDL Bàu Trắng - Suối Tiên', NULL, 4500000, NULL),
	('Tour04', '2024-06-16 12:20:12.000000', 'Huế', 25, NULL, 'LT03', 'P01', 'PT02', 'T33', 'Tour Miền Trung 3N3Đ: Đảo Kỳ Co - Gành Đá Đĩa - Đầm Ô Loan Xe Giường Nằm', NULL, 3300000, NULL),
	('Tour05', '2024-06-16 13:48:32.000000', 'Hà Nội', 10, NULL, 'LT03', 'P01', 'PT02', 'T01', 'Tour Yên Tử Trong Ngày: Hà Nội - Yên Tử - Chùa Đồng', NULL, 2600000, NULL),
	('Tour06', '2024-06-22 11:48:10.000000', 'TP.HCM', 35, NULL, 'LT03', 'P01', 'PT02', 'T06', 'Tour Nha Trang - Đà Lạt 4N3Đ: Khám Phá Thành Phố Hoa Biển và Xứ Sở Ngàn Hoa', NULL, 5300000, NULL),
	('Tour07', '2024-06-22 19:45:27.000000', 'Lâm Đồng', 50, NULL, 'LT02', 'P01', 'PT03', 'T06', 'QUẢNG BÌNH – HUẾ - ĐÀ NẴNG', NULL, 10000000, NULL),
	('Tour08', '2024-06-22 20:00:05.000000', 'Hà Nội', 9, NULL, 'LT03', 'P01', 'PT01', 'T48', 'Tour Ninh Bình - Hạ Long 2N1Đ: Hà Nội - Trải Nghiệm Di Sản và Thiên Nhiên Kỳ Vĩ', NULL, 10000, NULL);

-- Dumping structure for table db_dulichviet.user
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `dia_chi` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `ho_ten` varchar(255) DEFAULT NULL,
  `sdt` int DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.user: ~3 rows (approximately)
INSERT INTO `user` (`id_user`, `dia_chi`, `email`, `ho_ten`, `sdt`) VALUES
	(1, 'Thủ Đức', 'vanhai@gmail.com', 'Hà Văn Hải', 123456789),
	(2, NULL, 'vanhai11203@gmail.com', '7468-Hà Văn Hải', 0),
	(3, 'HCM', 'hehe@gmail.com', 'hehe', 123456788);

-- Dumping structure for table db_dulichviet.wishlist
CREATE TABLE IF NOT EXISTS `wishlist` (
  `wishlistid` int NOT NULL AUTO_INCREMENT,
  `id` int DEFAULT NULL,
  PRIMARY KEY (`wishlistid`),
  UNIQUE KEY `UKrudlbi2kgtv34bmqnnkdndp3d` (`id`),
  CONSTRAINT `FK48cwyo7spxwc6auc4bdck1ajv` FOREIGN KEY (`id`) REFERENCES `tai_khoan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_dulichviet.wishlist: ~1 rows (approximately)
INSERT INTO `wishlist` (`wishlistid`, `id`) VALUES
	(3, 1),
	(2, 2),
	(1, 3);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
