package services;

import model.Pilha;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Gerador {

	public void GerarDesenho(String saida, int angulo) throws IOException {

		Pilha pilha = new Pilha();
		FileWriter fw = new FileWriter("grafico.html");
		PrintWriter pw = new PrintWriter(fw);
		double x1, x2, y1, y2;
		double AngNow = 0;
		x1 = x2 = 500;
		y1 = y2 = 3000;

		pw.printf("<html>\r\n"
				+ "<body>\r\n"
				+ "\r\n"
				+ "<svg width=\"5000\" height=\"5000\">\r\n");

		int tam = saida.length();

		for (int m = 0; m < tam; m++) {
			if (Character.isLetter(saida.charAt(m))& saida.charAt(m)!='X') {
				double rad = Math.toRadians(AngNow);

				x2 = x2+(10*Math.cos(rad));
				y2 = y2-(10*Math.sin(rad));
				pw.printf("<line x1=\""+x1+"\" y1=\""+y1+"\" x2=\""+x2+"\" y2=\""+y2+"\" stroke-width=\"2\" stroke=\"red\"/>\r\n");
				x1 = x2;
				y1 = y2;
			}
			else if (saida.charAt(m)=='+') {
				AngNow=AngNow+angulo;

				if (AngNow>360) {
					AngNow=AngNow-360;
				}
				System.out.print(AngNow+"\n");
			}
			else if (saida.charAt(m)=='-') {
				AngNow=AngNow-angulo;

				if (AngNow<0) {
					AngNow=AngNow+360;
				}
				System.out.print(AngNow+"\n");
			}
			else if (saida.charAt(m)=='[') {
				pilha.empilhar(AngNow + "," + x2 + "," + y2);
			}
			else if (saida.charAt(m)==']') {
				String[] separa = pilha.exibeUltimoValor().split(",");
				AngNow = Double.parseDouble(separa[0]);
				x1 = x2 = Double.parseDouble(separa[1]);
				y1 = y2 = Double.parseDouble(separa[2]);
				pilha.desempilhar();
			}
		}

		pw.printf("Sorry, your browser does not support inline SVG.\r\n"
				+ "</svg>\r\n"
				+ "\r\n"
				+ "</body>\r\n"
				+ "</html>");
		fw.close();
	}
}
