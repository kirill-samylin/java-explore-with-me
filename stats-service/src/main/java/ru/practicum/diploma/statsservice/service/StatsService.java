package ru.practicum.diploma.statsservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.diploma.commondto.dto.HitDto;
import ru.practicum.diploma.commondto.dto.ViewStatsDto;
import ru.practicum.diploma.statsservice.model.Hit;
import ru.practicum.diploma.statsservice.repository.HitRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatsService {

    private final HitRepository hitRepository;

    @Transactional
    public void addHit(HitDto hitDto) {
        Hit hit = new Hit();
        hit.setApp(hitDto.getApp());
        hit.setUri(hitDto.getUri());
        hit.setIp(hitDto.getIp());
        hit.setTimestamp(hitDto.getTimestamp());

        hitRepository.save(hit);
        log.info("Сохранен hit: {}", hit);
    }

    @Transactional(readOnly = true)
    public List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end,
                                       List<String> uris, Boolean unique) {
        if (unique != null && unique) {
            return hitRepository.findUniqueStats(start, end, uris);
        }
        return hitRepository.findStats(start, end, uris);
    }
}