package com.lemon.fuckoff.services.rest;

import org.springframework.http.HttpHeaders;

public interface IRestService {
    <T> T get(String url, HttpHeaders headers, Class<T> responseType);
}
