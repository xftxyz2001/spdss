package com.xftxyz.smms.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.xftxyz.smms.utils.Debug;

public abstract class BaseDao<T> {
    private QueryRunner queryRunner = new QueryRunner();
    private Class<T> clazz;

    // 构造方法
    @SuppressWarnings("unchecked")
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
            Debug.log("update error: " + sql + " params:" + params);
        }
        return count;
    }

    // 插入一条数据返回自增列的值
    public int insert(Connection conn, String sql, Object... params) {
        int autogenerated_key = -1;
        try {
            autogenerated_key = queryRunner.insert(conn, sql, new ScalarHandler<BigInteger>(), params).intValue();
            // System.out.println(o);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return 0;
        return autogenerated_key;
    }

    // 查询一条数据
    public T getQuery(Connection conn, String sql, ResultSetHandler<T> rsh, Object... params) {
        T obj = null;
        try {
            obj = queryRunner.query(conn, sql, rsh, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    // 查询多条数据
    public List<T> getQueryList(Connection conn, String sql, ResultSetHandler<List<T>> rsh, Object... params) {
        List<T> list = null;
        try {
            list = queryRunner.query(conn, sql, rsh, params);
        } catch (SQLException e) {
            e.printStackTrace();
            Debug.log("getQueryList:" + conn + " " + sql + " " + params);
        }
        return list;
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
