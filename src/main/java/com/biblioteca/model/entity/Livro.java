package com.biblioteca.model.entity;

public class Livro extends AbstractEntity {

  private String titulo;
  private String autor;
  private int numPaginas;
  private boolean emprestado;

  public Livro(String titulo, String autor, int numPaginas) {
    this.titulo = titulo;
    this.autor = autor;
    this.numPaginas = numPaginas;
    this.emprestado = false;
  }

  public Livro(int id, String titulo, String autor, int numPaginas, boolean emprestado) {
    super.setId(id);
    this.titulo = titulo;
    this.autor = autor;
    this.numPaginas = numPaginas;
    this.emprestado = emprestado;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public int getNumPaginas() {
    return numPaginas;
  }

  public void setNumPaginas(int numPaginas) {
    this.numPaginas = numPaginas;
  }

  public boolean isEmprestado() {
    return emprestado;
  }

  public void setEmprestado(boolean emprestado) {
    this.emprestado = emprestado;
  }
}
