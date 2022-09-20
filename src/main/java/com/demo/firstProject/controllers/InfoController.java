package com.demo.firstProject.controllers;

import com.demo.firstProject.models.Post;
import com.demo.firstProject.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class InfoController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/info")
    public String infoMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts",posts);
        return "infoMain";
    }

    @GetMapping("/info/add")
    public String infoAdd(Model model) {
        return "info-add";
    }

    @PostMapping("/info/add")
    public String infoAddPost(@RequestParam String title, @RequestParam String anonce,@RequestParam String full_text, Model model) {
        Post post = new Post(title,anonce,full_text);
        postRepository.save(post);
        return "redirect:/info";
    }

    @GetMapping("/info/{id}")
    public String infoFull(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)){
            return "redirect:/info";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
        return "infoFull";
    }

    @GetMapping("/info/{id}/edit")
    public String infoEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)){
            return "redirect:/info";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
        return "infoEdit";
    }

    @PostMapping("/info/{id}/edit")
    public String infoUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anonce,@RequestParam String full_text, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnonce(anonce);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/info";
    }

    @PostMapping("/info/{id}/remove")
    public String infoDelete(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/info";
    }
}
