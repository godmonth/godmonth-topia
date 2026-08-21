/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/11/10 9:40:19                           */
/*==============================================================*/


drop table if exists ccctron.t_collect_address;

drop table if exists ccctron.t_collect_batch;

drop table if exists ccctron.t_collect_currency;

drop table if exists ccctron.t_command;

drop table if exists ccctron.t_command_param;

drop table if exists ccctron.t_crypto_wallet;

drop table if exists ccctron.t_deposit_event;

drop table if exists ccctron.t_deposit_event_scanner;

drop table if exists ccctron.t_request_identity;

drop table if exists ccctron.t_retryable_command;

drop table if exists ccctron.t_sequence_table;

drop table if exists ccctron.t_signed_transaction;

drop table if exists ccctron.t_transfer_order;

drop table if exists ccctron.t_trc20_deposit_event;

drop table if exists ccctron.t_unretryable_command;

/*==============================================================*/
/* Table: t_collect_address                                     */
/*==============================================================*/
create table ccctron.t_collect_address
(
   id                   bigint not null comment 'id(yyyyMMdd999999999)',
   batch_id             bigint not null comment '批次id',
   address              varchar(200) not null comment '地址',
   fail_collect_crny_count int comment '失败归集资产个数',
   success_collect_crny_count int comment '成功归集资产个数',
   total_collect_crny_count int comment '总共归集资产个数',
   fuel_amount          decimal(23,8) comment '加油金额',
   fuel_order_id        bigint comment '加油转账订单',
   status               varchar(50) not null comment '订单状态',
   success              varchar(50) comment '是否成功',
   fail_code            varchar(50) comment '失败代码',
   fail_message         varchar(200) comment '失败消息',
   data_version         bigint not null comment '数据版本',
   created_time         timestamp(3) not null comment '创建时间',
   last_updated_time    timestamp(3) not null comment '最后更新时间',
   fuel_cent            decimal(32) comment '加油(分)',
   primary key (id)
);

alter table ccctron.t_collect_address comment '归集地址(分区)';

/*==============================================================*/
/* Index: uk_ca_addr                                            */
/*==============================================================*/
create unique index uk_ca_addr on ccctron.t_collect_address
(
   batch_id,
   address
);

/*==============================================================*/
/* Table: t_collect_batch                                       */
/*==============================================================*/
create table ccctron.t_collect_batch
(
   id                   bigint not null comment 'id(yyyyMMdd999999999)',
   start_time           timestamp(3) not null comment '开始时间',
   end_time             timestamp(3) not null comment '结束时间',
   included_event_count int not null comment '事件计数',
   fail_collect_address_count int comment '失败归集地址个数',
   success_collect_address_count int comment '成功归集地址个数',
   total_collect_address_count int comment '总共归集地址个数',
   status               varchar(50) not null comment '订单状态',
   fail_code            varchar(50) comment '失败代码',
   fail_message         varchar(200) comment '失败消息',
   created_time         timestamp(3) not null comment '创建时间',
   last_updated_time    timestamp(3) not null comment '最后更新时间',
   data_version         bigint not null comment '数据版本'
);

alter table ccctron.t_collect_batch comment '归集批次(不分区)';

/*==============================================================*/
/* Index: i_cb_et                                               */
/*==============================================================*/
create index i_cb_et on ccctron.t_collect_batch
(
   end_time
);

/*==============================================================*/
/* Index: i_cb_lut                                              */
/*==============================================================*/
create index i_cb_lut on ccctron.t_collect_batch
(
   last_updated_time
);

/*==============================================================*/
/* Table: t_collect_currency                                    */
/*==============================================================*/
create table ccctron.t_collect_currency
(
   id                   bigint not null comment 'id(yyyyMMdd999999999)',
   address_id           bigint not null comment '批次id',
   currency_code        varchar(50) not null comment '资产代码',
   status               varchar(200) not null comment '订单状态',
   collect_order_id     bigint comment '归集转账订单',
   data_version         bigint not null comment '数据版本',
   created_time         timestamp(3) not null comment '创建时间',
   last_updated_time    timestamp(3) not null comment '最后更新时间',
   primary key (id)
);

alter table ccctron.t_collect_currency comment '归集资产(分区)';

/*==============================================================*/
/* Index: uk_cat_ast                                            */
/*==============================================================*/
create unique index uk_cat_ast on ccctron.t_collect_currency
(
   address_id,
   currency_code
);

/*==============================================================*/
/* Table: t_command                                             */
/*==============================================================*/
create table ccctron.t_command
(
   id                   bigint not null comment 'id',
   category             VARCHAR(50) not null comment '命令类型',
   external_order_id    VARCHAR(100) comment '外部订单id',
   status               VARCHAR(50) comment '状态',
   memo                 VARCHAR(100) comment '备注',
   created_time         timestamp(3) not null comment '创建时间',
   last_updated_time    timestamp(3) not null comment '最后更新时间',
   data_version         bigint not null comment '数据版本',
   primary key (id)
);

alter table ccctron.t_command comment '所有的发出id都从这里读';

/*==============================================================*/
/* Index: i_urcmd_ct                                            */
/*==============================================================*/
create index i_urcmd_ct on ccctron.t_command
(
   created_time
);

/*==============================================================*/
/* Table: t_command_param                                       */
/*==============================================================*/
create table ccctron.t_command_param
(
   id                   bigint not null comment '通用指令id',
   pkey                 VARCHAR(50) not null comment '键',
   value                VARCHAR(200) not null comment '值',
   primary key (id, pkey)
);

alter table ccctron.t_command_param comment '指令参数(分区)';

/*==============================================================*/
/* Table: t_crypto_wallet                                       */
/*==============================================================*/
create table ccctron.t_crypto_wallet
(
   id                   bigint not null comment 'id',
   address              varchar(200) not null comment '钱包地址(分区)',
   credential           varchar(200) not null comment '私钥',
   encryption_type      varchar(50) not null comment '密钥加密方式',
   deposit_enabled      varchar(50) not null comment '是否开启充值',
   wallet_owner         varchar(50) comment '所属',
   wallet_usage         varchar(50) comment '用途',
   created_time         timestamp(3) not null comment '创建时间',
   last_updated_time    timestamp(3) not null comment '最后更新时间',
   data_version         bigint not null comment '数据版本',
   primary key (id)
);

alter table ccctron.t_crypto_wallet comment '数字钱包(分区,根据address hash)';

/*==============================================================*/
/* Index: uk_cw_addr                                            */
/*==============================================================*/
create unique index uk_cw_addr on ccctron.t_crypto_wallet
(
   address
);

/*==============================================================*/
/* Table: t_deposit_event                                       */
/*==============================================================*/
create table ccctron.t_deposit_event
(
   id                   bigint not null comment 'id',
   event_usage          varchar(50) comment '用途',
   ignored              varchar(50) not null comment '是否被忽略',
   chain_code           varchar(32) not null,
   category             varchar(32) not null comment '事件类型',
   currency_code        varchar(32) not null comment '提币币种',
   amount               decimal(23,8) not null comment '充值金额',
   cent_amount          decimal(32) not null comment '原始充值金额',
   payer_address        varchar(200) not null comment '付款人地址',
   receiver_address     varchar(200) not null comment '收款人地址',
   block_number         bigint not null,
   tx_hash              varchar(200) not null comment '事务hash',
   created_time         timestamp(3) not null comment '创建时间',
   primary key (id)
);

alter table ccctron.t_deposit_event comment '转账事件(分区)';

/*==============================================================*/
/* Index: i_de_ct                                               */
/*==============================================================*/
create index i_de_ct on ccctron.t_deposit_event
(
   created_time
);

/*==============================================================*/
/* Index: i_de_bn                                               */
/*==============================================================*/
create index i_de_bn on ccctron.t_deposit_event
(
   block_number
);

/*==============================================================*/
/* Table: t_deposit_event_scanner                               */
/*==============================================================*/
create table ccctron.t_deposit_event_scanner
(
   chain_code           varchar(32) not null comment '链代码',
   scanned_block_number bigint not null comment '已扫描块高',
   created_time         timestamp(3) not null comment '创建时间',
   last_updated_time    timestamp(3) not null comment '最后更新时间',
   data_version         bigint not null comment '数据版本',
   primary key (chain_code)
);

alter table ccctron.t_deposit_event_scanner comment '转账事件扫描器(不分区)';

/*==============================================================*/
/* Table: t_request_identity                                    */
/*==============================================================*/
create table ccctron.t_request_identity
(
   id                   bigint not null comment '退款全局号',
   issuer_type          varchar(200) not null comment '发起人类型',
   issuer_code          varchar(200) not null comment '发起人代码',
   request_no           varchar(200) not null comment '商户订单号',
   id_usage             varchar(50) not null comment '用途',
   created_time         timestamp(3) not null comment '创建时间',
   rejected             varchar(10) comment '请求被拒绝',
   primary key (id)
);

alter table ccctron.t_request_identity comment '请求标识';

/*==============================================================*/
/* Index: uk_ri_no                                              */
/*==============================================================*/
create unique index uk_ri_no on ccctron.t_request_identity
(
   request_no,
   issuer_type,
   issuer_code
);

/*==============================================================*/
/* Table: t_retryable_command                                   */
/*==============================================================*/
create table ccctron.t_retryable_command
(
   id                   bigint not null comment 'id',
   parent_type          VARCHAR(50) not null comment '订单类型',
   parent_id            bigint not null comment '订单id',
   command              VARCHAR(50) not null comment '指令',
   primary key (id)
);

alter table ccctron.t_retryable_command comment '可重试指令(分区)';

/*==============================================================*/
/* Index: i_rcmd_prt                                            */
/*==============================================================*/
create index i_rcmd_prt on ccctron.t_retryable_command
(
   parent_id,
   parent_type,
   command
);

/*==============================================================*/
/* Table: t_sequence_table                                      */
/*==============================================================*/
create table ccctron.t_sequence_table
(
   sequence_name        varchar(128) not null comment '序列名称',
   sequence_value       bigint not null comment '序列值',
   primary key (sequence_name)
);

alter table ccctron.t_sequence_table comment '序号生成表';

/*==============================================================*/
/* Table: t_signed_transaction                                  */
/*==============================================================*/
create table ccctron.t_signed_transaction
(
   id                   varchar(100) not null comment 'id',
   body                 varchar(2000) not null comment '报文base64',
   created_time         timestamp(3) not null comment '创建时间',
   primary key (id)
);

alter table ccctron.t_signed_transaction comment '签名交易';

/*==============================================================*/
/* Table: t_transfer_order                                      */
/*==============================================================*/
create table ccctron.t_transfer_order
(
   id                   bigint not null comment 'id',
   payer_address        varchar(200) not null comment '付款地址',
   receiver_address     varchar(200) not null comment '收款地址',
   currency_code        varchar(32) not null comment '收款币种',
   amount               decimal(23,8) not null comment '收款金额',
   cent_amount          decimal(32) not null comment '收款金额(分)',
   tx_hash              varchar(200) comment '事务hash',
   estimate_gas         varchar(50) comment '预估邮费',
   status               varchar(32) not null comment 'status',
   fail_message         varchar(128) comment '错误原因',
   fail_code            varchar(32) comment '错误码',
   unity_result_code    varchar(64) comment '统一结果码',
   created_time         timestamp(3) not null comment '创建时间',
   last_updated_time    timestamp(3) not null comment '最后更新时间',
   data_version         bigint not null comment '数据版本',
   include_fee          varchar(32) comment '转账费用包含在金额中',
   actual_cent          decimal(32) comment '实际转账金额',
   primary key (id)
);

alter table ccctron.t_transfer_order comment '提现订单';

/*==============================================================*/
/* Index: i_tso_ct                                              */
/*==============================================================*/
create index i_tso_ct on ccctron.t_transfer_order
(
   created_time
);

/*==============================================================*/
/* Index: i_tso_lut                                             */
/*==============================================================*/
create index i_tso_lut on ccctron.t_transfer_order
(
   last_updated_time
);

/*==============================================================*/
/* Table: t_trc20_deposit_event                                 */
/*==============================================================*/
create table ccctron.t_trc20_deposit_event
(
   id                   bigint not null comment 'id(yyyyMMdd999999999)',
   block_number_index   bigint not null comment '事件区块高度索引',
   log_index            bigint not null comment '事件索引',
   primary key (id)
);

alter table ccctron.t_trc20_deposit_event comment 'erc20充值事件(分区)';

/*==============================================================*/
/* Index: uk_trc_de_log                                         */
/*==============================================================*/
create unique index uk_trc_de_log on ccctron.t_trc20_deposit_event
(
   block_number_index,
   log_index
);

/*==============================================================*/
/* Table: t_unretryable_command                                 */
/*==============================================================*/
create table ccctron.t_unretryable_command
(
   id                   bigint not null comment 'id',
   parent_type          VARCHAR(50) not null comment '订单类型',
   parent_id            bigint not null comment '订单id',
   command              VARCHAR(50) not null comment '指令',
   primary key (id)
);

alter table ccctron.t_unretryable_command comment '不可重试指令(分区)';

/*==============================================================*/
/* Index: uk_urcmd_prt                                          */
/*==============================================================*/
create unique index uk_urcmd_prt on ccctron.t_unretryable_command
(
   parent_id,
   parent_type,
   command
);

