package org.petar.shortenit.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.petar.shortenit.entity.MappedLink;
import org.petar.shortenit.exceptions.UrlNotValidException;
import org.petar.shortenit.repository.MappedLinkRepository;
import org.petar.shortenit.service.MappedLinkService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MappedLinkServiceImpl implements MappedLinkService {

    @Value("${resource-url}")
    private String resourceUrl;

    private final MappedLinkRepository mappedLinkRepository;

    @Override
    public String shortenOriginalLink(String originalLink) throws RuntimeException {
        log.info("Shortening URL link {}", originalLink);

        if (!isUrlValid(originalLink)) {
            log.error("URL parameter is not a authentic URL: {}", originalLink);
            throw new UrlNotValidException();
        }

        String stringCode = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 7);

        MappedLink newMappedLink = MappedLink.builder()
                .originalLink(originalLink)
                .shortenedLink(stringCode)
                .active(true)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now()
                                   .plusHours(24))
                .build();

        mappedLinkRepository.save(newMappedLink);
        return resourceUrl + "/" + stringCode;
    }

    /*Maybe make the redirect host based, not direct RedirectView object*/
    @Override
    @Cacheable(value = "linkCache", key = "#shortenedLink")
    public RedirectView redirectToOriginalLink(String shortenedLink) {
        MappedLink originalLink = mappedLinkRepository.findByShortenedLink(shortenedLink);
        return new RedirectView(originalLink.getOriginalLink());
    }

    private boolean isUrlValid(String url) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }
}
