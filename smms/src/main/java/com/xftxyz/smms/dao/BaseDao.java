package com.xftxyz.smms.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public abstract class BaseDao<T> {
    private QueryRunner queryRunner = new QueryRunner();
    private Class<T> clazz;

    // 构造方法
    public BaseDao() {
        // 获取子类类型
        Class<?> clazz = this.getClass();
        // 获取带泛型的父类类型
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        // 获取泛型类型
        Type[] actualclazzArguments = parameterizedType.getActualTypeArguments();
        this.clazz = (Class<T>) actualclazzArguments[0];
    }

    // 通用的增删改操作
    public int update(Connection conn, String sql, Object... params) {
        int count = 0;
        try {
            count = queryRunner.update(conn, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    // 获取一个对象
    public T getBean(Connection conn, String sql, Object... params) {
        T t = null;
        try {
            t = queryRunner.query(conn, sql, new BeanHandler<T>(clazz), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    // 获取所有对象
    public List<T> getBeanList(Connection conn, String sql, Object... params) {
        List<T> list = null;
        try {
            list = queryRunner.query(conn, sql, new BeanListHandler<T>(clazz), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 获取一个单值的方法，专门用来执行像 select count(*)...这样的sql语句
    public Object getValue(Connection conn, String sql, Object... params) {
        Object count = null;
        try {
            // 调用queryRunner的query方法获取一个单一的值
            count = queryRunner.query(conn, sql, new ScalarHandler<>(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    // 获取一组值的方法
    public Map<String, Object> getValues(Connection conn, String sql, Object... params) {
        Map<String, Object> map = null;
        try {
            map = queryRunner.query(conn, sql, new MapHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
}
