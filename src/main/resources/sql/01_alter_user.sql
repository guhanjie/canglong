USE canglong;

ALTER TABLE `user`
ADD COLUMN `promotor`  bigint(20) NULL AFTER `last_ip`;
