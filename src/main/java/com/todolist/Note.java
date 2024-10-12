package com.todolist;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.Serializable;
import java.util.Objects;

public class Note implements Serializable {
    public enum Priority {
        HIGH("priority-high"), MEDIUM("priority-medium"), LOW("priority-low");
        public final String cssClass;

        Priority(String cssClass) {
            this.cssClass = cssClass;
        }
    }

    private static int nextId = 0;
    protected int id;
    protected String title;
    protected String content;
    protected Priority priority;

    public Note(String title, String content, Priority priority) {
        this.id = nextId++;
        this.title = title;
        this.content = content;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getPlainContent() {
        Document doc = Jsoup.parse(content);
        Elements br = doc.select("br");
        for (Element el : br) {
            el.after("\n");
        }

        Elements other = doc.select("p, div, h1, h2, h3, h4, h5, h6, li");
        for (Element el : other) {
            el.after("\n");
        }

        return doc.text();
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
