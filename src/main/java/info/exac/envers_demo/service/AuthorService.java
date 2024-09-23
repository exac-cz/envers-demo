package info.exac.envers_demo.service;

import info.exac.envers_demo.common.exception.NotFoundException;
import info.exac.envers_demo.model.AuthorEntity;
import info.exac.envers_demo.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;


    public Iterable<AuthorEntity> getAuthors() {
        return authorRepository.findAll();
    }


    public AuthorEntity createAuthor(AuthorEntity author) {
        return authorRepository.save(author);
    }


    public AuthorEntity getAuthor(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Author not found"));
    }


    public AuthorEntity updateAuthor(Long authorId, AuthorEntity author) {
        AuthorEntity existingAuthor = getAuthor(authorId);
        existingAuthor.setName(author.getName());
        return authorRepository.save(existingAuthor);
    }


    public void deleteAuthor(Long authorId) {
        AuthorEntity author = getAuthor(authorId);
        authorRepository.delete(author);
    }


    public Revisions<Long, AuthorEntity> getRevisions(Long authorId) {
        return authorRepository.findRevisions(authorId);
    }


    public Revision<Long, AuthorEntity> getLatestRevision(Long authorId) {
        return authorRepository.findLastChangeRevision(authorId)
                .orElseThrow(() -> new NotFoundException("Author not found"));
    }

}
