package com.hazemdev.contentcalender.controller;

import com.hazemdev.contentcalender.exception.ContentNotFoundException;
import com.hazemdev.contentcalender.modell.Content;
import com.hazemdev.contentcalender.repository.ContentCollectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {

    final private ContentCollectionRepository repository;


    public ContentController(ContentCollectionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Content> getAllContent (){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findContentById (@PathVariable Integer id){

        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Content content){
        repository.save(content);
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void update (@RequestBody Content content, @PathVariable Integer id ) throws ContentNotFoundException {
        if (!repository.existsById(id)){
            throw new ContentNotFoundException(HttpStatus.NOT_FOUND, "Content not found");
        }
        repository.save(content);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete ( @PathVariable Integer id) throws ContentNotFoundException {
        if (!repository.existsById(id)){
            throw new ContentNotFoundException(HttpStatus.NOT_FOUND, "Content not found");
        }
        repository.removeById(id);
    }

}
