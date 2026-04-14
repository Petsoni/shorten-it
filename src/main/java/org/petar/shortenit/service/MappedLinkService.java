package org.petar.shortenit.service;

import org.springframework.web.servlet.view.RedirectView;

public interface MappedLinkService {

    String shortenOriginalLink(String originalLink);

    RedirectView redirectToOriginalLink(String shortenedLink);
}
