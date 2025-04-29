package org.app.services;

import org.app.db.HibernateUtil;
import org.app.model.Actor;
import org.app.web_services.dto.ActorDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ActorService {
    private final SessionFactory sessionFactory;

    public ActorService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Actor save(ActorDto actorDto){
        Actor newActor = buildNewActor(actorDto);

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(newActor);

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }

        return newActor;
    }

    private static Actor buildNewActor(ActorDto actorDto) {
        return Actor.builder()
                .name(actorDto.getName())
                .surname(actorDto.getSurname())
                .email(actorDto.getEmail())
                .phoneNumber(actorDto.getPhoneNumber())
                .build();
    }
}
