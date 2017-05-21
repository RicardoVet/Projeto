package br.com.negocios;

import java.io.FileOutputStream;
import java.io.IOException;

public class UploadPlanilha {

	public void salvar(byte[] dados, String nomeArquivo, String arquivo) throws IOException {
		FileOutputStream fout = new FileOutputStream(arquivo); {
        fout.write(dados);
        fout.flush();
        fout.close();
		}
	}
}
