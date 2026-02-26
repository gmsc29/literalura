package com.alura.literalura.principal;

import com.alura.literalura.dto.DadosAutor;
import com.alura.literalura.dto.DadosLivro;
import com.alura.literalura.dto.DadosResposta;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Livro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LivroRepository;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    ------------
                    Escolha o número de sua opção:
                    1- buscar livro pelo título
                    2- listar livros registrados
                    3- listar autores registrados
                    4- listar autores vivos em um determinado ano
                    5- listar livros em um determinado idioma
                    0 - sair
                    """;

            System.out.println(menu);

            try {
                opcao = leitura.nextInt();
                leitura.nextLine();

                switch (opcao) {
                    case 1:
                        buscarLivroPorTitulo();
                        break;
                    case 2:
                        listarLivrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivosEmAno();
                        break;
                    case 5:
                        listarLivrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Encerrando a aplicação. Até logo!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                leitura.nextLine();
            }
        }
    }

    private void buscarLivroPorTitulo() {
        System.out.println("Insira o nome do livro que você deseja procurar:");
        var nomeLivro = leitura.nextLine();

        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "+"));
        DadosResposta dados = conversor.obterDados(json, DadosResposta.class);

        if (dados.getResultados() != null && !dados.getResultados().isEmpty()) {
            DadosLivro dadosLivro = dados.getResultados().get(0);
            Livro livro = new Livro(dadosLivro);

            if (dadosLivro.getAutores() != null && !dadosLivro.getAutores().isEmpty()) {
                DadosAutor dadosAutor = dadosLivro.getAutores().get(0);

                Autor autor = autorRepository.findByNome(dadosAutor.getNome());
                if (autor == null) {
                    autor = new Autor(dadosAutor);
                    autorRepository.save(autor);
                }
                livro.setAutor(autor);
            }

            try {
                livroRepository.save(livro);
                System.out.println("Livro salvo com sucesso!");
                System.out.println(livro);
            } catch (Exception e) {
                System.out.println("Atenção: Não foi possível salvar. O livro já pode estar registrado no banco.");
            }

        } else {
            System.out.println("Livro não encontrado na API Gutendex.");
        }
    }

    private void listarLivrosRegistrados() {
        System.out.println("--- Livros Registrados no Banco ---");
        List<Livro> livros = livroRepository.findAll();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado ainda.");
        } else {
            livros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        System.out.println("--- Autores Registrados no Banco ---");
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado ainda.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarAutoresVivosEmAno() {
        System.out.println("Insira o ano para buscar autores vivos:");
        try {
            var ano = leitura.nextInt();
            leitura.nextLine();

            List<Autor> autores = autorRepository.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano);
            List<Autor> autoresVivosAinda = autorRepository.findByAnoNascimentoLessThanEqualAndAnoFalecimentoIsNull(ano);

            autores.addAll(autoresVivosAinda);

            if (autores.isEmpty()) {
                System.out.println("Nenhum autor vivo encontrado no banco para o ano de " + ano + ".");
            } else {
                System.out.println("--- Autores vivos em " + ano + " ---");
                autores.forEach(System.out::println);
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Ano inválido. Digite apenas números.");
            leitura.nextLine();
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("""
                Insira o idioma para realizar a busca:
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """);
        var idioma = leitura.nextLine();

        List<Livro> livros = livroRepository.findByIdioma(idioma);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no banco para o idioma '" + idioma + "'.");
        } else {
            System.out.println("--- Livros no idioma '" + idioma + "' ---");
            livros.forEach(System.out::println);

            long quantidadeDeLivros = livros.stream().count();
            System.out.println("\nQuantidade de livros no idioma '" + idioma + "': " + quantidadeDeLivros);
        }
    }
}