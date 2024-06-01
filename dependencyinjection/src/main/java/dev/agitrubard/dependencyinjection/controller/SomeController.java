package dev.agitrubard.dependencyinjection.controller;

import dev.agitrubard.dependencyinjection.service.SomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/something")
class SomeController {

    /**
     * The service that will be injected into this controller.
     * <p>
     * Best practice is to use constructor injection.
     */
    private final SomeService someService;

    SomeController(SomeService someService) {
        this.someService = someService;
    }

    @GetMapping("/ask")
    ResponseEntity<String> askSomething() {
        String something = someService.askSomething();
        return ResponseEntity.ok(something);
    }

}
