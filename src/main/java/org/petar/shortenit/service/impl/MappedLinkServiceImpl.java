package org.petar.shortenit.service.impl;

import lombok.RequiredArgsConstructor;
import org.petar.shortenit.entity.MappedLink;
import org.petar.shortenit.repository.MappedLinkRepository;
import org.petar.shortenit.service.MappedLinkService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

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
    public String shortenOriginalLink(String originalLink) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedLink = digest.digest(originalLink.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hashedLink) {
                String hex = Integer.toHexString(0xff & b);
                if (hexString.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }
            String linkHex = hexString.substring(hexString.length() - 7, hexString.length() - 1);
            MappedLink newMappedLink = MappedLink.builder()
                    .originalLink(originalLink)
                    .shortenedLink(linkHex)
                    .active(true)
                    .createdAt(LocalDateTime.now())
                    .expiresAt(LocalDateTime.now().plusHours(24))
                    .build();
            mappedLinkRepository.save(newMappedLink);
            return resourceUrl + "/" + linkHex;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RedirectView redirectToOriginalLink(String shortenedLink) {
        MappedLink originalLink = mappedLinkRepository.findByShortenedLink(shortenedLink);
        return new RedirectView(originalLink.getOriginalLink());
    }
}
