package com.admFunc.entity;

import core.pojo.Core;
import core.util.PK;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

public class admFunc {
    @Entity
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "ADM_FUNCTION")
    @IdClass(PK.class)
    public class AdmFunc extends Core {
        @Id
        @Column(name = "ADM_ID")
        private Integer admId;
        @Id
        @Column(name = "FUNC_ID")
        private Integer funcID;

    }
}
