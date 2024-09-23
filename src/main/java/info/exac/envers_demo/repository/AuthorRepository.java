package info.exac.envers_demo.repository;

import info.exac.envers_demo.model.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends
        JpaRepository<AuthorEntity, Long>,
        RevisionRepository<AuthorEntity, Long, Long> {


}
