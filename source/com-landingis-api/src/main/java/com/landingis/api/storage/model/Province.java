package com.landingis.api.storage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name=TablePrefix.PREFIX_TABLE + "province")
public class Province extends Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "province_name")
    private String provinceName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_Id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Province parentProvince;

    @JsonIgnore
    @OneToMany(mappedBy = "parentProvince",
            cascade = CascadeType.REMOVE,
            //Nếu đối tượng cha bị removed khỏi persistence context, thì các đối tượng tham chiếu tới nó cũng được removed.
            orphanRemoval = true
            //Các phần tử con sẽ bị xóa khi bạn xóa nó khỏi collection của phần tử cha
    )
    private List<Province> provinceList;
    private Integer kind;
}
