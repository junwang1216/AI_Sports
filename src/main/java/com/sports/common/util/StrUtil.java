package com.sports.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Administrator on 2015/12/30.
 */
public class StrUtil extends StringUtils {

    public static final String EMPTY = "";

    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    private static DecimalFormat decimalFormatThree = new DecimalFormat("#.###");

    public static String trimToEmpty(Object objStr){
        return objStr == null || "null".equals(objStr) ? "" : objStr.toString().trim();
    }

    public static String trimToEmptyToDefult(Object objStr, String defultName){
        return StringUtils.isNotBlank(trimToEmpty(objStr)) ? trimToEmpty(objStr) : defultName;
    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static Integer objToInt(Object objStr){
        if(objStr == null) return null;
        return Double.valueOf(String.valueOf(objStr)).intValue();
    }

    public static Double objToDouble(Object objStr){
        if(objStr == null) return null;
        return Double.valueOf(String.valueOf(objStr));
    }
    
    public static Double objToDoubleDef0(Object objStr){
        if(objStr == null || isBlank(objStr.toString())) return 0.0;
        return Double.valueOf(String.valueOf(objStr));
    }

    public static String objToNumStr(Object objStr){
        if(objStr == null) return null;
        return String.valueOf(Double.valueOf(String.valueOf(objStr)).intValue());
    }

    public static String objToStr(Object objStr){
        if(objStr == null) return null;
        return String.valueOf(objStr);
    }

    public static String leftFillToLenth(String param, int requestLength, char fillUnit){
        while(param.length() < requestLength){
            param = String.valueOf(fillUnit) + param;
        }
        return param;
    }

    public static double roundKeepTwo(String numStr){
        return roundKeepTwo(objToDouble(numStr));
    }

    public static double roundKeepTwo(double num){
        return Double.parseDouble(decimalFormat.format(num));
    }

    public static double roundKeepThree(String numStr){
        return roundKeepThree(objToDouble(numStr));
    }

    public static double roundKeepThree(double num){
        return Double.parseDouble(decimalFormatThree.format(num));
    }
    
    public static List<Map<String, Object>> zeroToLine(List<Map<String, Object>> list){
    	if(list != null && list.size() > 0){
    		for(Map<String, Object> map : list){
    			for(String key : map.keySet()){
    				Object obj = map.get(key);
    				if(obj instanceof Number && ((Number)obj).intValue() == 0){
    					map.put(key, "--");
    				}
    			}
    		}
    	}
    	return list;
    }

    public static List parseObjToList(Object obj){
        List list = new ArrayList();
        if(obj == null) return list;
        if(!(obj instanceof List)){
            list.add(obj);
            return list;
        }else{
            return (List)obj;
        }
    }
    
    /**
	 * 取出一个指定长度大小的随机正整数.
	 * 
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}
	
	/**
	 * 获取随机字符串
	 * @return
	 */
	public static String getNonceStr() {
		// 随机数
		String currTime = DateUtil.dateToString(new Date(), DateUtil.YYYYMMDD_HMS);
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = StrUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		return strTime + strRandom;
	}

	/**
	 * 元转换成分
	 * @param money
	 * @return
	 */
	public static String getMoney(String amount) {
		if(amount==null){
			return "";
		}
		// 金额转化为分为单位
		String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额  
        int index = currency.indexOf(".");  
        int length = currency.length();  
        Long amLong = 0l;  
        if(index == -1){  
            amLong = Long.valueOf(currency+"00");  
        }else if(length - index >= 3){  
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));  
        }else if(length - index == 2){  
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);  
        }else{  
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");  
        }  
        return amLong.toString(); 
	}

}