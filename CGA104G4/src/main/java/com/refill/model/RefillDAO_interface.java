package com.refill.model;

import com.basicDAO.BasicDAO_interface;
import java.util.List;

public interface RefillDAO_interface extends BasicDAO_interface<RefillVO> {
    public List<RefillVO> findByFK(Integer memId);


}
