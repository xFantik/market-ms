package ru.pb.market.auth.repositoties;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pb.market.auth.entities.Role;


import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findRoleByName(String name);

}
