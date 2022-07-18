package com.xftxyz.smms.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.xftxyz.smms.type.Role;

public class RoleConverter implements Converter<Role> {

    @Override
    public WriteCellData<Role> convertToExcelData(Role value, ExcelContentProperty contentProperty,
            GlobalConfiguration globalConfiguration) throws Exception {
        return new WriteCellData<>(value.name());
    }

    @Override
    public Role convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
            GlobalConfiguration globalConfiguration) throws Exception {
        return Role.valueOf(cellData.getStringValue());
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Class<Role> supportJavaTypeKey() {
        return Role.class;
    }

}
