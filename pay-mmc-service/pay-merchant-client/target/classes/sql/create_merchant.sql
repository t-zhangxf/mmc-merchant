DROP TABLE tb_users ;
DROP TABLE tb_user_login_auths ;
DROP TABLE tb_user_merc_binding ;
DROP TABLE tb_sms_validate ;
DROP TABLE tb_role;
DROP TABLE tb_user_role;
DROP TABLE tb_permission;
DROP TABLE tb_role_permission;
DROP TABLE tb_user_pay_auths;
DROP TABLE tb_login_log;
DROP TABLE IF EXISTS tb_permission_show;
--1、系统用户表
CREATE TABLE
    tb_users
    (
        id INT NOT NULL AUTO_INCREMENT,
        user_no VARCHAR(60) NOT NULL COMMENT'系统用户号',
        nick_name VARCHAR(200) DEFAULT'' COMMENT'昵称',
        avatar VARCHAR(200) DEFAULT'' COMMENT'头像',
        email VARCHAR(100) COMMENT'邮箱',
        mobile VARCHAR(20) DEFAULT'' COMMENT'手机',
        user_status TINYINT DEFAULT 0 NOT NULL COMMENT'状态(0: 正常，1: 被禁用，2：未激活)',
        failure_num TINYINT DEFAULT 0 NOT NULL COMMENT '登录失败次数',
        unlock_time DATETIME COMMENT '解锁时间',
        create_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
        creator VARCHAR(50) DEFAULT 'admin' COMMENT '操作员',
        modified_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
        modifier VARCHAR(50) DEFAULT 'admin' COMMENT '更新操作员',
        is_deleted TINYINT DEFAULT 0 NOT NULL COMMENT '是否逻辑删除(0：未删除，1：已删除)',
        PRIMARY KEY (id),
        CONSTRAINT uniq_user_no UNIQUE (user_no),
        constraint uniq_email UNIQUE (email),
        INDEX idx_users_email (email)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

--2、用户登录授权表
CREATE TABLE
    tb_user_login_auths
    (
        id INT NOT NULL AUTO_INCREMENT COMMENT'主键',
        user_no VARCHAR(60) NOT NULL COMMENT'系统用户号,外键',
        identity_type  TINYINT DEFAULT '0' NOT NULL COMMENT'登录类型，0：邮箱，1：手机，2：第三方账号',
        identifier VARCHAR(50) NOT NULL COMMENT'登录唯一标识，邮箱地址，手机号码，第三方账号如微信号',
        credential  VARCHAR(100) NOT NULL COMMENT'登权凭证，站内账号是密码、第三方登录是Token',
        verified_flag TINYINT DEFAULT 0 NOT NULL COMMENT'是否已验证，授权账号是否被验证',
        internal_flag TINYINT DEFAULT 0 NOT NULL COMMENT'是否内部账号(0：内部账号，1：外部账号)',
        create_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
        creator VARCHAR(50) DEFAULT 'admin' COMMENT '操作员',
        modified_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
        modifier VARCHAR(50) DEFAULT 'admin' COMMENT '修改操作员',
        is_deleted TINYINT DEFAULT 0 NOT NULL COMMENT '是否逻辑删除(0：未删除，1：已删除)',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录授权表';

--3、用户商户绑定表
CREATE TABLE
    tb_user_merc_binding
    (
        id INT NOT NULL AUTO_INCREMENT COMMENT'主键',
        user_no VARCHAR(60) NOT NULL COMMENT'系统用户号',
        user_type TINYINT DEFAULT 0 NOT NULL COMMENT'账号角色类型(0：管理员，1：操作员)',
        merc_id VARCHAR(30) NOT NULL COMMENT'商户号',
        binding_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT'绑定时间',
        status  TINYINT DEFAULT 0 NOT NULL COMMENT'绑定状态 (0：未绑定，1：绑定，2：解绑)',
        bus_cnl VARCHAR(20) DEFAULT 0 NOT NULL COMMENT'绑定渠道 (0：OMC，1：商户平台)',
        create_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
        creator VARCHAR(50) DEFAULT 'admin' COMMENT '操作员',
        modified_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
        modifier VARCHAR(50) DEFAULT 'admin' COMMENT '更新操作员',
        is_deleted TINYINT DEFAULT 0 NOT NULL COMMENT '是否逻辑删除(0：未删除，1：已删除)',
        PRIMARY KEY (id),
        CONSTRAINT uniq_merc_binding UNIQUE (user_no,merc_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户商户绑定表';

--4、验证码表
CREATE TABLE
    tb_sms_validate
    (
        id INT NOT NULL AUTO_INCREMENT COMMENT'主键',
        user_no VARCHAR(60) NOT NULL COMMENT'系统用户号',
        sender_type TINYINT DEFAULT 0 NOT NULL COMMENT'发送类型 (0：邮箱，1：手机)',
        sender  VARCHAR(20) NOT NULL COMMENT'发送者账号，邮箱号、手机号',
        validate_code  VARCHAR(64) NOT NULL COMMENT'验证码',
        sms VARCHAR(512) NOT NULL COMMENT'内容',
        dead_line DATETIME NOT NULL COMMENT'失效时间',
        usable TINYINT DEFAULT 1 NOT NULL COMMENT'是否有效(1：无效，2：有效)',
        sended TINYINT DEFAULT 1 NOT NULL COMMENT'是否已发送(1：未发送，2：已发送)',
        create_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
        creator VARCHAR(50) DEFAULT 'admin' COMMENT '操作员',
        modified_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
        modifier VARCHAR(50) DEFAULT 'admin' COMMENT '更新操作员',
        is_deleted TINYINT DEFAULT 0 NOT NULL COMMENT '是否逻辑删除(0：未删除，1：已删除)',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='验证码表';

--5、角色信息表
CREATE TABLE
    tb_role
    (
        id INT NOT NULL AUTO_INCREMENT,
        name VARCHAR(32) COMMENT '角色名称',
        description VARCHAR(60) COMMENT '描述',
        type INT COMMENT '角色类型',
        merc_id VARCHAR(30) NOT NULL COMMENT'商户号',
        is_deleted INT DEFAULT '0' COMMENT '0:正常1:删除',
        create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
        update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NULL COMMENT '修改时间',
        PRIMARY KEY (id)
    )
   ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';
   INSERT INTO   tb_role   (id  ,   NAME  ,   description  ,   TYPE  ,   is_deleted  ,   create_time  ,   update_time  ,   role_number  ) VALUES('0','管理员','管理员-角色','0','0','2019-02-27 12:01:17','2019-03-08 09:54:04','ROLE_20190308180116090QwYMMfaScSJM');
   INSERT INTO   tb_role   ( id  ,   NAME  ,   description  ,   TYPE  ,   is_deleted  ,   create_time  ,   update_time  ,   role_number  ) VALUES('1','操作员','操作员-角色','1','0','2019-02-27 12:01:00','2019-03-08 09:54:13','ROLE_20190308180116090QwYMMfaScSJN');
--6、用户商户角色表
CREATE TABLE
    tb_user_role
    (
        id INT(32) NOT NULL AUTO_INCREMENT,
        user_no VARCHAR(60) COMMENT '用户ID',
        merc_id VARCHAR(30) NOT NULL COMMENT'商户号',
        role_id INT COMMENT '角色ID',
        is_deleted INT(1) DEFAULT '0' COMMENT '0:正常1:删除',
        create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
        update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NULL COMMENT '修改时间',
        PRIMARY KEY (id),
        CONSTRAINT index_role_user UNIQUE (role_id, user_no,merc_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

--7、权限信息表
CREATE TABLE
    tb_permission
    (
        id INT NOT NULL AUTO_INCREMENT,
        name VARCHAR(64) COMMENT 'url描述',
        url VARCHAR(256) COMMENT 'url地址',
        father_id INT COMMENT '上级菜单id',
        sort INT NOT NULL COMMENT '排序',
        attr_id INT COMMENT '类型：页面、按钮',
        create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
        update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NULL COMMENT '修改时间',
        is_deleted TINYINT(1) DEFAULT '0' COMMENT '是否删除',
        creatBy VARCHAR(64) DEFAULT 'admin' COMMENT '创建用户',
        updateBy VARCHAR(64) DEFAULT 'admin' COMMENT '修改用户',
        en_name VARCHAR(64) DEFAULT 'M_TEST' COMMENT '菜单英文名称',
        ps_number VARCHAR(64) COMMENT '权限序号',
        role_type INT (1) DEFAULT '0' COMMENT '角色类型 0:管理员角色',
        permission_type INT (1) DEFAULT '1' COMMENT '权限类型 0:管理类 1:操作类2:审核类',
        PRIMARY KEY (id),
        CONSTRAINT index_psNumber UNIQUE (ps_number),
        INDEX index_url (url(255)),
        INDEX inedx_name (name),
        INDEX index_sort (sort)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限信息表';

--8、角色权限表
CREATE TABLE
    tb_role_permission
    (
        id INT NOT NULL AUTO_INCREMENT,
        role_id INT COMMENT '角色id',
        permission_id INT,
        is_deleted INT DEFAULT '0' COMMENT '0:正常1：删除',
        create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
       update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NULL COMMENT '修改时间',
        PRIMARY KEY (id),
        INDEX index_role (role_id),
        INDEX index_permission_id (permission_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

--9、用户支付授权表
CREATE TABLE
    tb_user_pay_auths
    (
        id INT NOT NULL AUTO_INCREMENT COMMENT'主键',
        user_no VARCHAR(60) NOT NULL COMMENT'系统用户号,外键',
        merc_id VARCHAR(30) NOT NULL COMMENT'商户号',
        identifier VARCHAR(50) NOT NULL COMMENT'支付唯一标识，支付账号',
        credential  VARCHAR(100) NOT NULL COMMENT'支付凭证，支付密码',
        create_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
        creator VARCHAR(50) DEFAULT 'admin' COMMENT '操作员',
        modified_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
        modifier VARCHAR(50) DEFAULT 'admin' COMMENT '修改操作员',
        is_deleted TINYINT DEFAULT 0 NOT NULL COMMENT '是否逻辑删除(0：未删除，1：已删除)',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户支付授权表';

CREATE TABLE
  tb_login_log (
   id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
   user_no varchar(64) DEFAULT NULL COMMENT '用户id',
   request_url longtext COMMENT '请求url',
   current_login_ip varchar(64) DEFAULT NULL COMMENT '登录ip',
   create_time timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   modified_time timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   is_deleted int(1) DEFAULT '0' COMMENT '是否删除 0:正常数据 1:删除数据',
   PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE   tb_permission_show   (
    id   INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    ps_number   VARCHAR(64) DEFAULT NULL COMMENT '权限主键',
    is_deleted   INT(11) DEFAULT '0' COMMENT '是否删除 0:正常数据 1:删除',
    created_time   TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time   TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    TYPE   INT(11) DEFAULT NULL COMMENT '类型 0:管理类 1:审核类 2:操作类',
    NAME   VARCHAR(64) DEFAULT NULL COMMENT '名称',
    father_ids   LONGTEXT COMMENT '权限父类ids',
    child_ids   LONGTEXT COMMENT '权限子ids',
  PRIMARY KEY (  id  ),
  KEY   index_ps_number   (  ps_number  )
) ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
INSERT  INTO   tb_permission_show  (  id  ,  ps_number  ,  is_deleted  ,  created_time  ,  updated_time  ,  TYPE  ,  NAME  ,  father_ids  ,  child_ids  ) VALUES

(1,'PS_10005',0,'2019-03-07 03:29:14','2019-03-12 11:16:45',0,'设置服务-权限管理','5','6,7,8,9,10,11,12,13,14,15,16,17,20,21'),

(2,'PS_10017',0,'2019-03-07 04:38:14','2019-03-12 11:17:53',1,'交易管理-订单查询-下载','1,2','18,19,20,21'),

(3,'PS_10001',0,'2019-03-07 05:50:42','2019-03-12 11:17:17',1,'交易管理-订单查询','1','2,3,4,22,23,20,21');

