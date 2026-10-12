package uz.songservice.infrastructure.web.dto;

import java.util.List;

public record SongsDeletionResponse(List<Long> ids) {}