package com.merchant.web.service;

import com.merchant.web.common.entity.request.TradePageQueryRequest;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface ExcelService {
    void addRowsContent(TradePageQueryRequest request, XSSFWorkbook wb);
}
