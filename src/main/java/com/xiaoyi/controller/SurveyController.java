package com.xiaoyi.controller;

import com.xiaoyi.entity.Admin;
import com.xiaoyi.entity.Survey;
import com.xiaoyi.service.SurveyService;
import com.xiaoyi.utils.Constant;
import com.xiaoyi.utils.MapControl;
import com.xiaoyi.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/survey")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @GetMapping("/create")
    public String v_create(){
        return "survey/add";
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create( @RequestPart("survey") Survey survey, @RequestPart("file") MultipartFile file, HttpServletRequest request, Model model){
        Admin curAdmin = SessionUtils.getAdmin(request);
        System.out.println(curAdmin+"bbbb");
        survey.setCreator(curAdmin.getId());
//        System.out.println(survey.getCreateTime());
//        survey.setCreateTime(survey.getCreateTime());
        survey.setState(Survey.state_create);
        survey.setAnon(survey.getAnon()!=null?0:1);


        if(surveyService.saveFile(survey, file)){

            model.addAttribute("survey",survey);
            return MapControl.getInstance().success().getMap();

        }
        return MapControl.getInstance().error().getMap();

    }
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids){
        int result = surveyService.deleteBatch(ids);
        if(result< 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }
    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(@RequestBody Survey survey){
        survey.setAnon(survey.getAnon()!=null?0:1);
        int result = surveyService.update(survey);
        if(result< 0){
            return MapControl.getInstance().error().getMap();

        }
        return MapControl.getInstance().success().getMap();

    }
    @GetMapping("/list")
    public String list(){
        return "survey/list";
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(@RequestBody Survey survey){
        System.out.println(survey.getPage());
        System.out.println(survey.getLimit());

        List<Survey> list = surveyService.query(survey);
        System.out.println(list);
        Integer count = surveyService.count(survey);
        return MapControl.getInstance().page(list,count).getMap();

    }
    @GetMapping("/detail")

    public String detail(Integer id, ModelMap modelMap){
        Survey survey = surveyService.detail(id);
        modelMap.addAttribute("survey",survey);
        return "survey/update";

    }
    /**
     * 处理图片显示请求
     * @param fileName
     */
    @RequestMapping("/{fileName}.{suffix}")
    @ResponseBody
    public void showPicture(@PathVariable("fileName") String fileName,
                            @PathVariable("suffix") String suffix,
                            HttpServletResponse response) {
        File imgFile = new File(Constant.IMG_PATH + fileName + "." + suffix);
        System.out.println(fileName);
        System.out.println(suffix);
        try {
            responseFile(response, imgFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理图片下载请求
     */
//    @RequestMapping("/downloadPic/{fileName}.{suffix}")
//    public void downloadPicture(@PathVariable("fileName") String fileName,
//                                @PathVariable("suffix") String suffix,
//                                HttpServletResponse response){
//        // 设置下载的响应头信息
//        response.setHeader("Content-Disposition",
//                "attachment;fileName=" + "headPic.jpg");
//        File imgFile = new File(Constant.IMG_PATH + fileName + "." + suffix);
//        responseFile(response, imgFile);
//    }

    private void responseFile(HttpServletResponse response, File imgFile) {

            try{
                InputStream is = new FileInputStream(imgFile);
            OutputStream os = response.getOutputStream();
            byte [] buffer = new byte[1024]; // 图片文件流缓存池
            while(is.read(buffer) != -1){
                os.write(buffer);
            }
            os.flush();
            is.close();
            os.close();
            }catch (Exception e){
                e.printStackTrace();
            }




    }




}
