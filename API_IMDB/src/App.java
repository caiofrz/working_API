import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import java.io.FileInputStream;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
        Properties prop = getProp();

        final String API_KEY = prop.getProperty("prop.API_KEY");

        String url = "https://imdb-api.com/en/API/Top250Movies/" + API_KEY;

        //url alternativa, caso a API do imdb esteja instável
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        URI endereco = URI.create(url);

        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();

        var client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        var parser = new JsonParser();
        List<Map<String, String>> listaFilmes = parser.parse(body);

        for (Map<String, String> filme : listaFilmes) {
        System.out.println(filme.get("fullTitle"));
        System.out.println("Avaliação: " + filme.get( "imDbRating"));
        System.out.println(filme.get("image"));
        System.out.println();
        }
    }

    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream(
                "./config.properties");
        props.load(file);
        return props;
    }
}
