package com.example.todoApp;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TodoController {
    @Autowired
    List<Todo> todoList;

    //add //post
    @PostMapping("todo")
    public String addTodo(@RequestBody Todo myTodo){
        todoList.add(myTodo);
        return todoList.size() + "Todo is added";
    }

    //read //get
    @GetMapping("todos")
    public List<Todo> getTodoList(){
        return todoList;

    }

    //remove //delete
    @DeleteMapping("del/{id}/id")
    public String deleteTodoList(@PathVariable Integer id){
        for(Todo todo : todoList){
            if(todo.getTodoId().equals(id)){
                todoList.remove(id);
                return "todo:" + "id" + "remove";
            }
        }
        return "Invalid Id";
    }
    //update //put
    @PutMapping("todo.up-date/{id}/status")
    public String updateTodoList(@PathVariable Integer id, @RequestParam boolean flag){
        for (Todo todo : todoList) {
            if(todo.getTodoId().equals(id)){
                todo.setTodoStatus(flag);
                return "todo:" +" "+ id +" "+ "updated to" +" "+ flag;
            }
        }
        return "Invalid Id";
    }
    //accept 1 or more todos
    @PostMapping("todos/collect/all")
    public String getAllTodos(@RequestBody List<Todo> myTodo)
    {
        todoList.addAll(myTodo);
        return todoList.size()+" "+ "todo were added";
    }

    //undone todos
    @GetMapping("undone/todo")
    public List<Todo> unDone(){
        List<Todo> myTodo = new ArrayList<>();
        for(Todo todo : todoList)
        {
            if(!todo.isTodoStatus()){
                myTodo.add(todo);
            }
        }
        return myTodo;
//        return todoList.stream().filter(todo -> !todo.isTodoStatus()).collect(Collectors.toList());
    }
    // delete todos by id (pass a list of id);
    @DeleteMapping("remove/by/id")
    public List<Todo> deleteId(@RequestBody List<Integer> todoId)
    {
        /*Concurrent Modification Exception while modification in same todolist*/
        /*for(Todo todo : todoList){
            for(Integer id : todoId){
                if(todo.getTodoId().equals(id)){
                    todoList.remove(todo);
                }
            }
        }
        return todoList;*/

        /*todo will skipped if we use 2nd approach */
/*
        for(int i = 0; i < todoList.size(); i++){
            Todo ogtodo = todoList.get(i);
            for(int j = 0; j < todoId.size(); j++){
                if(ogtodo.getTodoId().equals(todoId.get(j))){
                    todoList.remove(ogtodo);
                }
            }
        }
        return todoList;
*/
        /*reduce skipping problem*/
        for(Integer id : todoId){
            for(int i = 0; i < todoList.size(); i++){
                if(id.equals(todoList.get(i).getTodoId())){
                    todoList.remove(todoList.get(i));
                    break;
                }
            }
        }
        return todoList;
    }

}
