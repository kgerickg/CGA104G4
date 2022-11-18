package com.promo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PROMO")
public class PromoVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PROMO_ID")
	private Integer promoId;
	@Column(name="PROMO_NAME")
	private String promoName;
	@Column(name="PROMO_CONT")
	private String promoCont;
	@Column(name="PROMO_TIME_S")
	private Timestamp promoTimeS;
	@Column(name="PROMO_TIME_E")
	private Timestamp promoTimeE;
	@Column(name="PROMO_VAL")
	private Integer promoVal;

}
