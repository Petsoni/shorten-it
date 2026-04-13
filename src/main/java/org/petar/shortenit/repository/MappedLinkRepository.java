package org.petar.shortenit.repository;

import org.petar.shortenit.entity.MappedLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MappedLinkRepository extends JpaRepository<MappedLink, UUID> {

}
