package com.lishuai.service.impl;

import com.lishuai.entity.vo.DeliveryVO;
import com.lishuai.service.DeliveryService;
import com.lishuai.service.ExcelService;
import com.lishuai.utils.ExportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    DeliveryService deliveryService;

    @Override
    public void exportTest(OutputStream out, String excelTitle,int[] deliveryIds) throws IOException {

        // 定義列頭
        String[] rowsName = new String[]{"序号", "投递id", "用户名", "垃圾种类", "垃圾站", "投递时间"};
        List<Object[]> dataList = new ArrayList<Object[]>();


        List<DeliveryVO> DeliveryVOs = deliveryService.getDeliveryById(deliveryIds);


        // 存入數據
        for (DeliveryVO m : DeliveryVOs) {
            Object[] objs = new Object[]{"", m.getDeliveryId(), m.getUsername(),m.getCategoryName(),m.getStationName(),m.getCreateTime()};
            dataList.add(objs);
        }

        // 生成EXCEL
        ExportExcel ex = new ExportExcel(excelTitle, rowsName, dataList);
        try {
            ex.export(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.flush();
        out.close();
    }
}
