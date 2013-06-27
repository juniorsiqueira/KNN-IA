import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Controler {

	public List<Passageiro> treinamento;
	public List<Passageiro> teste;
	private int k = 3;
	private String nomeArquivoTreinamento = "treinamento.csv";
	private String nomeArquivoTeste = "teste.csv";

	public Controler() {
		treinamento = lerLista(nomeArquivoTreinamento);
		teste = lerLista(nomeArquivoTeste);
	}

	public void testarSeMorreu(Passageiro p) {

		List<Tupla> maisProximos = new ArrayList<Tupla>(k);

		for (Passageiro classificado : treinamento) {
			Tupla newT = new Tupla(classificado, p);
			maisProximos.add(newT);
			if (maisProximos.size() == k) {
				break;
			}
		}

		for (Passageiro classificado : treinamento) {
			Tupla newT = new Tupla(classificado, p);
			Collections.sort(maisProximos);

			Tupla maisDistante = maisProximos.get(0);

			if (maisDistante.getDistancia() > newT.getDistancia()) {
				maisProximos.remove(maisDistante);
				maisProximos.add(newT);
			}
		}

		int quantidadeMortos = 0, quantidadeVivo = 0;
		for (Tupla t : maisProximos) {
			if (t.getClassificado().isMorreu()) {
				quantidadeMortos++;
			} else {
				quantidadeVivo++;
			}
		}

		p.setMorreu(quantidadeMortos > quantidadeVivo);

	}

	public List<Passageiro> lerLista(String nomeArquivo) {
		List<StringTokenizer> toks = lerArquivo(nomeArquivo);
		List<Passageiro> treinamento = new ArrayList<Passageiro>();

		for (StringTokenizer t : toks) {

			Passageiro p = new Passageiro();
			if (nomeArquivo.equals(this.nomeArquivoTreinamento)) {

				if (t.nextToken().equalsIgnoreCase("0")) {
					p.setMorreu(true);
				} else {
					p.setMorreu(false);
				}
			}

			p.setPclass(Double.valueOf(t.nextToken()));
			p.setNome(t.nextToken() + ", " + t.nextToken());
			p.setSexo(t.nextToken());
			p.setIdade(Double.valueOf(t.nextToken()));
			treinamento.add(p);
		}
		return treinamento;

	}

	private List<StringTokenizer> lerArquivo(String nomeArquivo) {
		String linha = null;
		List<StringTokenizer> tok = new ArrayList<StringTokenizer>();
		try {

			FileReader reader = new FileReader(nomeArquivo);
			BufferedReader leitor = new BufferedReader(reader);

			boolean descartadoOPrimeiro = false;

			while ((linha = leitor.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(linha, ",");

				if (!descartadoOPrimeiro) {
					descartadoOPrimeiro = true;
					continue;
				} else {
					tok.add(st);
				}
			}

			leitor.close();
			reader.close();
			return tok;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void getPassageiros() {
		int contVivos = 0;
		int contMortos = 0;
		for (Passageiro p : teste) {
			testarSeMorreu(p);
			if (p.getMorreu() == true) {
				++contMortos;
				System.out.println("Morreu");
			} else {
				++contVivos;
				System.out.println("Vivo");
			}
		}
		System.out.println("Total de Vivos = " + contVivos);
		System.out.println("Total de Mortos = " + contMortos);
	}
}
