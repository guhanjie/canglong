USE canglong;

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL,
  `user_id` varchar(64) NOT NULL COMMENT '资金账户拥有者ID',
  `bank` varchar(20) DEFAULT NULL COMMENT '银行账号',
  `type` tinyint(4) NOT NULL COMMENT '1为人民币账户、2为积分账户',
  `status` tinyint(4) DEFAULT '1' COMMENT '0为冻结，1为启用',
  `balance` decimal(16,2) NOT NULL COMMENT '账户当前余额',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `IDX_ACCOUNT_USERID` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户';

DROP TABLE IF EXISTS `accounting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounting` (
  `id` bigint(20) NOT NULL COMMENT '记账流水号',
  `account_id` bigint(20) DEFAULT NULL,
  `balance` decimal(16,2) NOT NULL COMMENT '账户当前余额',
  `bank` varchar(20) DEFAULT NULL COMMENT '银行账号（充值或体现用）',
  `amount` decimal(16,2) DEFAULT NULL COMMENT '本次账目金额',
  `type` tinyint(4) NOT NULL COMMENT '1充值，2提现，3消费，4奖励',
  `increment` decimal(16,2) DEFAULT NULL,
  `decrement` decimal(16,2) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `IDX_ACCOUNTING_ACCOUNT_ID` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账簿';