package com.sjdf.platform.common.annotations;

/**
 * User: ketqi
 * Date: 2014-05-21 13:30
 * 数据库中的操作符号;如=,<,>,<=,>=,like
 */
public enum Operator {
    /** Apply an "equal" constraint to the named property */
    EQ,

    /** Apply an "not equal" constraint to the named property */
    NE,

    /** Apply an "like" constraint to the named property */
    LIKE,

    /** Apply a "greater than" constraint to the named property */
    GT,

    /** Apply a "less than" constraint to the named property */
    LT,

    /** Apply a "less than or equal" constraint to the named property */
    LE,

    /** Apply a "greater than or equal" constraint to the named property */
    GE,

    /** Apply an "is null" constraint to the named property */
    IS_NULL,

    /** Apply an "is not null" constraint to the named property */
    IS_NOT_NULL
}
