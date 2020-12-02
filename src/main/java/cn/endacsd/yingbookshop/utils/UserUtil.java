package cn.endacsd.yingbookshop.utils;

import cn.endacsd.yingbookshop.entity.YUser;

public class UserUtil {

    public static String checkUsername(String username){
        //
        if(username==null||username.equals("")) return "err2";
        if(username.indexOf(" ")!=-1) return "err1";
        // 检查非法字符
        return null;
    }
    public static String checkMail(String mail){
        return  null;
    }
    public static String checkPassword(String mail){
        return null;
    }

    //getInfo的处理
    public static R yUserInfo(YUser yUser){
        if(yUser==null) R.error(404,"user not found");
        return R.ok()
                .put("username",yUser.getUsername())
                .put("avatar",yUser.getAvatar())
                .put("mail",yUser.getMail())
                .put("authority",yUser.getAuthority())
                .put("surplus",yUser.getSurplus())
                ;
    }
}
