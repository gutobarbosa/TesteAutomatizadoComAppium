package gerarArquivo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class gerandoArquivo {

    public static void escreverCSV( String delimit,String texto, String caminhoArquivo)
            throws IOException {
        String nomeArquivo = "CapturaDados.txt";
        File file = new File(caminhoArquivo+nomeArquivo);// Criando arquivo.csv
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);

        if (!file.exists()) { // Verificando se o arquivo já existe.
            file.createNewFile();
        } else {

        }
        bw.write(texto + delimit); // Escrevendo no arquivo o texto capturado + o delimitador utilizado

        bw.flush(); // atualizando o arquivo

        // if file doesnt exists, then create i

        bw.close(); // Fechando arquivo

    }

}


