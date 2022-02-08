package com.lemon.fuckoff.services.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestServiceImpl implements RestService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public <T> T get(String url, HttpHeaders headers, Class<T> responseType) {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, responseType).getBody();
    }

}
