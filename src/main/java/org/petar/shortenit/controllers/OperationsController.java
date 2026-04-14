package org.petar.shortenit.controllers;

import lombok.RequiredArgsConstructor;
import org.petar.shortenit.entity.dto.ShortenLinkDto;
import org.petar.shortenit.service.MappedLinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class OperationsController {

    private final MappedLinkService mappedLinkService;

    @PostMapping("/shorten")
    public ResponseEntity<String> getAllLinks(@RequestBody() ShortenLinkDto shortenLinkDto) {
        return ResponseEntity.ok(mappedLinkService.shortenOriginalLink(shortenLinkDto.url()));
    }

    @GetMapping("/{shortened-link}")
    public RedirectView getOriginalLinkThroughShortenedLink(@PathVariable("shortened-link") String shortLink) {
        return mappedLinkService.redirectToOriginalLink(shortLink);
    }
}
