import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoNasa implements ExtratorConteudo{

    public List<Conteudo> extrairConteudos(String json) {
        var parser = new JsonParser();
        List<Map<String, String>> listaAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        for (Map<String, String> atributo : listaAtributos) {
            String urlImagem = atributo.get("url");
            String titulo = atributo.get("title");

            var conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);

        }

        return conteudos;

    }
}
