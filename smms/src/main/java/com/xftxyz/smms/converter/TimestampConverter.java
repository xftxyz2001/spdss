package com.xftxyz.smms.converter;

import java.sql.Timestamp;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

public class TimestampConverter implements Converter<Timestamp> {

    @Override
    public WriteCellData<Timestamp> convertToExcelData(Timestamp value, ExcelContentProperty contentProperty,
            GlobalConfiguration globalConfiguration) throws Exception {
        return new WriteCellData<>(value.toString());
    }

    @Override
    public Timestamp convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
            GlobalConfiguration globalConfiguration) throws Exception {
        return Timestamp.valueOf(cellData.getStringValue());
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Class<Timestamp> supportJavaTypeKey() {
        return Timestamp.class;
    }

}
