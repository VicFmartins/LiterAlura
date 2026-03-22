package br.com.vicfmartins.literalura.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GutendexBookDto(
        Long id,
        String title,
        List<GutendexAuthorDto> authors,
        List<String> languages,
        Integer download_count
) {
}
