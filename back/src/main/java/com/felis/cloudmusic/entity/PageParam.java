package com.felis.cloudmusic.entity;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.felis.cloudmusic.annotation.FieldDescription;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class PageParam<T> extends Page<T> {

    private T params;

    public PageParam() {
    }


    public T getParams() {

        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }

    @Override
    public List<OrderItem> getOrders() {

        List<OrderItem> orders = new ArrayList<>();

        for (OrderItem oi:super.getOrders()) {
            String orderName =  getFieldAnnotation(oi.getColumn());
            if ("".equals(orderName)){
                continue;
            }
            if (oi.isAsc()){
                orders.add(OrderItem.asc(orderName));
            }else {
                orders.add(OrderItem.desc(orderName));
            }
        }
        return orders;
    }

    private String getFieldAnnotation(String fieldName) {
        if (params == null){
            return "";
        }
        try {
            Field field = params.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            FieldDescription orderField = field.getAnnotation(FieldDescription.class);

            if (null == orderField) {
                return fieldName;
            }
            if ("".equals(orderField.alias())){
                return orderField.column();
            }else{
                String tmp = "".equals(orderField.column()) ? fieldName: orderField.column();
                return orderField.alias() +'.'+tmp;
            }
        } catch (Exception e) {
            return "";
        }

    }

}