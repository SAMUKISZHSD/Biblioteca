package  com.biblioteca.controller;

import java.util.List;

import com.biblioteca.model.dao.LivroDAO;
import com.biblioteca.model.entity.Livro;

public class LivroController {

  private final LivroDAO livroDAO;

  public LivroController(LivroDAO livroDAO) {
    this.livroDAO = livroDAO;
  }

  public String cadastrarLivro(Livro livro) {
    livroDAO.cadastrar(livro);
    return "Livro cadastrado com sucesso!";
  }

  public String atualizarLivro(Livro livro) {
    livroDAO.atualizar(livro);
    return "Livro atualizado com sucesso!";
  }

  public String excluirLivro(int id) {
    livroDAO.excluir(id);
    return "Livro excluído com sucesso!";
  }

  public Livro buscarLivro(int id) {
    return (Livro) livroDAO.buscar(id);
  }

  public List<Livro> listarLivros() {
    return livroDAO.listar().stream().map(entity -> (Livro) entity).toList();
  }
}
