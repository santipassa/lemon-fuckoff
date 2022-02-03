package com.lemon.fuckoff.services;

import com.lemon.fuckoff.dto.MessageResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageServiceImpl implements IMessageService {

    @Autowired
    IFuckOffService fuckOffService;

    @Override
    public MessageResponseDTO getMessage() {
        MessageResponseDTO messageResponseDTO = fuckOffService.getFuckYeahMessage();
        log.info(String.format("Message from foaas API: '%s'", messageResponseDTO.getMessage()));
        return messageResponseDTO;
    }
}
