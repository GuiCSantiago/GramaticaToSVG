package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
		 String[][] aux = new String[10][2];
		 int angulo, etapas, regras;
		 String axioma;
		
		public String efetuarLeitura(String saida) {
			try {
				 FileReader arq = new FileReader("gramatica.txt");
				 BufferedReader lerArq = new BufferedReader(arq);
				 String linha = lerArq.readLine();
				 int i=0;
				 int j=0;

				 while (linha != null)
				 {
					if (i == 1) {
						etapas = Integer.parseInt(linha.substring(linha.indexOf(':')+1).replace(" ", ""));
						System.out.print(etapas+"\n");
					}
					else if (i == 2) {
						axioma = linha.substring(linha.indexOf(':')+1).replace(" ", "");
						System.out.print(axioma+"\n");
					}
					else if (i == 3) {
						angulo = Integer.parseInt(linha.substring(linha.indexOf(':')+1,linha.length()-1).replace(" ", ""));
						System.out.print(angulo+"\n");
					}
					else if(i>3) {
						aux[j][0]=linha.substring(linha.indexOf(':')+1,linha.indexOf("→")).replace(" ", "");
						aux[j][1]=linha.substring(linha.indexOf("→") + 1).replace(" ", "");
						System.out.print(aux[j][0]+"\n");
						System.out.print(aux[j][1]+"\n");
						regras=j;
						j++;
					}
					i++;

					linha = lerArq.readLine();
				 }
				 arq.close();
				 for (int k = 0; k < etapas; k++) {
					boolean aplicou = true;
					int tam = axioma.length();
					for (int m = 0; m < tam; m++) {
						for (int l = 0 ; l < regras + 1; l++) {
							if(aux[l][0].equals(String.valueOf(axioma.charAt(m)))) {
								saida=saida+aux[l][1];
								aplicou=true;
								break;
							}
							else
								aplicou=false;
						}
						if (!aplicou) {
							saida = saida + axioma.charAt(m);
						}
					}
					axioma = saida;
					saida="";
				}
			}
			catch (IOException e) {
				System.err.printf("Erro na abertura do arquivo: %s.\n",
				e.getMessage());
			}
			return axioma;
		}

		public int getAngulo()
		{
			return angulo;
		}
}
