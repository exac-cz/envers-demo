package info.exac.envers_demo;

import info.exac.envers_demo.model.AuthorEntity;
import info.exac.envers_demo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.history.Revisions;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("")
    public Iterable<AuthorEntity> getAuthors() {
        return authorService.getAuthors();
    }

    @GetMapping("/{authorId}")
    public AuthorEntity getAuthor(@PathVariable("authorId") Long authorId) {
        return authorService.getAuthor(authorId);
    }

    @PostMapping("")
    public AuthorEntity createAuthor(@RequestBody AuthorEntity author) {
        return authorService.createAuthor(author);
    }

    @PutMapping("/{authorId}")
    public AuthorEntity updateAuthor(@PathVariable("authorId") Long authorId,
                                     @RequestBody AuthorEntity author)
    {
        return authorService.updateAuthor(authorId, author);
    }

    @DeleteMapping("/{authorId}")
    public void deleteAuthor(@PathVariable("authorId") Long authorId) {
        authorService.deleteAuthor(authorId);
    }



    @GetMapping("/{authorId}/revisions")
    public Revisions<Long, AuthorEntity> getRevisions(@PathVariable("authorId") Long authorId) {
        return authorService.getRevisions(authorId);
    }

    @GetMapping("/{authorId}/revisions/latest")
    public AuthorEntity getLatestRevision(@PathVariable("authorId") Long authorId) {
        return authorService.getLatestRevision(authorId).getEntity();
    }

}
