/*
 Navicat Premium Data Transfer

 Source Server         : linux
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : 192.168.232.128:3306
 Source Schema         : concretestatistics

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 22/09/2022 20:39:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for add_materials
-- ----------------------------
DROP TABLE IF EXISTS `add_materials`;
CREATE TABLE `add_materials`  (
  `add_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '附加材料名称',
  `add_unitprice` decimal(4, 2) NULL DEFAULT NULL COMMENT '附加材料单价',
  PRIMARY KEY (`add_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of add_materials
-- ----------------------------
INSERT INTO `add_materials` VALUES ('P10', 40.00);
INSERT INTO `add_materials` VALUES ('P12', 65.00);
INSERT INTO `add_materials` VALUES ('P6', 15.00);
INSERT INTO `add_materials` VALUES ('P8', 25.00);
INSERT INTO `add_materials` VALUES ('早强', 20.00);
INSERT INTO `add_materials` VALUES ('水下', 20.00);
INSERT INTO `add_materials` VALUES ('细石', 20.00);
INSERT INTO `add_materials` VALUES ('膨胀', 20.00);
INSERT INTO `add_materials` VALUES ('透水', 20.00);
INSERT INTO `add_materials` VALUES ('钢纤维', 40.00);
INSERT INTO `add_materials` VALUES ('防冻', 20.00);

-- ----------------------------
-- Table structure for business_detials
-- ----------------------------
DROP TABLE IF EXISTS `business_detials`;
CREATE TABLE `business_detials`  (
  `busid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录主键',
  `business_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户名称',
  `pouring_position` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浇筑部位',
  `pouring_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浇筑方式',
  `quantities` decimal(6, 2) NULL DEFAULT NULL COMMENT '工程量',
  `number_of_vehicles` int(11) NULL DEFAULT NULL COMMENT '车数',
  `strength_grade` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '强度等级',
  `unit_price_of_concrete` decimal(6, 2) NULL DEFAULT NULL COMMENT '砼单价',
  `freight` decimal(6, 2) NULL DEFAULT NULL COMMENT '运费',
  `total_amount` decimal(15, 2) NULL DEFAULT NULL COMMENT '总金额',
  `remarks` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  `business_date` datetime(0) NULL DEFAULT NULL COMMENT '业务日期',
  `pour_price` decimal(7, 2) NULL DEFAULT NULL COMMENT '浇筑单价',
  `strength_price` decimal(6, 2) NULL DEFAULT NULL COMMENT '强度单价',
  `isminflag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保底标记',
  PRIMARY KEY (`busid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of business_detials
-- ----------------------------

-- ----------------------------
-- Table structure for price_adjustment
-- ----------------------------
DROP TABLE IF EXISTS `price_adjustment`;
CREATE TABLE `price_adjustment`  (
  `pa_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '金额调价组合名称',
  `pa_price` decimal(4, 2) NULL DEFAULT NULL COMMENT '调价金额',
  `pa_date` datetime(0) NOT NULL COMMENT '金额调价日期',
  PRIMARY KEY (`pa_name`, `pa_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of price_adjustment
-- ----------------------------
INSERT INTO `price_adjustment` VALUES ('1', 1.00, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C15', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C15P6', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C15P6膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C15P8', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C15P8膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C15膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C20', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C20P6', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C20P6膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C20P8', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C20P8膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C20膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C25', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C25P6', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C25P6膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C25P8', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C25P8膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C25膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C30', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C30P6', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C30P6膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C30P8', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C30P8膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C30膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C35', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C35P6', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C35P6膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C35P8', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C35P8膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C35膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C40', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C40P6', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C40P6膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C40P8', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C40P8膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C40膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C45', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C45P6', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C45P6膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C45P8', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C45P8膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C45膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C50', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C50P6', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C50P6膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C50P8', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C50P8膨胀', -4.50, '2022-09-05 15:14:08');
INSERT INTO `price_adjustment` VALUES ('C50膨胀', -4.50, '2022-09-05 15:14:08');

-- ----------------------------
-- Table structure for strength_grade
-- ----------------------------
DROP TABLE IF EXISTS `strength_grade`;
CREATE TABLE `strength_grade`  (
  `strength_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '强度等级名称',
  `strength_unitprice` decimal(6, 2) NULL DEFAULT NULL COMMENT '强度等级单价',
  PRIMARY KEY (`strength_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of strength_grade
-- ----------------------------
INSERT INTO `strength_grade` VALUES ('C15', 250.00);
INSERT INTO `strength_grade` VALUES ('C20', 260.00);
INSERT INTO `strength_grade` VALUES ('C25', 270.00);
INSERT INTO `strength_grade` VALUES ('C30', 280.00);
INSERT INTO `strength_grade` VALUES ('C35', 300.00);
INSERT INTO `strength_grade` VALUES ('C40', 320.00);
INSERT INTO `strength_grade` VALUES ('C45', 350.00);
INSERT INTO `strength_grade` VALUES ('C50', 380.00);

-- ----------------------------
-- Table structure for type_of_shipping
-- ----------------------------
DROP TABLE IF EXISTS `type_of_shipping`;
CREATE TABLE `type_of_shipping`  (
  `ship_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '运输方式名称',
  `ship_unitprice` decimal(4, 2) NULL DEFAULT NULL COMMENT '运输方式单价',
  `ship_mini` int(11) NULL DEFAULT NULL COMMENT '运输方式保底',
  `ship_miniprice` decimal(6, 2) NULL DEFAULT NULL COMMENT '运输方式保底价格',
  PRIMARY KEY (`ship_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type_of_shipping
-- ----------------------------
INSERT INTO `type_of_shipping` VALUES ('40米臂架泵', 25.00, 80, 2000.00);
INSERT INTO `type_of_shipping` VALUES ('47米臂架泵', 25.00, 80, 2000.00);
INSERT INTO `type_of_shipping` VALUES ('52米臂架泵', 25.00, 80, 2000.00);
INSERT INTO `type_of_shipping` VALUES ('63米臂架泵', 35.00, 80, 2800.00);
INSERT INTO `type_of_shipping` VALUES ('塔吊', 10.00, 80, 10.00);
INSERT INTO `type_of_shipping` VALUES ('柴油泵', 25.00, 80, 2000.00);
INSERT INTO `type_of_shipping` VALUES ('汽车吊', 0.00, 80, 0.00);
INSERT INTO `type_of_shipping` VALUES ('自卸', 0.00, 80, 0.00);

SET FOREIGN_KEY_CHECKS = 1;
