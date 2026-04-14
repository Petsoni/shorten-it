package org.petar.shortenit.controllers;

import lombok.RequiredArgsConstructor;
import org.petar.shortenit.service.MappedLinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class OperationsController {

    private final MappedLinkService mappedLinkService;

    @PostMapping("/hashed")
    public ResponseEntity<String> getAllLinks(@RequestParam() String url) {
        return ResponseEntity.ok(mappedLinkService.shortenOriginalLink(url));
    }

    @GetMapping("/{shortened-link}")
    public RedirectView getOriginalLinkThroughShortenedLink(@PathVariable("shortened-link") String shortLink) {
        return mappedLinkService.redirectToOriginalLink(shortLink);
    }
}
