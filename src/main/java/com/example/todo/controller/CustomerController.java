package com.example.todo.controller;


import com.example.todo.entity.Book;
import com.example.todo.entity.Customer;
import com.example.todo.repository.BookRepository;
import com.example.todo.repository.CustomerRepository;
import com.example.todo.request.AddBookRequest;
import com.example.todo.request.AddCustomerRequest;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerRepository customerRepository;
    private BookRepository bookRepository;

    public CustomerController(CustomerRepository customerRepository, BookRepository bookRepository) {
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable Long customerId){
       return  customerRepository.findById(customerId).orElseThrow(() -> new NoSuchElementException());
    }

     @PostMapping
     public Customer addCustomer(@RequestBody AddCustomerRequest customerRequest){
            Customer customer = new Customer();
            customer.setCustomername(customerRequest.getCustomername());
            customer.setPassword(customerRequest.getPassword());
            return customerRepository.save(customer);
        }

     @PostMapping("/{customerId}/books")
     public void addBook(@PathVariable Long customerId, @RequestBody AddBookRequest bookRequest){
             Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NoSuchElementException());
             Book book = new Book();
             book.setContent(bookRequest.getContent());
             customer.getBookList().add(book);
             bookRepository.save(book);
             customerRepository.save(customer);
         }

     @PostMapping("/books/{bookId}")
     public void toggleBookCompleted( @PathVariable Long bookId){
             Book book = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException());
             book.setCompleted(!book.getCompleted());
             bookRepository.save(book);
         }


     @DeleteMapping("{customerId}/books/{bookId}")
     public void deleteBook(@PathVariable Long customerId,@PathVariable Long bookId){
             Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NoSuchElementException());
             Book book = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException());
             customer.getBookList().remove(book);
             bookRepository.delete(book);
         }

     @DeleteMapping("/{customerId}")
     public void deleteCustomer(@PathVariable Long customerId){
             Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NoSuchElementException());
             customerRepository.delete(customer);
         }







}
