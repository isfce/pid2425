package org.isfce.pid.dao;

import org.isfce.pid.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleDao<T extends Article> extends JpaRepository<T, String> {

}
