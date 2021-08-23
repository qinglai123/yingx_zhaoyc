package cn.baizhi.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Feil {

   public static void uploadFeil(MultipartFile photo) {
        // Endpoint以北京为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI5tJYmANmZSZuFFjFvGu5";
        String accessKeySecret = "5uDtIncx7WVukee0U5ciBr4uXzrBjU";
        String bucketName = "qinglai123";
        // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = photo.getOriginalFilename();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

        // 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objectName）。

       byte[] content = new byte[0];
       try {
           content = photo.getBytes();
       } catch (IOException e) {
           e.printStackTrace();
       }
       ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));

        // 关闭OSSClient。
        ossClient.shutdown();
    }


    //删除文件
    public static void  deleteFile(String string){
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI5tJYmANmZSZuFFjFvGu5";
        String accessKeySecret = "5uDtIncx7WVukee0U5ciBr4uXzrBjU";
        String bucketName = "qinglai123";
// 填写文件完整路径。文件完整路径中不能包含Bucket名称。


// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 删除文件或目录。如果要删除目录，目录必须为空。
        ossClient.deleteObject(bucketName, string);

// 关闭OSSClient。
        ossClient.shutdown();
    }
}
