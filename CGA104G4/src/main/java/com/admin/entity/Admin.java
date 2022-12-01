package com.admin.entity;

import javax.persistence.*;

import com.func.entity.Func;
import core.pojo.Core;
import lombok.*;

import java.io.Serial;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ADMIN")
public class Admin extends Core {
    @Serial
    private static final long serialVersionUID = 1062017833925367218L;
    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(
                    referencedColumnName = "ADM_ID",
                    name = "ADM_ID"),
            name = "ADM_FUNCTION",
            inverseJoinColumns = @JoinColumn(
                    name = "FUNC_ID",
                    referencedColumnName = "FUNC_ID")
    )
    private List<Func> funcs;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADM_ID")
    private Integer admId;
    @Column(name = "ADM_ACC_STAT", insertable = false)
    private Integer admAccStat;
    @Column(name = "ADM_ROLE_MEM", insertable = false)
    private Integer admRoleMem;
    @Column(name = "ADM_ROLE_EMP", insertable = false)
    private Integer admRoleEmp;
    @Column(name = "ADM_ROLE_REF", insertable = false)
    private Integer admRoleRef;
    @Column(name = "ADM_ACC")
    private String admAcc;
    @Column(name = "ADM_PWD")
    private String admPwd;
    @Column(name = "ADM_NAME")
    private String admName;
    @Column(name = "ADM_STAT", insertable = false)
    private Integer admStat;
    @Column(name = "ADM_PIC")
    private byte[] admPic;

}
