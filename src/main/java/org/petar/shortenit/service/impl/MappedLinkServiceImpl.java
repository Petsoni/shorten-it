package org.petar.shortenit.service.impl;

import lombok.RequiredArgsConstructor;
import org.petar.shortenit.repository.MappedLinkRepository;
import org.petar.shortenit.service.MappedLinkService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
            return resourceUrl + "/" + hexString.substring(hexString.length() - 7, hexString.length() - 1);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
