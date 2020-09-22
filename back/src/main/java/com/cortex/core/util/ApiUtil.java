package com.cortex.core.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ApiUtil {

    public String dateTimeFormated() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public List<String> formataPipeString(String stringPipe) {
        List<String> regioesList = new ArrayList<>();
        if (stringPipe.contains("|")) {
            String regioesReplace = stringPipe.replace("|",",");
            regioesList = Stream.of(regioesReplace.split(",", -1))
                    .collect(Collectors.toList());
        } else {
            regioesList.add(stringPipe);
        }
        return regioesList;
    }

}
