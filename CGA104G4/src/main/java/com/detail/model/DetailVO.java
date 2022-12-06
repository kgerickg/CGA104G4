package com.detail.model;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;
import com.prod.model.ProdService;
import com.prod.model.ProdVO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class DetailVO {
	
	private static final long serialVersionUID = 1L;
	
	private Integer prodId;
	private Integer prodQty;
	private Integer ordId;
	
	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getProdQty() {
		return prodQty;
	}

	public void setProdQty(Integer prodQty) {
		this.prodQty = prodQty;
	}

	public Integer getOrdId() {
		return ordId;
	}

	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}
	
    // for join prodName, prodPrc from prodId
    public ProdVO getProdVO() {
	    ProdService prodSvc = new ProdService();
	    ProdVO prodVO = prodSvc.getOneProd(prodId);
	    return prodVO;
    }
    
    public OrdersVO getOrdersVO() {
    	OrdersService ordersSvc = new OrdersService();
    	OrdersVO ordersVO = ordersSvc.getOneOrder(ordId);
    	return ordersVO;
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}