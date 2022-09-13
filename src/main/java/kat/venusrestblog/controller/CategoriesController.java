package kat.venusrestblog.controller;

import kat.venusrestblog.data.Category;
import kat.venusrestblog.data.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/categories", produces = "application/json")
public class CategoriesController {
    @GetMapping("")
    private Category fetchPostsByCategory(@RequestParam String categoryName){
        Category category = new Category(1L,categoryName, null);
        ArrayList<Post> fakePost = new ArrayList<>();
        fakePost.add(new Post(1L,"Bunnies", "fdf", ));
        return category;

    }

}
