package com.example.javagarden.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


public class DeleteService {
        public static void deleteData(int[] ids, CrudRepository repository) {

                if (ids != null) {
                    for (int id : ids) {
                        repository.deleteById(id);
                    }
                }

        }

}
