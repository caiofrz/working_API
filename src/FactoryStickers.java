import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.InputStream;

public class FactoryStickers {
    public void criar(InputStream input, String nomeArquivo){
        try {
            //lendo a imagem localmente
            // InputStream input = new File("image/filme.jpg");

            //lendo uma imagem atraves da url
            // URL input = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_2.jpg");


            BufferedImage imagemOriginal = ImageIO.read(input);

            // gerando uma nova imagem alterando o tamanho
            int width = imagemOriginal.getWidth();
            int heigth = imagemOriginal.getHeight();
            int newHeigth = heigth + 200;

            BufferedImage newImage = new BufferedImage(width, newHeigth, BufferedImage.TRANSLUCENT);

            Graphics2D g2d = (Graphics2D) newImage.getGraphics();
            g2d.drawImage(imagemOriginal, 0, 0, null);

            // escrevendo a frase na imagem
            var fonte = new Font(Font.SERIF, Font.BOLD, 80);
            g2d.setColor(Color.GREEN);
            g2d.setFont(fonte);
            g2d.drawString("TOP", width/3, newHeigth-100);

            // salvar a nova imagem no arquivo
            ImageIO.write(newImage, "png", new File(nomeArquivo));

        } catch (Exception e) {
            System.out.println(e);        
        }
    }
}
