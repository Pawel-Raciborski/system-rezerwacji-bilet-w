package org.app.movie.repository;

import org.app.movie.Actor;

import java.util.Optional;

public interface ActorRepository {
    Actor save(Actor actor);

    Optional<Actor> findByEmail(String email);
}
