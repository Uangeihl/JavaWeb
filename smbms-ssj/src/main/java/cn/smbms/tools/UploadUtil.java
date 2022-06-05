package cn.smbms.tools;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {
    /**
     * 上传文件
     * @param pic
     * @param session
     * @return 返回相对路径+文件名
     * @throws IllegalStateException
     * @throws IOException
     */
    public static String upload(MultipartFile pic,
                                HttpSession session ) throws IllegalStateException, IOException {
        String relativePath = "";
        String saveFileName = "";
        if (!pic.isEmpty()) {
            //获取路径
            relativePath = "static/upload/";
            //保存文件的绝对路径
            String path = session.getServletContext().getRealPath(relativePath);
            //获取上传原始文件名
            String fileName = pic.getOriginalFilename();
            //获取文件的后缀名
            String extName = fileName.substring(fileName.lastIndexOf("."));
            //生成上传保存的文件名
            saveFileName = UUID.randomUUID()+"_"+System.currentTimeMillis()+extName;
            //要保存的文件
            File file = new File(path,saveFileName);
            pic.transferTo(file);
        }
        return relativePath+saveFileName;
    }
}
