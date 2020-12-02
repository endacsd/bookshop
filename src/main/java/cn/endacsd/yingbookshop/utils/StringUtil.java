package cn.endacsd.yingbookshop.utils;

import java.util.Random;

public class StringUtil {


    static private Random random=new Random();
    static private String digString="0123456789";
    static private String letString="abcdefghigklmnopkrstuvwxyz";

    public static String randString(int length,String str){
        //
        char []ret=new char[length];
        for(int i=0;i<length;i++){
            //
            ret[i]=str.charAt(random.nextInt(str.length()));
        }
        return new String(ret);
    }
    public static String getSalt(){
        return randString(6,digString+letString+letString.toUpperCase());
    }

    public static void main(String[] args) {
        System.out.println(getSalt());
    }
}
