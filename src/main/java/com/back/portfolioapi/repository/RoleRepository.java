
package com.back.portfolioapi.repository;

import com.back.portfolioapi.model.ERole;
import com.back.portfolioapi.model.Roles.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author RaCode75
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
