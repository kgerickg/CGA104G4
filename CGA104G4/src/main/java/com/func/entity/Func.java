package com.func.entity;

import com.admin.entity.Admin;
import core.pojo.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FUNC")
public class Func extends Core {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FUNC_ID")
    private Integer funcId;
    @Column(name = "FUNC_NAME")
    private String funcName;
    @Column(name = "FUNC_CONT", insertable = false)
    private String funcCont;
    @ManyToMany(mappedBy = "funcs", fetch = FetchType.EAGER)
    private List<Admin> admins;
}
