package com.lishuai.controller;

import com.lishuai.common.lang.Result;
import com.lishuai.entity.Student;
import com.lishuai.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.apache.poi.hssf.record.DVALRecord;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Slf4j
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    ExcelService excelService;

    @PostMapping("/toExcel")
    public void prodTest(@RequestParam("deliveryIds")int[] deliveryIds, HttpServletResponse response) throws IOException {

        String fileName = "delivery.xls";
        String headStr = "attachment; filename=" + fileName;
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", headStr);
        excelService.exportTest(response.getOutputStream(), "投递信息",deliveryIds);
    }

//    @PostMapping("/toExcel2")
//    public void prodTest2(HttpServletResponse response) throws IOException {
//        //获得数据库当中的数据，这里是已经写好的一个方法，返回的是一个select * 的list集合。
//        Student student = new Student(1, "11", "111");
//        Student student2 = new Student(2, "22", "222");
//        Student student3 = new Student(3, "33", "333");
//        List<Student> list = Arrays.asList(student,student2,student3) ;
//
//        HSSFWorkbook workbook =  new HSSFWorkbook();
//        //创建一个表，是指一个xsl文件可以创建三个表其中的一个表的表名，设置为user info
//        HSSFSheet sheet = workbook.createSheet("userInfo");
//
//        //设置一行（第一行）
//        HSSFRow row =null;
//        //创建第一个单元格
//        row = sheet.createRow(0);
//        //设置行高，在这里26.5就是这一行的行高
//        row.setHeight((short)(26.5 * 20));
//        //这个单元格的值
//        row.createCell(0).setCellValue("学生信息");
//
//        //设置单元格合并，单元格合并的位置，0到0行，0到4列
//        //excel表也是从0行0列开始算起，注意自己设置合并的大小，避免已经设置值了的单元格也被合并导致代码报错
//        CellRangeAddress rowRegion = new CellRangeAddress(0,0,0,4);
//        //进行合并
//        sheet.addMergedRegion(rowRegion);
//
//        //另起一行，也就是第二行
//        row =sheet.createRow(1);
//        row.setHeight((short)(22.5*20));
//        //给单元格进行赋值
//        row.createCell(0).setCellValue("id");
//        row.createCell(1).setCellValue("name");
//        row.createCell(2).setCellValue("telephone");
//
//
//        //对前面得到的list集合进行遍历
//        for (int i =0 ;i<list.size();i++){
//            //重新找到这一行数据的行数，也就是在前端的基础上再另起一行
//            row = sheet.createRow(i+2);
//            //得到集合遍历的每一行数据
//            Student student1 =list.get(i);
//            //将值给到单元格
//            //user是一个实体类对象，获取get方法进行赋值
//            row.createCell(0).setCellValue(student1.getId());
//            row.createCell(1).setCellValue(student1.getName());
//            row.createCell(2).setCellValue(student1.getTelephone());
//        }
//
//        //设置自适应宽度
//        for (int i =0;i<=13 ;i++){
//            sheet.autoSizeColumn(i);
//        }
//
//        //response进行相应数据
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//        OutputStream os = response.getOutputStream();
//        //这里进行设置了一个文件名，其实也可以不要设置了，
//        //在前端进行下载的时候需要重新给定一个文件名进行下载
//        response.setHeader("Content-disposition","attachment;filename=User.xls");
//        workbook.write(os);
//        os.flush();
//        os.close();
//    }
}
