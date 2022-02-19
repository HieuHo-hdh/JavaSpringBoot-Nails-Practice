package com.landingis.api.storage.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = TablePrefix.PREFIX_TABLE + "import_export")
public class ImportExport extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code; //mã chứng từ
    private String filePath;
    private Double money = 0d;
    private String note;

    private Integer kind;//1 thu, 2 chi

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
