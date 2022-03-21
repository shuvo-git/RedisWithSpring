package com.istl.redis.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
public final class PersonDTO implements Serializable
{
    private final long id;
    private final String name;
    private final String age;
}
