package com.project.shopapp.common.constant;

public class MessageKeys {

    public static final String UNAUTHORIZE = "unauthorized";
    public static final String NOT_FOUND = "not_found";
    public static final String BAD_REQUEST = "bad_request";

    public static final class CATEGORY {
        public static final String DETAIL_NOT_FOUND = "category.get_detail.not_found";
    }

    public static final class ORDER {
        public static final String DETAIL_NOT_FOUND = "order.get_detail.not_found";
    }

    public static final class ORDER_DETAIL {
        public static final String DETAIL_NOT_FOUND = "order_detail.get_detail.not_found";
    }

    public static final class PERMISSION {
        public static final String DETAIL_NOT_FOUND = "permission.get_detail.not_found";
    }

    public static final class PRODUCT {
        public static final String DETAIL_NOT_FOUND = "product.get_detail.not_found";
    }

    public static final class PRODUCT_IMAGE {
        public static final String DETAIL_NOT_FOUND = "product.get_detail.not_found";
    }

    public static final class ROLE {
        public static final String GETS_NOT_FOUND = "role.gets.not_found";
        public static final String DETAIL_NOT_FOUND = "role.get_detail.not_found";
        public static final String UNAUTHORIZED_ACCESS = "role.unauthorized.access";
    }

    public static final class SOCIAL_ACCOUNT {
        public static final String GETS_NOT_FOUND = "social_account.get_detail.not_found";
    }

    public static final class USER {
        public static final String REGISTER_SUCCESS = "user.register.success";
        public static final String REGISTER_EXIST = "user.register.user_exists";
        public static final String REGISTER_RETYPE = "user.register.retype";
        public static final String REGISTER_UN_ACTIVE = "user.register.user_un_active";
        public static final String LOGIN_SUCCESS = "user.login.success";
        public static final String LOGIN_INVALID = "user.login.login_invalid";
        public static final String REGISTER_FAILED = "user.register.failed";
        public static final String LOGIN_FAILED = "user.login.failed";
    }
}
