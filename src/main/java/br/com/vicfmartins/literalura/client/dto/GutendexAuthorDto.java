package br.com.vicfmartins.literalura.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GutendexAuthorDto(
        String name,
        Integer birth_year,
        Integer death_year
) {
}
