package com.system.marketing.vo;

import com.system.marketing.consts.CommonConsts;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: Liu.B.J
 * @date: 2021/3/21 12:50
 * @description:
 */
@Data
@ApiModel(value="接口返回对象", description="接口返回对象")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    @ApiModelProperty(value = "成功标志")
    @Deprecated
    private boolean success = true;

    /**
     * 返回处理消息
     */
    @ApiModelProperty(value = "返回处理消息")
    private String message = "操作成功！";

    /**
     * 返回代码
     */
    @ApiModelProperty(value = "返回代码")
    private Integer code = ResultCode.OK.getCode();

    /**
     * 返回数据对象 data
     */
    @ApiModelProperty(value = "返回数据对象")
    private T data;

    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private long timestamp = System.currentTimeMillis();

    public Result() {

    }

    public Result<T> success(String message) {
        this.message = message;
        this.code = ResultCode.OK.getCode();
        this.success = true;
        return this;
    }


    public static Result<Object> ok() {
        Result<Object> r = new Result<Object>();
        r.setSuccess(true);
        r.setCode(ResultCode.OK.getCode());
        r.setMessage(ResultCode.OK.getMessage());
        r.setMessage("成功");
        return r;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setCode(ResultCode.OK.getCode());
        r.setMessage(ResultCode.OK.getMessage());
        r.setData(data);
        return r;
    }

    public static Result<Object> error(String msg) {
        return error(ResultCode.INTERNAL_SERVER_ERROR.getCode(), msg);
    }

    public static Result<Object> error(AbstractResultCode rc) {
        return error(rc.getCode(), rc.getMessage());
    }

    public static Result<Object> error(int code, String msg) {
        Result<Object> r = new Result<Object>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    public Result<T> error500(String message) {
        this.message = message;
        this.code = ResultCode.INTERNAL_SERVER_ERROR.getCode();
        this.success = false;
        return this;
    }
    /**
     * 无权限访问返回结果
     */
    public static Result<Object> noauth(String msg) {
        return error(CommonConsts.SC_JEECG_NO_AUTHZ, msg);
    }

}
