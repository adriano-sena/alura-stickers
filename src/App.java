import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
   
    public static void main(String[] args) throws Exception {
        
        //Conexão HTTP para buscar os filmes da api 
        String url = "https://api.mocki.io/v2/549a5d8b";
        var extratorImdb = new ExtratorDeConteudoDoIMDB();

        String urlNasa = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
        var extratorNasa = new ExtratorDeConteudoDaNasa();

        var http = new CLienteHttp();
        var json = http.buscaDados(urlNasa);

        //Exibir e manipular os dados
        TerminalPretier pretier = new TerminalPretier();
        var stickerFactory = new StickerFactory();

        List<Conteudo> conteudos = extratorNasa.extraiConteudos(json);

        for (int i = 0; i< 3 ; i++) {
        
         Conteudo conteudo = conteudos.get(i); 

         InputStream inputStream = new URL(conteudo.getUrlImage()).openStream();
         String nomeArquivo = "resources/"+ conteudo.getTitulo().replace(":", "-") + ".png";
         System.out.println(conteudo.getUrlImage());
         stickerFactory.cria(inputStream, nomeArquivo);
         System.out.println("");      

        }
    }
}
