package com.landingis.api.dto.importExport;

import com.landingis.api.dto.ResponseListObj;
import lombok.Data;

import java.util.List;

@Data
public class ImportExportResponseListObj<T> extends ResponseListObj<T> {
    private Double sum;
}
