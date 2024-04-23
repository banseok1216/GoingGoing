package com.example.personal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonalWriter {
    private final PersonalRepository personalRepository;
    public void savePersonalSchedule(PersonalSchedule personalSchedule){
        personalRepository.savePersonalSchedule(personalSchedule);
    }
}
