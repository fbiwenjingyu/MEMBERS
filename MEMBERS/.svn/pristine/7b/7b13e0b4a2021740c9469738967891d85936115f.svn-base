/*
Navicat MySQL Data Transfer

Source Server         : mysql-distribution
Source Server Version : 50621
Source Host           : 10.172.239.152:3306
Source Database       : member

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-12-16 09:15:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for 051_user
-- ----------------------------
DROP TABLE IF EXISTS `051_user`;
CREATE TABLE `051_user` (
  `xxxx` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  UNIQUE KEY `sx` (`xxxx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for cc
-- ----------------------------
DROP TABLE IF EXISTS `cc`;
CREATE TABLE `cc` (
  `ID` int(11) NOT NULL DEFAULT '0' COMMENT '主键，用户ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for M_BUSINESS_CENTER
-- ----------------------------
DROP TABLE IF EXISTS `M_BUSINESS_CENTER`;
CREATE TABLE `M_BUSINESS_CENTER` (
  `ID` int(12) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `BUSINESS_NO` varchar(30) NOT NULL COMMENT '商务中心编号',
  `ACCOUNT_NO` varchar(20) NOT NULL COMMENT '会员账号',
  `PHONE` varchar(20) NOT NULL COMMENT '电话',
  `REAL_NAME` varchar(20) DEFAULT NULL COMMENT '姓名',
  `OPEN_STATE` char(1) NOT NULL COMMENT '开通状态，0待开通，1已开通, 2已拒绝',
  `ACTIVE_STATE` char(1) NOT NULL COMMENT '存活状态，0未激活，1已激活，2已冻结',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime NOT NULL COMMENT '更新时间',
  `OPERATER` varchar(20) DEFAULT NULL COMMENT '操作人',
  `REMARK` varchar(100) DEFAULT NULL COMMENT '备注',
  `VERSION` int(11) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11566 DEFAULT CHARSET=utf8 COMMENT='商务中心表';

-- ----------------------------
-- Table structure for M_CURRENCY_CONVERT
-- ----------------------------
DROP TABLE IF EXISTS `M_CURRENCY_CONVERT`;
CREATE TABLE `M_CURRENCY_CONVERT` (
  `ID` int(12) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `SERIAL_NO` varchar(30) NOT NULL COMMENT '流水号',
  `USER_ID` int(11) NOT NULL COMMENT '用户ID',
  `ACCOUNT_NO` varchar(20) NOT NULL COMMENT '会员账号',
  `CONVERT_TYPE` char(1) NOT NULL COMMENT '转换类型，1：[博豆] 转 [积分] 2:现金币转博豆',
  `CONVERT_AMOUNT` int(16) DEFAULT NULL COMMENT '转换数值',
  `GET_AMOUNT` int(16) DEFAULT NULL COMMENT '获得数值',
  `FEE` varchar(8) DEFAULT NULL COMMENT '转换比例',
  `STATE` char(1) NOT NULL COMMENT '状态，1：成功，0:失败',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `REMARK` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=493 DEFAULT CHARSET=utf8 COMMENT='货币转换记录';

-- ----------------------------
-- Table structure for M_DAY_POINT_DETAIL
-- ----------------------------
DROP TABLE IF EXISTS `M_DAY_POINT_DETAIL`;
CREATE TABLE `M_DAY_POINT_DETAIL` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `COLLECT_DATE` date DEFAULT NULL COMMENT '统计日期',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `REWARD_AMOUNT` decimal(12,2) DEFAULT NULL COMMENT '直推奖励',
  `BEANS_NUM` int(11) DEFAULT NULL COMMENT '购物积分（博豆）',
  `POINT_AMOUNT` decimal(12,2) DEFAULT NULL COMMENT '推广积分',
  `MANAGE_AMOUNT` decimal(12,2) DEFAULT NULL COMMENT '管理奖励',
  `COACH_AMOUNT` decimal(12,2) DEFAULT NULL COMMENT '辅导奖励',
  `STATUS` int(8) DEFAULT NULL COMMENT '状态 1：汇总日积分；2：计算出会员直推的会员类型；3：计算管理奖励；4：计算辅导奖',
  `VERSION` int(11) DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `inx_day_point_detail` (`COLLECT_DATE`,`USER_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=552069 DEFAULT CHARSET=utf8 COMMENT='会员推送积分（日）';

-- ----------------------------
-- Table structure for M_DRAW
-- ----------------------------
DROP TABLE IF EXISTS `M_DRAW`;
CREATE TABLE `M_DRAW` (
  `ID` int(12) NOT NULL AUTO_INCREMENT COMMENT '递增主键',
  `VERSION` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `SERIAL_NO` varchar(20) NOT NULL COMMENT '流水号',
  `USER_ID` int(11) NOT NULL COMMENT '用户ID',
  `MEMBER_NO` varchar(30) NOT NULL COMMENT '会员编号',
  `CARD_OWNER_NAME` varchar(10) NOT NULL COMMENT '持卡人姓名',
  `BANK_NAME` varchar(20) NOT NULL COMMENT '所属行名称',
  `OPEN_BANK_CITY` varchar(20) NOT NULL COMMENT '开户行城市',
  `CARD_NO` varchar(40) NOT NULL COMMENT '卡号',
  `AMOUNT` int(11) NOT NULL COMMENT '提现金额',
  `FEE` int(11) NOT NULL COMMENT '提现手续费',
  `ARRIVAL_AMOUNT` int(11) NOT NULL COMMENT '到账金额',
  `JF` int(11) NOT NULL COMMENT '积分',
  `STATUS` int(11) NOT NULL COMMENT '提现状态：0待审核 ，1审核不通过， 2审核成功或待提现， 3已提现， 4提现失败， 5提现成功',
  `MEMO` varchar(100) DEFAULT NULL COMMENT '提现备注',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime NOT NULL COMMENT '更新时间',
  `OPERATOR` varchar(30) DEFAULT NULL COMMENT '操作员',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SERIAL_NO` (`SERIAL_NO`),
  KEY `idx_query` (`MEMBER_NO`,`CREATE_TIME`)
) ENGINE=InnoDB AUTO_INCREMENT=281 DEFAULT CHARSET=utf8 COMMENT='提现';

-- ----------------------------
-- Table structure for M_EXCHANGE_INFO
-- ----------------------------
DROP TABLE IF EXISTS `M_EXCHANGE_INFO`;
CREATE TABLE `M_EXCHANGE_INFO` (
  `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_ID` int(8) NOT NULL COMMENT '用户ID',
  `APPLY_TIME` datetime DEFAULT NULL COMMENT '申请时间',
  `EXCHANGE_NUM` int(8) DEFAULT NULL COMMENT '兑换数量',
  `RECEPIT_PHONE` varchar(11) NOT NULL COMMENT '收货人电话',
  `RECEPIT_USER_NAME` varchar(20) NOT NULL COMMENT '收货人姓名',
  `PROVINCE` varchar(10) DEFAULT NULL COMMENT '省份',
  `CITY` varchar(10) DEFAULT NULL COMMENT '市',
  `AREA` varchar(20) DEFAULT NULL COMMENT '所属地区',
  `FULL_ADDRESS` varchar(100) DEFAULT NULL COMMENT '详细地址',
  `STATE` tinyint(1) NOT NULL COMMENT '状态 1：兑换申请  2：已发货  3： 取消发货',
  `SEND_TIME` datetime DEFAULT NULL COMMENT '发货时间',
  `SEND_USER` varchar(10) DEFAULT NULL COMMENT '发货人',
  `EXPRESS_COMPANY` varchar(10) DEFAULT NULL COMMENT '快递公司',
  `EXPRESS_ORDER` varchar(30) DEFAULT NULL COMMENT '快递单号',
  `VERSION` int(10) NOT NULL DEFAULT '0' COMMENT '版本号',
  `OVERDRAFT_BEANS` int(10) DEFAULT NULL COMMENT '消费透支的博豆',
  `BEANS` int(10) DEFAULT NULL COMMENT '消费的博豆',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='云碟兑换信息表';

-- ----------------------------
-- Table structure for M_NOTICE
-- ----------------------------
DROP TABLE IF EXISTS `M_NOTICE`;
CREATE TABLE `M_NOTICE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '递增主键',
  `TITLE` varchar(200) NOT NULL COMMENT '公告标题',
  `DEPLOY_BY` varchar(20) DEFAULT NULL COMMENT '发布人',
  `DEPLOY_TIME` datetime NOT NULL COMMENT '发布日期',
  `TOP_TIME` datetime NOT NULL COMMENT '置顶时间',
  `IS_DELETE` int(1) DEFAULT '0' COMMENT '0：未删除 1：已删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='公告';

-- ----------------------------
-- Table structure for M_NOTICE_CONTENT
-- ----------------------------
DROP TABLE IF EXISTS `M_NOTICE_CONTENT`;
CREATE TABLE `M_NOTICE_CONTENT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '递增主键',
  `NOTICE_ID` int(11) NOT NULL,
  `CONTENT` text,
  PRIMARY KEY (`ID`),
  KEY `idx_query` (`NOTICE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='公告内容';

-- ----------------------------
-- Table structure for M_OPINION
-- ----------------------------
DROP TABLE IF EXISTS `M_OPINION`;
CREATE TABLE `M_OPINION` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `RESPONSE_PHONE` varchar(11) NOT NULL COMMENT '反馈者手机',
  `TITLE` varchar(200) NOT NULL COMMENT '反馈标题',
  `COMTENT` varchar(2000) NOT NULL COMMENT '反馈内容',
  `TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '反馈时间',
  `IS_REPLY` int(1) NOT NULL DEFAULT '0' COMMENT '是否有回答（0是没有，1是有）',
  `CREATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `IS_DELETED` int(1) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='反馈表';

-- ----------------------------
-- Table structure for M_POINT_TASK
-- ----------------------------
DROP TABLE IF EXISTS `M_POINT_TASK`;
CREATE TABLE `M_POINT_TASK` (
  `TASK_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '任务创建时间',
  `CURR_EXEC_DATE` date DEFAULT NULL COMMENT '任务需要执行的时间',
  `LAST_EXEC_DATE` date DEFAULT NULL COMMENT '任务实际执行时间',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `POINT_AMOUNT` int(11) DEFAULT NULL COMMENT '积分金额',
  `EXEC_STATUS` int(11) DEFAULT NULL COMMENT '执行状态 1：正常;2:暂停',
  `TOTAL_DAYS` int(11) DEFAULT NULL COMMENT '需要执行的天数',
  `EXEC_DAYS` int(11) DEFAULT NULL COMMENT '已执行的天数',
  `VERSION` int(11) DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`TASK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12268 DEFAULT CHARSET=utf8 COMMENT='赠送会员博豆，购物积分任务';

-- ----------------------------
-- Table structure for M_POINT_TASK_DETAIL
-- ----------------------------
DROP TABLE IF EXISTS `M_POINT_TASK_DETAIL`;
CREATE TABLE `M_POINT_TASK_DETAIL` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `TASK_ID` int(11) DEFAULT NULL COMMENT '任务ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '任务创建时间',
  `COLLECT_DATE` date DEFAULT NULL COMMENT '统计的日期',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `POINT_AMOUNT` int(11) DEFAULT NULL COMMENT '积分金额',
  `EXEC_STATUS` int(11) DEFAULT NULL COMMENT '执行状态 1：未执行;2:已执行；3：发送异常',
  `VERSION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `EXEC_TIME` datetime DEFAULT NULL COMMENT '执行时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `inx_pk` (`COLLECT_DATE`,`USER_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45431 DEFAULT CHARSET=utf8 COMMENT='赠送推广积分明细';

-- ----------------------------
-- Table structure for M_RECHARGE
-- ----------------------------
DROP TABLE IF EXISTS `M_RECHARGE`;
CREATE TABLE `M_RECHARGE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '递增主键',
  `VERSION` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `SERIAL_NO` varchar(30) NOT NULL COMMENT '流水号',
  `USER_ID` int(11) NOT NULL COMMENT '用户ID',
  `MEMBER_NO` varchar(30) NOT NULL COMMENT '会员编号',
  `AMOUNT` int(11) NOT NULL COMMENT '金额',
  `ACCOUNT_TYPE` int(11) NOT NULL COMMENT '账户类型',
  `OUT_ACCOUNT` varchar(150) DEFAULT NULL COMMENT '外部账号',
  `WAY` int(11) NOT NULL COMMENT '充值方式：0后台充值，1支付宝充值，2微信充值',
  `FORWARD` tinyint(4) NOT NULL COMMENT '金额方向：0新增，1减少',
  `STATUS` tinyint(4) NOT NULL COMMENT '充值状态：0充值中，1充值失败，2充值成功',
  `MEMO` varchar(100) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime NOT NULL COMMENT '更新时间',
  `OPERATOR` varchar(30) DEFAULT NULL COMMENT '操作员',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SERIAL_NO` (`SERIAL_NO`),
  KEY `idx_query` (`MEMBER_NO`,`CREATE_TIME`)
) ENGINE=InnoDB AUTO_INCREMENT=807 DEFAULT CHARSET=utf8 COMMENT='充值';

-- ----------------------------
-- Table structure for M_REPLY
-- ----------------------------
DROP TABLE IF EXISTS `M_REPLY`;
CREATE TABLE `M_REPLY` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `RESPONSE_ID` int(11) NOT NULL COMMENT '反馈id',
  `REPLY_COMTENT` varchar(500) NOT NULL COMMENT '回复内容',
  `REPLY_ID` int(11) NOT NULL COMMENT '回复人id',
  `REPLY_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '回复时间',
  `IS_READ` int(11) NOT NULL DEFAULT '0' COMMENT '此回复是否阅读0为未读，1为已读',
  `IS_SYSTEM` int(11) NOT NULL DEFAULT '1' COMMENT '1是系统管理员0是会员',
  `CREATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `IS_DELETED` int(11) NOT NULL COMMENT '是否删除（0是未删除，1是已经删除）',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for M_REWARD_DETAIL
-- ----------------------------
DROP TABLE IF EXISTS `M_REWARD_DETAIL`;
CREATE TABLE `M_REWARD_DETAIL` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `COLLECT_DATE` date DEFAULT NULL COMMENT '统计日期',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `FROM_USER_ID` int(11) DEFAULT NULL COMMENT '奖励来源对应的用户ID',
  `REWARD_TYPE` int(11) DEFAULT NULL COMMENT '奖励类型',
  `REWARD_AMOUNT` decimal(12,2) DEFAULT NULL COMMENT '奖励金额',
  `VERSION` int(11) DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1136082 DEFAULT CHARSET=utf8 COMMENT='会员奖励明细';

-- ----------------------------
-- Table structure for M_STAR_NODE
-- ----------------------------
DROP TABLE IF EXISTS `M_STAR_NODE`;
CREATE TABLE `M_STAR_NODE` (
  `USER_ID` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `PARENT_ID` int(11) DEFAULT NULL COMMENT '父节点,推荐人',
  `PARENT_STR` varchar(1000) DEFAULT NULL COMMENT '上层节点IDS,最多存十代',
  `RECOMMEND_NUMS` int(10) DEFAULT NULL COMMENT '直推人数',
  `FLOORS` int(11) DEFAULT NULL COMMENT '层数',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `VERSION` int(11) DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员层级关系';

-- ----------------------------
-- Table structure for M_STAR_NODE_copy
-- ----------------------------
DROP TABLE IF EXISTS `M_STAR_NODE_copy`;
CREATE TABLE `M_STAR_NODE_copy` (
  `USER_ID` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `PARENT_ID` int(11) DEFAULT NULL COMMENT '父节点,推荐人',
  `PARENT_STR` varchar(1000) DEFAULT NULL COMMENT '上层节点IDS,最多存十代',
  `RECOMMEND_NUMS` int(10) DEFAULT NULL COMMENT '直推人数',
  `FLOORS` int(11) DEFAULT NULL COMMENT '层数',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `VERSION` int(11) DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员层级关系';

-- ----------------------------
-- Table structure for M_STAR_NODE_copy_1210
-- ----------------------------
DROP TABLE IF EXISTS `M_STAR_NODE_copy_1210`;
CREATE TABLE `M_STAR_NODE_copy_1210` (
  `USER_ID` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `PARENT_ID` int(11) DEFAULT NULL COMMENT '父节点,推荐人',
  `PARENT_STR` varchar(1000) DEFAULT NULL COMMENT '上层节点IDS,最多存十代',
  `RECOMMEND_NUMS` int(10) DEFAULT NULL COMMENT '直推人数',
  `FLOORS` int(11) DEFAULT NULL COMMENT '层数',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `VERSION` int(11) DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员层级关系';

-- ----------------------------
-- Table structure for M_TASK_MONITOR
-- ----------------------------
DROP TABLE IF EXISTS `M_TASK_MONITOR`;
CREATE TABLE `M_TASK_MONITOR` (
  `ID` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `COLLECT_DATE` date NOT NULL COMMENT '统计日期',
  `TASK_TYPE` tinyint(1) NOT NULL COMMENT '任务类型',
  `TASK_NAME` varchar(30) NOT NULL COMMENT '任务名称',
  `RUN_DATE` datetime NOT NULL COMMENT '执行日期',
  `RUN_STATE` tinyint(1) NOT NULL COMMENT '运行状态 0:初始化;1:执行中 2：执行成功 3：执行失败',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `inx_monitor` (`COLLECT_DATE`,`TASK_TYPE`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for M_TRANSFER_DETAIL
-- ----------------------------
DROP TABLE IF EXISTS `M_TRANSFER_DETAIL`;
CREATE TABLE `M_TRANSFER_DETAIL` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `SERIAL_NO` varchar(30) DEFAULT NULL COMMENT '交易号',
  `TRANSFER_ID` int(11) NOT NULL COMMENT '转账人ID',
  `TRANSFER_NAME` varchar(20) DEFAULT NULL COMMENT '转账人姓名',
  `TRANSFER_PHONE` varchar(20) DEFAULT NULL COMMENT '转账人电话',
  `TRANSFER_AMOUNT` int(16) NOT NULL COMMENT '转账金额，单位分',
  `TRANSFER_TYPE` int(1) NOT NULL COMMENT '转账类型,1:平台账户转账(2:转账到银行卡)',
  `TYPE` int(1) NOT NULL COMMENT '转账货币类型(1:现金;2:博豆;3:积分)',
  `TRANSFER_STATE` int(1) NOT NULL COMMENT '转账状态,1：成功，2：失败',
  `RECEIVE_ID` int(11) NOT NULL COMMENT '收款人ID',
  `RECEIVE_PHONE` varchar(20) DEFAULT NULL COMMENT '收款人电话',
  `RECEIVE_NAME` varchar(20) DEFAULT NULL COMMENT '收款人姓名',
  `BANK_NAME` varchar(20) DEFAULT NULL COMMENT '收款人开户行',
  `OPEN_CITY` varchar(20) DEFAULT NULL COMMENT '收款人开户城市',
  `CARD_NAME` varchar(20) DEFAULT NULL COMMENT '持卡人姓名',
  `CARD_NO` varchar(40) DEFAULT NULL COMMENT '银行卡号',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `ARRIVAL_TIME` datetime NOT NULL COMMENT '到账时间',
  `IS_DELETED` int(4) NOT NULL DEFAULT '0' COMMENT '是否删除，0否，1是',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2830 DEFAULT CHARSET=utf8 COMMENT='转账记录表';

-- ----------------------------
-- Table structure for M_USER_ACCOUNT
-- ----------------------------
DROP TABLE IF EXISTS `M_USER_ACCOUNT`;
CREATE TABLE `M_USER_ACCOUNT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '账号ID',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `ACCOUNT_TYPE` int(11) DEFAULT NULL COMMENT '账号类型 1:现金币;2:博豆(购物积分);3:积分(推广积分)',
  `ACCOUNT_AMOUNT` int(16) DEFAULT NULL COMMENT '账户余额，单位分',
  `ACCOUNT_STATUS` int(11) DEFAULT NULL COMMENT '账号状态 1 : 正常;2：冻结',
  `VERSION` int(12) DEFAULT NULL COMMENT '版本信息',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '开户时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `PK_ACCOUNT_USER` (`ACCOUNT_TYPE`,`USER_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=47617 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for M_USER_ACCOUNT_INOUT
-- ----------------------------
DROP TABLE IF EXISTS `M_USER_ACCOUNT_INOUT`;
CREATE TABLE `M_USER_ACCOUNT_INOUT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户信息',
  `ACCOUNT_TYPE` int(11) DEFAULT NULL COMMENT '账号类型 1:现金币;2:博豆(购物积分);3:积分(推广积分)',
  `INOUT_TYPE` int(11) DEFAULT NULL COMMENT '账户变动类型   101:直推用户现金奖励;102:管理现金奖励;103:辅导奖励;110:账户充值;111:提现冲正;112:现金账户转入;113:韩流馆现金冲正;120:用户现金提现;121:现金账户余额消费;122:现金账户转出;123:现金转积分(出);124:现金激活用户;125:韩流馆现金账户余额消费;201:奖励博豆;210:博豆充值;213:韩流馆博豆冲正;222:博豆转积分(出);224:博豆激活用户;225:韩流馆博豆账户余额消费;301:奖励推荐积分;310:充积分;312:博豆转积分(入);313:现金转积分(入);314:推广积分转入;324:积分激活用户;325:推广积分转出;',
  `AMOUNT` int(16) DEFAULT NULL COMMENT '变动金额，现金收益 以分为单位',
  `TRADE_NO` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '交易流水',
  `INOUT_DESC` varchar(255) DEFAULT NULL COMMENT '账户变动描述',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `OPERATOR_NO` varchar(40) DEFAULT NULL COMMENT '操作员',
  `USER_TYPE` int(11) DEFAULT NULL COMMENT '用户类型 0：会员系统；11：韩流馆;22 : 笨笨商城',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4121864 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for M_USER_ACCOUNT_INOUT_HLG
-- ----------------------------
DROP TABLE IF EXISTS `M_USER_ACCOUNT_INOUT_HLG`;
CREATE TABLE `M_USER_ACCOUNT_INOUT_HLG` (
  `PK_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID` int(11) NOT NULL COMMENT '主键',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户信息',
  `ACCOUNT_TYPE` int(11) DEFAULT NULL COMMENT '账号类型 1:现金币;2:博豆(购物积分);3:积分(推广积分)',
  `INOUT_TYPE` int(11) DEFAULT NULL COMMENT '账户变动类型   101:直推用户现金奖励;102:管理现金奖励;103:辅导奖励;110:账户充值;111:提现冲正;112:现金账户转入;113:韩流馆现金冲正;120:用户现金提现;121:现金账户余额消费;122:现金账户转出;123:现金转积分(出);124:现金激活用户;125:韩流馆现金账户余额消费;201:奖励博豆;210:博豆充值;213:韩流馆博豆冲正;222:博豆转积分(出);224:博豆激活用户;225:韩流馆博豆账户余额消费;301:奖励推荐积分;310:充积分;312:博豆转积分(入);313:现金转积分(入);314:推广积分转入;324:积分激活用户;325:推广积分转出;',
  `AMOUNT` int(16) DEFAULT NULL COMMENT '变动金额，现金收益 以分为单位',
  `TRADE_NO` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '交易流水',
  `INOUT_DESC` varchar(255) DEFAULT NULL COMMENT '账户变动描述',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `OPERATOR_NO` varchar(40) DEFAULT NULL COMMENT '操作员',
  `USER_TYPE` int(11) DEFAULT NULL COMMENT '用户类型 0：会员系统；11：韩流馆;22 : 笨笨商城',
  `EXEC_FLAG` int(11) DEFAULT NULL COMMENT '对账标记（1: 未对账;2：数据一致；3：异常）',
  PRIMARY KEY (`PK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for M_USER_INFO
-- ----------------------------
DROP TABLE IF EXISTS `M_USER_INFO`;
CREATE TABLE `M_USER_INFO` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，用户ID',
  `ACCOUNT_NO` varchar(20) NOT NULL COMMENT '登录账号',
  `PHONE` varchar(20) NOT NULL COMMENT '联系电话',
  `PASSWORD` varchar(64) NOT NULL COMMENT '登录密码',
  `PAY_PASSWORD` varchar(64) DEFAULT NULL COMMENT '支付密码',
  `REAL_NAME` varchar(12) DEFAULT NULL COMMENT '真实姓名',
  `TYPE` int(1) DEFAULT '0' COMMENT '会员类型(-1冻结的会员 0未激活 1激活的普通 2 冻结的服务中心  3激活的服务中心)',
  `LEVEL` int(1) DEFAULT NULL COMMENT '会员级别(0 普通 1 铜卡 2银卡 3金卡)',
  `AGENT_ID` int(11) DEFAULT NULL COMMENT '注册人',
  `RECOMMEND_ID` int(11) DEFAULT NULL COMMENT '推荐人',
  `MY_MONEY` int(11) DEFAULT '0' COMMENT '个人业绩',
  `TEAM_MONEY` int(11) DEFAULT '0' COMMENT '团队业绩',
  `REGISTER_MONEY` int(6) DEFAULT NULL COMMENT '注册金额（元）',
  `PROVINCE` varchar(10) DEFAULT NULL COMMENT '省份',
  `CITY` varchar(10) DEFAULT NULL COMMENT '市',
  `AREA` varchar(10) DEFAULT NULL COMMENT '区域',
  `FULL_ADDRESS` varchar(50) DEFAULT NULL COMMENT '详细地址',
  `BANK_NAME` varchar(20) DEFAULT NULL COMMENT '开户行',
  `SUBBRANCH_BANK` varchar(40) DEFAULT NULL COMMENT '开户支行',
  `CARD_NAME` varchar(20) DEFAULT NULL COMMENT '持卡人姓名',
  `CARD_NO` varchar(40) DEFAULT NULL COMMENT '银行卡号',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `ACTIVATE_TIME` datetime DEFAULT NULL COMMENT '激活时间',
  `IS_OPEND` tinyint(4) DEFAULT NULL COMMENT '1:空单 2:实单',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `IS_DELETED` char(1) DEFAULT '0' COMMENT '0：未删除  1：已删除',
  `COPPER_CARD_NUM` int(11) DEFAULT NULL COMMENT '直推会员铜卡数量',
  `SILVER_CARD_NUM` int(11) DEFAULT NULL COMMENT '直推会员银卡数量',
  `AWARD_FLAG` int(4) DEFAULT NULL COMMENT '奖励处理标记(0:未处理；1：管理奖励处理完成；2：辅导奖励)',
  `ACTIVATE_BY` varchar(40) DEFAULT NULL COMMENT '激活人',
  `TOTAL_NUM` int(11) DEFAULT NULL COMMENT '用户总数',
  `MASTER_USER_ID` int(11) DEFAULT NULL COMMENT '主账号',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `inx_accountno` (`ACCOUNT_NO`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13213 DEFAULT CHARSET=utf8 COMMENT='会员信息表';

-- ----------------------------
-- Table structure for M_USER_INFO_copy
-- ----------------------------
DROP TABLE IF EXISTS `M_USER_INFO_copy`;
CREATE TABLE `M_USER_INFO_copy` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，用户ID',
  `ACCOUNT_NO` varchar(20) NOT NULL COMMENT '登录账号',
  `PHONE` varchar(20) NOT NULL COMMENT '联系电话',
  `PASSWORD` varchar(64) NOT NULL COMMENT '登录密码',
  `PAY_PASSWORD` varchar(64) DEFAULT NULL COMMENT '支付密码',
  `REAL_NAME` varchar(12) DEFAULT NULL COMMENT '真实姓名',
  `TYPE` int(1) DEFAULT '0' COMMENT '会员类型(-1冻结的会员 0未激活 1激活的普通 2 冻结的服务中心  3激活的服务中心)',
  `LEVEL` int(1) DEFAULT NULL COMMENT '会员级别(0 普通 1 铜卡 2银卡 3金卡)',
  `AGENT_ID` int(11) DEFAULT NULL COMMENT '注册人',
  `RECOMMEND_ID` int(11) DEFAULT NULL COMMENT '推荐人',
  `MY_MONEY` int(11) DEFAULT '0' COMMENT '个人业绩',
  `TEAM_MONEY` int(11) DEFAULT '0' COMMENT '团队业绩',
  `REGISTER_MONEY` int(6) DEFAULT NULL COMMENT '注册金额（元）',
  `PROVINCE` varchar(10) DEFAULT NULL COMMENT '省份',
  `CITY` varchar(10) DEFAULT NULL COMMENT '市',
  `AREA` varchar(10) DEFAULT NULL COMMENT '区域',
  `FULL_ADDRESS` varchar(50) DEFAULT NULL COMMENT '详细地址',
  `BANK_NAME` varchar(20) DEFAULT NULL COMMENT '开户行',
  `SUBBRANCH_BANK` varchar(40) DEFAULT NULL COMMENT '开户支行',
  `CARD_NAME` varchar(20) DEFAULT NULL COMMENT '持卡人姓名',
  `CARD_NO` varchar(40) DEFAULT NULL COMMENT '银行卡号',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `ACTIVATE_TIME` datetime DEFAULT NULL COMMENT '激活时间',
  `IS_OPEND` tinyint(4) DEFAULT NULL COMMENT '1:空单 2:实单',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `IS_DELETED` char(1) DEFAULT '0' COMMENT '0：未删除  1：已删除',
  `COPPER_CARD_NUM` int(11) DEFAULT NULL COMMENT '直推会员铜卡数量',
  `SILVER_CARD_NUM` int(11) DEFAULT NULL COMMENT '直推会员银卡数量',
  `AWARD_FLAG` int(4) DEFAULT NULL COMMENT '奖励处理标记(0:未处理；1：管理奖励处理完成；2：辅导奖励)',
  `ACTIVATE_BY` varchar(40) DEFAULT NULL COMMENT '激活人',
  `TOTAL_NUM` int(11) DEFAULT NULL COMMENT '用户总数',
  `MASTER_USER_ID` int(11) DEFAULT NULL COMMENT '主账号',
  PRIMARY KEY (`ID`),
  KEY `inx_accountno` (`ACCOUNT_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=12221 DEFAULT CHARSET=utf8 COMMENT='会员信息表';

-- ----------------------------
-- Table structure for M_USER_INFO_copy_1208
-- ----------------------------
DROP TABLE IF EXISTS `M_USER_INFO_copy_1208`;
CREATE TABLE `M_USER_INFO_copy_1208` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，用户ID',
  `ACCOUNT_NO` varchar(20) NOT NULL COMMENT '登录账号',
  `PHONE` varchar(20) NOT NULL COMMENT '联系电话',
  `PASSWORD` varchar(64) NOT NULL COMMENT '登录密码',
  `PAY_PASSWORD` varchar(64) DEFAULT NULL COMMENT '支付密码',
  `REAL_NAME` varchar(12) DEFAULT NULL COMMENT '真实姓名',
  `TYPE` int(1) DEFAULT '0' COMMENT '会员类型(-1冻结的会员 0未激活 1激活的普通 2 冻结的服务中心  3激活的服务中心)',
  `LEVEL` int(1) DEFAULT NULL COMMENT '会员级别(0 普通 1 铜卡 2银卡 3金卡)',
  `AGENT_ID` int(11) DEFAULT NULL COMMENT '注册人',
  `RECOMMEND_ID` int(11) DEFAULT NULL COMMENT '推荐人',
  `MY_MONEY` int(11) DEFAULT '0' COMMENT '个人业绩',
  `TEAM_MONEY` int(11) DEFAULT '0' COMMENT '团队业绩',
  `REGISTER_MONEY` int(6) DEFAULT NULL COMMENT '注册金额（元）',
  `PROVINCE` varchar(10) DEFAULT NULL COMMENT '省份',
  `CITY` varchar(10) DEFAULT NULL COMMENT '市',
  `AREA` varchar(10) DEFAULT NULL COMMENT '区域',
  `FULL_ADDRESS` varchar(50) DEFAULT NULL COMMENT '详细地址',
  `BANK_NAME` varchar(20) DEFAULT NULL COMMENT '开户行',
  `SUBBRANCH_BANK` varchar(40) DEFAULT NULL COMMENT '开户支行',
  `CARD_NAME` varchar(20) DEFAULT NULL COMMENT '持卡人姓名',
  `CARD_NO` varchar(40) DEFAULT NULL COMMENT '银行卡号',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `ACTIVATE_TIME` datetime DEFAULT NULL COMMENT '激活时间',
  `IS_OPEND` tinyint(4) DEFAULT NULL COMMENT '1:空单 2:实单',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `IS_DELETED` char(1) DEFAULT '0' COMMENT '0：未删除  1：已删除',
  `COPPER_CARD_NUM` int(11) DEFAULT NULL COMMENT '直推会员铜卡数量',
  `SILVER_CARD_NUM` int(11) DEFAULT NULL COMMENT '直推会员银卡数量',
  `AWARD_FLAG` int(4) DEFAULT NULL COMMENT '奖励处理标记(0:未处理；1：管理奖励处理完成；2：辅导奖励)',
  `ACTIVATE_BY` varchar(40) DEFAULT NULL COMMENT '激活人',
  `TOTAL_NUM` int(11) DEFAULT NULL COMMENT '用户总数',
  `MASTER_USER_ID` int(11) DEFAULT NULL COMMENT '主账号',
  PRIMARY KEY (`ID`),
  KEY `inx_accountno` (`ACCOUNT_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=11756 DEFAULT CHARSET=utf8 COMMENT='会员信息表';

-- ----------------------------
-- Table structure for M_USER_INFO_TEST
-- ----------------------------
DROP TABLE IF EXISTS `M_USER_INFO_TEST`;
CREATE TABLE `M_USER_INFO_TEST` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，用户ID',
  `ACCOUNT_NO` varchar(20) NOT NULL COMMENT '登录账号',
  `PHONE` varchar(20) NOT NULL COMMENT '联系电话',
  `PASSWORD` varchar(64) NOT NULL COMMENT '登录密码',
  `PAY_PASSWORD` varchar(64) DEFAULT NULL COMMENT '支付密码',
  `REAL_NAME` varchar(12) DEFAULT NULL COMMENT '真实姓名',
  `TYPE` tinyint(1) DEFAULT '0' COMMENT '会员类型(-1冻结的会员 0未激活 1激活的普通 2 冻结的服务中心  3激活的服务中心)',
  `LEVEL` tinyint(1) DEFAULT NULL COMMENT '会员级别(0 普通 1 铜卡 2银卡 3金卡)',
  `PARENT_ID` int(11) DEFAULT NULL COMMENT '注册人',
  `RECOMMEND_ID` int(11) DEFAULT NULL COMMENT '推荐人',
  `REGISTER_MONEY` int(6) DEFAULT NULL COMMENT '注册金额（元）',
  `PROVINCE` varchar(10) DEFAULT NULL COMMENT '省份',
  `CITY` varchar(10) DEFAULT NULL COMMENT '市',
  `AREA` varchar(10) DEFAULT NULL COMMENT '区域',
  `FULL_ADDRESS` varchar(50) DEFAULT NULL COMMENT '详细地址',
  `BANK_NAME` varchar(20) DEFAULT NULL COMMENT '开户行',
  `SUBBRANCH_BANK` varchar(40) DEFAULT NULL COMMENT '开户支行',
  `CARD_NAME` varchar(20) DEFAULT NULL COMMENT '持卡人姓名',
  `CARD_NO` varchar(40) DEFAULT NULL COMMENT '银行卡号',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `ACTIVATE_TIME` datetime DEFAULT NULL COMMENT '激活时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `IS_DELETED` char(1) DEFAULT '0' COMMENT '0：未删除  1：已删除',
  `COPPER_CARD_NUM` int(11) DEFAULT NULL COMMENT '直推会员铜卡数量',
  `SILVER_CARD_NUM` int(11) DEFAULT NULL COMMENT '直推会员银卡数量',
  `AWARD_FLAG` int(4) DEFAULT NULL COMMENT '奖励处理标记(0:未处理；1：管理奖励处理完成；2：辅导奖励)',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员信息表';

-- ----------------------------
-- Table structure for M_USER_REG
-- ----------------------------
DROP TABLE IF EXISTS `M_USER_REG`;
CREATE TABLE `M_USER_REG` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，用户ID',
  `ACCOUNT_NO` varchar(20) NOT NULL COMMENT '登录账号',
  `PHONE` varchar(20) NOT NULL COMMENT '联系电话',
  `PASSWORD` varchar(64) NOT NULL COMMENT '登录密码',
  `PAY_PASSWORD` varchar(64) NOT NULL COMMENT '支付密码',
  `REAL_NAME` varchar(12) DEFAULT NULL COMMENT '真实姓名',
  `LEVEL` tinyint(1) DEFAULT NULL COMMENT '会员级别(0 普通 1 铜卡 2银卡 3金卡)',
  `AGENT_ID` int(11) DEFAULT NULL COMMENT '服务中心',
  `RECOMMEND_ID` int(11) DEFAULT NULL COMMENT '推荐人',
  `REGISTER_MONEY` int(6) DEFAULT NULL COMMENT '注册金额（元）',
  `PROVINCE` varchar(10) DEFAULT NULL COMMENT '省份',
  `CITY` varchar(10) DEFAULT NULL COMMENT '市',
  `AREA` varchar(10) DEFAULT NULL COMMENT '区域',
  `FULL_ADDRESS` varchar(50) DEFAULT NULL COMMENT '详细地址',
  `BANK_NAME` varchar(20) DEFAULT NULL COMMENT '开户行',
  `SUBBRANCH_BANK` varchar(40) DEFAULT NULL COMMENT '开户支行',
  `CARD_NAME` varchar(20) DEFAULT NULL COMMENT '持卡人姓名',
  `CARD_NO` varchar(40) DEFAULT NULL COMMENT '银行卡号',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12012 DEFAULT CHARSET=utf8 COMMENT='会员信息表';

-- ----------------------------
-- Table structure for M_USERLEVEL_MANAGE
-- ----------------------------
DROP TABLE IF EXISTS `M_USERLEVEL_MANAGE`;
CREATE TABLE `M_USERLEVEL_MANAGE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_ID` varchar(20) NOT NULL COMMENT '会员编号',
  `OLD_LEVEL` tinyint(4) NOT NULL COMMENT '调整前级别(0 普通 1 铜卡 2银卡 3金卡)',
  `NEW_LEVEL` tinyint(4) NOT NULL COMMENT '调整后级别(0 普通 1 铜卡 2银卡 3金卡)',
  `STATUS` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态(0:待审核;1:通过;2:拒绝)',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `CREATE_BY` varchar(30) DEFAULT NULL,
  `AUTH_TIME` datetime DEFAULT NULL COMMENT '审核时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `IS_DELETED` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除(0:否;1:是)',
  `UPDATE_BY` varchar(30) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`ID`),
  KEY `INDEX_USER_ID` (`USER_ID`),
  KEY `INDEX_STATUS` (`STATUS`),
  KEY `INDEX_AUTH_TIME` (`AUTH_TIME`)
) ENGINE=InnoDB AUTO_INCREMENT=198 DEFAULT CHARSET=utf8 COMMENT='会员级别管理表';

-- ----------------------------
-- Table structure for T_S_DETAIL
-- ----------------------------
DROP TABLE IF EXISTS `T_S_DETAIL`;
CREATE TABLE `T_S_DETAIL` (
  `detail_id` int(16) NOT NULL AUTO_INCREMENT COMMENT '明细ID',
  `dict_id` int(16) DEFAULT NULL COMMENT '对应字典ID',
  `detail_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '明细名称',
  `detail_value` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '明细值',
  `detail_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '明细描述',
  `detail_status` int(16) DEFAULT NULL COMMENT '1启用，2禁用',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for T_S_DICT
-- ----------------------------
DROP TABLE IF EXISTS `T_S_DICT`;
CREATE TABLE `T_S_DICT` (
  `dict_id` int(16) NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `dict_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '字典名称',
  `dict_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '字典描述',
  `dict_status` int(8) DEFAULT NULL COMMENT '1启用，2禁用',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for T_S_LOG
-- ----------------------------
DROP TABLE IF EXISTS `T_S_LOG`;
CREATE TABLE `T_S_LOG` (
  `log_id` int(16) NOT NULL AUTO_INCREMENT,
  `action_url` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '访问URL',
  `log_time` datetime DEFAULT NULL COMMENT '访问时间',
  `user_ip` varchar(15) COLLATE utf8_bin DEFAULT '用户IP' COMMENT '用户IP',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `log_desc` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '日志描述',
  `user_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名称',
  `process_time` int(8) DEFAULT NULL COMMENT '操作响应时间',
  `controller_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '控制器名称',
  `controller_method` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '控制器方法名称',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49630 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for T_S_MENU
-- ----------------------------
DROP TABLE IF EXISTS `T_S_MENU`;
CREATE TABLE `T_S_MENU` (
  `menu_id` int(16) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `menu_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `menu_url` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `menu_pid` int(8) DEFAULT NULL,
  `menu_type` int(8) DEFAULT NULL COMMENT '资源类型，1：文件夹菜单，2：功能菜单，3：功能按钮',
  `menu_status` int(8) DEFAULT NULL,
  `menu_level` int(8) DEFAULT NULL,
  `menu_icon` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `menu_order` int(8) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=360 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for T_S_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `T_S_ROLE`;
CREATE TABLE `T_S_ROLE` (
  `role_id` int(16) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `role_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `role_order` int(8) DEFAULT NULL,
  `role_type` int(8) DEFAULT NULL,
  `role_status` int(8) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for T_S_ROLE_MENU
-- ----------------------------
DROP TABLE IF EXISTS `T_S_ROLE_MENU`;
CREATE TABLE `T_S_ROLE_MENU` (
  `role_menu_id` int(16) NOT NULL AUTO_INCREMENT,
  `role_id` int(16) DEFAULT NULL,
  `menu_id` int(16) DEFAULT NULL,
  PRIMARY KEY (`role_menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=901 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for T_S_ROLE_USER
-- ----------------------------
DROP TABLE IF EXISTS `T_S_ROLE_USER`;
CREATE TABLE `T_S_ROLE_USER` (
  `role_user_id` int(16) NOT NULL AUTO_INCREMENT,
  `user_id` int(16) DEFAULT NULL,
  `role_id` int(16) DEFAULT NULL,
  PRIMARY KEY (`role_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for T_S_USER
-- ----------------------------
DROP TABLE IF EXISTS `T_S_USER`;
CREATE TABLE `T_S_USER` (
  `user_id` int(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `user_order` int(8) DEFAULT NULL,
  `user_type` int(8) DEFAULT NULL,
  `user_status` int(8) DEFAULT NULL COMMENT '1启用2禁止',
  `user_pwd` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `real_name` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `MOBILE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `SEX` int(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Procedure structure for genHlgConsumptionData
-- ----------------------------
DROP PROCEDURE IF EXISTS `genHlgConsumptionData`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `genHlgConsumptionData`(IN `collectDate` date)
BEGIN
	INSERT into M_USER_ACCOUNT_INOUT_HLG(ID,USER_ID, ACCOUNT_TYPE, INOUT_TYPE, AMOUNT, TRADE_NO, INOUT_DESC, CREATE_TIME,OPERATOR_NO, USER_TYPE,EXEC_FLAG)
SELECT A.ID,A.USER_ID, A.ACCOUNT_TYPE, A.INOUT_TYPE, A.AMOUNT, A.TRADE_NO, A.INOUT_DESC, A.CREATE_TIME,A.OPERATOR_NO, A.USER_TYPE ,1 EXEC_FLAG FROM M_USER_ACCOUNT_INOUT A

LEFT JOIN M_USER_ACCOUNT_INOUT_HLG X ON A.ID = X.ID WHERE A.USER_TYPE = 12 AND DATE(A.CREATE_TIME) = collectDate  AND X.ID IS NULL;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for GenPointTaskDetail
-- ----------------------------
DROP PROCEDURE IF EXISTS `GenPointTaskDetail`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GenPointTaskDetail`(IN `collectDate` date)
BEGIN
  declare taskId int; 
  declare userId int; 
  declare pointAmount int; 
  declare done int default 0;  
  
  
  declare cur_task CURSOR for select P.TASK_ID,P.USER_ID,P.POINT_AMOUNT FROM M_POINT_TASK P LEFT JOIN  M_USER_INFO U  ON P.USER_ID = U.ID
  LEFT JOIN M_POINT_TASK_DETAIL X ON P.USER_ID = X.USER_ID AND X.COLLECT_DATE = collectDate
  WHERE P.EXEC_STATUS = 1 AND U.TYPE IN(1,3) AND P.TOTAL_DAYS > P.EXEC_DAYS AND X.ID IS NULL;
  
  declare CONTINUE HANDLER  FOR NOT FOUND SET done = 1; 
     
     open cur_task; 
         fetch cur_task into taskId,userId,pointAmount; 
           while done = 0 do       
		
              INSERT INTO M_POINT_TASK_DETAIL(TASK_ID, CREATE_TIME,COLLECT_DATE, USER_ID, POINT_AMOUNT, EXEC_STATUS, VERSION) 
              VALUES(taskId,CURRENT_TIMESTAMP(),collectDate,userId,pointAmount,1,1);

              update M_POINT_TASK set EXEC_DAYS = EXEC_DAYS + 1, LAST_EXEC_DATE = CURR_EXEC_DATE,CURR_EXEC_DATE =CURDATE(),VERSION = VERSION+1    where TASK_ID=taskId;

              fetch cur_task into taskId,userId,pointAmount; 
          end while;    
     close cur_task;
 
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for getCollectDate
-- ----------------------------
DROP PROCEDURE IF EXISTS `getCollectDate`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCollectDate`(OUT `collectDate` date,OUT `nums` int)
BEGIN 
  declare tmpDate date;
  set collectDate = IF(HOUR(CURRENT_TIME())>=12, CURDATE(),DATE_SUB(CURDATE(),INTERVAL 1 DAY));
 
  
  
  IF tmpDate <  collectDate THEN  
   set collectDate = tmpDate;
  END IF ;
	
  SELECT COUNT(1) INTO nums FROM M_POINT_TASK_DETAIL  WHERE EXEC_STATUS = 1 AND COLLECT_DATE = collectDate;
END
;;
DELIMITER ;
