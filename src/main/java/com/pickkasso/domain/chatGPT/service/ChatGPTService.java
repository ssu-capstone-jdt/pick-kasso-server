package com.pickkasso.domain.chatGPT.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;


@Service
public interface ChatGPTService {

    List<Map<String, Object>> modelList();


    Map<String, Object> prompt(String prompt);


    Map<String, Object> isValidModel(String modelName);
}
