package com.slcp.devops.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slcp.devops.dto.PictureUploadDTO;
import com.slcp.devops.entity.Picture;
import com.slcp.devops.utils.QiniuUtils;
import com.slcp.devops.service.IPictureService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: Slcp
 * @date: 2020/9/23 19:57
 * @code: 一生的挚爱
 * @description: 图片管理控制器
 */
@Controller
@RequestMapping("/admin")
@AllArgsConstructor
@Api(value = "图片管理后台", tags = "图片管理后台")
public class PictureController {

    private final IPictureService pictureService;

    @GetMapping("/pictures")
    public String listPicture(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 10);
        List<Picture> list = pictureService.list();
        PageInfo<Picture> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/pictures";
    }

    @GetMapping("/pictures/input")
    public String input(Model model) {
        model.addAttribute("picture", new Picture());
        return "admin/pictures-input";
    }

    @PostMapping("/pictures")
    public String post(@Valid Picture picture) {
        pictureService.save(picture);
        return "redirect:/admin/pictures";
    }

    @GetMapping("/pictures/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("picture", pictureService.getById(id));
        return "admin/pictures-input";
    }

    @PostMapping("/pictures/{id}")
    public String editPost(@Valid Picture picture) {
        pictureService.updateById(picture);
        return "redirect:/admin/pictures";
    }

    @GetMapping("/pictures/{id}/delete")
    public String deletePicture(@PathVariable Long id, RedirectAttributes attributes) {
        pictureService.removeById(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/pictures";
    }

    /**
     * 查询上传文件列表
     */
    @GetMapping("/pictures/upload")
    public String upload(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model) {
        List<PictureUploadDTO> lists = pictureService.listUpload();
        PageHelper.startPage(pageNum, 10);
        PageInfo<PictureUploadDTO> pageInfo = new PageInfo<>(lists);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/upload";
    }

    /**
     * 新增文件
     */
    @GetMapping("/pictures/upload-input")
    public String uploadInput(Model model) {
        model.addAttribute("pictureUpload", new PictureUploadDTO());
        return "admin/upload-input";
    }

     /* @PostMapping("/pictures/upload")
      public String upload(PictureUploadDTO pictureUpload, RedirectAttributes attributes) throws IOException {

          System.out.println(pictureUpload);
          MultipartFile file = pictureUpload.getPictureupload();
          if (file.isEmpty()){
              attributes.addFlashAttribute("message", "上传文件不能为空");
              return "redirect:/admin/pictures/input";
          }
          String relapath = ResourceUtils.getURL("classpath:").getPath() + "static/files/";
          String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
          String dataDir = relapath + format;

          File fileUpload = new File(dataDir);
          if (!fileUpload.exists()) {
              fileUpload.mkdirs();
          }
          file.transferTo(new File(fileUpload, file.getOriginalFilename()));
          //设置保存到数据库的图片路径（前端可直接根据链接获取到图片）
          pictureUpload.setPicturepath("/files/" + format + "/" + file.getOriginalFilename());

          int p = pictureService.savePictureUpload(pictureUpload);
          if (p == 0) {
              attributes.addFlashAttribute("message", "新增失败");
          } else {
              attributes.addFlashAttribute("message", "新增成功");
          }
          System.out.println(pictureUpload);
          return "redirect:/admin/pictures/upload";
      }
  */
    /*@PostMapping("/pictures/upload")
    public String upload(PictureUploadDTO pictureUpload, RedirectAttributes attributes) throws IOException {

        System.out.println(pictureUpload);
        MultipartFile file = pictureUpload.getPictureupload();
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "上传文件不能为空");
            return "redirect:/admin/pictures/input";
        }


//       File temp = ResourceUtils.getFile("classpath:static\\temp");

        //新建服务器接受文件的目录
//       String realPath = ServletActionContext.getServletContext().getRealPath("/upload");
//        String realPath ="http://47.107.128.84:8080/" +"file/test/"+fileName + suffix;

//       String path = this.getClass().getResource("/").getPath();
//       String relapath = path  + "static" + File.separator + "files"+File.separator;
//       System.out.println(path);

//    String path1 = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
//       System.out.println(path1);


//       String path01 = ResourceUtils.getURL("classpath:").getPath() + "static/files/";
//       System.out.println("ResourceUtils: "+path01);


        //在linux服务器上建一个文件夹images （服务器默认~路径为/root/）这就是在~路径下建一个images文件夹
        String relapath = "/root/images/";

        //获取项目类路径也就是资源路径        //jar包上传文件获取到文件的绝对路径 文件存储的路径为：/root/file:/root/demo-0.0.1-SNAPSHOT.jar!/BOOT-INF/classes!/static/files/2020-11-21
//       String relapath = ResourceUtils.getURL("classpath:").getPath() + "static/files/";
        //建一个时间文件夹作为标识
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dataDir = relapath + format;

        File fileUpload = new File(dataDir);
        if (!fileUpload.exists()) {
            fileUpload.mkdirs();
        }
        String absolutePath = fileUpload.getAbsolutePath();
//       /root/images/2020-11-21
        System.out.println("文件存储的路径为：" + absolutePath);

        file.transferTo(new File(absolutePath, file.getOriginalFilename()));
        //设置保存到数据库的图片路径（前端可直接根据链接获取到图片）
//       pictureUpload.setPicturepath("static/files/"+format+"/"+file.getOriginalFilename());
        //存入数据库路径 img是因为在WebConfig中配了一个资源处理器将img访问的路径，转到服务器的file:/root/images/实际图片所在地
        pictureUpload.setPicturepath("img/" + format + "/" + file.getOriginalFilename());

        int p = pictureService.savePictureUpload(pictureUpload);
        if (p == 0) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        System.out.println(pictureUpload);
        return "redirect:/admin/pictures/upload";
    }*/

    /**
     * 七牛云上传方式
     * 3.后端代码调用
     * @param pictureUpload 上传对象
     * @param attributes a
     * @return 数据
     */
    @RequestMapping("/pictures/upload")
    public String upload(PictureUploadDTO pictureUpload, RedirectAttributes attributes) {

        MultipartFile imgFile = pictureUpload.getPictureUpload();
        if (imgFile.isEmpty()) {
            attributes.addFlashAttribute("message", "上传文件不能为空");
            return "redirect:/admin/pictures/input";
        }
        //获取图片原始文件名
        String originalFilename = imgFile.getOriginalFilename();
        /*int index = originalFilename.lastIndexOf(".");
        String fileStartName = originalFilename.substring(0, index);
        String extention = originalFilename.substring(index);//获得图片后缀名  .jpg*/
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //进行拼接 时间+UUID+原始文件名+文件后缀名
        String fileName = format + "-" + UUID.randomUUID().toString().substring(0, 4) + "-" + originalFilename;
        try {
            //使用工具类将文件上传到七牛云服务器 返回上传图片链接
            String filePath = QiniuUtils.upload2Qiniu(imgFile.getBytes(), fileName);

            //保存到数据库
            pictureUpload.setPicturePath(filePath);
            int i = pictureService.savePictureUpload(pictureUpload);
            if (i == 0) {
                attributes.addFlashAttribute("message", "新增失败");
            } else {
                attributes.addFlashAttribute("message", "新增成功");
            }
            System.out.println(pictureUpload);
        } catch (IOException e) {
            throw new RuntimeException("上传图片失败！" + e.getMessage());
        }

        return "redirect:/admin/pictures/upload";

    }


    /**
     * 根据id查询上传文件
     * @param id id
     * @param model 对象
     * @return r
     */
    @GetMapping("/pictures/{id}/upload-input")
    public String uploadEdit(@PathVariable Long id, Model model) {
        model.addAttribute("pictureUpload", pictureService.getUploadById(id));
        return "admin/upload-input";
    }

    /**
     * 更新上传文件
     * @param pictureUpload 图片对象
     * @param attributes a
     * @return 数据
     */
    @PostMapping("/pictures/{id}/uploadUpdate")
    public String uploadUpdate(PictureUploadDTO pictureUpload, RedirectAttributes attributes) {
        int b = pictureService.updatePictureUpload(pictureUpload);

        if (b == 0) {
            attributes.addFlashAttribute("message", "修改失败");
        } else {
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/pictures/upload";
    }

    /**
     * 删除上传文件
     * @param id 主键
     * @param attributes a
     * @return 数据
     * @throws FileNotFoundException 文件异常
     */
    @GetMapping("/pictures/{id}/upload-delete")
    public String uploadDelete(@PathVariable Long id,
                               RedirectAttributes attributes) throws FileNotFoundException {
        PictureUploadDTO uploadFile = pictureService.getUploadById(id);
        System.out.println(uploadFile);

        //http://img.qingfenginn.top/2020-11-24-aedd-j1.jpg
        //获取2开头的索引
        int index = uploadFile.getPicturePath().indexOf("2");
        //以2的索引处截取
        String fileName = uploadFile.getPicturePath().substring(index);
        System.out.println(fileName);
        try {
            //调用七牛云工具类中的删除方法删除文件
            QiniuUtils.deleteFileFromQiniu(fileName);
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "七牛云文件删除失败");
            e.printStackTrace();
        }

        int b = pictureService.uploadDelete(id);

        if (b == 0) {
            attributes.addFlashAttribute("message", "删除失败");
        } else {
            attributes.addFlashAttribute("message", "删除成功");
        }
        return "redirect:/admin/pictures/upload";
    }
}
