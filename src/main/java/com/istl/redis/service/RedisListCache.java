package com.istl.redis.service;

import com.istl.redis.dto.PersonDTO;
import com.istl.redis.dto.RangeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RedisListCache {
    private final ListOperations<String, Object> listOps;

    @Autowired
    public RedisListCache(final RedisTemplate<String, Object> redisTemplate) {
        this.listOps = redisTemplate.opsForList();
    }

    public void cachePersons(final String key, final List<PersonDTO> persons){
        for(PersonDTO person: persons){
            listOps.leftPush(key,person);
        }
    }

    public List<PersonDTO> getPersonsInRange(final String key, final RangeDTO range){
        List<Object> objects = listOps.range(key,range.getFrom(),range.getTo());

        if(objects.isEmpty())
            return Collections.emptyList();

        return objects
                .stream()
                .map(object -> (PersonDTO) object)
                .collect(Collectors.toList());
    }

    public PersonDTO getLastPersonCached(final String key){
        Object object = listOps.rightPop(key);

        if(object==null)
            return null;

        return (PersonDTO) object;
    }

    public void trim(final String key, final RangeDTO range){
        listOps.trim(key, range.getFrom(), range.getTo());
    }




}
