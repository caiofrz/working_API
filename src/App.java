import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws Exception {
        // Properties prop = getProp();
        // final String API_KEY = prop.getProperty("prop.API_KEY");
        // String url = "https://imdb-api.com/en/API/Top250Movies/" + API_KEY;

        //utilize a url alternativa abaixo, caso a API do imdb esteja instável ou indisponível
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        
        // String url = "https://api.nasa.gov/planetary/apod?api_key=" + API_KEY;


        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // ExtratorConteudo extrator = new ExtratorConteudoNasa();
        ExtratorConteudo extrator = new ExtratorConteudoIMDB();
        List<Conteudo> conteudos = extrator.extrairConteudos(json);


        var Factory = new FactoryStickers();

        for (int i = 0; i < conteudos.size(); i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream input = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo ="image/" + conteudo.getTitulo() + ".png";

            Factory.criar(input, nomeArquivo);

            System.out.println(conteudo.getTitulo());
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
