package com.chattingus.commons.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 所有请求Service Response的基类
 *
 * @author lifeng
 */
public class ServiceResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String messageType;

    protected String code;

    protected String msg;

    protected String subCode;

    protected String subMsg;

    protected String body;

    protected Map<String, Object> params;

    protected JSONObject data;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public boolean isSuccess() {
        return subMsg == null && subCode == null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public <T> T put(String key, T value) {
        if (null == data) {
            data = new JSONObject();
        }
        return (T) data.put(key, value);
    }

    private JSONObject simpleParser(Object o, String[] fields) {
        SimplePropertyPreFilter f          = new SimplePropertyPreFilter(fields);
        String                  jsonString = JSON.toJSONString(o, f);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        return jsonObject;
    }

    private List<Object> listParser(Object value, String[] fields) {
        List<Object> valueList = Lists.newArrayList();
        for (Object o : (List) value) {
            if (o instanceof Map) { // 如果子对象是map
                Map<Object, Object> subValueMap = mapParser(o, fields); // 递归调用
                valueList.add(subValueMap);
            } else if (o instanceof List) { // 如果子对象是List
                List<Object> subValueList = listParser(o, fields);
                valueList.add(subValueList);
            } else {
                JSONObject jsonObject = simpleParser(o, fields);
                valueList.add(jsonObject);
            }
        }
        return valueList;
    }

    private Map<Object, Object> mapParser(Object value, String[] fields) {
        Map<Object, Object> valueMap = new LinkedHashMap<Object, Object>();
        Set<String> keySet = ((Map) value).keySet();
        for (Object valueKey : keySet) {
            Object o = ((Map) value).get(valueKey);
            // 如果子对象还是map
            if (o instanceof Map) {
                Map<Object, Object> subValueMap = mapParser(o, fields); // 递归调用
                valueMap.put(valueKey, subValueMap);
            } else if (o instanceof List) { // 如果子对象是List
                List<Object> valueList = listParser(o, fields);
                valueMap.put(valueKey, valueList);
            } else {
                JSONObject jsonObject = simpleParser(o, fields);
                valueMap.put(valueKey, jsonObject);
            }

        }
        return valueMap;
    }

    /* json put数据并过滤字段 */
    public <T> T put(String key, T value, String[] fields) {
        if (null == data) {
            data = new JSONObject();
        }
        if (fields == null || fields.length == 0) {
            return (T) data.put(key, value);
        }
        if (value instanceof Map) {
            Map<Object, Object> valueMap = mapParser(value, fields);
            return (T) data.put(key, valueMap);
        } else if (value instanceof List) {
            List<Object> valueList = listParser(value, fields);
            return (T) data.put(key, valueList);
        } else {
            JSONObject jsonObject = simpleParser(value, fields);
            return (T) data.put(key, jsonObject);
        }
    }

    /* json put数据并过滤字段 */
    public <T> T put(String key, T value, String fieldsStr) {
        if (null == data) {
            data = new JSONObject();
        }
        if (StringUtils.isEmpty(fieldsStr)) {
            return (T) data.put(key, value);
        }
        String[] fields = fieldsStr.split(",");
        return put(key, value, fields);
    }


    public <T> T put(T value) {
        String key = StringUtils.uncapitalize(value.getClass().getSimpleName());
        return put(key, value);
    }

    public <T> T get(String key) {
        if (null == data) {
            data = new JSONObject();
        }
        return (T) data.get(key);
    }

    public JSONObject getData() {
        return this.data;
    }
}