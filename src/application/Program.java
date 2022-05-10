package application;

import services.Gerador;
import services.Reader;

import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        Reader rd = new Reader();
        Gerador gerador = new Gerador();

        try {
            String saida = rd.efetuarLeitura("");
            int angulo = rd.getAngulo();
            gerador.GerarDesenho(saida, angulo);
            System.out.print(saida+"\n");
            System.out.print(angulo+"\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
