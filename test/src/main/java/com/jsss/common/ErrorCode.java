package com.jsss.common;

public interface ErrorCode {

    // 通用异常
    int UNDEFINED_ERROR = 0;
    int PARAMETER_ERROR = 1;

    // 用户异常
    int USER_NOT_LOGIN = 101;
    int USER_LOGIN_FAILURE = 102;

    // 用户修改异常
    int USER_UPDATE_FAIL=103;

    //没有找到
    int NOT_FIND=104;

    //用户已经绑定角色
    int HAVE_BIND=105;


    //用户没有权限
    int NO_PERMISSION=106;


}
