package com.lishuai.service;

import java.io.IOException;
import java.io.OutputStream;

public interface ExcelService {

    void exportTest(OutputStream out, String excelTitle,int[] deliveryIds) throws IOException;
}
