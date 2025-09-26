package ru.practicum.diploma.statsclient;


import ru.practicum.diploma.commondto.dto.HitDto;
import ru.practicum.diploma.commondto.dto.ViewStatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsClient {
    void addHit(HitDto hitDto);

    List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end,
                                List<String> uris, Boolean unique);
}