package org.petar.shortenit.controllers;

import lombok.RequiredArgsConstructor;
import org.petar.shortenit.service.MappedLinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
@RequiredArgsConstructor
public class OperationsController {

    private final MappedLinkService mappedLinkService;

    @PostMapping("/hashed")
    public ResponseEntity<String> getAllLinks(@RequestParam() String url) {
        return ResponseEntity.ok(mappedLinkService.shortenOriginalLink(url));
    }

}
