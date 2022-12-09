package ru.pb.market.auth.repositoties;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.pb.market.auth.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findUserByUsername(String name);
}
