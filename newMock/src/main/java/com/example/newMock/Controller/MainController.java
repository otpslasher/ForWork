package com.example.newMock.Controller;
import com.example.newMock.Model.RequestDTO;
import com.example.newMock.Model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import java.math.BigDecimal;


@RestController
public class MainController {
    private Logger log = LoggerFactory.getLogger(MainController.class);
    ObjectMapper mapper = new ObjectMapper();

    @PostMapping(
            value = "/info/postBalances",  //путь куда будут приходить ответы
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Object postBalances(@RequestBody RequestDTO requestDTO){   //принимает входящее сообщение. Как он понял? анотация. принимает объект класса реквестдто
        try{
            String clientId = requestDTO.getClientId(); //вытаскиваем нужные переменные
            char firstDigit = clientId.charAt(0); //достаём нужный символ(в задании важна первая цифра клиента)
            BigDecimal maxLimit;  //не обязательно биг децимал есть много вариантов как это можно сделать
            String rqUID = requestDTO.getRqUID();
            String currency;
            BigDecimal balance;
            // -- balance = random from 0 to max limit minValue + Math.random() * (maxValue - minValue + 1)
            if(firstDigit == '8'){
                maxLimit = BigDecimal.valueOf(2000.00);
                currency = "US";
                balance = BigDecimal.valueOf(0 + Math.random() * (maxLimit.intValue() + 1));
            }
            else if (firstDigit == '9'){
                maxLimit = BigDecimal.valueOf(1000.00);   //логика что по заданию. что должен выдавать
                currency = "EU";
                balance = BigDecimal.valueOf(0 + Math.random() * (maxLimit.intValue() + 1));
            }
            else {
                maxLimit = BigDecimal.valueOf(10000.00);
                currency = "RUB";
                balance = BigDecimal.valueOf(0 + Math.random() * (maxLimit.intValue() + 1));
            }

            ResponseDTO responseDTO = new ResponseDTO(); //объявляем ответ
            //заполняем все поля ответ. что то тащим из реквеста,чтото мы тут нашли и т.д.
            responseDTO.setRqUID(rqUID);
            responseDTO.setClientId(clientId);
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setCurrency(currency);
            responseDTO.setBalance(BigDecimal.valueOf(Math.round(balance.doubleValue() * 100.0) / 100.0)); //внимание это хардкод!! баланс пока неоткуда тащить
            responseDTO.setMaxLimit(maxLimit);

            log.error("********** RequestDTO **********"+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.error("********** RequestDTO **********"+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));

            return responseDTO;

        } catch (Exception ex) {return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());}

    }

}
