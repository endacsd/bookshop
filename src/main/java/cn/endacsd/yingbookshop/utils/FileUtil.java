package cn.endacsd.yingbookshop.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class FileUtil {




    static String preUrl="src/Files/";
    public static String fileUpload(
            MultipartFile file,String path)  {


        try{
            byte[] bytes = file.getBytes();
            //for(byte byt : bytes) System.out.println(byt);
            String [] strs=splitName(Objects.requireNonNull(file.getOriginalFilename()));
            String a=strs[0];
            String b=strs[1];
            String uri=path+a;
            System.out.println(uri);
            System.out.println(file.getContentType());
            uri+="_"+DateUtils.format(new Date())+StringUtil.getSalt();
            uri=MD5.encrypt(uri);
            String lastPath=merge(preUrl+uri,b).toLowerCase();
            Files.write(Paths.get(lastPath),bytes);

            return path+merge(uri,b).toLowerCase();
        }catch (IOException e){
            e.printStackTrace();
           return null;
        }
    }


    static void createFile(File file)  {
        try {
            if(!file.exists()) file.createNewFile();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    static String[] splitName(String name){
        if(name.lastIndexOf('.')==-1) return new String[]{name,""};
        else{
            return new String[]{name.substring(0,name.lastIndexOf('.')),name.substring(name.lastIndexOf('.')+1)};
        }
    }
    static String merge(String a,String b){
        if(b==null||b.isEmpty()) return a;
        return a+"."+b;
    }

    public static void main(String[] args) {
        Arrays.stream(splitName("1.txt.txt")).forEach(System.out::println);
        Arrays.stream(splitName("1txttxt124213")).forEach(System.out::println);
    }
}
