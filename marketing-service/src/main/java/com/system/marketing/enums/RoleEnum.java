package com.system.marketing.enums;

/**
 * @author: Liu.B.J
 * @date: 2021/3/21 15:49
 * @description:
 */
public enum RoleEnum {

    SYSTEM_ADMIN(1, "系统管理员"),
    PROJECT_MANAGER(2, "项目经理"),
    BUSSINESS_MANAGER(3, "商务经理"),
    MARKETING_DIRECTOR(4, "市场主任"),
    LEADER(5, "分管领导");

    private Integer type;
    private String desc;

    RoleEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }


    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }


    public static RoleEnum formatOrNull(Integer type) {
        if (null == type) {
            return null;
        }
        RoleEnum[] enums = values();
        for (RoleEnum _enu : enums) {
            if (_enu.getType().equals(type)) {
                return _enu;
            }
        }

        return null;
    }

}
