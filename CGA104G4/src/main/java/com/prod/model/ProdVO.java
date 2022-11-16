package com.prod.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.prodtype.model.ProdTypeVO;
import com.store.model.StoreVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PROD")
public class ProdVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PROD_ID")
	private Integer prodId;
	@Column(name="STORE_ID")
	private Integer storeId;
	@Column(name = "PROD_TYPE_ID")
	private Integer prodTypeId;
	@Column(name = "PROD_STAT")
	private Integer prodStat;
	@Column(name = "PROD_NAME")
	private String prodName;
	@Column(name="PROD_CONT")
	private String prodCont;
	@Column(name="PROD_PRC")
	private Integer prodPrc;
	@Column(name="PROD_TIME")
	private Date prodTime;
	@OneToMany
	@JoinColumn(name="STORE_ID",referencedColumnName = "STORE_ID")
	private StoreVO storeVO;
	@OneToMany
	@JoinColumn(name="PROD_TYPE_ID",referencedColumnName = "PROD_TYPE_ID")
	private ProdTypeVO prodTypeVO;
}