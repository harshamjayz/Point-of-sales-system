/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.business.custom;

import java.util.ArrayList;
import lk.ijse.pos.business.SuperBO;
import lk.ijse.pos.dto.ItemdetailDTO;
import lk.ijse.pos.dto.OrdersDTO;

/**
 *
 * @author Harsha madushan
 */
public interface PlaceOrderBO extends SuperBO {
    
    public boolean placeOrder(OrdersDTO order,ArrayList<ItemdetailDTO> itemArray)throws Exception;
    
}
