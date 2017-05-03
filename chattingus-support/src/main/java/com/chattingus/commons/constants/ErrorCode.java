package com.chattingus.commons.constants;

/**
 * 错误码
 *
 * @author lifeng
 */
public class ErrorCode {

    /* 用户名或密码错误 */
    public final static String USERNAME_OR_PASSWORD_IS_WRONG_SSTRING = "用户名或密码错误";
    public final static String USERNAME_OR_PASSWORD_IS_WRONG_CODE = "username_or_password_is_wrong";

    /* CRM_CONTACT IS NOT EXIST */
    public final static String CRM_CONTACT_IS_NOT_EXIST_STRING = "crmContact不存在";
    public final static String CRM_CONTACT_IS_NOT_EXIST_CODE = "crm-contact-is-not-exist";

    /* 备注类型不存在 */
    public final static String REMARK_TYPE_CAN_NOT_BE_EMPTY_CODE = "remark-type-can-not-be-empty";
    public final static String REMARK_TYPE_CAM_NOT_BE_EMPTY_STRING = "需要备注类型";

    /* 用户昵称不存在 */
    public final static String USER_NICK_CAN_NOT_BE_EMPTY_CODE = "user-nick-can-not-be-empty";
    public final static String USER_NICK_CAN_NOT_BE_EMPTY_STRING = "需要用户昵称";

    /* 备注类型不存在 */
    public final static String REMARK_CONTENT_CAN_NOT_BE_EMPTY_CODE = "remark-content-can-not-be-empty";
    public final static String REMARK_CONTENT_CAN_NOT_BE_EMPTY_STRING = "需要备注内容";

    /* 备注维度不存在 */
    public final static String REMARK_SCOPE_CAN_NOT_BE_EMPTY_CODE = "remark-scope-can-not-be-empty";
    public final static String REMARK_SCOPE_CAN_NOT_BE_EMPTY_STRING = "需要备注类型";

    /* 客服id不存在 */
    public final static String CUS_SERVICE_ID_CAN_NOT_BE_EMPTY_CODE = "cus-service-id-can-not-be-empty";
    public final static String CUS_SERVICE_ID_CAN_NOT_BE_EMPTY_STRING = "客服id不存在";

    /* 联系类型不存在 */
    public final static String CUS_CONTACT_TYPE_CAN_NOT_BE_EMPTY_CODE = "cus-contact-type-can-not-be-empty";
    public final static String CUS_CONTACT_TYPE_CAN_NOT_BE_EMPTY_STRING = "联系类型不能为空";

    /* 状态不能为空 */
    public final static String STATUS_CAN_NOT_BE_EMPTY_CODE = "staus_can_not_be_empty";
    public final static String STATUS_CAN_NOT_BE_EMPTY_STRING = "状态不能为空";

    /* response is null */
    public final static String RESPONSE_IS_NULL_CODE = "response_is_null";
    public final static String RESPONSE_IS_NULL_STRING = "返回response为空";

    /* 用户不存在 */
    public final static String PROCESS_FAILED_CODE = "process_failed";
    public final static String PROCESS_FAILED_STRING = "处理失败";

    /* 权限不够 */
    public final static String PERMISSION_DENIED_CODE = "permission_denied_code";
    public final static String PERMISSION_DENIED_STRING = "权限不够";
}
