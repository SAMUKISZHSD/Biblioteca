package  com.biblioteca.model.dao;

import java.util.List;

import com.biblioteca.model.entity.AbstractEntity;

public interface IDAO {

  void cadastrar(AbstractEntity entidade);

  void atualizar(AbstractEntity entidade);

  void excluir(int id);

  AbstractEntity buscar(int id);

  List<AbstractEntity> listar();

}
