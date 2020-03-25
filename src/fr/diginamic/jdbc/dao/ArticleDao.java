package fr.diginamic.jdbc.dao;

import java.util.List;

import fr.diginamic.jdbc.entities.Article;

public interface ArticleDao {
	
	List<Article> extraire();

	void insert(Article article);

	int update(Article ancienArticle, Article nouvelArticle);

	boolean delete(Article article);

}
