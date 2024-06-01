package dev.agitrubard.dependencyinjection.service.impl;

import dev.agitrubard.dependencyinjection.service.SomeService;
import org.springframework.stereotype.Service;

@Service
class SomeServiceImpl implements SomeService {

    @Override
    public String askSomething() {
        return "Slm, nbr?";
    }

}
