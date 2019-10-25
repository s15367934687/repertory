package com.newer.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * 用于各种类型null值转空字符串的处理
 * @author fulia
 *
 */
public class JsonTypeAdapter {
	
public static final String EMPTY = "";
	
	/**
     * 对于String 类型 的 策略
     */
    public static final TypeAdapter<String> STRING = new TypeAdapter<String>() {
        //进行反序列化，json对象---》java对象
        @Override
        public String read(JsonReader reader) {
            try {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return null;
                }
                //要进行属性值的判断 若为 空字符串 则返回null 否则返回 本身的值
                String result = reader.nextString();
                return result.length() > 0 ? result : null;
            } catch (Exception e) {
                throw new JsonSyntaxException(e);
            }
        }

        // 进行序列化，从java对象转json对象
        @Override
        public void write(JsonWriter writer, String value) {
            try {
                if (value == null) {
                    writer.value(EMPTY);
                    return;
                }
                writer.value(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    
    /**
     * 对于Date 类型 的 策略
     */
    public static final TypeAdapter<Date> DATE = new TypeAdapter<Date>() {
    	
    	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //进行反序列化
        @Override
        public Date read(JsonReader reader) {
            try {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return null;
                }
        //要进行属性值的判断 若为 空字符串 则返回null 否则返回 本身的值
        String result = reader.nextString();
                return result.length() > 0 ? sdf.parse(result) : null;
            } catch (Exception e) {
                throw new JsonSyntaxException(e);
            }
        }

        // 进行序列化
        @Override
        public void write(JsonWriter writer, Date value) {
            try {
                if (value == null) {
                    writer.value(EMPTY);
                    return;
                }
                writer.value(sdf.format(value));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    
    
    

}
