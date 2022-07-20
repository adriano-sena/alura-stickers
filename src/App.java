import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
   
    public static void main(String[] args) throws Exception {
        
        //Conexão HTTP para buscar os filmes da api 
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);

        //Utilizando o recurso de inferência de tipo, feature mais recente do java 
        var client =  HttpClient.newHttpClient();   
        var request = HttpRequest.newBuilder(endereco)
            .GET()
            .build();

        //requisição realizada de fato e parseando a o body para uma srting            
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        var json = response.body();
        // System.out.println(json);

        //parsear os dados que nos interessam (titulo, poster, classificacao)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(json);
        System.out.println(listaDeFilmes.size());

        //Exibir e manipular os dados

        TerminalPretier pretier = new TerminalPretier();
        var stickerFactory = new StickerFactory();

        for (Map<String,String> filme : listaDeFilmes) {

        
         String urlImagem = filme.get("image");
         String titulo = filme.get("title");
         InputStream inputStream = new URL(urlImagem).openStream();
         String nomeArquivo = "resources/"+ titulo + ".png";

         stickerFactory.cria(inputStream, nomeArquivo);

         System.out.println((pretier.boldText("Titulo : ") + titulo));      
        //  System.out.println((pretier.boldText("Poster : ") + filme.get("image")));      
         System.out.println((pretier.boldText("Nota   : ") + pretier.ratingStars(filme.get("imDbRating"))));    
         System.out.println(pretier.resetFormat());   
         System.out.println("");      

        }
    }
}
