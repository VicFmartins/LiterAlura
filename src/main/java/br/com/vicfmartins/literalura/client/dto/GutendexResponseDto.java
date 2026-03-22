package br.com.vicfmartins.literalura.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GutendexResponseDto(
        Integer count,
        List<GutendexBookDto> results
) {
}
