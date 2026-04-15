package org.petar.shortenit.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.UrlValidator;
import org.petar.shortenit.entity.MappedLink;
import org.petar.shortenit.exceptions.UrlNotValidException;
import org.petar.shortenit.repository.MappedLinkRepository;
import org.petar.shortenit.service.MappedLinkService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MappedLinkServiceImpl implements MappedLinkService {

    @Value("${resource-url}")
    private String resourceUrl;

    private final MappedLinkRepository mappedLinkRepository;

    @Override
    public String shortenOriginalLink(String originalLink) throws RuntimeException {
        if (!isUrlValid(originalLink)) {
            throw new UrlNotValidException("Passed URL is not valid");
        }
        String stringCode = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
        MappedLink newMappedLink = MappedLink.builder()
                .originalLink(originalLink)
                .shortenedLink(stringCode)
                .active(true)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusHours(24))
                .build();
        mappedLinkRepository.save(newMappedLink);
        return resourceUrl + "/" + stringCode;
    }

    @Cacheable(value = "linkCache", key = "#shortenedLink")
    @Override
    public RedirectView redirectToOriginalLink(String shortenedLink) {
        MappedLink originalLink = mappedLinkRepository.findByShortenedLink(shortenedLink);
        return new RedirectView(originalLink.getOriginalLink());
    }

    private boolean isUrlValid(String url) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }
}
