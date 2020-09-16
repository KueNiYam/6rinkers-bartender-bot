package bar.cocktailpick.bartender.domain.rolemembers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleMembersRepository extends JpaRepository<RoleMembers, Long> {
    Optional<RoleMembers> findFirstByOrderByCreatedAtDesc();
}
