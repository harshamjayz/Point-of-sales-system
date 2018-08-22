/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.business.custom.impl;

import java.sql.Connection;
import java.util.ArrayList;
import lk.ijse.pos.business.custom.PlaceOrderBO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.ItemdetailDTO;
import lk.ijse.pos.dto.OrdersDTO;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    OrdersBOImpl orderBOImpl = new OrdersBOImpl();
    ItemdetailBOImpl itemdetailBOImpl = new ItemdetailBOImpl();

    @Override
    public boolean placeOrder(OrdersDTO order, ArrayList<ItemdetailDTO> itemArray) throws Exception {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean result1 = orderBOImpl.saveOrders(order);
            if (result1 == true) {
                for (ItemdetailDTO itemdetailDTO : itemArray) {
                    itemdetailDTO.setOrderId(order.getId());
                    System.out.println("Order id : " + itemdetailDTO.getOrderId());
                    Boolean result2 = itemdetailBOImpl.saveItemdetail(itemdetailDTO);
                    if (!result2) {
                        connection.rollback();
                        return false;
                    }
                }

            }else{
                connection.rollback();return false;
            }
        } catch (Exception ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(true);
        }
        
        return true;

    }
}
