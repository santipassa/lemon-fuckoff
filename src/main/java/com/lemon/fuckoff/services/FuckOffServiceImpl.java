package com.lemon.fuckoff.services;

import com.lemon.fuckoff.dto.MessageResponseDTO;
import com.lemon.fuckoff.services.rest.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class FuckOffServiceImpl implements FuckOffService {

    @Autowired
    private RestService restService;

    @Value("${rest.foaas-base-url}")
    private String foaasBaseUrl;

    /**
     * Retrieves a "Fuck yeah message from foaas API" with caching beacuse the message is the same every time.
     *
     * @return
     */
    @Cacheable("fuck-yeah-cache")
    public MessageResponseDTO getFuckYeahMessage() {
        HttpHeaders headers = new HttpHeaders();
        //Added accept:application/json header to get response in json format
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return this.restService.get(foaasBaseUrl + "/yeah/lemon", headers, MessageResponseDTO.class);
    }

}
