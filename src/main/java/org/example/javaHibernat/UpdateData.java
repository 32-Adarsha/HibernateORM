package org.example.javaHibernat;

import com.code.entity.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UpdateData {
    SessionFactory sessionFactory;
    public UpdateData(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.updateUser(3);
    }

    private void updateUser(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        Users user = currentSession.get(Users.class, id);
        user.setUsername("New Username");
        System.out.println(user.getUsername());
        currentSession.persist(user);

        currentSession.getTransaction().commit();
        currentSession.close();

    }

}
