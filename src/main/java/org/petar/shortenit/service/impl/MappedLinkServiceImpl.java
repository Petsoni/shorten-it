package org.petar.shortenit.service.impl;

import lombok.RequiredArgsConstructor;
import org.petar.shortenit.repository.MappedLinkRepository;
import org.petar.shortenit.service.MappedLinkService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MappedLinkServiceImpl implements MappedLinkService {

    private final MappedLinkRepository mappedLinkRepository;
}
