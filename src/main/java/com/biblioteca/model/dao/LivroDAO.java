package com.biblioteca.model.dao;

import com.biblioteca.config.DBConfig;
import com.biblioteca.model.entity.AbstractEntity;
import com.biblioteca.model.entity.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO implements IDAO {

  private static final String INSERT_QUERY = "INSERT INTO livro (titulo, autor, num_paginas, num_copias, emprestado) VALUES (?, ?, ?, ?, ?)";
  private static final String UPDATE_QUERY = "UPDATE livro SET titulo=?, autor=?, num_paginas=?, num_copias=?, emprestado=? WHERE id=?";
  private static final String DELETE_QUERY = "DELETE FROM livro WHERE id=?";
  private static final String SELECT_QUERY = "SELECT * FROM livro";
  private static final String SELECT_BY_ID_QUERY = "SELECT * FROM livro WHERE id=?";
  private static final String EMPRESTAR_QUERY = "UPDATE livro SET emprestado=true WHERE id=?";
  private static final String DEVOLVER_QUERY = "UPDATE livro SET emprestado=false WHERE id=?";

  public void emprestar(int id) {
    try (Connection conn = DBConfig.getConnection();
         PreparedStatement stmt = conn.prepareStatement(EMPRESTAR_QUERY)) {
      stmt.setInt(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void devolver(int id) {
    try (Connection conn = DBConfig.getConnection();
         PreparedStatement stmt = conn.prepareStatement(DEVOLVER_QUERY)) {
      stmt.setInt(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void cadastrar(AbstractEntity entidade) {
    Livro livro = (Livro) entidade;
    try (Connection conn = DBConfig.getConnection();
         PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
      stmt.setString(1, livro.getTitulo());
      stmt.setString(2, livro.getAutor());
      stmt.setInt(3, livro.getNumPaginas());
      stmt.setInt(4, livro.getNumCopias());
      stmt.setBoolean(5, livro.isEmprestado());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void atualizar(AbstractEntity entidade) {
    Livro livro = (Livro) entidade;
    try (Connection conn = DBConfig.getConnection();
         PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY)) {
      stmt.setString(1, livro.getTitulo());
      stmt.setString(2, livro.getAutor());
      stmt.setInt(3, livro.getNumPaginas());
      stmt.setInt(4, livro.getNumCopias());
      stmt.setBoolean(5, livro.isEmprestado());
      stmt.setInt(6, livro.getId());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void excluir(int id) {
    try (Connection conn = DBConfig.getConnection();
         PreparedStatement stmt = conn.prepareStatement(DELETE_QUERY)) {
      stmt.setInt(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public AbstractEntity buscar(int id) {
    Livro livro = null;
    try (Connection conn = DBConfig.getConnection();
         PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_QUERY)) {
      stmt.setInt(1, id);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          livro = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("num_paginas"), rs.getInt("num_copias"), rs.getBoolean("emprestado"));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return livro;
  }

  @Override
  public List<AbstractEntity> listar() {
    List<AbstractEntity> livros = new ArrayList<>();
    try (Connection conn = DBConfig.getConnection();
         PreparedStatement stmt = conn.prepareStatement(SELECT_QUERY);
         ResultSet rs = stmt.executeQuery()) {
      while (rs.next()) {
        Livro livro = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"),
            rs.getInt("num_paginas"), rs.getInt("num_copias"), rs.getBoolean("emprestado"));
        livros.add(livro);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return livros;
  }
}
