package org.petar.shortenit.controllers;

import lombok.RequiredArgsConstructor;
import org.petar.shortenit.entity.MappedLink;
import org.petar.shortenit.repository.MappedLinkRepository;
import org.petar.shortenit.service.MappedLinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/link")
@RequiredArgsConstructor
public class OperationsController {

    private final MappedLinkRepository mappedLinkRepository;
    private final MappedLinkService mappedLinkService;

    @GetMapping("/hashed")
    public ResponseEntity<String> getAllLinks(@RequestParam() String url) {
        return ResponseEntity.ok(mappedLinkService.shortenOriginalLink(url));
    }
}
