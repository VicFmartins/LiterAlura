package br.com.vicfmartins.literalura.client;

import br.com.vicfmartins.literalura.client.dto.GutendexBookDto;
import br.com.vicfmartins.literalura.client.dto.GutendexResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class GutendexClient {

    private final RestClient restClient;

    public GutendexClient(RestClient gutendexRestClient) {
        this.restClient = gutendexRestClient;
    }

    public List<GutendexBookDto> searchBooksByTitle(String title) {
        GutendexResponseDto response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/books/")
                        .queryParam("search", title)
                        .build())
                .retrieve()
                .body(GutendexResponseDto.class);

        if (response == null || response.results() == null) {
            return List.of();
        }

        return response.results();
    }
}
