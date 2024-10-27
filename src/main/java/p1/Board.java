package p1;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private int no;
    private String name;
    private List<CategoryVertex> categories;
    private List<Post> posts;

    public Board(int no, String name) {
        this.no = no;
        this.name = name;
        this.categories = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryVertex> getCategories() {
        return categories;
    }

    public void addCategory(CategoryVertex category) {
        categories.add(category);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

//    @Override
//    public String toString() {
//        return "Board{no=" + no + ", name='" + name + "', categories=" + categories + ", posts=" + posts + "}";
//    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();

//        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // TODO : 일단 이렇게
            return "{}";
        }
    }
}