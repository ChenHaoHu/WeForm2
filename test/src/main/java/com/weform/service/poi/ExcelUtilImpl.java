package com.weform.service.poi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weform.mapper.FormMapper;
import com.weform.model.Join;
import com.weform.service.time.TimeUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-4 10:55
 * @Description: Excel操作工具
 */

@Service
public class ExcelUtilImpl implements ExcelUtil {

    @Value("${path.excel}")
    String pathname;


    @Value("${ip.home}")
    String home;

    @Autowired
    FormMapper formMapper;

    @Autowired
    TimeUtil timeUtil;

    XSSFWorkbook workBook;
    XSSFSheet sheet;
    XSSFCellStyle cellStyle;

    @Override
    public void init() {
        // 声明一个工作薄
        workBook = null;
        workBook = new XSSFWorkbook();
        // 生成一个表格
        sheet = workBook.createSheet();
        workBook.setSheetName(0,"报名结果");
        cellStyle = workBook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);// 居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);;//垂直
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框
    }

    @Override
    public String export(List<Join> data) {

        String back = "";
        try
        {
            init();
            String formId = data.get(0).getFormid();
            String formName = formMapper.getTitleByFormid(formId);
            String fileName = formName+timeUtil.getNowTime().substring(0,10)+".xlsx";

            //分析提取数据
            List<JSONObject> content = new ArrayList<>();

            for (int i = 0; i < data.size() ; i++) {
                content.add(JSONObject.parseObject(data.get(i).getContent()));
            }

            //获取标题
            Set<String> keys =  content.get(0).keySet();
            Object[] array = keys.toArray();

            //设置标题
            XSSFRow row = sheet.createRow(1);
            for (int i = 0; i < array.length; i++) {
                String title = array[i].toString();
                Cell cell = row.createCell(i);
                cell.setCellValue(title);
                cell.setCellStyle(cellStyle);
                sheet.setColumnWidth(i, (title.length()+2) * 512);
            }

            //插入需导出的数据
            for(int i = 0; i < content.size(); i++){
                row = sheet.createRow(i+2);
                JSONObject  rowdata =  content.get(i);

                for (int j = 0; j < array.length; j++) {
                    String title = array[i].toString();
                    String str = rowdata.getString(array[j].toString());
                    System.out.println(array[j]);
                    Cell cell = row.createCell(j);
                    cell.setCellValue(str);
                    cell.setCellStyle(cellStyle);
                    if(title.length() < str.length()){
                        sheet.setColumnWidth(j, (str.length()+2) * 512);
                    }
                }

            }

            File path = new File(pathname);

            if(!path.exists()){
                path.setWritable(true, false);
                path.mkdirs();
                System.out.println("创建文件");
            }

            File  file = new File(pathname+File.separator+fileName);

            back = home  + "excel/"+fileName;
            //文件输出流
            FileOutputStream outStream = new FileOutputStream(file);
            System.out.println(file.getPath());
            workBook.write(outStream);
            outStream.flush();
            outStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return back;
    }

}
