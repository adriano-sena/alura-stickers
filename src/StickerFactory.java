import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerFactory {
    
    public void cria(InputStream inputStream, String nomeArquivo) throws IOException{

        //Ler imagem da listagem
        // InputStream inputStream = new FileInputStream(new File("resources/filme.jpg"));
        // InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_2.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //Criar uma nova imagem em memória com transparência e novo tamanho
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;

        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //Copiar a imagem original para nova imagem (em memória)

        /**
         * Utilizamos a api Graphics do java.awt para desenhar uma nova imagem em cima da imagem original
         */
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //configurar fonte 
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 128);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);

        //Escrever uma legenda na nova imagem 
        graphics.drawString("CLÁSSICOS", 500, novaAltura - 80);

        //Escrever a nova imagem em um arquivo (Refatoriar para utilizar outra assinatura do new File)        
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }

    // public static void main(String[] args) {
    //     StickerFactory stickerFactory = new StickerFactory();
    //     try {
    //         stickerFactory.cria();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
}
