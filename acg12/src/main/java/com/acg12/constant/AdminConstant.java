package com.acg12.constant;


/**
 * 后台常量
 * @author jumper
 */
public class AdminConstant {

//    public final static String APP_NAME = "bangshou";

    /**
     *> `appName -> [bangshou|market]`
     *
     * uuid userId
     * - 在线Token key 规则
     *   `key: {uuid}:token:{token}  value:登录时间`
     *
     *   > 用户登录的时候，将token key 写入到 Redis 中，登出的时候,删除此key
     *   > 建议加入过期时间，比如 15分钟，然后 每分钟更新Redis的过期时间， 这样就不需要在登出的时候，删除此token
     */
    public final static String USER_TOKEN_KEY_TEMPLATE = "{}:token:{}";

    //token 登录sessionId的前缀
    public static  final String  TOKEN_PRFFIX= "TOKEN:{}";
    /**
     * - 用户视频 key 规则
     *
     *  `key: {uuid}:void:{vodId}  value:{视频id}`
     *
     *
     * > 用户购买后，将购买到的视频，写入到Redis 中， KeyServer在必要的时候，验证该uuid的用户是否购买过该vodID
     * >key例子，uuid为系统用户端code，vodId课时id，数据格式：      31484455:vodId:123456  value：下面的vodId值
     *
     *     用户跟课时关系：key ->  {uuid}:void:{课时ID}   value ->  {课时ID}
     *  课时和视频关系：`key ->  {课时ID}:videokey    value -> {腾讯云视频id}`
     *
     * - vodId 对应的 fileId       vodId数据格式 videokey:课时id ,如 videokey:123456
     *
     *     > 因为fileId是腾讯提供的文件ID, 所以我们需要把自己的vodId（也就是视频的ID）对应到腾讯的ID上，也就是所每个课时，需要将一个课时ID（vodId）对应到腾讯的fileId
     */

    /**
     * 默认客服id
     */
    public final static Long DEFAULT_KEFU_ID = -2L;
    /**
     * 默认客服名称
     */
    public final static String DEFAULT_KEFU_NAME = "客服";
    /**
     * 默认注册PHONE_CODE
     */
    public final static String DEFAULT_PHONECODE = "DEFAULT_PHONECODE";
    /***
     * 客户专员激活后，医生登陆期限 (分钟) 测试为10分钟，正式为3天72小时，72*60分钟
     */
    public final static Integer DEFAULT_ACTIVE_DATE = 60 * 72;
    /**
     * 统计营销数据count log
     */
    public final static String TASK_COUNT_LOG = "{}统计营销数据count log";
    /**
     * 同步视频课程群组
     */
    public final static String TASK_SYNC_VIDEO_GROUP = "{}同步视频课程群组";
    /**
     * 同步群组头像
     */
    public final static String TASK_SYNC_GROUP_DB_HEADER = "{}同步群组头像";
    /**
     * 群组默认群主名称
     */
    public static final String GROUP_ADMIN_NAME = "上医客服";
    /**
     * 每日病例统计，清空挂号
     */
    public static final CharSequence USER_RETISTER_TASK = "{}每日病例统计，清空挂号。";

    /**
     * 未通过审核或审核中key前缀
     */
    public static final String UNCHECKED_TOKEN_PREFIX = "CHECKTOKEN_";

    /**
     * 上传状态key前缀
     */
    public static final String UPLOAD_STATUS_TOKEN_PREFIX = "UPLOADSTATUS_";
    /**
     * 当前版本号
     */
    public static final String VERSION_NOW = "VERSION_NOW_%d_%d";
    /**
     * 当前信用权限
     */
    public static final String CREDITPE_RMISSION = "CREDITPE_RMISSION";
    /**
     * 当前首页广告
     */
    public static final String BANNERUP = "BANNERUP";
    /**
     * 当前跑马灯
     */
    public static  final String HORSE_RACE_LAMP = "HORSE_RACE_LAMP";
    /**
     * 当前广告页
     */
    public static  final String BANNERS = "BANNERS_";
    /**
     * VIP信息
     */
    public static  final String  SYVIP= "SYVIP";
    /**
     * 门诊审核日志
     */
    public static final String CLINICCHECKLOG ="CLINIC_CHECK_LOG_";
    /**
     * 医生信用记录
     */
    public static final String SYDOCTORCREDIT = "SY_DOCTOR_CREDIT_";
    /**
     * 医生门诊信息
     */
    public static final String SYDOCTORCLINIC = "SY_DOCTOR_CLINIC_";



    //门诊展示详情 {}中跟医生 编号
    public static  final String  CLINIC_SHOW_DETAIL= "CLINIC_SHOW:DETAIL:{}";

    //上医信息删除记录
    public static  final String  SYMESSAGE_SHARE_LOG_DELETE= "SYMESSAGE:SHARE_LOG_DELETE:{}:{}";

    //登录次数
    public static  final String  WEB_LOGIN_COUNT= "WEB_LOGIN_COUNT:{}";

    //白名单
    public static  final String  WHITE_LIST = "WHITE_LIST";
}
