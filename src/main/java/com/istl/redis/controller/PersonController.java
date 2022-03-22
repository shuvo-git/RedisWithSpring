package com.istl.redis.controller;

import com.istl.redis.dto.PersonDTO;
import com.istl.redis.dto.RangeDTO;
import com.istl.redis.service.RedisListCache;
import com.istl.redis.service.RedisValueCache;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {

    private final RedisValueCache redisValueCache;
    private final RedisListCache redisListCache;

    @PostMapping
    public void cachePerson(@RequestBody final PersonDTO person) {
        redisValueCache.cache(person.getId(), person);
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable final String id) {
        return (PersonDTO) redisValueCache.getCachedValue(id);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable final String id) {
        redisValueCache.deleteCachedValue(id);
    }

    @PostMapping("/list/{key}")
    public void cachePersons(@PathVariable final String key, @RequestBody final List<PersonDTO> persons){
        redisListCache.cachePersons(key, persons);
    }

    @GetMapping("/list/{key}")
    public List<PersonDTO> getPersonsInRange(@PathVariable final String key,@RequestBody final RangeDTO range){
        return redisListCache.getPersonsInRange(key, range);
    }

    @GetMapping("/list/{key}/last")
    public PersonDTO getLastPersonCached(@PathVariable final String key) {
        return redisListCache.getLastPersonCached(key);
    }

    @DeleteMapping("/list/{key}")
    public void trim(@PathVariable final String key, @RequestBody final RangeDTO range){
        redisListCache.trim(key, range);
    }
}
