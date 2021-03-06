package com.chopnix.panes;

import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.chopnix.ru.Repo;
import java.awt.BorderLayout;
import java.awt.Color;

public class NewsPane extends JPanel implements ILauncherPane {
	private static final long serialVersionUID = 1L;

	private JEditorPane news;
	private JScrollPane newsPanel;

	public NewsPane() {
		super();
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		this.setBackground(new Color(0, 0, 0, 64));

		news = new JEditorPane();
		news.setEditable(false);
		newsPanel = new JScrollPane(news);
		newsPanel
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		newsPanel
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(newsPanel);

	}

	@Override
	public void onVisible() {
		try {
			news.setPage(Repo.NEWS_FILE);
		} catch (IOException e1) {
		}
	}

	@Override
	public void onVisible(String[] args) {
		news.setBackground(new Color(0, 0, 0, 64));

	}
}