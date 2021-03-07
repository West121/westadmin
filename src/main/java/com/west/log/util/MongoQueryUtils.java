package com.west.log.util;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * mongo query 工具
 *
 * @author west
 * @date 2021/2/19 13:26
 */
public class MongoQueryUtils {

    public static Query getQuery(Object object) {
        List<Field> fields = getAllFields(object.getClass(), new ArrayList<>());
        for (Field field : fields) {
            boolean accessible = field.canAccess(object.getClass());
            // 设置对象的访问权限，保证对private的属性的访
            field.setAccessible(true);

            field.setAccessible(accessible);
        }
        Criteria criteria = Criteria.where("").is("");
        Query query = new Query();
        criteria = Criteria.where("").is("");
        query.addCriteria(criteria);
        return query;
    }

    public static List<Field> getAllFields(Class clazz, List<Field> fields) {
        if (clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            getAllFields(clazz.getSuperclass(), fields);
        }
        return fields;
    }
}
