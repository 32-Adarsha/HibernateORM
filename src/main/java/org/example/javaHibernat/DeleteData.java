package org.example.javaHibernat;

import com.code.entity.OrderDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DeleteData {
    SessionFactory sessionFactory;
    public DeleteData(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.DeleteOrderDetails(1);
    }

    private void DeleteOrderDetails(int id) {
        Session currentSession = sessionFactory.openSession();
        currentSession.beginTransaction();
        OrderDetails orderDetails = currentSession.get(OrderDetails.class, id);
        currentSession.remove(orderDetails);
        currentSession.getTransaction().commit();
        currentSession.close();
    }
}
